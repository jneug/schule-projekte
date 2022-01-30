# -*- coding: utf-8 -*-

##!! Behebt das Import-Problem im Mu-Editor
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
def printNextMail():
    """
    Ruft die erste verfügbare Mail vom Server ab und schreibt sie
    auf die Kommandozeile.
    """

    ## Verbindung herstellen
    con = Connection(POP3_SERVER, POP3_PORT)
    ## Falls es einen "UnicodeDecodeError" gibt, die Zeile
    ## oben durch die Folgende ersetzen.
    #con = Connection(POP3_SERVER, POP3_PORT, 'latin1')

    ## Erste Begrüßung vom Server empfangen
    msg = con.receive()
    ## Prüfen der Begrüßung auf Fehler-Markierung
    if msg.startswith('-ERR'):
        print('Fehler beim Verbindungsaufbau!')     ## Fehler ausgeben,
        con.close()                                 ## Verbindung schließen und
        return                                      ## Methode beenden

    ## Anmeldung am Server
    con.send('USER {}'.format(POP3_USER))
    msg = con.receive() ## Auf Antwort warten, "+OK" erwartet
    if msg.startswith('-ERR'):
        print('Benutzername nicht akzeptiert!')
        con.close()
        return

    ## Passwort senden
    #### TODO: Ab hier weiter implementieren
    #### Tipp: Arbeitet hier viel mit Copy&Paste!


    ## Statistik abfragen


    ## Statistik verarbeiten
    count = getMessageCount(msg)
    if count == 0:
        print('Keine E-Mails auf dem Server vorhanden.')
        con.close()
        return


    ## Erste Mail abrufen



    ## Die Nahricht besteht aus Textzeilen, die mit einer Zeile abgeschlossen
    ## werden, die nur aus einem Punkt besteht. Benutzt eine Schleife, um die
    ## Mail auszugeben:
    ## while msg != '.':


    ## Verbindung beenden
    con.send('QUIT')
    con.close()

def deleteNextEmail():
    """
    Löscht genau eine (die erste) Mail vom Server.
    """
    ## pass markiert eine leere Funktion.
    pass

# Aufruf der Funktion, wenn das Skript direkt gestartet wird
if __name__ == '__main__':
    printNextMail()
