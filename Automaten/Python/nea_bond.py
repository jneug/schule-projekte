# Scanner-Funktion für ein Wort
# True, wenn das Wort in der Sprache liegt
def scan_word(state, word):
    # Rekursionsabbruch wenn Wort leer (Länge = 0)
    if len(word) == 0:
        # Akzeptiert, wenn Automat in Endzustand
        return state == 3

    char = word[0]  # Ersten Buchstaben ist die Eingabe
    word = word[1:]  # Ersten Buchstaben vom Restwort abtrennen

    # Übergangsfunktion
    if state == 0:
        if char in "123456789":
            return scan_word(0, word)
        elif char == "0":
            return scan_word(0, word) or scan_word(1, word)
    elif state == 1:
        if char in "123456789":
            return scan_word(0, word)
        elif char == "0":
            return scan_word(0, word) or scan_word(2, word)
    elif state == 2:
        if char in "01234589":
            return scan_word(0, word)
        elif char in "67":
            return scan_word(0, word) or scan_word(3, word)
    elif state == 3:
        if char in "0123456789":
            return scan_word(3, word)

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
