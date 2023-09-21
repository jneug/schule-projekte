
/**
 * Implementierung der Tiefensuche auf einem ungereichteten, gewichteten
 * Graphen.
 */
public class Breitensuche {

    public static void main( String[] args ) {
        Breitensuche b = new Breitensuche();
        b.findVertex("Bloom");
    }

    private Graph<Film> g;

    public Breitensuche() {
        g = new Graph<>();

        // Aufbau des Graphen
        // Erstellen der Knoten (Vertices)
        Vertex<Film> koe = g.addVertex(new Film("Foo"));
        Vertex<Film> dues = g.addVertex(new Film("Bar"));
        Vertex<Film> dor = g.addVertex(new Film("Baz"));
        Vertex<Film> bi = g.addVertex(new Film("Broom"));
        Vertex<Film> hnv = g.addVertex(new Film("Froom"));
        Vertex<Film> bo = g.addVertex(new Film("Bloom"));

        // Erstellen der Kanten (Edges)
        g.addEdge(koe, dor, 96.0);
        g.addEdge(dues, dor, 70.0);
        g.addEdge(dor, bo, 22.0);
        g.addEdge(bi, hnv, 113.0);
        g.addEdge(bi, dor, 112.0);
        g.addEdge(bi, dues, 178.0);
        g.addEdge(bo, dues, 52.0);
    }

    public boolean findVertex( String pVertexID ) {
        return findVertex(new Film(pVertexID), g);
    }

    /**
     * Suche nach einem Vertex mit der angegebenen ID mittels der Breitensuche.
     *
     * @param pVertexID
     * @return
     */
    public <T> boolean findVertex( T pVertexID, Graph<T> pGraph ) {
        g.setAllVertexMarks(false); // Markierungen zurücksetzen
        Queue<Vertex<T>> searchQueue = new Queue<>(); // Speicher für zu bearbeitende Knoten erstellen

        // Ersten Knoten markieren und in die Schlange enqueuen
        Vertex<T> startVertex = pGraph.getVertices().getContent();
        startVertex.setMark(true);
        searchQueue.enqueue(startVertex);

        while( !searchQueue.isEmpty() ) {
            Vertex<T> current = searchQueue.front();
            searchQueue.dequeue();
            System.out.printf("%s\n", current.getContent());

            if( current.getContent().equals(pVertexID) ) {
                return true;
            } else {
                List<Vertex<T>> neighbours = pGraph.getNeighbours(current);
                neighbours.toFirst();
                while( neighbours.hasAccess() ) {
                    Vertex<T> v = neighbours.getContent();
                    if( !v.isMarked() ) {
                        v.setMark(true);
                        searchQueue.enqueue(v);
                    }
                    neighbours.next();
                }
            }
        }

        return false;
    }

    /**
     * @param pVertexID
     * @return
     */
    public <T> boolean findVertexByID( T pVertexID, Graph<T> pGraph ) {
        g.setAllVertexMarks(false); // Markierungen zurücksetzen
        Queue<Vertex<T>> searchQueue = new Queue<>(); // Speicher für zu bearbeitende Knoten erstellen

        // Ersten Knoten markieren und in die Schlange enqueuen
        Vertex<T> startVertex = pGraph.getVertices().getContent();
        startVertex.setMark(true);
        searchQueue.enqueue(startVertex);

        while( !searchQueue.isEmpty() ) {
            Vertex<T> current = searchQueue.front();
            searchQueue.dequeue();
            System.out.printf("%s\n", current.getContent());

            if( current.getContent().equals(pVertexID) ) {
                return true;
            } else {
                List<Vertex<T>> neighbours = pGraph.getNeighbours(current);
                while( !neighbours.isEmpty() ) {
                    Vertex<T> v = getVertexFromListByID(neighbours, pGraph);
                    if( !v.isMarked() ) {
                        v.setMark(true);
                        searchQueue.enqueue(v);
                    }
                }
            }
        }

        return false;
    }

    public <T> boolean findVertexByWeight( T pVertexID, Graph<T> pGraph ) {
        g.setAllVertexMarks(false); // Markierungen zurücksetzen
        Queue<Vertex<T>> searchQueue = new Queue<>(); // Speicher für zu bearbeitende Knoten erstellen

        // Ersten Knoten markieren und in die Schlange enqueuen
        Vertex<T> startVertex = pGraph.getVertices().getContent();
        startVertex.setMark(true);
        searchQueue.enqueue(startVertex);

        while( !searchQueue.isEmpty() ) {
            Vertex<T> current = searchQueue.front();
            searchQueue.dequeue();
            System.out.printf("%s\n", current.getContent());

            if( current.getContent().equals(pVertexID) ) {
                return true;
            } else {
                List<Vertex<T>> neighbours = pGraph.getNeighbours(current);
                while( !neighbours.isEmpty() ) {
                    Vertex<T> v = getVertexFromListByWeight(current, neighbours, pGraph);
                    if( !v.isMarked() ) {
                        v.setMark(true);
                        searchQueue.enqueue(v);
                    }
                }
            }
        }
        //*ml
        return false;
    }


    /**
     * Sucht aus einer Liste von Knoten denjenigen, dessen ID alphabetisch als
     * erstes kommt. Der Knoten wird aus der Liste gelöscht und dann
     * zurückgegeben.
     *
     * @param pVertices Liste mit Knotenobjekten.
     * @return
     */
    private <T> Vertex<T> getVertexFromListByID( List<Vertex<T>> pVertices, Graph<T> pGraph ) {
        pVertices.toFirst();
        Vertex<T> v = pVertices.getContent();
        do {
            pVertices.next();

            if( pVertices.hasAccess() &&
                //pVertices.getContent().getContent().compareToIgnoreCase(v.getContent()) < 0
                pVertices.getContent().toString().compareToIgnoreCase(v.toString()) < 0
            ) {
                v = pVertices.getContent();
            }
        } while( pVertices.hasAccess() );

        pVertices.toFirst();
        while( pVertices.hasAccess() ) {
            if( pVertices.getContent().getContent().equals(v.getContent()) ) {
                break;
            }
            pVertices.next();
        }
        pVertices.remove();
        return v;
    }

    /**
     * Sucht aus einer Liste von Knoten denjenigen, dessen Kantengewicht zum
     * Konten {code pCurrentVertex} am geringsten ist. Der Knoten wird aus der
     * Liste gelöscht und dann zurückgegeben. Haben zwei Kanten das gleiche
     * Gewicht, wird der erste in der Liste zurückgegeben.
     *
     * @param pCurrentVertex Der aktuelle Knoten.
     * @param pVertices Liste mit Knotenobjekten.
     * @return Der Knoten mit dem geringsten Kantengewicht zum aktuellen Knoten.
     */
    private <T> Vertex<T> getVertexFromListByWeight( Vertex<T> pCurrentVertex, List<Vertex<T>> pVertices, Graph<T> pGraph ) {
        pVertices.toFirst();
        Vertex<T> v = pVertices.getContent();
        double weight = pGraph.getEdge(pCurrentVertex, v).getWeight();
        do {
            pVertices.next();

            if( pVertices.hasAccess() &&
                pGraph.getEdge(pCurrentVertex, pVertices.getContent()).getWeight() < weight ) {
                v = pVertices.getContent();
            }
        } while( pVertices.hasAccess() );

        pVertices.toFirst();
        while( pVertices.hasAccess() ) {
            if( pVertices.getContent().getContent().equals(v.getContent()) ) {
                break;
            }
            pVertices.next();
        }
        pVertices.remove();
        return v;
    }

}
