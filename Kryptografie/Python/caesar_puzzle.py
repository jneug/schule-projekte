klartext = input("Klartextwort (ohne Leerzeichen): ")
klartext = klartext.upper()
schluessel = input("Schlüsselbuchstabe: ")
schluessel = schluessel.upper()
schluessel = ord(schluessel) - ord("A")

geheimtext = ""

for zeichen in klartext:
    unicode = ord(zeichen)
    verschoben = unicode + schluessel
    if verschoben > ord("Z"):
        verschoben = verschoben - 26
    neuesZeichen = chr(verschoben)
    geheimtext = geheimtext + verschoben

print(klartext + " -> " + geheimtext)
