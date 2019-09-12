/**
 * Klassifikationen sind Blattknoten im Entschiedungsbaum, die
 * einem Passagier eine Klasse zuweisen. Diese Klasse ist statisch, also
 * für jeden Passagier gleich, und wird im Konstruktor festgelegt.
 */
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
