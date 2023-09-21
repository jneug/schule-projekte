import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphDojoTest {

    private GraphDojo dojo;

    private Graph graph;

    @Before
    public void setUp() throws Exception {
        graph = new Graph();

        for( int i = 0; i < 24; i++ ) {
            graph.addVertex(new Vertex("" + i));
        }

        for( int i = 0; i < 24; i += 1 ) {
            graph.addEdge(new Edge(
                graph.getVertex("" + i),
                graph.getVertex("" + ((i + 1) * 3) % 24),
                (double) (i + ((i + 1) * 3) % 24) / 2.0
            ));
        }

        graph.addEdge(new Edge(
            graph.getVertex("1"),
            graph.getVertex("4"),
            2.5
        ));

        dojo = new GraphDojo();
    }

    @Test
    public void listDepthFirst() {
        List<String> vertices = dojo.listDepthFirst(graph, "0");

        String[] expected = new String[]{
            "0", "3", "12", "11", "15", "4", "1", "6", "21", "14", "18", "5",
            "13", "9", "2", "10", "22", "17", "20", "19", "8", "16", "7", "23"
        };
        int i = 0;

        String expectedStr = join(expected);
        String resultStr = join(vertices);

        vertices.toFirst();
        while( vertices.hasAccess() ) {
            assertEquals(
                "\nErwartete Reihenfolge: <" + expectedStr + ">\n" +
                    "Erhaltene Reihenfolge: <" + resultStr + ">" +
                    "Fehler an Index " + i + ":",
                expected[i], vertices.getContent()
            );
            vertices.next();
            i += 1;
        }
    }

    @Test
    public void listBreadthFirst() {
        List<String> vertices = dojo.listBreadthFirst(graph, "0");

        String[] expected = new String[]{
            "0", "3", "7", "15", "23", "12", "8", "16", "4", "20", "11", "19",
            "1", "6", "21", "9", "17", "14", "18", "22", "2", "10", "5", "13"
        };
        int i = 0;


        String expectedStr = join(expected);
        String resultStr = join(vertices);

        vertices.toFirst();
        while( vertices.hasAccess() ) {
            assertEquals(
                "\nErwartete Reihenfolge: <" + expectedStr + ">\n" +
                    "Erhaltene Reihenfolge: <" + resultStr + ">\n" +
                    "Fehler an Index " + i + ":",
                expected[i], vertices.getContent()
            );
            vertices.next();
            i += 1;
        }
    }

    private static String join( String[] pStrings ) {
        return String.join(", ", pStrings);
    }

    private static String join( List<String> pStrings ) {
        if( pStrings.isEmpty() ) {
            return "";
        }

        pStrings.toFirst();
        String result = pStrings.getContent();
        pStrings.next();

        while( pStrings.hasAccess() ) {
            result += ", " + pStrings.getContent();
            pStrings.next();
        }

        return result;
    }

}
