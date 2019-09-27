/**
 * Blattknoten eines Entscheidungsbaums. Klassifiziert einen Datensatz,
 * nachdem alle Entscheidungen des Pfades getroffen wurden.
 */
public class Klassifikation extends Knoten {

    // Die Klassifikation als String
    private String klasse;

    /**
     * Erstellt eine Klassifikation
     * @param pKlasse
     */
    public Klassifikation( String pKlasse ) {
        klasse = pKlasse;
    }

    @Override
    public String entscheide( Datensatz d ) {
        return klasse;
    }

}
