M = 0.1; %Mass of cart
m = 0.4; %Mass of pendulum
%b = 0.1; %Cart friction
g = 9.8;
l = 0.15; %length of pendulum
w = 0.1;

gamma=0; %rotational friction
J=m*(l^2+w^2)/12+(l/2)^2*m; %mass moment of inertia of the pendulum 
Jt=J+m*l^2;
h=0.05; %sampling interval
p=[-1+1i -1-1i]; %continous time poles
pd=exp(p*h);

A=[0 1; m*g*l/Jt -gamma/Jt];
B=[0;l/Jt];
C=[1 0];
D=0;

G=ss(A, B, C, D);
H=c2d(G, h);
L=place(H.a, H.b, pd); %place poles in continous time
lr=inv(C/(eye(2)-H.a+H.b*L)*H.b);


%With integral action
p=[0.6+2*1i, 0.6-2*1i, 0.55];

Ae=[H.a H.b;zeros(1, 2) 1];
Be=[H.b;0];
Ce=[C 0];

K=place(Ae', Ce', p);