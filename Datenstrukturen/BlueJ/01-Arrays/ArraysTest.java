

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test-Klasse für Array-Übungen.
 */
public class ArraysTest
{
    private Arrays arrays;

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @Before
    public void setUp() {
        arrays = new Arrays();
    }
    
    @Test
    public void testGenerateIntArray() {
        int[] arr;
        for( int i = 0; i < 50; i += 5 ) {
            arr = arrays.generateIntArray(i, 0, 100);
            assertNotNull(arr);
            assertEquals(i, arr.length);
        }
    }
    
    @Test
    public void testGenerateStringArray() {
        String[] arr;
        for( int i = 0; i < 50; i += 5 ) {
            arr = arrays.generateStringArray(i);
            assertNotNull("String-Array darf nicht <null> sein.", arr);
            assertEquals("Arraylänge passt nicht.", i, arr.length);
        }
    }
    
    @Test
    public void testGenerateBooleanArray() {
        boolean[] arr;
        for( int i = 0; i < 50; i += 5 ) {
            arr = arrays.generateBooleanArray(i);
            assertNotNull(arr);
            assertEquals(i, arr.length);
        }
    }
}
