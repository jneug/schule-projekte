
public class Klassifikation extends Knoten {
    
    private String klasse;
    
    public Klassifikation( String pKlasse ) {
        klasse = pKlasse;
    }
    
    public String entscheide( Passagier pPassagier ) {
        return klasse;
    }

    public String toString() {
        return "Klassifikation["+klasse+"]";
    }
    
}
