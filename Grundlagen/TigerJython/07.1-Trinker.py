from gturtle import *
from random import *

makeTurtle("sprites/alien.gif")
repeat 100:
    forward(20)
    zufall = randint(1, 100)
    if zufall < 25:
        left(45)
    elif zufall >= 25 and zufall < 50:
        # 'elif' steht kurz für 'else-if'
        right(135)
    elif zufall >= 50 and zufall < 90:
        back(50)
    else:
        playTone(220, 800)