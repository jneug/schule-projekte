
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test-Klasse für Array-Übungen.
 */
public class ArraysTest {

    private Arrays arrays;

    @Before
    public void setUp() {
        arrays = new Arrays();
    }

    @Test
    public void testGenerateIntArray() {
        int[] arr;
        for( int i = 0; i < 50; i += 5 ) {
            arr = arrays.generateIntArray(i, 0, 100);
            assertNotNull("Int-Array darf nicht <null> sein.", arr);
            assertEquals("Arraylänge passt nicht.", i, arr.length);
        }

        arr = arrays.generateIntArray(-6, 0, 100);
        assertEquals("Bei negativer pNumberOfElements muss das Array leer sein.", 0, arr.length);
    }

    @Test
    public void testGenerateStringArray() {
        String[] arr;
        for( int i = 0; i < 50; i += 5 ) {
            arr = arrays.generateStringArray(i);
            assertNotNull("String-Array darf nicht <null> sein.", arr);
            assertEquals("Arraylänge passt nicht.", i, arr.length);
        }

        arr = arrays.generateStringArray(-8);
        assertEquals("Bei negativer pNumberOfElements muss das Array leer sein.", 0, arr.length);
    }

    @Test
    public void testGenerateBooleanArray() {
        boolean[] arr;
        for( int i = 0; i < 50; i += 5 ) {
            arr = arrays.generateBooleanArray(i);
            assertNotNull("Boolean-Array darf nicht <null> sein.", arr);
            assertEquals("Arraylänge passt nicht.", i, arr.length);
        }

        arr = arrays.generateBooleanArray(-4);
        assertEquals("Bei negativer pNumberOfElements muss das Array leer sein.", 0, arr.length);
    }

    @Test
    public void testMin() {
        int min;

        min = arrays.min(new int[]{1,4,12,15,28});
        assertEquals("min(1,4,12,15,28)", 1, min);

        min = arrays.min(new int[]{5,4,3,2,1,0});
        assertEquals("min(5,4,3,2,1,0)", 0, min);

        min = arrays.min(new int[]{1,2,3,4,5,-6,-7,-8});
        assertEquals("min(1,2,3,4,5,-6,-7,-8)", -8, min);

        min = arrays.min(new int[]{10000});
        assertEquals("min(10000)", 10000, min);

        min = arrays.min(new int[]{});
        assertEquals("min()", 0, min);
    }

    @Test
    public void testMax() {
        int max;

        max = arrays.max(new int[]{1,4,12,15,28});
        assertEquals("max(1,4,12,15,28)", 28, max);

        max = arrays.max(new int[]{5,4,3,2,1,0});
        assertEquals("max(5,4,3,2,1,0)", 5, max);

        max = arrays.max(new int[]{1,2,3,4,5,-6,-7,-8});
        assertEquals("max(1,2,3,4,5,-6,-7,-8)", 5, max);

        max = arrays.max(new int[]{-10000});
        assertEquals("max(-10000)", -10000, max);

        max = arrays.max(new int[]{});
        assertEquals("max()", 0, max);
    }

    @Test
    public void testSum() {
        int sum;

        sum = arrays.sum(new int[]{1,4,12,15,28});
        assertEquals(60, sum);

        sum = arrays.sum(new int[]{5,4,3,2,1,0});
        assertEquals(15, sum);

        sum = arrays.sum(new int[]{1,2,3,4,5,-6,-7,-8});
        assertEquals(-6, sum);

        sum = arrays.sum(new int[]{-10000,10000});
        assertEquals(0, sum);

        sum = arrays.sum(new int[]{4});
        assertEquals(4, sum);

        sum = arrays.sum(new int[]{});
        assertEquals(0, sum);
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
        assertEquals(3, count);

        count = arrays.over(new int[]{1,4,12,15,28}, 2);
        assertEquals(4, count);

        count = arrays.over(new int[]{1,4,12,15,28}, 28);
        assertEquals(0, count);

        count = arrays.over(new int[]{1,4,12,15,28}, -100);
        assertEquals(5, count);

        count = arrays.over(new int[]{1}, 1);
        assertEquals(0, count);

        count = arrays.over(new int[]{1}, 2);
        assertEquals(0, count);

        count = arrays.over(new int[]{1}, 0);
        assertEquals(1, count);

        count = arrays.over(new int[]{}, 0);
        assertEquals(0, count);

        count = arrays.over(new int[]{}, 100);
        assertEquals(0, count);

        count = arrays.over(new int[]{}, -10);
        assertEquals(0, count);
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
