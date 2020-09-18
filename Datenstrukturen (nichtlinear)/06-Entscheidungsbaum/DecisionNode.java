/**
 * Ein Knoten im Entscheidungsbaum. Knoten sind entweder {@link Decision Entscheidungen}
 * (innere Knoten) oder {@link Classification Klassifikationen} (Blattknoten).
 */
public abstract class DecisionNode {

    /**
     * Führt den Test der Entscheidung durch. Die Methode
     * bekommt den Datensatz als Parameter und gibt einen
     * von drei Strings zurück:
     * <ol>
     * <li>"left": Folge dem linken Teilbaum</li>
     * <li>"right": Folge dem rechten Teilbaum</li>
     * <li>Die finale Klassifikation, wenn dies ein Blatt des Baumes ist (z.B. "yes" oder "no")</li>
     * </ol>
     */
    public abstract String decide( Dataset pDataset );

}
