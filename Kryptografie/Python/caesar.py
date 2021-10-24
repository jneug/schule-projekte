

def ceasear_encode( msg, key ):
    code = ""
    key = ord(key.upper())-ord("A")
    for c in msg.upper():
        if c in "ABCDEFGHIJKLMNOPQRSTUVWXYZ":
            new_ord = ord(c)+key
            if new_ord > ord("Z"):
                new_ord -= 26
            code += chr(new_ord)
        else:
            code += c
    return code

def ceasear_decode( code, key ):
    msg = ""
    key = ord(key.upper())-ord("A")
    for c in code.upper():
        if c in "ABCDEFGHIJKLMNOPQRSTUVWXYZ":
            new_ord = ord(c)-key
            if new_ord < ord("A"):
                new_ord += 26
            msg += chr(new_ord)
        else:
            msg += c
    return msg


#print(ceasear_decode(ceasear_encode("HalloWelt", "F"), "F"))
msg = """
MRN BRLQNAQNRC NRWNA PNQNRVBLQAROC
MJAO WDA EXW MNA PNQNRVQJUCDWP
MNB BLQUDNBBNUB JKQJNWPNW, WRLQC
SNMXLQ EXW MNA PNQNRVQJUCDWP MNA
ENABLQUDNBBNUDWPBVNCQXMN.
"""
for key in "ABCDEFGHIJKLMNOPQRSTUVWXYZ":
#for key in "J":
    print(key + ": " + ceasear_decode(msg, key)[:14])
