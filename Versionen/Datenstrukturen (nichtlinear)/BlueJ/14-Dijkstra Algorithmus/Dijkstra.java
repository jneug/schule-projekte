public class Dijkstra {

    private Graph graph;

    private boolean verbose;

    /**
     * Implementierung des Dijkstra-Algorithmus auf ungerichteten,
     * gewichteten Grapen.
     */
    public Dijkstra() {
        graph = new Graph();
        verbose = false;
    }

    /**
     * Schaltet die ausführliche Ausgabe ein bzw. aus.
     */
    public void toggleVerbose() {
        verbose = !verbose;
    }

    /**
     * Erstellt einen Testgraphen und führt den Algorithmus für die Strecke
     * Münster -> Lünen aus.
     */
    public void test() {
        System.out.println("Creating graph..");
        createGraphExample1();
        System.out.println("Searching for shortest path..");
        List<Vertex> path = findShortestPath("Münster", "Lünen");
        //List<Vertex> path = findShortestPath("Haltern", "Münster");
        System.out.println();
        System.out.println("Result:");
        printPath(path);
    }

    /**
     * Führt den Dijkstra-Algorithmus von einem Startknoten aus und bricht ab,
     * sobald der kürzeste Weg zum Zielknoten gefunden wurde.
     *
     * Der kürzeste Weg wird als Liste von Knoten zurückgegeben. Der kürzeste
     * Weg zu den Knoten (auf dem Weg) wird im Attribut {@link Vertex#getValue() value}
     * der Knoten gespeichert.
     *
     * @param from Startknoten
     * @param to Endknoten
     */
    public List<Vertex> findShortestPath( String from, String to ) {
        Vertex start = graph.getVertex(from);
        Vertex end = graph.getVertex(to);

        // Markierungen zeigen an, ob ein Knoten schon in die
        // Liste einsortiert wurde
        graph.setAllEdgeMarks(false);

        List<Vertex> vList = new List<>();
        // Startknoten kommt als Erster in der Liste,
        // mit Weglänge 0.0
        start.setValue(0.0);
        start.setMark(true);
        vList.append(start);

        while( !vList.isEmpty() ) {
            Vertex current = findMinVertex(vList);
            if( verbose )
                System.out.println("Current node: " + current.getID());
            if( current.equals(end) ) {
                // Kürzester Weg für den Zielknoten gefunden
                // Können abbrechen
                if( verbose )
                    System.out.println("   Vertex found; breaking loop");
                break;
            }

            List<Vertex> neighbours = graph.getNeighbours(current);
            neighbours.toFirst();
            while( neighbours.hasAccess() ) {
                Vertex v = neighbours.getContent();
                Edge e = graph.getEdge(current, v);

                if( verbose )
                    System.out.println("   Checking neighbour: " + v.getID());

                if( current.getValue() + e.getWeight() < v.getValue() ) {
                    // Es wurde ein kürzerer Weg gefunden
                    // Der Knoten wird aktualisiert
                    if( verbose ) {
                        if (v.getValue() == Integer.MAX_VALUE) {
                            System.out.printf("      Shorter path found: %.2f < Infinity\n", current.getValue() + e.getWeight(), v.getValue());
                        } else {
                            System.out.printf("      Shorter path found: %.2f < %.2f\n", current.getValue() + e.getWeight(), v.getValue());
                        }
                    }

                    v.setValue(current.getValue() + e.getWeight());
                    v.setPredecessor(current);
                } else {
                    // Kein kürzerer Weg gefunden
                    if( verbose )
                        System.out.printf("      No update: %.2f <= %.2f\n", v.getValue(), current.getValue() + e.getWeight());
                }
                if( !v.isMarked() ) {
                    // Der Knoten muss noch in die Liste einsortiert werden.
                    v.setMark(true);
                    vList.append(v);
                    if( verbose )
                        System.out.println("      Added node to list.");
                }

                neighbours.next();
            }
        }

        return generatePath(end);
    }

    /**
     * Sucht den Vertex mit dem geringsten Wert aus der Liste, entfernt ihn
     * und gibt den Vertex zurück.
     * @param pList
     * @return
     */
    private Vertex findMinVertex( List<Vertex> pList ) {
        // Knoten mit kleinstem Wert suchen
        pList.toFirst();
        Vertex min = pList.getContent();
        pList.next();
        while( pList.hasAccess() ) {
            if (pList.getContent().getValue() < min.getValue() ) {
                min = pList.getContent();
            }
            pList.next();
        }
        // Knoten wieder suchen und entfernen
        pList.toFirst();
        while( !pList.getContent().equals(min) ) {
            pList.next();
        }
        pList.remove();
        // Fertig
        return min;
    }

    /**
     * Erzeugt <strong>nach</strong> Ablauf des Algorithmus den Weg zu einem
     * Knoten aus den {@link Vertex#getPredecessor() predecessor} Attributen
     * der Knoten.
     *
     * @param pVertex
     * @return
     */
    public List<Vertex> generatePath( Vertex pVertex ) {
        List<Vertex> shortestpath = new List<>();
        double length = 0.0;
        Vertex current = pVertex;
        while( current != null ) {
            shortestpath.insert(current);
            shortestpath.toFirst();
            current = current.getPredecessor();
        }

        return shortestpath;
    }

    /**
     * Gibt einen Pfad aus, der in Form einer Liste von Knoten vorliegt. Es
     * wird davon ausgegangen, dass die Knoten einen zusammenhängenden Pfad
     * darstellen. Ob jeweils Kanten zwischen den Folgeknoten vorliegen wird
     * nicht geprüft.
     * @param pPath
     */
    public void printPath( List<Vertex> pPath ) {
        double length = 0.0;

        pPath.toFirst();
        while(pPath.hasAccess()) {
            System.out.println("- " + pPath.getContent().getID());
            length = pPath.getContent().getValue();
            pPath.next();
        }

        System.out.printf("Length of shortest path: %.2f\n", length);
    }

    public void createGraphExample1() {
        graph = new Graph();

        String[] ids = new String[]{
            "Münster", "Hamm", "Dülmen",
            "Haltern", "Lüdinghsn", "Werne", "Lünen", "Kamen",
            "y", "x", "3", "79", "80"
        };
        Vertex[] v = new Vertex[ids.length];
        for( int i = 0; i < ids.length; i++ ) {
            v[i] = new Vertex(ids[i]);
            graph.addVertex(v[i]);
        }

        graph.addEdge(new Edge(v[0], v[1], 40.0));
        graph.addEdge(new Edge(v[0], v[11], 24.0));
        graph.addEdge(new Edge(v[0], v[5], 37.0));
        graph.addEdge(new Edge(v[0], v[9], 8.0));
        graph.addEdge(new Edge(v[0], v[8], 13.0));

        graph.addEdge(new Edge(v[1], v[7], 17.0));
        graph.addEdge(new Edge(v[1], v[6], 24.0));
        graph.addEdge(new Edge(v[1], v[11], 24.0));

        graph.addEdge(new Edge(v[2], v[8], 31.0));
        graph.addEdge(new Edge(v[2], v[3], 12.0));

        graph.addEdge(new Edge(v[3], v[4], 20.0));

        graph.addEdge(new Edge(v[4], v[10], 16.0));
        graph.addEdge(new Edge(v[4], v[11], 15.0));
        graph.addEdge(new Edge(v[4], v[6], 21.0));

        graph.addEdge(new Edge(v[5], v[12], 5.0));
        graph.addEdge(new Edge(v[5], v[6], 10.0));

        graph.addEdge(new Edge(v[6], v[8], 12.0));

        graph.addEdge(new Edge(v[8], v[10], 1.0));

        graph.addEdge(new Edge(v[9], v[10], 4.0));
        graph.addEdge(new Edge(v[9], v[11], 15.0));

        graph.addEdge(new Edge(v[11], v[12], 11.0));
    }




}
