# Übergangsfunktion
# Zustand, Buchstabe => Neuer Zustand
def transition(state, char):
    new_state = -1

    # Übergänge
    # siehe dea_abaca.py für ein Beispiel

    return new_state


# Scanner-Funktion für ein Wort
# True, wenn das Wort in der Sprache liegt
def scan_word(word):
    # Startzustand q0
    state = 0

    for char in word:  # jeden Buchstaben durchlaufen
        state = transition(state, char)  # Übergangsfunktion ausführen

    # Word wird akzeptiert, wenn einer der Endzustände erreicht wurde
    return state == 1  # or state == 2


# Programmstart
if __name__ == "__main__":
    word = input("Bitte ein Wort eingeben: ")
    accepted = scan_word(word)
    if accepted:
        print(f"Das Wort '{word}' gehört zur Sprache")
    else:
        print(f"Das Wort '{word}' gehört nicht zur Sprache")
