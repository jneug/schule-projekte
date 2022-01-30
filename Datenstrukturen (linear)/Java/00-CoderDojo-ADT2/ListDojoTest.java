import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Test-Klasse für das ListDojo.
 * <p>
 * Starte die Tests mit einem Rechtsklick auf die Klasse im
 * BlueJ Projektfenster und durch Auswahl der passenden
 * Testmethode. Du kannst alle Tests in der linken Seitenleiste
 * starten, indem du auf den kleinen Pfeil klickst und "Tests
 * starten" wählst.
 *
 * <b>Achtung:</b> Verändere diese Klasse nicht! Du brauchst dir
 * diesen Quelltext auch nicht ansehen. Es reicht, die Tests zur
 * Überprüfung deiner Lösungen zu nutzen.
 */
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
        assertNotNull("Es darf nicht <null> zurückgegeben werden", ascList);

        int i = 0;
        ascList.toFirst();
        while( ascList.hasAccess() ) {
            i += 1;
            assertEquals("ascendingList(100): Falsche Elemente in der Liste.", i, ascList.getContent().intValue());
            ascList.next();
        }
        assertEquals("ascendingList(100): Liste muss 100 Elemente enthalten.", i, 100);

        ascList = ld.ascendingList(1);
        assertNotNull("Es darf nicht <null> zurückgegeben werden", ascList);
        ascList.toFirst();
        assertFalse("ascendingList(1): Liste muss genau ein Element enthalten.", ascList.isEmpty());
        assertEquals(1, ascList.getContent().intValue());
        ascList.next();
        assertFalse("ascendingList(1): Liste muss genau ein Element enthalten.", ascList.hasAccess());

        ascList = ld.ascendingList(0);
        assertNotNull("Es darf nicht <null> zurückgegeben werden", ascList);
        assertTrue("ascendingList(0): Liste muss leer sein.", ascList.isEmpty());
    }

    @Test
    public void testRandomList() {
        ListDojo ld = new ListDojo();
        List<Integer> randList = ld.randomList(1000, 5000, 6000);
        assertNotNull("Es darf nicht <null> zurückgegeben werden", randList);

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
        assertNotNull("Es darf nicht <null> zurückgegeben werden.", randList);
        randList.toFirst();
        assertFalse("randomList(1, 1, 1):  Liste darf nicht leer sein.", randList.isEmpty());
        assertEquals("randomList(1, 1, 1):  Liste darf nur <1> enthalten.", 1, randList.getContent().intValue());
        randList.next();
        assertFalse(randList.hasAccess());

        randList = ld.randomList(10, 100, 101);
        assertNotNull("Es darf nicht <null> zurückgegeben werden.", randList);
        assertFalse("randomList(10, 100, 101): Darf nicht leer sein.", randList.isEmpty());
        randList.toFirst();
        while( randList.hasAccess() ) {
            int value = randList.getContent().intValue();
            assertTrue("randList(10, 100, 101): Liste darf nur <100> und <101> enthalten.", value == 100 || value == 101);
            randList.next();
        }

        randList = ld.randomList(0, 0, 100);
        assertNotNull("Es darf nicht <null> zurückgegeben werden", randList);
        assertTrue("randomList(0, 0, 100): Für pCount == 0 muss die Liste leer sein.", randList.isEmpty());

        randList = ld.randomList(-10, 0, 500);
        assertNotNull("Es darf nicht <null> zurückgegeben werden", randList);
        assertTrue("randomList(-10, 0, 500): Für pCount < 0 muss die Liste leer sein.", randList.isEmpty());

        randList = ld.randomList(100, 1000, 500);
        assertNotNull("Es darf nicht <null> zurückgegeben werden", randList);
        assertTrue("randomList(100, 1000, 500): Für pMin > pMax muss die Liste leer sein.", randList.isEmpty());
    }

    @Test
    public void testPrintList() {
        List<Integer> intList = new List<>();
        for( int i = 0; i < 100; i++ ) {
            intList.append(i);
        }

        ListDojo ld = new ListDojo();
        ld.printList(intList);

        String[] lines = outContent.toString().split(System.lineSeparator());
        assertEquals("printList([0,1,2,...,99]): Falsche Anzahl Zahlen ausgegeben.", 100, lines.length);
        assertEquals("printList([0,1,2,...,99]): Falsche erstes Element ausgegeben.", "0", lines[0]);
        assertEquals("printList([0,1,2,...,99]): Falsche 50-tes Element ausgegeben.", "50", lines[50]);
        assertEquals("printList([0,1,2,...,99]): Falsche letztes Element ausgegeben.", "99", lines[99]);
    }

    @Test
    public void testPrintPokemon() {
        List<Pokemon> pList = new List<>();
        for( int i = 0; i < 100; i++ ) {
            pList.append(new Pokemon("p" + i, 0, 0));
        }

        ListDojo ld = new ListDojo();
        ld.printPokemon(pList);

        String[] lines = outContent.toString().split(System.lineSeparator());
        assertEquals("printPokemon([p0,p1,p2,...,p99]): Falsche Anzahl Pokemon ausgeben.", 100, lines.length);
        assertEquals("printPokemon([p0,p1,p2,...,p99]): Falsches erstes Pokemon ausgegeben.", "p0", lines[0]);
        assertEquals("printPokemon([p0,p1,p2,...,p99]): Falsches 50-tes Pokemon ausgegeben.", "p50", lines[50]);
        assertEquals("printPokemon([p0,p1,p2,...,p99]): Falsches letztes Pokemon ausgegeben.", "p99", lines[99]);
    }

    @Test
    public void testSearchInList() {
        List<Integer> intList = new List<>();
        for( int i = 0; i < 100; i++ ) {
            intList.append(i);
        }

        ListDojo ld = new ListDojo();
        for( int i = 0; i < 100; i++ ) {
            assertTrue("searchInList([0,1,2,...,99], " + i + "): Sollte <true> sein.", ld.searchInList(intList, i));
        }
        assertFalse("searchInList([0,1,2,...,99], 150): Sollte <false> sein.", ld.searchInList(intList, 150));
    }

    @Test
    public void testSearchMinimum() {
        List<Integer> intList = new List<>();
        intList.append(50);
        intList.append(40);
        intList.append(1);
        intList.append(100);

        ListDojo ld = new ListDojo();
        assertEquals("searchMinimum([50,40,1,100])", 1, ld.searchMinimum(intList));

        intList = new List<>();
        intList.append(5);
        intList.append(40);
        intList.append(5);
        intList.append(100);
        assertEquals("searchMinimum([5,40,5,100])", 5, ld.searchMinimum(intList));

        intList = new List<>();
        intList.append(-5);
        intList.append(40);
        intList.append(5);
        intList.append(100);
        assertEquals("searchMinimum([-5,40,5,100])", -5, ld.searchMinimum(intList));

        intList = new List<>();
        assertEquals("searchMinimum([])", Integer.MAX_VALUE, ld.searchMinimum(intList));
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
        assertEquals("insertIntoList([0,1,2,...,99], 1000, 3): Erstes Element", 0, intList.getContent().intValue());
        intList.next();
        assertEquals("insertIntoList([0,1,2,...,99], 1000, 3): Zweites Element", 1, intList.getContent().intValue());
        intList.next();
        assertEquals("insertIntoList([0,1,2,...,99], 1000, 3): Drittes Element", 1000, intList.getContent().intValue());

        ld.insertIntoList(intList, 9999, 1);
        intList.toFirst();
        assertEquals("insertIntoList([0,1,1000,...,99], 9999, 1): Erstes Element", 9999, intList.getContent().intValue());

        intList = new List<>();
        intList.append(100);
        ld.insertIntoList(intList, 900, 3);
        intList.toFirst();
        assertTrue("insertIntoList([100], 900, 3): Liste muss zwei Elemente enthalten.", intList.hasAccess());
        assertEquals("insertIntoList([100], 900, 3): Erstes Element", 100, intList.getContent().intValue());
        intList.next();
        assertTrue("insertIntoList([100], 900, 3): Liste muss zwei Elemente enthalten.", intList.hasAccess());
        assertEquals("insertIntoList([100], 900, 3): Zweites Element", 900, intList.getContent().intValue());

        ld.insertIntoList(intList, 909, 3);
        intList.toFirst();
        intList.next();
        intList.next();
        assertTrue("insertIntoList([100], 900, 3): Liste muss drei Elemente enthalten.", intList.hasAccess());
        assertEquals("insertIntoList([100,900], 909, 3): Drittes Element", 909, intList.getContent().intValue());

        ld.insertIntoList(intList, 999, 3);
        intList.toFirst();
        intList.next();
        intList.next();
        assertTrue("insertIntoList([100], 900, 3): Liste muss vier Elemente enthalten.", intList.hasAccess());
        assertEquals("insertIntoList([100,900,909], 999, 3): Drittes Element", 999, intList.getContent().intValue());
        intList.next();
        assertTrue("insertIntoList([100], 900, 3): Liste muss vier Elemente enthalten.", intList.hasAccess());
        assertEquals("insertIntoList([100,900,909], 999, 3): Viertes Element", 909, intList.getContent().intValue());

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
        assertFalse("deleteFromList([0,1,2,...,99], 50): <50> darf nicht mehr in Liste vorkommen.", search(intList, 50));

        assertTrue(search(intList, 99));
        ld.deleteFromList(intList, 99);
        assertFalse("deleteFromList([0,1,2,...,49,51,...,99], 99): <99> darf nicht mehr in Liste vorkommen.", search(intList, 99));

        assertTrue(search(intList, 0));
        ld.deleteFromList(intList, 0);
        assertFalse("deleteFromList([0,1,2,...,49,51,...,98], 0): <0> darf nicht mehr in Liste vorkommen.", search(intList, 0));

        intList.append(100);
        intList.append(100);
        intList.append(100);

        assertTrue(search(intList, 100));
        ld.deleteFromList(intList, 100);
        assertTrue("deleteFromList([0,1,2,...,100,100,100], 100): Es darf nur das erste Vorkommen einer Zahl gelöscht werden.", search(intList, 100));
        ld.deleteFromList(intList, 100);
        assertTrue("deleteFromList([0,1,2,...,100,100], 100): Es darf nur das erste Vorkommen einer Zahl gelöscht werden.", search(intList, 100));
        ld.deleteFromList(intList, 100);
        assertFalse("deleteFromList([0,1,2,...,100], 100): <100> darf nicht mehr in Liste vorkommen.", search(intList, 100));
        ld.deleteFromList(intList, 100);
        assertFalse("deleteFromList([0,1,2,...,98], 100): Die Liste darf nicht verändert werden.", search(intList, 100));

        intList = new List<>();
        ld.deleteFromList(intList, 100);
        assertTrue("deleteFromList([], 100): Die Liste darf nicht verändert werden.", intList.isEmpty());
    }

    @Test
    public void testDeleteMinimum() {
        int[] numbers = new int[]{100, 30, 40, 50, 120, 30, 10, 1};

        List<Integer> intList = new List<>();
        for( int i : numbers ) {
            intList.append(i);
        }

        ListDojo ld = new ListDojo();

        ld.deleteMinimum(intList);
        assertFalse("deleteMinimum([100,30,40,50,120,30,10,1]): <1> darf nicht mehr vorkommen.", search(intList, 1));

        ld.deleteMinimum(intList);
        assertFalse("deleteMinimum([100,30,40,50,120,30,10]): <10> darf nicht mehr vorkommen.", search(intList, 10));

        ld.deleteMinimum(intList);
        assertTrue("deleteMinimum([100,30,40,50,120,30]): <30> muss noch einmal vorkommen.", search(intList, 30));
        ld.deleteMinimum(intList);
        assertFalse("deleteMinimum([100,40,50,120,30]): <30> darf nicht mehr vorkommen.", search(intList, 30));

        intList = new List<>();
        ld.deleteMinimum(intList);
        assertTrue("deleteMinimum([]): Die Liste darf nicht verändert werden.", intList.isEmpty());
    }

    @Test
    public void testPrintOrdered() {
        List<Integer> intList = new List<>();
        for( int i = 0; i < 100; i++ ) {
            intList.append(100 - i - 1);
        }

        ListDojo ld = new ListDojo();
        ld.printOrdered(intList);

        String[] lines = outContent.toString().split(System.lineSeparator());
        assertEquals("printOrdered([99,98,97,...,0]): Falsche Anzahl Zahlen ausgegeben.", 100, lines.length);
        assertEquals("printOrdered([99,98,97,...,0]): Falsche erstes Element ausgegeben.", "0", lines[0]);
        assertEquals("printOrdered([99,98,97,...,0]): Falsche 50-tes Element ausgegeben.", "50", lines[50]);
        assertEquals("printOrdered([99,98,97,...,0]): Falsche letztes Element ausgegeben.", "99", lines[99]);
    }

    @Test
    public void testSearchInListPokemon() {
        List<Pokemon> pList = new List<>();
        for( int i = 0; i < 100; i++ ) {
            pList.append(new Pokemon("p" + i, 0, 0));
        }

        ListDojo ld = new ListDojo();
        for( int i = 0; i < 100; i++ ) {
            assertNotNull("searchInList([p0,p1,p2,...,p99], p" + i + "): Darf nicht <null> sein.", ld.searchInList(pList, "p" + i));
        }
        assertNull("searchInList([p0,p1,p2,...,p99], none): Muss <null> sein.", ld.searchInList(pList, "none"));
    }

    // Hilfsmethode, um eine Zahl in einer Liste zu suchen.
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
