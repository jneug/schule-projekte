from gturtle import *

def zeichneBaum (durchmesser):
    forward(durchmesser)
    dot(durchmesser/2)
    forward(-durchmesser)
      
makeTurtle()
durchmesser = 30
abstand = 30
repeat 10:
    zeichneBaum(durchmesser)
    penUp()
    right(90)
    forward(abstand)
    left(90)
    forward(abstand)
    penDown()
    abstand *= 0.9
    durchmesser *= 0.9
    
hideTurtle()
