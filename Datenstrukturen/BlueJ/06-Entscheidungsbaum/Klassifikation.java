
public class Klassifikation extends Knoten {
    
    private String klasse;
    
    public Klassifikation( String pKlasse ) {
        klasse = pKlasse;
    }
    
    public String entscheide( Datensatz d ) {
        return klasse;
    }
    
}
