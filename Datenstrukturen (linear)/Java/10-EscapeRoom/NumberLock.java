/**
 * Ein Schloss, dass mit einem Nummernschlüssel entriegelt werden kann.
 */
public class NumberLock extends Lock {

    protected int correctNumber, lastGuess;

    public NumberLock( int pCorrectNumber ) {
        correctNumber = pCorrectNumber;
        lastGuess = -1;
    }

    public boolean unlock( Item pItem ) {
        if( isOpen() ) {
            return true;
        }
        try {
            int n = Integer.parseInt(pItem.use());
            lastGuess = n;
            if( n == correctNumber ) {
                open = true;
            }
            return isOpen();
        } catch( NumberFormatException ex ) {
            return false;
        }
    }

    public void printHint() {
        if( lastGuess == -1 ) {
            EscapeRoom.println("Du hast bisher nicht versucht das Schloss zu öffnen.");
        }
        try {
            if( lastGuess < correctNumber ) {
                EscapeRoom.println("Die Nummer muss höher sein.");
            } else if( lastGuess > correctNumber ) {
                EscapeRoom.println("Die Nummer muss niedriger sein.");
            } else {
                EscapeRoom.println("Die Nummer scheint zu passen!");
            }
        } catch( NumberFormatException ex ) {
            EscapeRoom.println("Du musst für dieses Schloss einen Nummernschlüssel benutzen.");
        }
    }
}
