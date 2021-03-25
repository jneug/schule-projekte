import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TiefensucheTest {

    @Before
    public void setUp() {

    }

    @Test
    public void testFindVertex() {
        Tiefensuche ts = new Tiefensuche();

        assertTrue("Der Knoten Köln ist im Graphen vorhanden.", ts.findVertex("Köln"));
        assertTrue("Der Knoten Bielefeld ist im Graphen vorhanden.", ts.findVertex("Bielefeld"));
        assertTrue("Der Knoten Bochum ist im Graphen vorhanden.", ts.findVertex("Bochum"));

        assertFalse("Der Knoten Tokio ist nicht im Graphen vorhanden.", ts.findVertex("Tokio"));
        assertFalse("Der Knoten London ist nicht im Graphen vorhanden.", ts.findVertex("London"));
    }

}
