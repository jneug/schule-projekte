
public class Entscheidung extends Knoten {
    
    private String attribut;
    
    private String wertLinks;
    
    public Entscheidung( String pAttribut, String pWertLinks ) {
        attribut = pAttribut;
        wertLinks = pWertLinks;
    }
    
    public String entscheide( Datensatz d ) {
        if( d.get(attribut).equals(wertLinks) ) {
            return "links";
        } else {
            return "rechts";
        }
    }
    
}
