
from random import randint

FELD_BREITE = 15
FELD_HOEHE = 10
ANZAHL_MINEN = randint(
    int(FELD_BREITE * FELD_HOEHE * 0.1), int(FELD_BREITE * FELD_HOEHE * 0.2)
)

WIDTH = FELD_BREITE * 20
HEIGHT = FELD_HOEHE * 20


feld = []


def minen_verteilen(anzahl):
    for i in range(FELD_BREITE):
        feld.append([])
        for j in range(FELD_HOEHE):
            if anzahl > 0 and randint(0, 10) < 3:
                feld[i].append("X")
                anzahl -= 1
            else:
                feld[i].append(0)


def anzahl_anpassen(i, j):
    for x in range(3):
        for y in range(3):
            new_i = i - 1 + x
            new_j = j - 1 + y
            if new_i >= 0 and new_i < FELD_BREITE and new_j >= 0 and new_j < FELD_HOEHE:
                if feld[new_i][new_j] != "X":
                    feld[new_i][new_j] += 1


def minen_zaehlen():
    for i in range(FELD_BREITE):
        for j in range(FELD_HOEHE):
            cell = feld[i][j]
            if cell == "X":
                anzahl_anpassen(i, j)


sprites = []


def feld_aufbauen():
    for i in range(FELD_BREITE):
        for j in range(FELD_HOEHE):
            inhalt = feld[i][j]
            if inhalt == "X":
                bomb_sprite = Actor("bomb")
                bomb_sprite.center = (i * 20 + 10, j * 20 + 10)
                sprites.append(bomb_sprite)
            feld_sprite = Actor("feld")
            feld_sprite.topleft = (i * 20, j * 20)
            sprites.append(feld_sprite)


minen_verteilen(ANZAHL_MINEN)
minen_zaehlen()
feld_aufbauen()


def draw():
    screen.clear()
    for i in range(FELD_BREITE):
        for j in range(FELD_HOEHE):
            inhalt = feld[i][j]
            screen.draw.textbox(str(inhalt), Rect((i*20,j*20), (20,20)))
    for sprite in sprites:
        sprite.draw()

def on_mouse_down(pos, button):
    if button == mouse.LEFT:
        for sprite in sprites:
            if sprite.collidepoint(pos):
                sprites.remove(sprite)
        i, j = int(pos[0] / 20), int(pos[1] / 20)
        if feld[i][j] == 'X':
            print("Bombe!")
        else:
            print(feld[i][j])