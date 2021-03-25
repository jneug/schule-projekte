# Scanner-Funktion für ein Wort
# True, wenn das Wort in der Sprache liegt
def scan_word(state, word, stack):
    # obersten Kellerbuchstaben "popen" (wenn vorhanden)
    stack_char = stack.pop(0) if stack else ""

    # Rekursionsabbruch wenn Wort leer (Länge = 0)
    if len(word) == 0:
        # Akzeptiert, wenn Automat in Endzustand
        # (und ggf. bestimmter Kellerzustand), z.B.:
        return state == 1 and stack_char == "#"

    char = word[0]  # Ersten Buchstaben ist die Eingabe
    word = word[1:]  # Ersten Buchstaben vom Restwort abtrennen

    # Übergangsfunktion
    if state == 0:
        if stack_char == "#":
            if char == "a":
                return scan_word(0, word, ["A", "#"] + stack)
        elif stack_char == "A":
            if char == "a":
                return scan_word(0, word, ["A", "A"] + stack)
            elif char == "b":
                return scan_word(1, word, stack)
    elif state == 1:
        if stack_char == "A":
            if char == "b":
                return scan_word(1, word, stack)

    # Kein Übergang möglich, Wort nicht akzeptiert
    return False


# Programmstart
if __name__ == "__main__":
    word = input("Bitte ein Wort eingeben: ")
    accepted = scan_word(0, word, ["#"])
    if accepted:
        print(f"Das Wort '{word}' gehört zur Sprache")
    else:
        print(f"Das Wort '{word}' gehört nicht zur Sprache")
