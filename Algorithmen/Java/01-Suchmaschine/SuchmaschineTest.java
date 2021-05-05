

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SuchmaschineTest {

    int[] zufallsArray, sortiertesArray;
    List<Integer> zufallsListe, sortierteListe;

    @Before
    public void setUp() {
        zufallsArray = new int[]{5, 3, 17, 23, 59, 83, 64, 19, 99, 11, 2, 32, 33, 34, 7, 66, 51};
        sortiertesArray = java.util.Arrays.copyOf(zufallsArray, zufallsArray.length);
        java.util.Arrays.sort(sortiertesArray);

        zufallsListe = new List<Integer>();
        sortierteListe = new List<Integer>();
        for (int i = 0; i < zufallsArray.length; i++) {
            zufallsListe.append(zufallsArray[i]);
            sortierteListe.append(sortiertesArray[i]);
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testLineareSucheArray() {
        Suchmaschine s = new Suchmaschine();

        for (int i : zufallsArray) {
            assertTrue(i + " wurde nicht im Array gefunden, obwohl vorhanden!",
                s.lineareSuche(i, zufallsArray));
        }
        assertFalse("1 ist nicht im Array, wurde aber gefunden!",
            s.lineareSuche(1, zufallsArray));
        assertFalse("58 ist nicht im Array, wurde aber gefunden!",
            s.lineareSuche(58, zufallsArray));
    }

    @Test
    public void testLineareSucheList() {
        Suchmaschine s = new Suchmaschine();

        sortierteListe.toFirst();
        while (sortierteListe.hasAccess()) {
            int i = sortierteListe.getContent().intValue();
            assertTrue(i + " wurde nicht in der Liste gefunden, obwohl vorhanden!",
                s.lineareSuche(i, zufallsListe));
            sortierteListe.next();
        }
        assertFalse("1 ist nicht in der Liste, wurde aber gefunden!",
            s.lineareSuche(1, zufallsListe));
        assertFalse("58 ist nicht in der Liste, wurde aber gefunden!",
            s.lineareSuche(58, zufallsListe));
    }

    @Test
    public void testBinaereSucheArray() {
        Suchmaschine s = new Suchmaschine();

        for (int i : zufallsArray) {
            assertTrue(i + " wurde nicht im Array gefunden, obwohl vorhanden!",
                s.binaereSuche(i, sortiertesArray));
        }
        assertFalse("1 ist nicht im Array, wurde aber gefunden!",
            s.binaereSuche(1, sortiertesArray));
        assertFalse("58 ist nicht im Array, wurde aber gefunden!",
            s.binaereSuche(58, sortiertesArray));

        // Randbedingungen prüfen
        for( int i = 10; i >= 1; i-- ) {
            int[] arr = new int[i];
            for( int j = 1; j <= i; j++ ) {
                arr[j-1] = j;
            }
            assertTrue("1 in 1.."+i, s.binaereSuche(1, arr));
        }
        assertFalse("1 in {}", s.binaereSuche(1, new int[]{}));

        assertTrue(s.binaereSuche(10, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        for( int i = 9; i >= 1; i-- ) {
            int[] arr = new int[i];
            for( int j = 1; j <= i; j++ ) {
                arr[j-1] = j;
            }
            assertFalse("10 in 1.."+i, s.binaereSuche(10, arr));
        }
        assertFalse("10 in {}", s.binaereSuche(10, new int[]{1}));
    }

}
