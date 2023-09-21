/**
 * Unterklasse von Vertex, die den kürzesten Weg und den Vorgänger speichert.
 * <p>
 * Da die Graph-Klasse nur {@link Vertex}-Objekte verwendet, muss beim Zugriff
 * auf Vertices ein expliziter Typecast durchgeführt werden.
 * <p>
 * Beispielsweise:
 * <pre><code>
 * Graph g = new Graph();
 * g.addVertex( new DijkstraVertex("Test") );
 * DijkstraVertex v = (DijkstraVertex)g.getVertex("Test");
 * </code></pre>
 */
public class DijkstraVertex extends Vertex {

    // Speicher für den kürzesten Weg
    private double value;

    // Vorgängenknoten im kürzesten Weg
    private DijkstraVertex predecessor;

    /**
     * Erstellt einen Knoten mit der angegebenen ID.
     *
     * @param pID Die eindeutige ID des Knotens.
     */
    public DijkstraVertex( String pID ) {
        super(pID);
        this.value = Integer.MAX_VALUE;
        this.predecessor = null;
    }

    /**
     * Liefert den Wert des (derzeit) kürzesten Weges zurück.
     *
     * @return Der kürzeste Weg.
     */
    public double getValue() {
        return value;
    }

    /**
     * Setzt den (derzeit) kürzesten Weg auf den angegebenen Wert.
     *
     * @param value Der neue kürzeste Weg.
     */
    public void setValue( double value ) {
        this.value = value;
    }

    /**
     * Liefert den aktuellen Vorgängerknoten zurück.
     *
     * @return Der aktuell gesetzte Vorgängerknoten.
     */
    public DijkstraVertex getPredecessor() {
        return predecessor;
    }

    /**
     * Setzt den Vorgängerknoten auf das angegebene Objekt.
     *
     * @param predecessor Der neue Vorgänger.
     */
    public void setPredecessor( DijkstraVertex predecessor ) {
        this.predecessor = predecessor;
    }

}
