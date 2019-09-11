
public class Entscheidung extends Knoten {
    
    private String attribut;
    
    private String wertLinks;
    
    public Entscheidung( String pAttribut, String pWertLinks ) {
        attribut = pAttribut;
        wertLinks = pWertLinks;
    }
    
    public String entscheide( Passagier pPassagier ) {
        if( pPassagier.get(attribut).equals(wertLinks) ) {
            return "links";
        } else {
            return "rechts";
        }
    }

    public String toString() {
        return "Entscheidung[attribut:"+attribut+",links:"+wertLinks+",rechts:nicht "+wertLinks+"]";
    }
    
}
