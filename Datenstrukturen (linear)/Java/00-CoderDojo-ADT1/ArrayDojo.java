/**
 * Das ArrayDojo Projekt ist eine Sammlung unzusammenhängender Übungen zur
 * Programmierung mit zweidimensionalen Arrays.
 * <p>
 * <p>
 * Im Kommentar jeder Methode steht eine Beschreibung des Problems, das in der
 * Methode gelöst werden soll. Die Klasse {@link ArrayDojoTest} enthält
 * Testmethoden, die prüfen, ob die Methode korrekt implementiert wurde. Die
 * Tests können in BlueJ mit einem Rechtsklick auf die Testklasse gestartet
 * werden.
 * <p>
 * Du kannst die Methoden in beliebiger Reihenfolge implementieren. Such
 * dir also die für dich interessantesten heraus.
 * <p>
 * Einige Methoden sind schon vollständig implementiert, und können
 * als Beispiel dienen. Die Methoden {@link #printArray(int[][])},
 * {@link #printArray(Wuerfel[])} und {@link #printArray(Wuerfel[][])}
 * geben ein Array auf der Kommandozeile aus und können bei der Fehlersuche
 * helfen. Denke auch daran, den Debugger zu nutzen.
 * <p>
 * TODO:
 * Implementiere die Methoden nach der Beschreibung im jeweiligen
 * Kommentar. Teste deine Umsetzungen regelmäßig mit den Test-Methoden
 * in der Klasse {@link ArrayDojoTest}.
 */
public class ArrayDojo {

    /**
     * Ein Beispiel zu Objekt-Arrays.
     * <p>
     * Die Methode erstellt ein Array mit {@link Wuerfel}n und führt Methoden
     * auf den Objekten aus.
     * <p>
     * Teilweise refernzieren die Elemente des Arrays dasselbe Objekt.
     */
    public void objectArrayExample() {
        // Array mit Objektreferenzen erstellen
        Wuerfel[] wuerfel = new Wuerfel[6];
        wuerfel[4] = new Wuerfel("W4");
        wuerfel[0] = new Wuerfel("W0");
        wuerfel[1] = new Wuerfel("W1");
        wuerfel[2] = wuerfel[4]; // Index 2 ist dasselbe Objekt wie Index 4
        wuerfel[3] = wuerfel[1]; // Index 3 ist dasselbe Objekt wie Index 1

        // Ausgabe der Elemente im Array
        printArray(wuerfel);

        // wuerfel[2] und wuerfel[4] zeigen auf dasselbe Objekt
        wuerfel[2].werfen();
        System.out.printf("Index 2: %s\n", wuerfel[2].toString());
        System.out.printf("Index 4: %s\n", wuerfel[4].toString());
        if( wuerfel[2] == wuerfel[4] ) {
            System.out.println("Index 2 und Index 4 sind dasselbe Objekt.");
        }
        System.out.println();

        // Die Referenz in wuerfel[2] kann auf ein neues Objekt gesetzt werden
        wuerfel[2] = new Wuerfel("W2");
        System.out.printf("Index 2: %s\n", wuerfel[2].toString());
        System.out.printf("Index 4: %s\n", wuerfel[4].toString());
        if( wuerfel[2] != wuerfel[4] ) {
            System.out.println("Index 2 und Index 4 sind unterschiedliche Objekt.");
        }
        System.out.println();

        // Eine Objektreferenz im Array kann auch gelöscht werden
        // Das zugehörige Objekt bleibt erhalten, solange noch eine andere
        // Referenz vorhanden ist (hier wuerfel[1]).
        System.out.printf("Index 1: %s\n", wuerfel[1].toString());
        System.out.printf("Index 3: %s\n", wuerfel[3].toString());
        wuerfel[3] = null;
        System.out.printf("Index 1: %s\n", wuerfel[1].toString());
        System.out.printf("Index 3: %s\n", wuerfel[3]);
        System.out.println();

        // Referenzen können beliebig neu gesetzt werden
        Wuerfel w5 = new Wuerfel("W5");
        wuerfel[5] = w5;
        wuerfel[0] = w5;

        // Ausgabe der Elemente im Array
        printArray(wuerfel);
    }

    /**
     * Wirft alle Würfel in einem <code>Wuerfel</code>-Array neu.
     * <p>
     * Indizes ohne Referenz auf einen <code>Wuerfel</code> (also mit Inhalt
     * <code>null</code>) werden übersprungen.
     *
     * @param pArray Ein eindimensionales Array mit <code>Wuerfel</code>-Objekten.
     * @see Wuerfel#werfen()
     */
    public void rollAll( Wuerfel[] pArray ) {
        //ml*
        for( int i = 0; i < pArray.length; i += 1 ) {
            if( pArray[i] != null ) {
                pArray[i].werfen();
            }
        }
        //*ml
    }

