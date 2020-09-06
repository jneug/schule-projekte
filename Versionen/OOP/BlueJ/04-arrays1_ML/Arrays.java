
import java.util.Random;

/**
 * Verschiedene Übungen zu Arrays.
 *
 * TODO:
 * Implementiere die Methoden nach der Beschreibung im jeweiligen
 * Kommentar. Teste deine Umsetzungen regelmäßig mit den Test-Methoden
 * in der Klasse {@link ArraysTest}.
 *
 * Du kannst die Methoden in beliebiger Reihenfolge implementieren. Such
 * dir also die für dich interessantesten raus.
 *
 * Einige Methoden sind schon vollständig implementiert, und können
 * als Beispiel dienen. Die Methoden {@link #printArray(int[])},
 * {@link #printArray(String[])} und {@link #printArray(boolean[])}
 * geben ein Array auf der Kommandozeile aus und können bei der Fehlersuche
 * helfen. Denke auch daran, den Debugger zu nutzen.
 */
public class Arrays {

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
     *
     * Wird ein negativer Wert für die Anzahl Elemente angegeben,
     * dann wird ein leeres Array zurück gegeben.
     *
     * @see #getRandomInt(int, int)
     */
    public int[] generateIntArray( int pNumberOfElements, int pMin, int pMax ) {
        if( pNumberOfElements < 1 ) {
            return new int[0];
        }
        int[] result = new int[pNumberOfElements];
        for( ; pNumberOfElements > 0; pNumberOfElements-- ) {
            result[pNumberOfElements-1] = getRandomInt(pMin, pMax);
        }
        return result;
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
     *
     * Wird ein negativer Wert für die Anzahl Elemente angegeben,
     * dann wird ein leeres Array zurück gegeben.
     */
    public String[] generateStringArray( int pNumberOfElements ) {
        if( pNumberOfElements < 1 ) {
            return new String[0];
        }
        String[] result = new String[pNumberOfElements];
        for( int i = 0; i < pNumberOfElements; i++ ) {
            result[pNumberOfElements-1] = "String "+(i+1);
        }
        return result;
    }

    /**
     * Erstellt ein Boolean-Array mit <var>pNumberOfElements</var>
     * zufällig gewählten Wahrheitswerten.
     *
     * Wird ein negativer Wert für die Anzahl Elemente angegeben,
     * dann wird ein leeres Array zurück gegeben.
     *
     * @see Random#nextBoolean()
     */
    public boolean[] generateBooleanArray( int pNumberOfElements ) {
        if( pNumberOfElements < 1 ) {
            return new boolean[0];
        }
        boolean[] result = new boolean[pNumberOfElements];
        for( ; pNumberOfElements > 0; pNumberOfElements-- ) {
            result[pNumberOfElements-1] = rand.nextBoolean();
        }
        return result;
    }

    /**
     * Sucht in einem Integer-Array das kleinste Element.
     *
     * Für ein leeres Array wird 0 zurück gegeben.
     */
    public int min( int[] pArray ) {
        if( pArray.length == 0 ) {
            return 0;
        }

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
     *
     * Für ein leeres Array wird 0 zurück gegeben.
     */
    public int max( int[] pArray ) {
        if( pArray.length == 0 ) {
            return 0;
        }

        int max = pArray[0];
        for( int i = 1; i < pArray.length; i++ ) {
            if( pArray[i] > max ) {
                max = pArray[i];
            }
        }
        return max;
    }

    /**
     * Berechnet die Summe aller Elemente in
     * einem Zahlen-Array.
     *
     * Für ein leeres Array wird 0 zurück gegeben.
     */
    public int sum( int[] pArray ) {
        int sum = 0;
        for( int i = 0; i < pArray.length; i++ ) {
            sum += pArray[i];
        }
        return sum;
    }

    /**
     * Berechnet den Mittelwert (average) eines
     * Integer-Arrays.
     *
     * Für ein leeres Array wird 0 zurück gegeben.
     */
    public double avg( int[] pArray ) {
        if( pArray.length == 0 ) {
            return 0;
        }

        return (double)sum(pArray) / (double)pArray.length;
    }

    /**
     * Prüft, ob <b>alle</b> Elemente in einem Array von
     * Wahrheitswerten <code>true</code> sind. Sonst wird
     * <code>false</code> zurück gegeben.
     *
     * Für ein leeres Array wird <code>false<code> zurück gegeben.
     */
    public boolean and( boolean[] pArray ) {
        if( pArray.length == 0 ) {
            return false;
        }

        for( int i = 0; i < pArray.length; i++ ) {
            if( !pArray[i] ) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prüft, ob <b>mindestens ein</b> Element in einem Array von
     * Wahrheitswerten <code>true</code> ist. Sonst wird
     * <code>false</code> zurück gegeben.
     *
     * Für ein leeres Array wird <code>false<code> zurück gegeben.
     */
    public boolean or( boolean[] pArray ) {
        if( pArray.length == 0 ) {
            return false;
        }

        for( int i = 0; i < pArray.length; i++ ) {
            if( pArray[i] ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prüft, ob <b>genau ein</b> Element in einem Array von
     * Wahrheitswerten <code>true</code> ist. Sonst wird
     * <code>false</code> zurück gegeben. (Bei keinmal <code>true</code>
     * oder mehr als einmal <code>true</code>.)
     *
     * Für ein leeres Array wird <code>false<code> zurück gegeben.
     */
    public boolean xor( boolean[] pArray ) {
        if( pArray.length == 0 ) {
            return false;
        }

        boolean result = false;
        for( int i = 0; i < pArray.length; i++ ) {
            if( pArray[i] && !result ) {
                result = true;
            } else if( pArray[i] && result ) {
                return false;
            }
        }
        return result;
    }

    /**
     * Erzeugt aus einem String-Array einen neuen String,
     * in dem alle Elemente des Arrays hintereinander
     * verknüpft sind.
     *
     * Aus <code>["String 1","String 2"]</code> wird <code>"String 1String 2"</code>.
     *
     * (Der <code>+</code> Operator verknüpft zwei Strings miteinander:
     * <code>"Foo" + "Bar"</code> ergibt <code>"FooBar"</code>.)
     *
     * Für ein leeres Array wird ein leerer String zurück gegeben.
     */
    public String concat( String[] pArray ) {
        String result = "";
        for( int i = 0; i < pArray.length; i++ ) {
            result += pArray[i];
        }
        return result;
    }

    /**
     * Erzeugt aus einem String-Array einen neuen String,
     * in dem alle Elemente des Arrays durch <var>pSep</var> getrennt
     * hintereinander verknüpft  sind.
     *
     * Aus <code>["String 1","String 2"]</code> wird mit dem Aufruf
     * <code>pSep = ";"</code> zum Beispiel <code>"String 1;String 2"</code>.
     *
     * Für ein leeres Array wird ein leerer String zurück gegeben.
     */
    public String join( String[] pArray, String pSep ) {
        String result = "";
        for( int i = 0; i < pArray.length; i++ ) {
            if( i > 0 ) {
                result += pSep;
            }
            result += pArray[i];

        }
        return result;
    }

    /**
     * Setzt vor jeden String im Array das Prefix <var>pPrefix</var> und gibt
     * das Array mit den veränderten Werten zurück.
     *
     * Aus <code>["String 1","String 2"]</code> wird mit <code>pPrefix = "+"</code>
     * zum Beispiel <code>["+String 1","+String 2"]</code>.
     */
    public String[] prefix( String[] pArray, String pPrefix ) {
        for( int i = 0; i < pArray.length; i++ ) {
            pArray[i] = pPrefix + pArray[i];
        }
        return pArray;
    }

    /**
     * Erstellt ein neues String-Array, in dem die Elemente aus <var>pArray</var>
     * in umgekehrter Reihenfolge vorkommen.
     *
     * Aus <code>["String 1","String 2"]</code> wird zum Beispiel
     * <code>["String 2","String 1"]</code>.
     */
    public String[] reverse( String[] pArray ) {
        String[] result = new String[pArray.length];
        for( int i = 0; i < pArray.length; i++ ) {
            result[pArray.length-i-1] = pArray[i];
        }
        return result;
    }

    /**
     * Zählt, wie viele Elemente im Zahlen-Array
     * größer als <var>pValue</var> sind.
     *
     * Für <code>pArray = int[]{1,2,3,4,5}</code> und
     * <code>pValue = 3</var> wäre das Ergebnis dann
     * <code>2</code>.
     *
     * Für ein leeres Array wird 0 zurück gegeben.
     */
    public int over( int[] pArray, int pValue ) {
        int count = 0;
        for( int i = 0; i < pArray.length; i++ ) {
            if( pArray[i] > pValue ) {
                count++;
            }
        }
        return count;
    }

    /**
     * Erstellt ein Integer-Array mit den ersten <var>pNumberOfElements</var>
     * Fibonacci-Zahlen.
     *
     * Die ersten sechs Fibonacci-Zahlen sind: 1,1,2,3,5,8,...
     *
     * (Wenn du nicht weißt, was die Fibonacci-Zahlen sind hilft Wikipedia.)
     *
     * Wird ein negativer Wert für die Anzahl Elemente angegeben,
     * dann wird ein leeres Array zurück gegeben.
     */
    public int[] generateFibonacci( int pNumberOfElements ) {
        if( pNumberOfElements < 1 ) {
            return new int[0];
        }

        int a = 0;
        int[] fib = new int[pNumberOfElements];
        fib[0] = 1;
        for( int i = 1; i < pNumberOfElements; i++ ) {
            fib[i] = a + fib[i-1];
            a = fib[i-1];
        }
        return fib;
    }


    // Ausgabe-Methoden
    // Ab hier ist schon alles implementiert :)

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
