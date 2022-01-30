import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test-Klasse für das CoderDojo.
 *
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
public class CoderDojoTest {

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
    public void testMax2() {
        CoderDojo cd = new CoderDojo();

        assertEquals(4, cd.max(4, 1));
        assertEquals(4, cd.max(4, 3));
        assertEquals(4, cd.max(4, 4));
        assertEquals(5, cd.max(4, 5));
        assertEquals(8, cd.max(4, 8));


        assertEquals(4, cd.max(4, -1));
        assertEquals(4, cd.max(-1, 4));
    }

    @Test
    public void testMax3() {
        CoderDojo cd = new CoderDojo();

        assertEquals(4, cd.max(4, 1, 2));
        assertEquals(4, cd.max(4, 1, 3));
        assertEquals(4, cd.max(4, 1, 4));
        assertEquals(4, cd.max(4, 1, 1));
        assertEquals(4, cd.max(4, 3, 1));
        assertEquals(4, cd.max(4, 4, 1));
        assertEquals(4, cd.max(4, 1, 4));
        assertEquals(4, cd.max(1, 3, 4));
        assertEquals(4, cd.max(3, 1, 4));
        assertEquals(4, cd.max(4, 4, 4));
        assertEquals(5, cd.max(4, 5, 1));
        assertEquals(5, cd.max(4, 5, 4));
        assertEquals(6, cd.max(4, 5, 6));
        assertEquals(8, cd.max(4, 8, 6));
        assertEquals(10, cd.max(4, 8, 10));


        assertEquals(4, cd.max(4, -1, 2));
        assertEquals(4, cd.max(-1, 4, -1));
        assertEquals(4, cd.max(-1, -1, 4));
        assertEquals(4, cd.max(-1, 2, 4));
        assertEquals(4, cd.max(2, -1, 4));
    }

    @Test
    public void testSleepIn() {
        CoderDojo cd = new CoderDojo();

        assertTrue("Am Wochenende im Urlaub ausschlafen.", cd.sleepIn(false, true));
        assertTrue("An Wochentagen im Urlaub ausschlafen.", cd.sleepIn(true, true));
        assertTrue("Am Wochenende ohne Urlaub ausschlafen.", cd.sleepIn(false, false));
        assertFalse("An Wochentagen ohne Urlaub nicht ausschlafen.", cd.sleepIn(true, false));
    }

    @Test
    public void testMakes10() {
        CoderDojo cd = new CoderDojo();

        assertTrue("makes10(10,10)", cd.makes10(10, 10));
        assertTrue("makes10(10,0)", cd.makes10(10, 0));
        assertTrue("makes10(0,10)", cd.makes10(0, 10));
        assertTrue("makes10(5,5)", cd.makes10(5, 5));
        assertTrue("makes10(8,2)", cd.makes10(8, 2));
        assertTrue("makes10(11,-1)", cd.makes10(11, -1));

        assertFalse("makes10(11,1)", cd.makes10(11, 1));
        assertFalse("makes10(4,3)", cd.makes10(4, 3));
    }

        @Test
    public void testIsEven() {
        CoderDojo cd = new CoderDojo();

        assertTrue("0 is even", cd.isEven(0));
        assertTrue("2 is even", cd.isEven(2));
        assertTrue("4 is even", cd.isEven(4));
        assertTrue("24 is even", cd.isEven(24));
        assertTrue("12226 is even", cd.isEven(12226));

        assertFalse("1 is not even", cd.isEven(1));
        assertFalse("3 is not even", cd.isEven(3));
        assertFalse("1443 is not even", cd.isEven(1443));
        assertFalse("7838675 is not even", cd.isEven(7838675));
    }

    @Test
    public void testThreeOrFive() {
        CoderDojo cd = new CoderDojo();

        for( int i: new int[]{3,5,9,15,20,25,125,300} ) {
            assertTrue(i+" is divisable by 3 or 5", cd.threeOrFive(i));
        }

        for( int i: new int[]{1,8,13,22,73} ) {
            assertFalse(i+" is not divisable by 3 or 5", cd.threeOrFive(i));
        }
    }

    @Test
    public void testThreeAndFive() {
        CoderDojo cd = new CoderDojo();

        for( int i: new int[]{15,30,45,60,90,120} ) {
            assertTrue(i+" is divisable by 3 and 5", cd.threeAndFive(i));
        }

        for( int i: new int[]{3,5,25,33,35,91,110,4321} ) {
            assertFalse(i+" is not divisable by 3 and 5", cd.threeAndFive(i));
        }
    }

