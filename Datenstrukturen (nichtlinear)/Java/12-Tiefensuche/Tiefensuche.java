
/**
 * Implementierung der Tiefensuche auf einem ungerichteten, gewichteten
 * Graphen.
 */
public class Tiefensuche {

    private Graph g;

    public Tiefensuche() {
        g = new Graph();

        // Aufbau des Graphen
        // Erstellen der Knoten (Vertices)
        g.addVertex(new Vertex("Köln"));
        g.addVertex(new Vertex("Düsseldorf"));
        g.addVertex(new Vertex("Dortmund"));

        Vertex bi = new Vertex("Bielefeld");
        g.addVertex(bi);
        Vertex hnv = new Vertex("Hannover");
        g.addVertex(hnv);
        Vertex bo = new Vertex("Bochum");
        g.addVertex(bo);

        // Erstellen der Kanten (Edges)
        g.addEdge(new Edge(
            g.getVertex("Köln"),
            g.getVertex("Dortmund"),
            96.0
        ));
        g.addEdge(new Edge(
            g.getVertex("Düsseldorf"),
            g.getVertex("Dortmund"),
            70.0
        ));
        g.addEdge(new Edge(g.getVertex("Dortmund"), bo, 22.0));
        g.addEdge(new Edge(bi, hnv, 113.0));
        Edge bi_do = new Edge(bi, g.getVertex("Dortmund"), 112.0);
        g.addEdge(bi_do);
        Edge bi_dus = new Edge(bi, g.getVertex("Düsseldorf"), 178.0);
        g.addEdge(bi_dus);
        Edge bo_dus = new Edge(bo, g.getVertex("Düsseldorf"), 52.0);
    }

    /**
     * Suche nach einem Vertex mit der angegebenen ID mittels der Tiefensuche.
     *
     * @param pVertexID Die gesuchte Knoten-ID.
     * @return {@code true}, wenn ein Knoten mit der gesuchten ID im Graphen
     *     exisitert, {@code false} sonst.
     */
    public boolean findVertex( String pVertexID ) {
        /*aufg* 1
        // Setze alle Markierungen zurück
        // Starte die Rekursion
        return false;
        *aufg*/
        /*aufg* 2
        *aufg*/
        //ml* 2
        g.setAllVertexMarks(false); // Markierungen zurücksetzen
        return findVertex(pVertexID, g.getVertices().getContent());
        //*ml
    }

    /**
     * Hilfsmethode für die rekursive Tiefensuche. Sucht nach einem Vertex mit
     * der angegebenen ID mittels der Tiefensuche, ausgehend vom angegebenen
     * Startknoten.
     *
     * @param pVertexID Die gesuchte Knoten-ID.
     * @return {@code true}, wenn ein Knoten mit der gesuchten ID im Graphen
     *     exisitert, {@code false} sonst.
     */
    private boolean findVertex( String pVertexID, Vertex pCurrent ) {
        /*aufg*
        // Ist der aktuelle Knoten der Gesuchte?
        // Sonst
        //  Markiere aktuellen Knoten als besucht
        //  Wiederhole für jeden Nachbarknoten
        //    Wenn Nachbar nicht markiert
        //      rufe findVertex rekursiv auf
        *aufg*/
        //ml*
        if( pCurrent.getID().equals(pVertexID) ) {
            return true;
        } else {
            pCurrent.setMark(true);

            List<Vertex> neighbours = g.getNeighbours(pCurrent);
            neighbours.toFirst();
            while( neighbours.hasAccess() ) {
                Vertex v = neighbours.getContent();
                if( !v.isMarked() ) {
                    if( findVertex(pVertexID, v) ) {
                        return true;
                    }
                }
                neighbours.next();
            }
        }
        //*ml
        return false;
    }

}
