/**
 * Klassifikationen sind Blattknoten im Entschiedungsbaum, die
 * einem Passagier eine Klasse zuweisen. Diese Klasse ist statisch, also
 * für jeden Datensatz gleich, und wird im Konstruktor festgelegt.
 */
public class Classification extends DecisionNode {

    private String classification;

    public Classification( String pClass ) {
        classification = pClass;
    }

    public String decide( Passenger pPassenger ) {
        return classification;
    }

    public String toString() {
        return "Classification[" + classification + "]";
    }

}
