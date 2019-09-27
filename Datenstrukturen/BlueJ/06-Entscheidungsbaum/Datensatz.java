/**
 * Ein Datensatz, der vom {@link Entscheider} im Entscheidungsbaum
 * klassifiziert werden kann.
 * Basiert auf einer {@link java.util.HashMap}.
 */
public class Datensatz {

    // Speicher für die Daten
    private java.util.HashMap<String, String> data;

    /**
     * Erzeugt einen leeren Datensatz
     */
    public Datensatz() {
        data = new java.util.HashMap<String, String>();
    }

    /**
     * Gibt den Wert für ein Attribut in diesem Datensatz zurück oder
     * <code>null</code>, wenn das Attribut im Datensatz nicht existiert.
     * @param pAttribut Name des Attributs.
     * @return Wert im Datensatz oder <code>null</code>.
     */
    public String get( String pAttribut ) {
        return data.getOrDefault(pAttribut, null);
    }

    /**
     * Setzt den Wert eines Attributs in diesem Datensatz.
     * @param pAttribut Name des Attributs.
     * @param pWert Neuer Wert des Attributs.
     */
    public void set( String pAttribut, String pWert ) {
        data.put(pAttribut, pWert);
    }

}
