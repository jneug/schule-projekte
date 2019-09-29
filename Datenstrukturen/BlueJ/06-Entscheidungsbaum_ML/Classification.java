/**
 * Blattknoten eines Entscheidungsbaums. Klassifiziert einen Datensatz,
 * nachdem alle Entscheidungen des Pfades getroffen wurden.
 */
public class Classification extends DecisionNode {

    // Die Klassifikation als String
    private String classification;

    /**
     * Erstellt eine Klassifikation
     * @param pClassification
     */
    public Classification( String pClassification ) {
        classification = pClassification;
    }

    @Override
    public String decide( Dataset pDataset ) {
        return classification;
    }

}
