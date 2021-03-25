def transition(state, char, stack_char):
    new_state = -1
    new_stack_chars = ""

    if state == 0:
        new_state = 1
        new_stack_chars = "S#"
    elif state == 1:
        if stack_char in "0123456789+-*:().":
            new_state = 1
            new_stack_chars = ""
        elif stack_char == "S":
            if char in "123456789":
                new_state = 1
                new_stack_chars = "A"
            elif char == "0":
                new_state = 1
                new_stack_chars = "B"
            elif char == "(":
                new_state = 1
                new_stack_chars = "E)R"
        elif stack_char == "A":
            if char in "0123456789":
                new_state = 1
                new_stack_chars = "A"
            elif char == ".":
                new_state = 1
                new_stack_chars = "C"
            elif char in "+-:*":
                new_state = 1
                new_stack_chars = "E"
        elif stack_char == "B":
            if char == ".":
                new_state = 1
                new_stack_chars = "C"
            elif char in "+-:*":
                new_state = 1
                new_stack_chars = "E"
        elif stack_char == "C":
            if char in "0123456789":
                new_state = 1
                new_stack_chars = "D"
        elif stack_char == "D":
            if char in "0123456789":
                new_state = 1
                new_stack_chars = "D"
            elif char in "+-:*":
                new_state = 1
                new_stack_chars = "E"
        elif stack_char == "E":
            if char in "123456789":
                new_state = 1
                new_stack_chars = "A"
            elif char == "0":
                new_state = 1
                new_stack_chars = "B"
            elif char == "(":
                new_state = 1
                new_stack_chars = "E)R"
        elif stack_char == "R":
            if char in "+-:*":
                new_state = 1
                new_stack_chars = "E"
            elif char == "":
                new_state = 2
        elif stack_char == "#":
            new_state = 2

    return new_state, new_stack_chars


def scan_word(word):
    state = 0
    stack = ["#"]

    for char in word:
        stack_char = stack.pop(0)

        state, stack_chars = transition(state, char, stack_char)

        for sc in reversed(stack_chars):
            stack.insert(0, sc)

    if len(stack) > 0:
        transition(state, "", stack[0])

    return word == "" and state == 2


if __name__ == "__main__":
    word = input("Bitte ein Wort eingeben: ")
    accepted = scan_word(word)
    if accepted:
        print("Wort gehört zur Sprache")
    else:
        print("Wort gehört nicht zur Sprache")
