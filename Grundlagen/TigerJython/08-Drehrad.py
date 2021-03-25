from gturtle import *

def dreieck():
    repeat 3:
        forward(100)
        right(120)
        
makeTurtle()
runde = 1
while runde <= 6:   # neu!
    if runde == 1 or runde == 3 or runde == 5:
        setPenColor("red")
    else:
        setPenColor("green")
    fillToPoint()  # neu !
    dreieck()
    right(60)
    runde = runde + 1
    
