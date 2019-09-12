/**
 * Ein Knoten im Entschiedungsbaum. Knoten sind Entschiedungen,
 * die für einen Passagier entscheiden, ob im linken oder
 * rechten Teilbaum weitergesucht werden muss, oder dem Passagier
 * eine Klasse zuweisen (Blattknoten).
 */
public abstract class Knoten
{

    public abstract String entscheide( Passagier pPassagier );
    
    public abstract String toString();

}
