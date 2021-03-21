

def scan_word( state, word, stack ):
    stack_char = stack.pop(0) if stack else ""

    if not word:
        return state == 1 and stack_char == "#"

    char = word[0]
    word = word[1:]

    if state == 0:
        if stack_char == '#':
            if char == 'a':
                return scan_word(0, word, ['A','#'] + stack)
        elif stack_char == 'A':
            if char == 'a':
                return scan_word(0, word, ['A','A'] + stack)
            elif char == 'b':
                return scan_word(1, word, stack)
    elif state == 1:
        if stack_char == 'A':
            if char == 'b':
                return scan_word(1, word, stack)

    # Endzustand erreicht und ggf. test, ob Keller leer,
    # falls von der Sprache gefordert.
    return False


if __name__ == "__main__":
    word = input("Bitte ein Wort eingeben: ")
    accepted = scan_word(0, word, ["#"])
    if accepted:
        print("Wort gehört zur Sprache")
    else:
        print("Wort gehört nicht zur Sprache")
