# Übergangsfunktion
# Zustand, Buchstabe, Kellerbuchstabe => Neuer Zustand, Neue Kellerbuchstaben
def transition( state, char, stack_char ):
    new_state = -1
    new_stack_chars = ''

    # Übergänge

    return new_state, new_stack_chars

# Scanner-Funktion für ein Wort
# True, wenn das Wort in der Sprache liegt
def scan_word(word):
    state = 0 # Startzustand q0
    stack = ['#'] # Keller mit Startsymbol

    for char in word: # Jeden Buchstaben durchlaufen
        stack_char = stack.pop(0) # Oberstes Symbol vom Keller nehmen

        # Übergangsfunktion ausführen
        state, stack_chars = transition(state, char, stack_char)

        # Neue Symbole auf den Keller pushen
        # reversed = in umgekehrter Reihenfolge
        for sc in reversed(stack_chars):
            stack.insert(0, sc)

    return state == 2

if __name__ == "__main__":
    word = input("Bitte ein Wort eingeben: ")
    accepted = scan_word(word)
    if accepted:
        print("Wort gehört zur Sprache")
    else:
        print("Wort gehört nicht zur Sprache")
