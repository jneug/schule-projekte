import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ListDojoTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testAscendingList() {
        ListDojo ld = new ListDojo();
        List<Integer> ascList = ld.ascendingList(100);
        assertNotNull(ascList);

        int i = 0;
        ascList.toFirst();
        while( ascList.hasAccess() ) {
            i += 1;
            assertEquals(i, ascList.getContent().intValue());
            ascList.next();
        }
        assertEquals("ascendingList(100) muss 100 Elemente enthalten.", i, 100);

        ascList = ld.ascendingList(1);
        assertNotNull(ascList);
        ascList.toFirst();
        assertFalse("ascendingList(1) muss ein Element enthalten.", ascList.isEmpty());
        assertEquals(1, ascList.getContent().intValue());
        ascList.next();
        assertFalse(ascList.hasAccess());

        ascList = ld.ascendingList(0);
        assertNotNull(ascList);
        assertTrue("ascendingList(0) soll eine leere Liste ergeben.", ascList.isEmpty());
    }

    @Test
    public void testRandomList() {
        ListDojo ld = new ListDojo();
        List<Integer> randList = ld.randomList(1000, 5000, 6000);
        assertNotNull(randList);

        int i = 0;
        randList.toFirst();
        while( randList.hasAccess() ) {
            int value = randList.getContent().intValue();

            assertTrue(5000 <= value);
            assertTrue(value <= 6000);

            randList.next();
            i += 1;
        }
        assertEquals(i, 1000);


        randList = ld.randomList(1, 1, 1);
        assertNotNull(randList);
        randList.toFirst();
        assertFalse(randList.isEmpty());
        assertEquals(1, randList.getContent().intValue());
        randList.next();
        assertFalse(randList.hasAccess());


        randList = ld.randomList(0, 0, 100);
        assertNotNull(randList);
        assertTrue(randList.isEmpty());
    }

    @Test
    public void testPrintList() {
        List<Integer> intList = new List<>();
        for( int i = 0; i < 100; i++ ) {
            intList.append(i);
        }

        ListDojo ld = new ListDojo();
        ld.printList(intList);

        String[] lines = outContent.toString().split("\n");
        assertEquals("Falsche Anzahl Zahlen.", 100, lines.length);
        assertEquals("0", lines[0]);
        assertEquals("50", lines[50]);
        assertEquals("99", lines[99]);
    }

    @Test
    public void testPrintPokemon() {
        List<Pokemon> pList = new List<>();
        for( int i = 0; i < 100; i++ ) {
            pList.append(new Pokemon("p" + i, 0, 0));
        }

        ListDojo ld = new ListDojo();
        ld.printPokemon(pList);

        String[] lines = outContent.toString().split("\n");
        assertEquals("Falsche Anzahl Pokemon.", 100, lines.length);
        assertEquals("p0", lines[0]);
        assertEquals("p50", lines[50]);
        assertEquals("p99", lines[99]);
    }

    @Test
    public void testSearchInList() {
        List<Integer> intList = new List<>();
        for( int i = 0; i < 100; i++ ) {
            intList.append(i);
        }

        ListDojo ld = new ListDojo();
        for( int i = 0; i < 100; i++ ) {
            assertTrue(ld.searchInList(intList, i));
        }
        assertFalse(ld.searchInList(intList, 150));
    }

    @Test
    public void testSearchMinimum() {
        List<Integer> intList = new List<>();
        intList.append(50);
        intList.append(40);
        intList.append(1);
        intList.append(100);

        ListDojo ld = new ListDojo();
        assertEquals(1, ld.searchMinimum(intList));

        intList = new List<>();
        intList.append(5);
        intList.append(40);
        intList.append(5);
        intList.append(100);
        assertEquals(5, ld.searchMinimum(intList));
    }

    @Test
    public void testInsertIntoList() {
        List<Integer> intList = new List<>();
        for( int i = 0; i < 100; i++ ) {
            intList.append(i);
        }

        ListDojo ld = new ListDojo();
        ld.insertIntoList(intList, 1000, 3);
        intList.toFirst();
        intList.next();
        intList.next();
        assertEquals(1000, intList.getContent().intValue());

        ld.insertIntoList(intList, 9999, 1);
        intList.toFirst();
        assertEquals(9999, intList.getContent().intValue());
    }

    @Test
    public void testDeleteFromList() {
        List<Integer> intList = new List<>();
        for( int i = 0; i < 100; i++ ) {
            intList.append(i);
        }

        ListDojo ld = new ListDojo();

        assertTrue(search(intList, 50));
        ld.deleteFromList(intList, 50);
        assertFalse(search(intList, 50));

        assertTrue(search(intList, 99));
        ld.deleteFromList(intList, 99);
        assertFalse(search(intList, 99));

        assertTrue(search(intList, 0));
        ld.deleteFromList(intList, 0);
        assertFalse(search(intList, 0));


        intList.append(100);
        intList.append(100);
        intList.append(100);

        assertTrue(search(intList, 100));
        ld.deleteFromList(intList, 100);
        assertTrue("Es darf nur das erste Vorkommen einer Zahl gelöscht werden.", search(intList, 100));
        ld.deleteFromList(intList, 100);
        assertTrue("Es darf nur das erste Vorkommen einer Zahl gelöscht werden.", search(intList, 100));
        ld.deleteFromList(intList, 100);
        assertFalse(search(intList, 100));
    }

    @Test
    public void testDeleteMinimum() {
        int[] numbers = new int[]{100,30,40,50,120,30,10,1};

        List<Integer> intList = new List<>();
        for( int i: numbers ) {
            intList.append(i);
        }

        ListDojo ld = new ListDojo();

        ld.deleteMinimum(intList);
        assertFalse(search(intList, 1));

        ld.deleteMinimum(intList);
        assertFalse(search(intList, 10));

        ld.deleteMinimum(intList);
        assertTrue(search(intList, 30));
        ld.deleteMinimum(intList);
        assertFalse(search(intList, 30));
    }

    @Test
    public void testPrintOrdered() {
        List<Integer> intList = new List<>();
        for( int i = 0; i < 100; i++ ) {
            intList.append(100-i-1);
        }

        ListDojo ld = new ListDojo();
        ld.printOrdered(intList);

        String[] lines = outContent.toString().split("\n");
        assertEquals("Falsche Anzahl Zahlen.", 100, lines.length);
        assertEquals("0", lines[0]);
        assertEquals("50", lines[50]);
        assertEquals("99", lines[99]);
    }

    @Test
    public void testSearchInListPokemon() {
        List<Pokemon> pList = new List<>();
        for( int i = 0; i < 100; i++ ) {
            pList.append(new Pokemon("p" + i, 0, 0));
        }

        ListDojo ld = new ListDojo();
        for( int i = 0; i < 100; i++ ) {
            assertNotNull(ld.searchInList(pList, "p" + i));
        }
        assertNull(ld.searchInList(pList, "none"));
    }

    private boolean search( List<Integer> list, int value ) {
        list.toFirst();
        while( list.hasAccess() ) {
            if( value == list.getContent().intValue() ) {
                return true;
            }
            list.next();
        }
        return false;
    }

}
