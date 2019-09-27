/**
 * Innerer Knoten eines Entscheidungsbaums. Entscheidet basierend auf
 * dem Wert eines Attributs eines {@link }Datensatz}es, ob im linken oder
 * rechten Teilbaum weitergesucht werden muss.
 * <p>
 *  Da es sich um binäre Entscheidungen handelt wird immer nur der Wert für
 *  den linken Teilbaum angegeben und für alle anderen Werte der rechte
 *  Teilbaum gewählt.
 * </p>
 */
public class Entscheidung extends Knoten {

    // Name des Attributs
    private String attribut;

    // Wert, bei dem links weitergesucht werden soll
    private String wertLinks;

    /**
     * Erstellt eine binäre Entscheidung.
     * @param pAttribut Name des Attributs.
     * @param pWertLinks Wert, wann im linken Teilbaum weitergesucht werden muss.
     */
    public Entscheidung( String pAttribut, String pWertLinks ) {
        attribut = pAttribut;
        wertLinks = pWertLinks;
    }

    @Override
    public String entscheide( Datensatz d ) {
        if( d.get(attribut).equals(wertLinks) ) {
            return "links";
        } else {
            return "rechts";
        }
    }

}
