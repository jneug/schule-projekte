
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test-Klasse für Array-Übungen.
 */
public class ArraysTest {

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

        arr = arrays.generateIntArray(-4, 0, 100);
        assertEquals(0, arr.length);
    }

    @Test
    public void testGenerateStringArray() {
        String[] arr;
        for( int i = 0; i < 50; i += 5 ) {
            arr = arrays.generateStringArray(i);
            assertNotNull("String-Array darf nicht <null> sein.", arr);
            assertEquals("Arraylänge passt nicht.", i, arr.length);
        }

        arr = arrays.generateStringArray(-4);
        assertEquals(0, arr.length);
    }

    @Test
    public void testGenerateBooleanArray() {
        boolean[] arr;
        for( int i = 0; i < 50; i += 5 ) {
            arr = arrays.generateBooleanArray(i);
            assertNotNull(arr);
            assertEquals(arr.length, i);
        }

        arr = arrays.generateBooleanArray(-4);
        assertEquals(0, arr.length);
    }

    @Test
    public void testMin() {
        int min;

        min = arrays.min(new int[]{1,4,12,15,28});
        assertEquals(min, 1);

        min = arrays.min(new int[]{5,4,3,2,1,0});
        assertEquals(min, 0);

        min = arrays.min(new int[]{1,2,3,4,5,-6,-7,-8});
        assertEquals(min, -8);

        min = arrays.min(new int[]{10000});
        assertEquals(min, 10000);

        min = arrays.min(new int[]{});
        assertEquals(min, 0);
    }

    @Test
    public void testMax() {
        int max;

        max = arrays.max(new int[]{1,4,12,15,28});
        assertEquals(max, 28);

        max = arrays.max(new int[]{5,4,3,2,1,0});
        assertEquals(max, 5);

        max = arrays.max(new int[]{1,2,3,4,5,-6,-7,-8});
        assertEquals(max, 5);

        max = arrays.max(new int[]{-10000});
        assertEquals(max, -10000);

        max = arrays.max(new int[]{});
        assertEquals(max, 0);
    }

    @Test
    public void testSum() {
        int sum;

        sum = arrays.sum(new int[]{1,4,12,15,28});
        assertEquals(sum, 60);

        sum = arrays.sum(new int[]{5,4,3,2,1,0});
        assertEquals(sum, 15);

        sum = arrays.sum(new int[]{1,2,3,4,5,-6,-7,-8});
        assertEquals(sum, -6);

        sum = arrays.sum(new int[]{-10000,10000});
        assertEquals(sum, 0);

        sum = arrays.sum(new int[]{4});
        assertEquals(sum, 4);

        sum = arrays.sum(new int[]{});
        assertEquals(sum, 0);
    }

    @Test
    public void testAvg() {
        double avg;

        avg = arrays.avg(new int[]{1,4,12,15,28});
        assertEquals(12.0, avg, 0.0001);

        avg = arrays.avg(new int[]{5,4,3,2,1,0});
        assertEquals(2.5, avg, 0.0001);

        avg = arrays.avg(new int[]{1,2,3,4,5,-6,-7,-8});
        assertEquals(-0.75, avg, 0.0001);

        avg = arrays.avg(new int[]{-10000,10000});
        assertEquals(0.0, avg, 0.0001);

        avg = arrays.avg(new int[]{4});
        assertEquals(4.0, avg, 0.0001);

        avg = arrays.avg(new int[]{});
        assertEquals(0.0, avg, 0.0001);
    }

    @Test
    public void testAnd() {
        boolean bln;

        bln = arrays.and(new boolean[]{true,true,true,true});
        assertTrue(bln);

        bln = arrays.and(new boolean[]{true});
        assertTrue(bln);

        bln = arrays.and(new boolean[]{true,false,true,true});
        assertFalse(bln);

        bln = arrays.and(new boolean[]{false,false,false,false});
        assertFalse(bln);

        bln = arrays.and(new boolean[]{false});
        assertFalse(bln);

        bln = arrays.and(new boolean[]{});
        assertFalse(bln);
    }

    @Test
    public void testOr() {
        boolean bln;

        bln = arrays.or(new boolean[]{true,true,true,true});
        assertTrue(bln);

        bln = arrays.or(new boolean[]{true});
        assertTrue(bln);

        bln = arrays.or(new boolean[]{true,false,true,true});
        assertTrue(bln);

        bln = arrays.or(new boolean[]{false,false,false,false});
        assertFalse(bln);

        bln = arrays.or(new boolean[]{false});
        assertFalse(bln);

        bln = arrays.or(new boolean[]{});
        assertFalse(bln);
    }

    @Test
    public void testXor() {
        boolean bln;

        bln = arrays.xor(new boolean[]{true,true,true,true});
        assertFalse(bln);

        bln = arrays.xor(new boolean[]{true});
        assertTrue(bln);

        bln = arrays.xor(new boolean[]{true,false,true,true});
        assertFalse(bln);

        bln = arrays.xor(new boolean[]{true,false,false,false});
        assertTrue(bln);

        bln = arrays.xor(new boolean[]{false,false,false,false});
        assertFalse(bln);

        bln = arrays.xor(new boolean[]{false,false,false,true});
        assertTrue(bln);

        bln = arrays.xor(new boolean[]{false});
        assertFalse(bln);

        bln = arrays.xor(new boolean[]{});
        assertFalse(bln);
    }

    @Test
    public void testConcat() {
        String str;

        str = arrays.concat(new String[]{"Foo","Bar"});
        assertEquals("FooBar", str);

        str = arrays.concat(new String[]{"Foo","Bar","Foo"});
        assertEquals("FooBarFoo", str);

        str = arrays.concat(new String[]{});
        assertEquals("", str);
    }

    @Test
    public void testJoin() {
        String str;

        str = arrays.join(new String[]{"Foo","Bar"}, ";");
        assertEquals("Foo;Bar", str);

        str = arrays.join(new String[]{"Foo","Bar","Foo"}, "----");
        assertEquals("Foo----Bar----Foo", str);

        str = arrays.join(new String[]{}, "");
        assertEquals("", str);
    }

    @Test
    public void testPrefix() {
        String[] arr;

        arr = arrays.prefix(new String[]{"Foo","Bar","Hello"}, "--");
        assertNotNull(arr);
        assertEquals(3, arr.length);
        assertEquals("--Foo", arr[0]);
        assertEquals("--Bar", arr[1]);
        assertEquals("--Hello", arr[2]);

        arr = arrays.prefix(new String[]{}, "");
        assertNotNull(arr);
        assertEquals(0, arr.length);
    }

    @Test
    public void testReverse() {
        String[] arr;

        arr = arrays.reverse(new String[]{"Foo","Bar","Hello"});
        assertNotNull(arr);
        assertEquals(3, arr.length);
        assertEquals("Hello", arr[0]);
        assertEquals("Bar", arr[1]);
        assertEquals("Foo", arr[2]);

        arr = arrays.reverse(new String[]{"Foo","Bar"});
        assertNotNull(arr);
        assertEquals(2, arr.length);
        assertEquals("Bar", arr[0]);
        assertEquals("Foo", arr[1]);

        arr = arrays.reverse(new String[]{});
        assertNotNull(arr);
        assertEquals(0, arr.length);
    }

    @Test
    public void testOver() {
        int count;

        count = arrays.over(new int[]{1,4,12,15,28}, 10);
        assertEquals(count, 3);

        count = arrays.over(new int[]{1,4,12,15,28}, 2);
        assertEquals(count, 4);

        count = arrays.over(new int[]{1,4,12,15,28}, 28);
        assertEquals(count, 0);

        count = arrays.over(new int[]{1,4,12,15,28}, -100);
        assertEquals(count, 5);

        count = arrays.over(new int[]{1}, 1);
        assertEquals(count, 0);

        count = arrays.over(new int[]{1}, 2);
        assertEquals(count, 0);

        count = arrays.over(new int[]{1}, 0);
        assertEquals(count, 1);

        count = arrays.over(new int[]{}, 0);
        assertEquals(count, 0);

        count = arrays.over(new int[]{}, 100);
        assertEquals(count, 0);

        count = arrays.over(new int[]{}, -10);
        assertEquals(count, 0);
    }

    @Test
    public void testGenerateFibonacci() {
        int[] fib;

        fib = arrays.generateFibonacci(1);
        assertNotNull(fib);
        assertEquals(1, fib.length);
        assertEquals(1, fib[0]);

        fib = arrays.generateFibonacci(2);
        assertNotNull(fib);
        assertEquals(2, fib.length);
        assertEquals(1, fib[0]);
        assertEquals(1, fib[1]);

        fib = arrays.generateFibonacci(4);
        assertNotNull(fib);
        assertEquals(4, fib.length);
        assertEquals(1, fib[0]);
        assertEquals(1, fib[1]);
        assertEquals(2, fib[2]);
        assertEquals(3, fib[3]);

        fib = arrays.generateFibonacci(6);
        assertNotNull(fib);
        assertEquals(6, fib.length);
        assertEquals(1, fib[0]);
        assertEquals(1, fib[1]);
        assertEquals(2, fib[2]);
        assertEquals(3, fib[3]);
        assertEquals(5, fib[4]);
        assertEquals(8, fib[5]);

        fib = arrays.generateFibonacci(0);
        assertNotNull(fib);
        assertEquals(0, fib.length);

        fib = arrays.generateFibonacci(-4);
        assertNotNull(fib);
        assertEquals(0, fib.length);
    }

}
