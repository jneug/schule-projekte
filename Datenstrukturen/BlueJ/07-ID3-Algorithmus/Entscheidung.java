
/**
 * Entschiedungen sind die Knoten im Entschiedungsbaum. Sie
 * entschieden für einen Passagier, ob im linken oder rechten
 * Teilbaum weitergesucht werden muss.
 *
 * Entscheidungen sind binär und werden durch den Namen des Attributs
 * und den Wert für den linken Teilbaum festgelegt. Die Entschiedung ist
 * dann also "links", falls das Attribut des Passagiers den festgelegten
 * Wert hat und "rechts" für alle anderen.
 */
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