    /**
     * Berechnet die Summe aller Augenzahlen, die die Würfel in einem
     * <code>Wuerfel</code>-Array gerade anzeigen.
     * <p>
     * Indizes ohne Referenz auf einen <code>Wuerfel</code> (also mit Inhalt
     * <code>null</code>) werden übersprungen.
     * <p>
     * Für ein leeres Array wird <code>0</code> zurückgegeben.
     *
     * @param pArray Ein eindimensionales Array mit <code>Wuerfel</code>-Objekten.
     * @see Wuerfel#getAugenzahl()
     */
    public int diceSum( Wuerfel[] pArray ) {
        /*aufg*
        return 0;
        *aufg*/
        //ml*
        int sum = 0;
        for( int m = 0; m < pArray.length; m += 1 ) {
            if( pArray[m] != null ) {
                sum += pArray[m].getAugenzahl();
            }
        }
        return sum;
        //*ml
    }

    /**
     * Wirft alle Würfel in einem zweidimensionalen <code>Wuerfel</code>-Array
     * neu.
     * <p>
     * Indizes ohne Referenz auf einen Würfel (also mit Inhalt <code>null</code>)
     * werden übersprungen.
     *
     * <b>Hinweis:</b> Achte darauf, dass du nun zweimal auf <code>null</code>
     * prüfen musst.
     *
     * @param pArray Ein zweidimensionales Array mit <code>Wuerfel</code>-Objekten.
     * @see Wuerfel#werfen()
     */
    public void twoDimRoll( Wuerfel[][] pArray ) {
        //ml*
        for( int i = 0; i < pArray.length; i += 1 ) {
            if( pArray[i] != null ) {
                for( int j = 0; j < pArray[i].length; j += 1 ) {
                    if( pArray[i][j] != null ) {
                        pArray[i][j].werfen();
                    }
                }
            }
        }
        //*ml
    }

    /**
     * Berechnet die Summe aller Augenzahlen, die die Würfel in einem
     * zweidimensionalen <code>Wuerfel</code>-Array gerade anzeigen.
     * <p>
     * Indizes ohne Referenz auf einen Würfel (also mit Inhalt <code>null</code>)
     * werden übersprungen.
     * <p>
     * Für ein leeres Array wird <code>0</code> zurückgegeben.
     *
     * @param pArray Ein zweidimensionales Array mit <code>Wuerfel</code>-Objekten.
     * @see Wuerfel#getAugenzahl()
     */
    public int twoDimSum( Wuerfel[][] pArray ) {
        /*aufg*
        return 0;
        *aufg*/
        //ml*
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
        //*ml
    }

    /**
     * Berechnet in einem zweidimensionalen <code>Wuerfel</code>-Array die
     * Zeilensummen der gezeigten Augenzahlen.
     * <p>
     * Das bedeutet für jeden Index <code>i</code> in <var>pArray</var> wird die
     * Summe der Elemente im Array <code>pArray[i]</code> berechnet. Die Summe
     * wird im Ergebnisarray wieder im Index <code>i</code> gespeichert.
     * <p>
     * Zeigen die <code>Wuerfel</code> in Array zum Beispiel diese Augenzahlen:
     * <pre>
     *     {
     *         {1,2,3},
     *         {4,5,6},
     *         {-3,5,-2}
     *     }
     * </pre>
     * dann ist das Ergebnis-Array:
     * <pre>
     *     {
     *         6,
     *         15,
     *         0
     *     }
     * </pre>
     * <p>
     * Es darf angenommen werden, dass <var>pArray</var> keine
     * <code>null</code>-Referenzen enthält.
     *
     * <b>Hinweis:</b> Die Eingabe ist ein Array mit <code>Wuerfel</code>-Objekten,
     * die Ausgabe ein Array mit Zahlen (Integer).
     *
     * @param pArray Ein zweidimensionales Array mit <code>Wuerfel</code>-Objekten.
     * @return Ein eindimensionales Array mit den Zeilensummen
     */
    public int[] rowSum( Wuerfel[][] pArray ) {
        /*aufg*
        return null;
        *aufg*/
        //ml*
        int[] sums = new int[pArray.length];
        for( int i = 0; i < pArray.length; i += 1 ) {
            Wuerfel[] innerArray = pArray[i];
            for( int j = 0; j < innerArray.length; j += 1 ) {
                sums[i] += innerArray[j].getAugenzahl();
            }
        }
        return sums;
        //*ml
    }

