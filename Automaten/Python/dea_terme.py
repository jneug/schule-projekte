# Übergangsfunktion
# Zustand, Buchstabe => Neuer Zustand
def transition(state, char):
    new_state = -1

    if state == 0:
        if char in "123456789":
            new_state = 1
        elif char == "0":
            new_state = 2
    elif state == 1:
        if char in "0123456789":
            new_state = 1
        elif char == ".":
            new_state = 3
        elif char in "+-:*":
            new_state = 5
    elif state == 2:
        if char == ".":
            new_state = 3
        elif char in "+-:*":
            new_state = 5
    elif state == 3:
        if char in "0123456789":
            new_state = 4
    elif state == 4:
        if char in "0123456789":
            new_state = 4
        elif char in "+-:*":
            new_state = 5
    elif state == 5:
        if char in "123456789":
            new_state = 1
        elif char == "0":
            new_state = 2

    return new_state


# Scanner-Funktion für ein Wort
# True, wenn das Wort in der Sprache liegt
def scan_word(word):
    # Startzustand q0
    state = 0

    for char in word:  # jeden Buchstaben durchlaufen
        state = transition(state, char)  # Übergangsfunktion ausführen

    # Word wird akzeptiert, wenn einer der Endzustände erreicht wurde
    return state in [0, 1, 2, 4]


# Programmstart
if __name__ == "__main__":
    word = input("Bitte ein Wort eingeben: ")
    accepted = scan_word(word)
    if accepted:
        print(f"Das Wort '{word}' gehört zur Sprache")
    else:
        print(f"Das Wort '{word}' gehört nicht zur Sprache")
