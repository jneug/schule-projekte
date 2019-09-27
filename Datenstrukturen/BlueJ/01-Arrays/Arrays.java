
import java.util.Random;

/**
 * Verschiedene Übungen zu Arrays.
 */
public class Arrays
{
    
    // Interner Zufallsgenerator
    private Random rand;
    
    public Arrays() {
        // Neuen Zufallsgenerator erstellen
        rand = new Random();
    }
    
    /**
     * Erstellt ein Integer-Array mit <var>pNumberOfElements</var> 
     * zufällig gewählten Zahl-Elementen im Bereich <var>pMin</var>
     * bis <var>pMax</var> (exklusive).
     */
    public int[] generateIntArray( int pNumberOfElements, int pMin, int pMax ) {
        return null;
    }

    /**
     * Erzeugt eine Zufallszahl zwischen <var>pMin</var>
     * und <var>pMax</var> (exklusive).
     */
    private int getRandomInt( int pMin, int pMax ) {
        return rand.nextInt(pMax-pMin)+pMin;
    }
    
    /**
     * Erstellt ein String-Array mit <var>pNumberOfElements</var> 
     * Text-Elementen der Form <code>String 1</code>, <code>String 2</code>.
     */
    public String[] generateStringArray( int pNumberOfElements ) {
        return null;
    }
    
    /**
     * Erstellt ein Boolean-Array mit <var>pNumberOfElements</var> 
     * zufällig gewählten Wahrheitswerten.
     */
    public boolean[] generateBooleanArray( int pNumberOfElements ) {
        boolean[] result = new boolean[pNumberOfElements];
        for( ; pNumberOfElements > 0; pNumberOfElements-- ) {
            result[pNumberOfElements-1] = rand.nextBoolean();
        }
        return result;
    }
    
    /**
     * Sucht in einem Integer-Array das kleinste Element.
     */
    public int min( int[] pArray ) {
        int min = pArray[0];
        for( int i = 1; i < pArray.length; i++ ) {
            if( pArray[i] < min ) {
                min = pArray[i];
            }
        }
        return min;
    }
    
    /**
     * Sucht in einem Integer-Array das größte Element.
     */
    public int max( int[] pArray ) {
        return 0;
    }
    
    /**
     * Berechnet den Mittelwert (average) eines
     * Integer-Arrays.
     */
    public int avg( int[] pArray ) {
        return 0;
    }
    
    /**
     * Prüft, ob <b>alle</b> Elemente in einem Array von
     * Wahrheitswerten <code>true</code> sind. Sonst wird
     * <code>false</code> zurück gegeben.
     */
    public boolean and( boolean[] pArray ) {
        return true;
    }
    
    /**
     * Prüft, ob <b>mindestens ein</b> Element in einem Array von
     * Wahrheitswerten <code>true</code> ist. Sonst wird
     * <code>false</code> zurück gegeben.
     */
    public boolean or( boolean[] pArray ) {
        return true;
    }
    
    
    /**
     * Prüft, ob <b>genau ein</b> Element in einem Array von
     * Wahrheitswerten <code>true</code> ist. Sonst wird
     * <code>false</code> zurück gegeben. (Bei keinmal <code>true</code>
     * oder mehr als einmal <code>true</code>.)
     */
    public boolean xor( boolean[] pArray ) {
        return true;
    }
    
    /**
     * Erzeugt aus einem String-Array einen neuen String,
     * in dem alle Elemente des Arrays hintereinander 
     * verknüpft sind. 
     * 
     * Aus <code>String 1,String 2</code> wird <code>String 1String 2</code>.
     */
    public String concat( String[] pArray ) {
        return "";
    }
    
    /**
     * Erzeugt aus einem String-Array einen neuen String,
     * in dem alle Elemente des Arrays hintereinander 
     * verknüpft und durch <var>pSep</var> getrennt sind.
     * 
     * Aus <code>String 1,String 2</code> wird mit dem Aufruf
     * <code>pSep = ";"</code> zum beispiel <code>String 1;String 2</code>.
     */
    public String join( String[] pArray, String pSep ) {
        return "";
    }
    
    /**
     * Dreht die Reihenfolge der Elemente in einem String-Array 
     * um.
     */
    public String[] reverse( String[] pArray ) {
        return null;
    }
    
    /**
     * Erstellt ein Integer-Array mit den ersten <var>pNumberOfElements</var>
     * Fibonacci-Zahlen.
     * 
     * (Wenn du nicht weißt, was die Fibonacci-Zahlen sind hilft Wikipedia.)
     */
    public int[] generateFibonacci( int pNumberOfElements ) {
        return null;
    }
    
    /**
     * Gibt die Elemente eines Integer-Array auf der Kommandozeile aus.
     */
    public void printArray( int[] pArray ) {
        boolean first = true; // Nötig um nicht zu viele Kommata zu setzen
        
        // Zählschleife für den Durchlauf durchs Arrays
        for( int i = 0; i < pArray.length; i++ ) {
            // Kommata als Trenner
            if( !first ) {
                System.out.print(",");
            } else {
                first = false;
            }
            
            // Array-Element ausgeben
            System.out.print(pArray[i]);
        }
    }
    /**
     * Gibt die Elemente eines String-Array auf der Kommandozeile aus.
     */
    public void printArray( String[] pArray ) {
        boolean first = true; // Nötig um nicht zu viele Kommata zu setzen
        
        // Zählschleife für den Durchlauf durchs Arrays
        for( int i = 0; i < pArray.length; i++ ) {
            // Kommata als Trenner
            if( !first ) {
                System.out.print(",");
            } else {
                first = false;
            }
            
            // Array-Element ausgeben
            System.out.print(pArray[i]);
        }
    }
    
    /**
     * Gibt die Elemente eines Boolean-Array auf der Kommandozeile aus.
     */
    public void printArray( boolean[] pArray ) {
        boolean first = true; // Nötig um nicht zu viele Kommata zu setzen
        
        // Zählschleife für den Durchlauf durchs Arrays
        for( int i = 0; i < pArray.length; i++ ) {
            // Kommata als Trenner
            if( !first ) {
                System.out.print(",");
            } else {
                first = false;
            }
            
            // Array-Element ausgeben
            System.out.print(pArray[i]);
        }
    }
    
}
