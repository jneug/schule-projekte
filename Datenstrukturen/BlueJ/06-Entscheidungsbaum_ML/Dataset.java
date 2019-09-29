/**
 * Ein Datensatz, der vom {@link DecisionTreeBuilder} im Entscheidungsbaum
 * klassifiziert werden kann.
 * Basiert auf einer {@link java.util.HashMap}.
 */
public class Dataset {

    // Speicher für die Daten
    private java.util.HashMap<String, String> data;

    /**
     * Erzeugt einen leeren Datensatz
     */
    public Dataset() {
        data = new java.util.HashMap<String, String>();
    }

    /**
     * Gibt den Wert für ein Attribut in diesem Datensatz zurück oder
     * <code>null</code>, wenn das Attribut im Datensatz nicht existiert.
     * @param pAttribute Name des Attributs.
     * @return Wert im Datensatz oder <code>null</code>.
     */
    public String get( String pAttribute ) {
        return data.getOrDefault(pAttribute, null);
    }

    /**
     * Setzt den Wert eines Attributs in diesem Datensatz.
     * @param pAttribute Name des Attributs.
     * @param pValue Neuer Wert des Attributs.
     */
    public void set( String pAttribute, String pValue ) {
        data.put(pAttribute, pValue);
    }

}
