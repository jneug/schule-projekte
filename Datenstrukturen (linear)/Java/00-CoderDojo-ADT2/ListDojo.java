import java.util.Random;

/**
 * Das CoderDojo Projekt ist eine Sammlung unzusammenhängender Übungen zur
 * Objektorientierten Programmierung.
 * <p>
 * Dieser Dojo befasst sich mit den grundlegenden Konzepten der dynamischen
 * linearen Datenstruktur List aus dem Material des Zentralabitur NRW.
 * <p>
 * Im Kommentar jeder Methode steht eine Beschreibung des Problems, das in der
 * Methode gelöst werden soll. Die Klasse {@link ListDojoTest} enthält
 * Testmethoden, die prüfen, ob die Methode korrekt implementiert wurde. Die
 * Tests können in BlueJ mit einem Rechtsklick auf die Testklasse gestartet
 * werden.
 * <p>
 * Du kannst die Methoden in beliebiger Reihenfolge implementieren. Such
 * dir also die für dich interessantesten heraus. Allerdings sind manche
 * leichter zu implementieren, wenn du die anderen Methoden schon fertig hast
 * und als Hilfsmethoden nutzen kannst.
 * <p>
 * TODO:
 * Implementiere die Methoden nach der Beschreibung im jeweiligen
 * Kommentar. Teste deine Umsetzungen regelmäßig mit den Test-Methoden
 * in der Klasse {@link ListDojoTest}.
 */
public class ListDojo {

    /**
     * Erstellt eine Liste mit aufsteigenden Zahlen (Integern) von <code>1</code>
     * bis <var>pMax</var>.
     * <p>
     * Der Aufruf <code>ascendingList(10)</code> erzeugt die Liste
     * <pre>
     *     1, 2, 3, 4, 5, 6, 7, 8, 9, 10
     * </pre>
     *
     * @param pMax Das Maximum der Zahlen
     * @return Die Liste mit aufsteigenden Zahlen
     */
    public List<Integer> ascendingList( int pMax ) {
        /*aufg*
        return null;
        *aufg*/
        //ml*
        List<Integer> list = new List<>();
        for( int i = 1; i <= pMax; i++ ) {
            list.append(i);
        }
        return list;
        //*ml
    }

    /**
     * Erstellt eine Liste mit <var>pCount</var> Zufallszahlen zwischen
     * <code>pMin</code> und <var>pMax</var>.
     * <p>
     * Der Aufruf <code>randomList(10, 100, 200)</code> könnte zum Beispiel diese
     * Liste erzeugen
     * <pre>
     *     133, 200, 150, 124, 178, 199, 105, 156, 100, 144
     * </pre>
     * <p>
     * Die Zahlen <code>pMin</code> und <var>pMax</var> können auch Teil der
     * erzeugten Zahlen sein!
     * <p>
     * Der Aufruf <code>randomList(10, 100, 101)</code> könnte zum Beispiel diese
     * Liste erzeugen
     * <pre>
     *     101, 101, 100, 101, 100, 100, 100, 101, 101, 100
     * </pre>
     * <p>
     * Wenn <var>pCount</var> kleiner oder gleich <code>0</code> ist, oder
     * <var>pMin</var> größer als <var>pMax</var>, dann wird eine leere
     * Liste zurückgegeben.
     *
     * @param pCount Die Anzahl zu erzeugender Zufallszahlen
     * @param pMin   Das Minimum der Zufallszahlen
     * @param pMax   Das Maximum der Zufallszahlen
     * @return Die Liste mit Zufallszahlen
     * @see Random#nextInt(int)
     */
    public List<Integer> randomList( int pCount, int pMin, int pMax ) {
        Random zufall = new Random();

        /*aufg*
        return null;
        *aufg*/
        //ml*
        List<Integer> list = new List<>();
        if( pCount <= 0 || pMin > pMax ) {
            return list;
        }
        for( int i = 0; i < pCount; i++ ) {
            list.append(zufall.nextInt(pMax - pMin + 1) + pMin);
        }
        return list;
        //*ml
    }

    /**
     * Gibt eine Liste mit Zahlen auf der Konsole ({@code System.out}) aus. Pro Zeile
     * wird ein Element der Liste ausgegeben.
     *
     * @param pList Eine Liste mit Zahlen
     */
    public void printList( List<Integer> pList ) {
        //ml*
        pList.toFirst();
        while( pList.hasAccess() ) {
            System.out.println(pList.getContent());
            pList.next();
        }
        //*ml
    }

    /**
     * Gibt die Namen aller Pokemon in der Liste auf der Konsole ({@code System.out})
     * aus. Pro Zeile ein Name.
     *
     * @param pList Eine Liste mit Pokemon
     */
    public void printPokemon( List<Pokemon> pList ) {
        //ml*
        pList.toFirst();
        while( pList.hasAccess() ) {
            System.out.println(pList.getContent().getName());
            pList.next();
        }
        //*ml
    }

