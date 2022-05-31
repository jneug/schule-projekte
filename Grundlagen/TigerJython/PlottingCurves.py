# inspired by 
# https://www.youtube.com/watch?v=kMBj2fp52tA
from gturtle import *

d = 1
t = 60.0654231173  # 1.0483394 in rad
n = 100000


makeTurtle()
hideTurtle()

rt(90)
for i in range(n):
    fd(d)
    rt(i*t)
        
showTurtle()