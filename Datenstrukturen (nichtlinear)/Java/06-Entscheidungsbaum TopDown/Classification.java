/**
 * Blattknoten eines Entscheidungsbaums. Klassifiziert einen Datensatz, nachdem
 * alle Entscheidungen des Pfades getroffen wurden.
 */
public class Classification implements DecisionNode {

    // Die Klassifikation als String
    private String classification;

    /**
     * Erstellt eine Klassifikation
     *
     * @param pClassification Name der Klasse (z.B. "Ja" oder "Nein")
     */
    public Classification( String pClassification ) {
        classification = pClassification;
    }

    @Override
    public String decide( Dataset pDataset ) {
        return classification;
    }

}
