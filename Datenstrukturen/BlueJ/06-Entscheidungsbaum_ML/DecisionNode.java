/**
 * Ein Knoten im Entscheidungsbaum. Knoten sind entweder {@link Decision}en
 * (innere Knoten) oder {@link Classification}en (Blätter).
 */
public abstract class DecisionNode {

    /*
     * Führt den Test der Entscheidung durch. Die Methode
     * bekommt den Datensatz als Parameter und gibt einen
     * von drei Strings zurück:
     * 1. "left" - Folge dem linken Teilbaum
     * 2. "right" - Folge dem rechten Teilbaum
     * 3. Die finale Entscheidung, wenn dies ein Blatt des Baumes
     *      ist (z.B. "yes" oder "no")
     */
    public abstract String decide( Dataset pDataset );

}
