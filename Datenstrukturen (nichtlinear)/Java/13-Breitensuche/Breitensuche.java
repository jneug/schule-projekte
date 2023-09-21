
/**
 * Implementierung der Tiefensuche auf einem ungereichteten, gewichteten
 * Graphen.
 */
public class Breitensuche {

    private Graph g;

    public Breitensuche() {
        g = new Graph();

        // Aufbau des Graphen
        // Erstellen der Knoten (Vertices)
        Vertex koe = new Vertex("Köln");
        g.addVertex(koe);
        Vertex dues = new Vertex("Düsseldorf");
        g.addVertex(dues);
        Vertex dor = new Vertex("Dortmund");
        g.addVertex(dor);
        Vertex bi = new Vertex("Bielefeld");
        g.addVertex(bi);
        Vertex hnv = new Vertex("Hannover");
        g.addVertex(hnv);
        Vertex bo = new Vertex("Bochum");
        g.addVertex(bo);

        // Erstellen der Kanten (Edges)
        g.addEdge(new Edge(koe, dor, 96.0));
        g.addEdge(new Edge(dues, dor, 70.0));
        g.addEdge(new Edge(dor, bo, 22.0));
        g.addEdge(new Edge(bi, hnv, 113.0));
        g.addEdge(new Edge(bi, dor, 112.0));
        g.addEdge(new Edge(bi, dues, 178.0));
        g.addEdge(new Edge(bo, dues, 52.0));
    }

    /**
     * Suche nach einem Vertex mit der angegebenen ID mittels der Breitensuche.
     *
     * @param pVertexID
     * @return
     */
    public boolean findVertex( String pVertexID ) {
        g.setAllVertexMarks(false); // Markierungen zurücksetzen
        Queue<Vertex> searchQueue = new Queue<>(); // Speicher für zu bearbeitende Knoten erstellen

        /*aufg*
        // TODO: Implementiere die Breitensuche als iterativen Algorithmus:
        //       Reihe dazu die noch nicht markierten Nachbarknoten in die
        //       searchQueue ein und arbeite die Knoten in ihr der Reihe nach
        //       ab, bis die Queue leer ist, oder der gesuchte Knoten
        //       gefunden wurde.
        // TODO: Ergänze deine Methode um Ausgaben, anhand derer die Abarbeitung
        //       deutlich wird.
        // TODO: Wenn dein Algorithmus funnktioniert, kopiere die Methode und
        //       Erstelle Varianten, bei denen die Reihenfolge der Nachbarknoten
        //       modifiziert ist.
        *aufg*/
        //ml*
        // Ersten Knoten markieren und in die Schlange enqueuen
        Vertex startVertex = g.getVertices().getContent();
        startVertex.setMark(true);
        searchQueue.enqueue(startVertex);

        while( !searchQueue.isEmpty() ) {
            Vertex current = searchQueue.front();
            searchQueue.dequeue();
            System.out.printf("%s\n", current.getID());

            if( current.getID().equals(pVertexID) ) {
                return true;
            } else {
                List<Vertex> neighbours = g.getNeighbours(current);
                neighbours.toFirst();
                while( neighbours.hasAccess() ) {
                    Vertex v = neighbours.getContent();
                    if( !v.isMarked() ) {
                        v.setMark(true);
                        searchQueue.enqueue(v);
                    }
                    neighbours.next();
                }
            }
        }
        //*ml
        return false;
    }

    /**
     * @param pVertexID
     * @return
     */
    public boolean findVertexByID( String pVertexID ) {
        g.setAllVertexMarks(false); // Markierungen zurücksetzen
        Queue<Vertex> searchQueue = new Queue<>(); // Speicher für zu bearbeitende Knoten erstellen

        /*aufg*
        // TODO: Ändere die Breitensuche so ab, dass die Knoten so abgearbeitet
        //       werden, dass die Nachbarknoten in alphabetischer Reihenfolge
        //       besucht werden.
        //       Nutze dazu die Hilfsmethode getVertexFromListByID, um aus
        //       Liste der Nachbarknoten denjenigen, der alphabetisch als
        //       erstes kommt, herauszusuchen.
        *aufg*/
        //ml*
        // Ersten Knoten markieren und in die Schlange enqueuen
        Vertex startVertex = g.getVertices().getContent();
        startVertex.setMark(true);
        searchQueue.enqueue(startVertex);

        while( !searchQueue.isEmpty() ) {
            Vertex current = searchQueue.front();
            searchQueue.dequeue();
            System.out.printf("%s\n", current.getID());

            if( current.getID().equals(pVertexID) ) {
                return true;
            } else {
                List<Vertex> neighbours = g.getNeighbours(current);
                while( !neighbours.isEmpty() ) {
                    Vertex v = getVertexFromListByID(neighbours);
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

    public boolean findVertexByWeight( String pVertexID ) {
        g.setAllVertexMarks(false); // Markierungen zurücksetzen
        Queue<Vertex> searchQueue = new Queue<>(); // Speicher für zu bearbeitende Knoten erstellen

        /*aufg*
        // TODO: Ändere die Breitensuche so ab, dass die Knoten so abgearbeitet
        //       werden, dass der Nachbarknoten mit der Kante mit dem geringsten
        //       Gewicht zuerst besucht wird.
        //       Nutze dazu die Hilfsmethode getVertexFromListByWeight, um aus
        //       Liste der Nachbarknoten denjenigen mit dem geringsten Gewicht
        //       herauszusuchen.
        *aufg*/
        //ml*
        // Ersten Knoten markieren und in die Schlange enqueuen
        Vertex startVertex = g.getVertices().getContent();
        startVertex.setMark(true);
        searchQueue.enqueue(startVertex);

        while( !searchQueue.isEmpty() ) {
            Vertex current = searchQueue.front();
            searchQueue.dequeue();
            System.out.printf("%s\n", current.getID());

            if( current.getID().equals(pVertexID) ) {
                return true;
            } else {
                List<Vertex> neighbours = g.getNeighbours(current);
                while( !neighbours.isEmpty() ) {
                    Vertex v = getVertexFromListByWeight(current, neighbours);
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
    private Vertex getVertexFromListByID( List<Vertex> pVertices ) {
        pVertices.toFirst();
        Vertex v = pVertices.getContent();
        do {
            pVertices.next();

            if( pVertices.hasAccess() &&
                pVertices.getContent().getID().compareToIgnoreCase(v.getID()) < 0 ) {
                v = pVertices.getContent();
            }
        } while( pVertices.hasAccess() );

        pVertices.toFirst();
        while( pVertices.hasAccess() ) {
            if( pVertices.getContent().getID().equals(v.getID()) ) {
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
    private Vertex getVertexFromListByWeight( Vertex pCurrentVertex, List<Vertex> pVertices ) {
        pVertices.toFirst();
        Vertex v = pVertices.getContent();
        double weight = g.getEdge(pCurrentVertex, v).getWeight();
        do {
            pVertices.next();

            if( pVertices.hasAccess() &&
                g.getEdge(pCurrentVertex, pVertices.getContent()).getWeight() < weight ) {
                v = pVertices.getContent();
            }
        } while( pVertices.hasAccess() );

        pVertices.toFirst();
        while( pVertices.hasAccess() ) {
            if( pVertices.getContent().getID().equals(v.getID()) ) {
                break;
            }
            pVertices.next();
        }
        pVertices.remove();
        return v;
    }

}
