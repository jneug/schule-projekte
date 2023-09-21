public class GraphDojo {

    public GraphDojo() {

    }


    public List<String> listDepthFirst( Graph pGraph, String startID ) {
        /*aufg*
        return null;
        *aufg*/
        //ml*
        pGraph.setAllVertexMarks(false);
        Vertex startVertex = pGraph.getVertex(startID);

        return listDepthFirstRec(pGraph, startVertex);
        //*ml
    }
    //ml*

    private List<String> listDepthFirstRec( Graph pGraph, Vertex pVertex ) {
        pVertex.setMark(true);

        List<String> vertices = new List<>();
        vertices.append(pVertex.getID());

        List<Vertex> neighbours = pGraph.getNeighbours(pVertex);
        neighbours.toFirst();
        while( neighbours.hasAccess() ) {
            if( !neighbours.getContent().isMarked() ) {
                vertices.concat(
                    listDepthFirstRec(pGraph, neighbours.getContent())
                );
            }
            neighbours.next();
        }

        return vertices;
    }
    //*ml

    public List<String> listBreadthFirst( Graph pGraph, String startID ) {
        /*aufg*
        return null;
        *aufg*/
        //ml*
        Queue<Vertex> q = new Queue<>();

        pGraph.setAllVertexMarks(false);
        Vertex startVertex = pGraph.getVertex(startID);
        startVertex.setMark(true);
        q.enqueue(startVertex);

        List<String> vertices = new List<>();

        while( !q.isEmpty() ) {
            Vertex current = q.front();
            q.dequeue();

            vertices.append(current.getID());

            List<Vertex> neighbours = pGraph.getNeighbours(current);
            neighbours.toFirst();
            while( neighbours.hasAccess() ) {
                Vertex v = neighbours.getContent();
                if( !v.isMarked() ) {
                    v.setMark(true);
                    q.enqueue(v);
                }
                neighbours.next();
            }
        }

        return vertices;
        //*ml
    }

}
