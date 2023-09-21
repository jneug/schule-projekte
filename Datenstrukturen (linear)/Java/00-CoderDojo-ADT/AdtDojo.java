
/**
 * Das AdtDojo Projekt ist eine Sammlung unzusammenhängender Übungen zur
 * Programmierung mit den linearen Datenstrukturen Queue und Stack.
 * <p>
 * Die Klassen {@link QueueDojo} und {@link StackDojo} enthalten Probleme, deren
 * Lösung nur die jeweilige Datenstruktur benötigt. In dieser Klasse
 * ({@link AdtDojo}) werden die Datenstrukturen kombiniert.
 * <p>
 * Im Kommentar jeder Methode steht eine Beschreibung des Problems, das in der
 * Methode gelöst werden soll. Die Test-Klassen {@link QueueDojoTest},
 * {@link StackDojoTest} und {@link AdtDojoTest} enthalten Testmethoden, die
 * prüfen, ob die Methode korrekt implementiert wurde. Die Tests können in BlueJ
 * mit einem Rechtsklick auf die Testklasse gestartet werden.
 * <p>
 * Du kannst die Methoden in beliebiger Reihenfolge implementieren. Such dir
 * also die für dich interessantesten heraus.
 * <p>
 * Einige Methoden sind schon vollständig implementiert, und können als Beispiel
 * dienen. Denke auch daran, den Debugger zu nutzen.
 * <p>
 * TODO:
 * Implementiere die Methoden nach der Beschreibung im jeweiligen
 * Kommentar. Teste deine Umsetzungen regelmäßig mit den Test-Methoden
 * in den Test-Klassen.
 */
public class AdtDojo {

    /**
     * Übersetzt die angegebene {@link Queue} in einen {@link Stack}, wobei das
     * erste Element der Schlange das "unterste" Element des Stapels wird.
     * <p>
     * Es kann davon ausgegangen werden, dass {@code pQueue} niemals
     * {@code null} ist. Die Schlange {@code pQueue} muss nicht erhalten bleiben
     * und kann nach der Ausführung der Methode zerstört sein.
     *
     * @param pQueue Die Queue mit {@link Pokemon}-Objekten.
     * @return Ein {@link Stack} mit den {@code Pokemon}-Objekten.
     */
    public Stack<Pokemon> queueToStack( Queue<Pokemon> pQueue ) {
        /*aufg*
        return null;
        *aufg*/
        //ml*
        Stack<Pokemon> s = new Stack<>();
        while( !pQueue.isEmpty() ) {
            s.push(pQueue.front());
            pQueue.dequeue();
        }
        return s;
        //*ml
    }

    /**
     * Übersetzt den angegebenen {@link Stack} in eine {@link Queue}, wobei das
     * erste Element des Stapels das "erste" Element der Schlange wird.
     * <p>
     * Es kann davon ausgegangen werden, dass {@code pStack} niemals
     * {@code null} ist. Der Stack {@lcode pStack} muss nicht erhalten bleiben
     * und kann nach der Ausführung der Methode zerstört sein.
     *
     * @param pStack Der Stack mit {@link Die}-Objekten.
     * @return Eine {@link Queue} mit den {@code Die}-Objekten.
     */
    public Queue<Die> stackToQueue( Stack<Die> pStack ) {
        /*aufg*
        return null;
        *aufg*/
        //ml*
        Queue<Die> q = new Queue<>();
        while( !pStack.isEmpty() ) {
            q.enqueue(pStack.top());
            pStack.pop();
        }
        return q;
        //*ml
    }

    /**
     * Dreht die angegbene {@link Queue} um, so dass das aktuell erste Element
     * das Letzte wird, das Zweite das Vorletzte, usw.
     * <p>
     * Beispielsweise wird aus der Schlange {@code [1, 2, 3, 4]} das Ergebnis
     * {@code [4, 3, 2, 1]}.
     * <p>
     * Es kann davon ausgegangen werden, dass {@code pQueue} niemals
     * {@code null} ist.
     * <p>
     * <strong>Tipp:</strong>
     * <a
     * href="https://tipp.ngb.schule/lbra-8hcb-to3v">https://tipp.ngb.schule/lbra-8hcb-to3v</a>
     *
     * @param pQueue
     */
    public void reverse( Queue<Integer> pQueue ) {
        //ml*
        Stack<Integer> temp = new Stack<>();
        while( !pQueue.isEmpty() ) {
            temp.push(pQueue.front());
            pQueue.dequeue();
        }
        while( !temp.isEmpty() ) {
            pQueue.enqueue(temp.top());
            temp.pop();
        }
        //*ml
    }

    /**
     * Entfernt nur genau das <em>zweite</em> Element aus dem angegebenen Stack.
     * Gibt es kein zweites Element, dann wird der Stapel nicht verändert.
     * <p>
     * Aus der Eingabe {@code [1, 2, 3, 4]} wir das Ergebnis {@code [1, 3, 4]}.
     * <p>
     * Es kann davon ausgegangen werden, dass {@code pQueue} niemals
     * {@code null} ist.
     * <p>
     * <strong>Tipp:</strong>
     * <a
     * href="https://tipp.ngb.schule/rjfg-0540-yv9k">https://tipp.ngb.schule/rjfg-0540-yv9k</a>
     *
     * @param pStack
     */
    public void removeSecond( Stack<Double> pStack ) {
        //ml*
        int i = 0;
        Stack<Double> temp = new Stack<>();
        while( !pStack.isEmpty() ) {
            if( i != 1 ) {
                temp.push(pStack.top());
            }
            pStack.pop();
            i += 1;
        }
        while( !temp.isEmpty() ) {
            pStack.push(temp.top());
            temp.pop();
        }
        //*ml
    }

    /**
     * Erstellt ein Array mit {@link Pokemon}-Objekten. Das Array hat die Größe
     * {@code pCount}.
     * <p>
     * Der Stapel {@code pAttackRolls} enthält immer mindestens {@code pCount}
     * {@link Die}-Objekte. Für jedes erstellte Pokemon wird der oberste Würfel
     * geworfen und das Ergebnis als Angriffswert verwendet. Jeder Würfel wird also
     * genau einmal geworfen.
     * <p>
     * Analog enthält die Schlange {@code pDefenseRolls} mindestens {@code pCount}
     * Würfel, mit denen die Verteidigungswerte erwürfelt werden.
     *
     * Die Namen der Pokemon können frei gewählt werden.
     *
     * @param pCount
     * @param pAttackRolls
     * @param pDefenseRolls
     * @return
     */
    public Pokemon[] createPokemon( int pCount, Stack<Die> pAttackRolls, Queue<Die> pDefenseRolls ) {
        /*aufg*
        return null;
        *aufg*/
        //ml*
        if( pCount <= 0 ) {
            return new Pokemon[0];
        }

        Pokemon[] result = new Pokemon[pCount];
        for( int i = 0; i < pCount; i++ ) {
            Die attackDie = pAttackRolls.top();
            Die defenseDie = pDefenseRolls.front();
            pAttackRolls.pop();
            pDefenseRolls.dequeue();

            attackDie.roll();
            defenseDie.roll();

            Pokemon poke = new Pokemon("Pokemon", attackDie.getValue(), defenseDie.getValue());
        }
        return result;
        //*ml
    }

}
