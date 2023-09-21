# inspired by 
# https://www.youtube.com/watch?v=kMBj2fp52tA
from gturtle import *

d = 4
n = 6

def triangle(i, xy="X"):
    for c in xy:
        if c == "X":
            if i > 0:
                triangle(i-1, "Y+X+Y")
        elif c == "Y":
            if i > 0:
                triangle(i-1, "X-Y-X")
        elif c == "+":
            rt(60)
            fd(d)
        elif c == "-":
            lt(60)
            fd(d)

makeTurtle()
hideTurtle()

rt(90)
triangle(n)
    
showTurtle()
