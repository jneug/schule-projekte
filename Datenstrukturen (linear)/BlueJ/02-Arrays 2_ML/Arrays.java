
import java.util.Random;

public class Arrays {

    /**
     * Ein Beispiel zu Objekt-Arrays.
     */
    public void objectArrayExample() {
        // Array mit Objektreferenzen erstellen
        Wuerfel[] wuerfel = new Wuerfel[6];
        wuerfel[4] = new Wuerfel("W4");
        wuerfel[0] = new Wuerfel("W0");
        wuerfel[1] = new Wuerfel("W1");
        wuerfel[2] = wuerfel[4];
        wuerfel[3] = wuerfel[1];

        // Ausgabe der Elemente im Array
        printArray( wuerfel );

        // wuerfel[2] und wuerfel[4] zeigen auf dasselbe Objekt
        wuerfel[2].werfen();
        System.out.printf("Index %s: %s\n", 4, wuerfel[4].toString());
        if( wuerfel[2] == wuerfel[4] ) {
            System.out.println("Index 2 und Index 4 sind gleich");
        }
        System.out.println();

        // Die Referenz in wuerfel[2] kann auf ein neues Objekt gesetzt werden
        wuerfel[2] = new Wuerfel("W2");

        // Eine Objektreferenz im Array kann auch gelöscht werden
        // Das zugehörige Objekt bleibt erhalten, solange noch eine andere
        // Referenz vorhanden ist (hier wuerfel[1]).
        wuerfel[3] = null;

        // Referenzen können beliebig neu gesetzt werden
        Wuerfel w5 = new Wuerfel("W5");
        wuerfel[5] = w5;
        wuerfel[0] = w5;

        // Ausgabe der Elemente im Array
        printArray( wuerfel );
    }

    /**
     * Wirft alle Würfel im Array neu.
     *
     * Indizes ohne Referenz auf einen Würfel (<code>null</code>)
     * werden übersprungen.
     *
     * @see Wuerfel#werfen()
     */
    public void rollAll( Wuerfel[] pArray ) {
        for( int i = 0; i < pArray.length; i += 1 ) {
            if( pArray[i] != null ) {
                pArray[i].werfen();
            }
        }
    }

    /**
     * Berechnet die Summe aller Augenzahlen, die die Würfel
     * im Array gerade anzeigen.
     *
     * Indizes ohne Referenz auf einen Würfel (<code>null</code>)
     * werden übersprungen.
     *
     * @see Wuerfel#getAugenzahl()
     */
    public int diceSum( Wuerfel[] pArray ) {
        int sum = 0;
        for( int m = 0; m < pArray.length; m += 1 ) {
            if( pArray[m] != null ) {
                sum += pArray[m].getAugenzahl();
            }
        }
        return sum;
    }

    /**
     * Wirft alle Würfel im zweidimensionalen Array neu.
     *
     * Indizes ohne Referenz auf einen Würfel (<code>null</code>)
     * werden übersprungen.
     *
     * @see Wuerfel#werfen()
     */
    public void twoDimRoll( Wuerfel[][] pArray ) {
        for( int i = 0; i < pArray.length; i += 1 ) {
            if( pArray[i] != null ) {
                Wuerfel[] innerArray = pArray[i];
                for( int j = 0; j < innerArray.length; j += 1 ) {
                    if( innerArray[j] != null ) {
                        innerArray[j].werfen();
                    }
                }
            }
        }
    }

    /**
     * Berechnet die Summe aller Augenzahlen, die die Würfel
     * im zweidimensionalen Array gerade anzeigen.
     *
     * Indizes ohne Referenz auf einen Würfel (<code>null</code>)
     * werden übersprungen.
     *
     * @see Wuerfel#getAugenzahl()
     */
    public int twoDimSum( Wuerfel[][] pArray ) {
        int sum = 0;
        for( int i = 0; i < pArray.length; i += 1 ) {
            if( pArray[i] != null ) {
                Wuerfel[] innerArray = pArray[i];
                for( int j = 0; j < innerArray.length; j += 1 ) {
                    if( innerArray[j] != null ) {
                        sum += innerArray[j].getAugenzahl();
                    }
                }
            }
        }
        return sum;
    }

    /**
     * Berechnet in einem zweidimensionalen Array die Zeilensummen.
     * Das bedeutet für jeden Index <code>i</code> in pArray wird die Summe der Elemente
     * im Array <code>pArray[i]</code> berechnet. Die Summe wird im Ergebnisarray
     * wieder im Index <code>i</code> gespeichert.
     *
     * @param pArray
     * @return Ein eindimensionales Array mit den Zeilensummen
     */
    public int[] rowSum( Wuerfel[][] pArray ) {
        int[] sums = new int[pArray.length];
        for( int i = 0; i < pArray.length; i += 1 ) {
            Wuerfel[] innerArray = pArray[i];
            for( int j = 0; j < innerArray.length; j += 1 ) {
                sums[i] += innerArray[j].getAugenzahl();
            }
        }
        return sums;
    }

    /**
     * Gibt die Augenzahlen der Würfel im Array auf
     * der Kommandozeile aus.
     */
    public void printArray( Wuerfel[] pArray ) {
        for( int i = 0; i < pArray.length; i++ ) {
            if( pArray[i] != null ) { // prüfen, ob es ein Objekt gibt
                System.out.printf("Index %s: %s\n", i, pArray[i].toString());
            } else {
                System.out.printf("Index %s: null\n", i);
            }
        }
        System.out.println();
    }

    /**
     * Erstellt ein Array mit <var>pNumberOfElements</var>
     * {@link Wuerfel}n.
     */
    public Wuerfel[] generateArray( int pNumberOfElements ) {
        Wuerfel[] dice = new Wuerfel[pNumberOfElements];
        for( int i = 0; i < dice.length; i++ ) {
            dice[i] = new Wuerfel("W"+i);
        }
        return dice;
    }

    /**
     * Erstellt ein Array das wiederum <var>pNumberOfRows</var> Arrays enthält, die
     * jeweils <var>pNumberOfCols</var> {@link Wuerfel} enthalten.
     */
    public Wuerfel[][] generateArray( int pNumberOfRows, int pNumberOfCols ) {
        Wuerfel[][] dice = new Wuerfel[pNumberOfRows][pNumberOfCols];
        for( int i = 0; i < dice.length; i++ ) {
            dice[i] = generateArray(pNumberOfCols);
        }
        return dice;
    }

}
