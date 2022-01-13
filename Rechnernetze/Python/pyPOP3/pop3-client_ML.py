# -*- coding: utf-8 -*-

##!! Behebt das Import-Problem in Mu-Editor
import os
os.chdir(os.path.dirname(__file__))

## Importieren der Connection-Klasse
from connection import Connection



## Konfiguration der Verbindung
POP3_SERVER = "pop3.neugebauer.cc"
POP3_PORT = 110
POP3_USER = "informatik@neugebauer.cc"
POP3_PASS = "ifq2_user1"

#POP3_SERVER = '127.0.0.1'
#POP3_PORT   = 110
#POP3_USER   = 'user1'
#POP3_PASS   = 'user1'



## Hilfsmethode
def getMessageCount(pStatMessage):
    """
    Hilfsfunktion:
    Extrahiert die Anzahl an Mails auf dem Server aus der STAT Antwort
    und gibt sie als int zurück.
    """
    firstSpace, secondSpace  = pStatMessage.find(' '), pStatMessage.rfind(' ')
    return int(pStatMessage[firstSpace+1:secondSpace]);


## TODO: Ab hier implementieren
def printNextMail(i):
    con = Connection(POP3_SERVER, POP3_PORT, "latin1")

    ## Erste Begrüßung vom Server empfangen
    msg = con.receive()
    print(msg)

    ## Benutzernamen senden
    con.send("USER {}".format(POP3_USER))
    msg = con.receive()
    print(msg)
    if msg.startswith("-ERR"):
        print("Benutzername nicht akzeptiert!")
        con.close()
        return

    ## Passwort senden
    con.send("PASS {}".format(POP3_PASS))
    msg = con.receive()
    print(msg)
    if msg.startswith("-ERR"):
        print("Falsches Passwort!")
        con.close()
        return

    ## Anzahl Mails abfragen
    con.send("STAT")
    msg = con.receive()
    print(msg)
    if msg.startswith("-ERR"):
        print("Fehler bei der STAT Anfrage.")
        con.close()
        return

    ## Anzahl Mails aus Antwort auslesen
    count = getMessageCount(msg)
    print(f'{count} Mails auf dem Server')
    if count < i:
        print("Nicht genug E-Mails auf dem Server vorhanden.")
        con.close()
        return

    ## Erste Mail abrufen
    con.send("RETR {}".format(i))
    mes = con.receive()
    if msg.startswith("-ERR"):
        print("Fehler beim Empfangen der Mail.")
        con.close()
        return

    ## Mail ausgeben
    while mes != ".":
        if mes.startswith("."):
            mes = mes[1:]
        print(mes)
        mes = con.receive()

    con.send("QUIT")
    con.close()


def deleteNextEmail():
    con = Connection(POP3_SERVER, POP3_PORT, "latin1")

    ## Erste Begrüßung vom Server empfangen
    msg = con.receive()

    ## Löschung markieren
    con.send("DELE 1")
    con.receive()
    # Antwort verwerfen
    ## Abmelden und Löschung ausführen
    con.send("QUIT")
    con.close()


# Aufruf der Funktion, wenn das Skript direkt gestartet wird
if __name__ == "__main__":
    printNextMail(4)
