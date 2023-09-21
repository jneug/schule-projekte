/**
 * <p>
 * Materialien zu den zentralen NRW-Abiturpruefungen im Fach Informatik ab 2018
 * </p>
 * <p>
 * Klasse Vertex
 * </p>
 * <p>
 * Die Klasse Vertex stellt einen einzelnen Knoten eines Graphen dar. Jedes
 * Objekt dieser Klasse verfuegt ueber eine im Graphen eindeutige ID als String
 * und kann diese ID zurueckliefern. Darueber hinaus kann eine Markierung
 * gesetzt und abgefragt werden.
 * </p>
 *
 * @author Qualitaets- und UnterstuetzungsAgentur - Landesinstitut fuer Schule
 * @version Oktober 2015
 */
public class Vertex<ContentType> {

    private boolean mark;

    private float value;

    private Vertex predecessor;

    private ContentType content;

    /**
     * Ein neues Objekt vom Typ Vertex wird erstellt. Seine Markierung hat den
     * Wert false.
     */
    public Vertex( ContentType pContent ) {
        content = pContent;
        mark = false;
        value = 0f;
        predecessor = null;
    }

    /**
     * Der Auftrag setzt die Markierung des Knotens auf den Wert pMark.
     */
    public void setMark( boolean pMark ) {
        mark = pMark;
    }

    /**
     * Die Anfrage liefert true, wenn die Markierung des Knotens den Wert true
     * hat, ansonsten false.
     */
    public boolean isMarked() {
        return mark;
    }

    public float getValue() {
        return value;
    }

    public void setValue( float pValue ) {
        this.value = pValue;
    }

    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor( Vertex pPredecessor ) {
        this.predecessor = pPredecessor;
    }

    public ContentType getContent() {
        return content;
    }

    public void setContent( ContentType pContent ) {
        if( pContent != null ) {
            this.content = pContent;
        }
    }


    @Override
    public String toString() {
        return content.toString();
    }

}
