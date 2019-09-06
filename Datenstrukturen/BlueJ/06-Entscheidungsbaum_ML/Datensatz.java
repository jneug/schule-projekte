
public class Datensatz {
    
    private java.util.HashMap<String, String> daten;

    /**
     * Konstruktor für Objekte der Klasse Datensatz
     */
    public Datensatz() {
        daten = new java.util.HashMap<String, String>();
    }
    
    public String get( String pAttribut ) {
        return daten.get(pAttribut);
    }
    
    public void set( String pAttribut, String pWert ) {
        daten.put(pAttribut, pWert);
    }
    
}