    /**
     * Erzeugt aus einem zweidimensionalen Array mit Zahlen ein eindimensionales,
     * dass alle Zahlen des Ursprungsarrays hintereinander enthält.
     * <p>
     * Aus dem Array
     * <pre>
     * {
     *     {1,2,3},
     *     {4,5,6},
     *     null,
     *     {7,8,9}
     * }
     * </pre>
     * <p>
     * wird zum Beispiel das Ergebnis
     * <pre>
     * {1,2,3,4,5,6,7,8,9}
     * </pre>
     * <p>
     * <b>Hinweis:</b> Ermittele zunächst die Gesamtanzahl an Zahlen in
     * <var>pArray</var>. (Bedenke, dass die inneren Arrays unterschiedliche
     * Längen haben können.) Durchlaufe dann alle Arrays und zähle mit, den
     * wievielten Index im Zielarray das Element hat.
     *
     * @param pArray Ein zweidimensionales Array mit Zahlen.
     * @return Ein eindimensionales Array mit allen Zahlen.
     */
    public int[] flatten( int[][] pArray ) {
        /*aufg*
        return null;
        *aufg*/
        //ml*
        int len = 0;
        for( int i = 0; i < pArray.length; i++ ) {
            if( pArray[i] != null ) {
                len += pArray[i].length;
            }
        }

        int k = 0;
        int[] result = new int[len];
        for( int i = 0; i < pArray.length; i += 1 ) {
            if( pArray[i] != null ) {
                for( int j = 0; j < pArray[i].length; j += 1 ) {
                    result[k] = pArray[i][j];
                    k += 1;
                }
            }
        }

        return result;
        //*ml
    }

    /**
     * Gibt die das zweidimensionalen Array mit Zahlen auf
     * der Konsole ({@link System#out}) aus.
     *
     * @param pArray Ein zweidimensionales Array mit Wuerfel-Referenzen
     */
    public void printArray( int[][] pArray ) {
        for( int i = 0; i < pArray.length; i++ ) {
            if( pArray[i] != null ) { // prüfen, ob es ein Objekt gibt
                for( int j = 0; j < pArray[i].length; j++ ) {
                    System.out.printf("Index %d,%d: %s\n", i, j, pArray[i][j]);
                }
            } else {
                System.out.printf("Kein inneres Array an Index %d\n", i);
            }
        }
        System.out.println();
    }

    /**
     * Gibt die Augenzahlen der Würfel im Array auf
     * der Konsole ({@link System#out}) aus.
     *
     * @param pArray Ein Array mit Wuerfel-Referenzen
     */
    public void printArray( Wuerfel[] pArray ) {
        for( int i = 0; i < pArray.length; i++ ) {
            if( pArray[i] != null ) { // prüfen, ob es ein Objekt gibt
                System.out.printf("Index %d: %s\n", i, pArray[i].toString());
            } else {
                System.out.printf("Index %d: null\n", i);
            }
        }
        System.out.println();
    }


    /**
     * Gibt die Augenzahlen der Würfel im zweidimensionalen Array auf
     * der Konsole ({@link System#out}) aus.
     *
     * @param pArray Ein zweidimensionales Array mit Wuerfel-Referenzen
     */
    public void printArray( Wuerfel[][] pArray ) {
        for( int i = 0; i < pArray.length; i++ ) {
            if( pArray[i] != null ) { // prüfen, ob es ein Objekt gibt
                Wuerfel[] innerArray = pArray[i];
                for( int j = 0; j < innerArray.length; j++ ) {
                    if( innerArray[j] != null ) {
                        System.out.printf("Index %d,%d: %d\n", i, j, pArray[i][j].toString());
                    } else {
                        System.out.printf("Index %d,%d: null\n", i, j);
                    }
                }
            } else {
                System.out.printf("Kein inneres Array an Index %d\n", i);
            }
        }
        System.out.println();
    }

    /**
     * Erstellt ein eindimensionales Array mit <var>pNumberOfElements</var>
     * {@link Wuerfel}n.
     *
     * @param pNumberOfElements Anzahl an Elementen im Array.
     * @return Ein Array mit Wuerfeln.
     */
    public Wuerfel[] generateArray( int pNumberOfElements ) {
        Wuerfel[] dice = new Wuerfel[pNumberOfElements];
        for( int i = 0; i < dice.length; i++ ) {
            dice[i] = new Wuerfel("W" + i);
        }
        return dice;
    }

    /**
     * Erstellt ein Array das wiederum <var>pNumberOfRows</var> Arrays enthält,
     * die jeweils <var>pNumberOfCols</var> {@link Wuerfel} enthalten.
     *
     * @param pNumberOfCols Anzahl an Elementen (Arrays).
     * @param pNumberOfRows Anzahl Elemente der inneren Arrays.
     * @return Das zweidimensionale Array mit Würflen.
     */
    public Wuerfel[][] generateArray( int pNumberOfRows, int pNumberOfCols ) {
        Wuerfel[][] dice = new Wuerfel[pNumberOfRows][pNumberOfCols];
        for( int i = 0; i < dice.length; i++ ) {
            dice[i] = generateArray(pNumberOfCols);
        }
        return dice;
    }

}
