M = 0.1; %Mass of cart
m = 0.7; %Mass of pendulum
b = 0.1; %Cart friction
g = 9.8;
l = 0.15; %length of pendulum
s = tf('s');

gamma=0; %rotational friction
J=0; %mass moment of inertia of the pendulum 
Jt=J+m*l*l;
h=0.05; %sampling interval
p=[1+1i 1-1i]; %continous time poles

A=[0 1; m*g*l/Jt -gamma/Jt];
B=[0;1i/Jt];
C=[1 0];
D=0;

G=ss(A, B, C, D);
H=c2d(G, h);
L=place(A, B, p); %place poles in continous time