    @Test
    public void testPiffPaff() {
        CoderDojo cd = new CoderDojo();

        int n, j;
        n = cd.piffPaff(20);
        assertEquals(1, n);

        j = ( outContent.toString().split("piff").length ) - 1;
        assertEquals("Falsche Anzahl piffs", 6, j);
        j = ( outContent.toString().split("paff").length ) - 1;
        assertEquals("Falsche Anzahl paffs", 4, j);

        outContent.reset();

        n = cd.piffPaff(100);
        assertEquals(6, n);

        j = ( outContent.toString().split("piff").length ) - 1;
        assertEquals("Falsche Anzahl piffs", 33, j);
        j = ( outContent.toString().split("paff").length ) - 1;
        assertEquals("Falsche Anzahl paffs", 20, j);
    }

    @Test
    public void testMakeTag() {
        CoderDojo cd = new CoderDojo();

        assertEquals("makeTag(\"Yay\", \"i\")", "<i>Yay</i>", cd.makeTag("Yay", "i"));
        assertEquals("makeTag(\"Hallo, Welt\", \"h1\")", "<h1>Hallo, Welt</h1>", cd.makeTag("Hallo, Welt", "h1"));
    }

    @Test
    public void testCountChars() {
        CoderDojo cd = new CoderDojo();

        assertEquals("\"aaaaaa\" hat sechs 'a'", 6, cd.countChars("aaaaaa", 'a'));
        assertEquals("\"aaaaaa\" hat kein 'c'", 0, cd.countChars("aaaaaa", 'c'));
        assertEquals("\"Hallo, Welt!\" hat ein ','", 1, cd.countChars("Hallo, Welt!", ','));
        assertEquals("\"Hallo, Welt!\" hat drei 'l'", 3, cd.countChars("Hallo, Welt!", 'l'));
    }

    @Test
    public void testRepeatString() {
        CoderDojo cd = new CoderDojo();

        assertEquals("repeatString(\"foo\", 4)", "foofoofoofoo", cd.repeatString("foo", 4));
        assertEquals("repeatString(\"foo\", 1)", "foo", cd.repeatString("foo", 1));
        assertEquals("repeatString(\"foo\", 0)", "", cd.repeatString("foo", 0));
        assertEquals("repeatString(\"Hi \", 10)", "Hi Hi Hi Hi Hi Hi Hi Hi Hi Hi ", cd.repeatString("Hi ", 10));
    }


    @Test
    public void testGetPokemonName() {
        CoderDojo cd = new CoderDojo();

        Pokemon p1 = new Pokemon("Bulbasa", 10, 10);
        Pokemon p2 = new Pokemon("Pikachu", 5, 15);
        Pokemon p3 = new Pokemon("Enton", 10, 5);

        assertEquals("Bulbasa", cd.getPokemonName(p1));
        assertEquals("Pikachu", cd.getPokemonName(p2));
        assertEquals("Enton", cd.getPokemonName(p3));
    }


    @Test
    public void testPokemonFight() {
        CoderDojo cd = new CoderDojo();

        Pokemon p1 = new Pokemon("Bulbasa", 10, 10);
        Pokemon p2 = new Pokemon("Pikachu", 5, 15);
        Pokemon p3 = new Pokemon("Enton", 10, 5);

        assertEquals(p2, cd.pokemonFight(p1, p2));
        assertEquals(p1, cd.pokemonFight(p2, p1));

        assertEquals(p1, cd.pokemonFight(p1, p1));

        assertEquals(p1, cd.pokemonFight(p3, p1));
        assertEquals(p3, cd.pokemonFight(p2, p3));
        assertEquals(p2, cd.pokemonFight(p3, p2));
        assertEquals(p1, cd.pokemonFight(p1, p3));
    }


    @Test
    public void testAddAttack() {
        CoderDojo cd = new CoderDojo();

        Pokemon p1 = new Pokemon("Bulbasa", 10, 10);
        Pokemon p2 = new Pokemon("Pikachu", 5, 15);
        Pokemon p3 = new Pokemon("Enton", 10, 5);

        p2.addAttack(25);
        assertEquals(30, p2.getAttack());

        cd.addAttack(p1,100);
        assertEquals(110, p1.getAttack());

        cd.addAttack(p3,-5);
        assertEquals(5, p3.getAttack());
    }
}
