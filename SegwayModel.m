clear;
close all;

%M = 0.1; %Mass of cart
m = 0.61; %Mass of pendulum
%b = 0.1; %Cart friction
g = 9.8;
l = 0.27; %length of pendulum
w = 0.1;

gamma=0; %rotational friction
J=m*(l^2+w^2)/12+(0.13)^2*m; %mass moment of inertia of the pendulum 
h=0.05; %sampling interval
p=[-5+1i -5-1i]; %continous time poles
pd=exp(p*h);

A=[0 1; m*g*l/J -gamma/J]; %I think A(2, 1) is too big, probably because J is too small. mgl=0.5, but then we divide by Jt, which is small, and suddenly it's 37
B=[0;l/J]; %B(2) would also be more resonable if Jt was bigger
C=[1 0];
D=0;

G=ss(A, B, C, D);
H=c2d(G, h);
H.a;
H.b;
L=place(H.a, H.b, pd) %place poles in continous time
lr=inv(C/(eye(2)-H.a+H.b*L)*H.b)


%With integral action
p=[0.8+0.1*1i, 0.8-0.1*1i, 0.85]; %We might be placiing the poles wrong, changing to 1i instead of 2 more than halved the K:s

phie=[H.a H.b;zeros(1, 2) 1];
gammae=[H.b;0];
Ce=[C 0];

K=place(phie', Ce', p)' %Do we want to place poles in discrete or continous time?

Ae=[A B;zeros(1, 2) 0];
Be=[B;0];
pc=[-1+1i -1-1i -0.5];
pd=exp(pc*h);

He=c2d(ss(Ae, Be, Ce, D), h);
K2=place(He.a', He.c', p)'; % If the system is expanded in continous time and then sampled the results are slightly different
