# inspired by 
# https://www.youtube.com/watch?v=kMBj2fp52tA
from gturtle import *
from math import degrees

dist = 10
base = 6
ang = 360/base

pi = (int(i) for i in "31415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679")
n = 10000


# copied from https://stackoverflow.com/a/9005163
def make_pi(n):
    q, r, t, k, m, x = 1, 0, 1, 1, 3, 3
    for j in range(n):
        if 4 * q + r - t < m * t:
            yield m
            q, r, t, k, m, x = 10*q, 10*(r-m*t), t, k, (10*(3*q+r))//t - 10*m, x
        else:
            q, r, t, k, m, x = q*k, (2*q+r)*x, t*x, k+1, (q*(7*k+2)+r*x)//(t*x), x+2

makeTurtle()
hideTurtle()

rt(90)
for i in make_pi(n):
    fd(dist)
    rt(i*ang)
    
showTurtle()