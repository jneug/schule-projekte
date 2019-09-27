/**
 * Ein Knoten im Entschiedungsbaum. Knoten sind Entschiedungen,
 * die für einen Passagier entscheiden, ob im linken oder
 * rechten Teilbaum weitergesucht werden muss, oder dem Passagier
 * eine Klasse zuweisen (Blattknoten).
 */
public abstract class DecisionNode {

    public abstract String decide( Passenger pPassenger );

    public abstract String toString();

}
