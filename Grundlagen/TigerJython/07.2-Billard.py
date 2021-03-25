from gturtle import *

# Lässt eine Billardkugel (dot) über den Bildschirm wandern
def laufe(geschwindigkeit, durchmesser):
    # Änderung der Kugel in X- und Y-Richtung festlegen
    deltaX = geschwindigkeit
    deltaY = geschwindigkeit/2
    
    # Größe des Bildschirms ermitteln
    # siehe dazu das Bild im Tauschordner
    radius = durchmesser/2
    minX = - getPlaygroundWidth()/2 +radius
    maxX = 0
    minY = 0
    maxY = 0
    
    # Animation starten
    repeat 1000:
        dot(durchmesser)
        delay(20)
        # Kugel abprallen lassen
        # Wenn die Kugel am Rand ist, wird die 
        # Richtung der Änderung umgekehrt.
        if getY() > maxY:
            deltaY *= -1
        # Weitere Ränder abfragen
        # ...
        # Position des Balls anpassen
        setPos (getX() + deltaX, getY() + deltaY)
        clear()

makeTurtle()
hideTurtle()
laufe(10, 40)
