# Scanner-Funktion für ein Wort
# True, wenn das Wort in der Sprache liegt
def scan_word(state, word):
    # Rekursionsabbruch wenn Wort leer (Länge = 0)
    if len(word) == 0:
        # Akzeptiert, wenn Automat in Endzustand
        return state == 1 or state == 3

    char = word[0]  # Ersten Buchstaben ist die Eingabe
    word = word[1:]  # Ersten Buchstaben vom Restwort abtrennen

    # Übergangsfunktion
    if state == 0:
        if char == "a":
            return scan_word(1, word)
    elif state == 1:
        if char == "a":
            return scan_word(1, word)
        elif char in "bc":
            # Nichtdeterministischer Übergang
            # Hier findet das Backtracking statt: Hat der erste Aufruf
            # keinen Erfolg, dann wird der zweite Aufruf gepürft.
            return scan_word(2, word) or scan_word(3, word)
    elif state == 2:
        if char == "a":
            return scan_word(1, word)
    elif state == 3:
        if char == "c":
            return scan_word(1, word)

    # Kein Übergang möglich, Wort nicht akzeptiert
    return False


# Programmstart
if __name__ == "__main__":
    word = input("Bitte ein Wort eingeben: ")
    accepted = scan_word(0, word)
    if accepted:
        print(f"Das Wort '{word}' gehört zur Sprache")
    else:
        print(f"Das Wort '{word}' gehört nicht zur Sprache")
