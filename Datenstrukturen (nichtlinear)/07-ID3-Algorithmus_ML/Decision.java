
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
public class Decision extends DecisionNode {

    private String attribute;

    private String valueLeft;

    public Decision( String pAttribute, String pValueLeft ) {
        attribute = pAttribute;
        valueLeft = pValueLeft;
    }

    public String decide( Passenger pPassenger ) {
        if( pPassenger.getValue(attribute).equals(valueLeft) ) {
            return "left";
        } else {
            return "right";
        }
    }

    public String toString() {
        return "Decision[attribute:"+ attribute +",left:"+ valueLeft +",right:not "+ valueLeft +"]";
    }

}
