from gturtle import *

makeTurtle()   # die Turtle guckt nach oben
right(90)

durchmesser = 5
repeat 5:
    forward(30)
    dot(durchmesser)
    durchmesser = durchmesser + 3

durchmesser = durchmesser - 3    
repeat 4:
    forward(30)
    durchmesser = durchmesser - 3
    dot(durchmesser)
    
forward(30)
hideTurtle()

