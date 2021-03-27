

def hanoi(n, von="A", hilfe="B", nach="C"):
    if n == 0:
        return
    hanoi(n-1, von, nach, hilfe)
    print(f"Scheibe {n} von {von} nach {nach}")
    hanoi(n-1, hilfe, von, nach)


n = 4
hanoi(n)