    /**
     * Sucht in der Liste <var>pList</var> nach dem Wert <var>pValue</var> und
     * gibt <code>true</code> zurück, wenn der Wert enthalten ist, <code>false</code>
     * andernfalls.
     *
     * @param pList  Eine Liste mit Zahlen
     * @param pValue Die zu suchende Zahl
     * @return Ob der Wert gefunden wurde
     */
    public boolean searchInList( List<Integer> pList, int pValue ) {
        /*aufg*
        return false;
        *aufg*/
        //ml*
        pList.toFirst();
        while( pList.hasAccess() ) {
            if( pValue == pList.getContent() ) {
                return true;
            }
            pList.next();
        }
        return false;
        //*ml
    }

    /**
     * Such aus der Liste die kleinste Zahl heraus.
     * <p>
     * Für eine leere Liste wird {@code Integer.MAX_VALUE} zurückgegeben.
     *
     * @param pList Eine Liste mit Zahlen
     * @return Die kleinste Zahl in der Liste
     */
    public int searchMinimum( List<Integer> pList ) {
        // Setze das Minimum zunächst auf den größten möglichen Wert
        int min = Integer.MAX_VALUE;

        // Suche das Minimum aus der Liste
        /*aufg*
        return min;
        *aufg*/
        //ml*
        pList.toFirst();
        while( pList.hasAccess() ) {
            if( pList.getContent() < min ) {
                min = pList.getContent();
            }
            pList.next();
        }
        return min;
        //*ml
    }

    /**
     * Fügt den Wert <var>pValue</var> an der Stelle <var>pPosition</var> in
     * die Liste <var>pList</var> ein. Gibt es diese Position nicht, weil die
     * Liste zu kurz ist, wird der Wert hinten angehängt.
     * <p>
     * Die Zählung der Positionen beginnt bei <code>1</code>. Der Aufruf
     * <code>insertIntoList(liste, 1000, 3)</code> auf der Liste
     * <pre>
     *     1, 2, 3, 4, 5, 6, 7, 8, 9, 10
     * </pre>
     * <p>
     * ergibt also
     * <pre>
     *     1, 2, 1000, 3, 4, 5, 6, 7, 8, 9, 10
     * </pre>
     *
     * @param pList     Eine Liste mit Zahlen
     * @param pValue    Die einzufügende Zahl
     * @param pPosition Die Position in der Liste
     */
    public void insertIntoList( List<Integer> pList, int pValue, int pPosition ) {
        //ml*
        pList.toFirst();
        while( pList.hasAccess() && pPosition > 1 ) {
            pList.next();
            pPosition -= 1;
        }
        if( pList.hasAccess() ) {
            pList.insert(pValue);
        } else {
            pList.append(pValue);
        }
        //*ml
    }

    /**
     * Löscht den Wert <var>pValue</var> aus der Liste <var>pList</var>. Kommt
     * der Wert mehrfach in der Liste vor, dann wird nur das erste Vorkommen
     * gelöscht.
     * <p>
     * Ist die Zahl nicht in der Liste vorhanden oder ist die Liste leer, dann
     * passiert nichts.
     *
     * @param pList  Eine Liste mit Zahlen
     * @param pValue Eine Zahl, die gelöscht werden soll
     */
    public void deleteFromList( List<Integer> pList, int pValue ) {
        //ml*
        pList.toFirst();
        while( pList.hasAccess() ) {
            if( pValue == pList.getContent() ) {
                pList.remove();
                break;
            }
            pList.next();
        }
        //*ml
    }

    /**
     * Löscht die kleinste Zahl aus der Liste. Kommt die kleinste Zahl mehrfach
     * vor, dann wird nur das erste Vorkommen gelöscht.
     * <p>
     * Ist die Liste leer, dann passiert nichts.
     *
     * @param pList Eine Liste mit Zahlen
     */
    public void deleteMinimum( List<Integer> pList ) {
        // Tipp: Implementiere zunächst searchMinimum() und deleteFromList()
        // und nutze die Methoden hier.
        //ml*
        int min = searchMinimum(pList);
        deleteFromList(pList, min);
        //*ml
    }

    /**
     * Gibt eine Liste mit Zahlen vom kleinsten zum größten auf der Konsole
     * ({@code System.out}) geordnet vom kleinsten zum größten aus. Dazu
     * wird in jedem Schritt das Minimum der Liste gesucht, ausgegeben und dann
     * aus der Liste entfernt. Die Liste ist am Ende leer.
     *
     * @param pList Eine Liste mit Zahlen
     */
    public void printOrdered( List<Integer> pList ) {
        while( !pList.isEmpty() ) {
            int min = searchMinimum(pList);
            System.out.println(min);

            deleteMinimum(pList);
        }
    }

    /**
     * Sucht in der Liste <var>pList</var> nach dem Pokemon mit dem Namen
     * <var>pName</var> und gibt das Pokemon-Objekt zurück, wenn der Wert
     * enthalten ist. Andernfalls wird <code>null</code> zurückgegeben.
     * <p>
     * Tipp: Für den Vergleich von zwei Strings sollte equals() benutzt werden.
     *
     * @param pList Eine Liste mit Pokemon
     * @param pName Das zu suchende pokemon
     * @return Das Pokemon-Objekt
     */
    public Pokemon searchInList( List<Pokemon> pList, String pName ) {
        /*aufg*
        return null;
        *aufg*/
        //ml*
        pList.toFirst();
        while( pList.hasAccess() ) {
            if( pName.equals(pList.getContent().getName()) ) {
                return pList.getContent();
            }
            pList.next();
        }
        return null;
        //*ml
    }

}
