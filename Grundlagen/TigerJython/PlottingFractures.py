# inspired by 
# https://www.youtube.com/watch?v=kMBj2fp52tA
from gturtle import *
from math import degrees

dist = 20
base = 10
ang = 360/base

num = 1
den = 119

n = 10000


# https://stackoverflow.com/a/33699831
def infinite_divide(numerator, denominator, n):
    if numerator > denominator:
        raise ValueError('This function only returns digits after the decimal')
    while numerator != 0 and n > 0:
        numerator *= 10
        digit, numerator = divmod(numerator, denominator)
        yield digit
        n -= 1

makeTurtle()
hideTurtle()


r,g,b = 0,0,255

clear("black")
setPenWidth(2)

rt(90)
for i in infinite_divide(num, den, n):
    setPenColor(r,g,b)
    fd(dist)
    rt(i*ang)
    r,g,b = g,b,int(i*25.5)
    
showTurtle()