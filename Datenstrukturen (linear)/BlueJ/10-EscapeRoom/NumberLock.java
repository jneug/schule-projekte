


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
    
    public String hint() {
        if( lastGuess == -1 ) {
            return "Du hast bisher nicht versucht das Schloss zu öffnen.";
        }
        try {
            if( lastGuess < correctNumber ) {
                return "Die Nummer muss höher sein.";
            } else if( lastGuess > correctNumber ) {
                return "Die Nummer muss niedriger sein.";
            } else {
                return "Die Nummer scheint zu passen!";
            }
        } catch( NumberFormatException ex ) {
            return "Du musst für dieses Schloss ein Item mit einer Nummer benutzen.";
        }
    }
}
