M = 0.1; %Mass of cart
m = 0.4; %Mass of pendulum
%b = 0.1; %Cart friction
g = 9.8;
l = 0.15; %length of pendulum
w = 0.1;

gamma=0; %rotational friction
J=m*(l^2+w^2)/12+(l/2)^2*m; %mass moment of inertia of the pendulum 
h=0.05; %sampling interval
p=[-1+1i -1-1i]; %continous time poles
pd=exp(p*h);

A=[0 1; m*g*l/t -gamma/J]; %I think A(2, 1) is too big, probably because Jt is too small. mgl=0.5, but then we divide by Jt, which is small, and suddenly it's 37J
B=[0;l/J]; %B(2) would also be more resonable if Jt was bigger
C=[1 0];
D=0;

G=ss(A, B, C, D);
H=c2d(G, h);
L=place(H.a, H.b, pd); %place poles in continous time
lr=inv(C/(eye(2)-H.a+H.b*L)*H.b);


%With integral action
p=[0.6+1i, 0.6-1i, 0.55]; %We might be placiing the poles wrong, changing to 1i instead of 2 more than halved the K:s

Ae=[H.a H.b;zeros(1, 2) 1];
Be=[H.b;0];
Ce=[C 0];

K=place(Ae', Ce', p); %Do we want to place poles in discrete or continous time?