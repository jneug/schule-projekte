/**
 * Innerer Knoten eines Entscheidungsbaums. Entscheidet basierend auf dem Wert
 * eines Attributs eines {@link Dataset Datensatzes}, ob im linken oder rechten
 * Teilbaum weitergesucht werden muss.
 * <p>
 * Da es sich um <em>binäre Entscheidungen</em> handelt wird immer nur der Wert
 * für den linken Teilbaum angegeben und für alle anderen Werte der rechte
 * Teilbaum gewählt.
 * </p>
 */
public class Decision implements DecisionNode {

    // Name des Attributs
    private String attribute;

    // Wert, bei dem links weitergesucht werden soll
    private String valueLeft;

    /**
     * Erstellt eine binäre Entscheidung.
     *
     * @param pAttribute Name des Attributs.
     * @param pValueLeft Wert, wann im linken Teilbaum weitergesucht werden
     * muss.
     */
    public Decision( String pAttribute, String pValueLeft ) {
        attribute = pAttribute;
        valueLeft = pValueLeft;
    }

    @Override
    public String decide( Dataset pDataset ) {
        if( pDataset.get(attribute).equals(valueLeft) ) {
            return "left";
        } else {
            return "right";
        }
    }

}
