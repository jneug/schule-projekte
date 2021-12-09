import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Test-Klasse für Array-Übungen.
 */
public class ArrayDojoTest {

    private ArrayDojo ad;

    @Before
    public void setUp() {
        ad = new ArrayDojo();
    }

    @Test
    public void getFirst() {
        int[] arr = new int[]{45,64,56,67,23,7,8,34,54,2,24,6,72,334,456};
        for( int i = 0; i < 10; i++ ) {
            assertEquals(arr[0], ad.getFirst(arr));
            arr = Arrays.copyOfRange(arr, 1, arr.length);
        }
    }

    @Test
    public void getSecond() {
        int[] arr = new int[]{45,64,56,67,23,7,8,34,54,2,24,6,72,334,456};
        for( int i = 0; i < 10; i++ ) {
            assertEquals(arr[1], ad.getSecond(arr));
            arr = Arrays.copyOfRange(arr, 1, arr.length);
        }
    }

    @Test
    public void getLast() {
        int[] arr = new int[]{45,64,56,67,23,7,8,34,54,2,24,6,72,334,456};
        for( int i = 0; i < 10; i++ ) {
            assertEquals(arr[arr.length-1], ad.getLast(arr));
            arr = Arrays.copyOfRange(arr, 0, arr.length-1);
        }

        assertEquals(0, ad.getLast(new int[]{}));
    }

    @Test
    public void getValue() {
        String[] arr = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        for( int i = 0; i < arr.length; i++ ) {
            assertEquals(arr[i], ad.getValue(arr, i));
        }

        assertNull(ad.getValue(arr, 30));
        assertNull(ad.getValue(arr, 31));
        assertNull(ad.getValue(arr, 32));
        assertNull(ad.getValue(new String[]{}, 0));
        assertNull(ad.getValue(new String[]{}, 1));
    }

    @Test
    public void getElement() {
        String[] arr = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        for( int i = 1; i < arr.length; i++ ) {
            assertEquals(arr[i-1], ad.getElement(arr, i));
        }


        assertNull(ad.getElement(arr, 30));
        assertNull(ad.getElement(arr, 31));
        assertNull(ad.getElement(arr, 32));
        assertNull(ad.getElement(new String[]{}, 0));
        assertNull(ad.getElement(new String[]{}, 1));
    }

    @Test
    public void getLength() {
        String[] arr = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        for( int i = 1; i < 10; i++ ) {
            assertEquals(arr.length, ad.getLength(arr));
            arr = Arrays.copyOfRange(arr, 1, arr.length);
        }

        assertEquals(0, ad.getLength(new String[]{}));
    }

    @Test
    public void setFirst() {
        int[] arr = new int[]{45,64,56,67,23,7,8,34,54,2,24,6,72,334,456};
        for( int i = 0; i < 10; i++ ) {
            arr = ad.setFirst(arr, 100+i);
            assertEquals(100+i, arr[0]);
            arr = Arrays.copyOfRange(arr, 1, arr.length);
        }

        int[] arrEmpty = new int[]{};
        assertEquals(arrEmpty, ad.setFirst(arrEmpty, 0));
        assertEquals(arrEmpty, ad.setFirst(arrEmpty, 2));
        assertEquals(arrEmpty, ad.setFirst(arrEmpty, 3));
    }

    @Test
    public void testGenerateIntArray() {
        int[] arr;
        for( int i = 0; i < 50; i += 5 ) {
            arr = ad.generateIntArray(i, 0, 100);
            assertNotNull("Int-Array darf nicht <null> sein.", arr);
            assertEquals("Arraylänge passt nicht.", i, arr.length);
        }

        arr = ad.generateIntArray(-6, 0, 100);
        assertEquals("Bei negativer <pNumberOfElements> muss das Array leer sein.", 0, arr.length);
    }

    @Test
    public void testGenerateStringArray() {
        String[] arr;
        for( int i = 0; i < 50; i += 5 ) {
            arr = ad.generateStringArray(i);
            assertNotNull("String-Array darf nicht <null> sein.", arr);
            assertEquals("Arraylänge passt nicht.", i, arr.length);
        }

        arr = ad.generateStringArray(-8);
        assertEquals("Bei negativer <pNumberOfElements> muss das Array leer sein.", 0, arr.length);
    }

    @Test
    public void testGenerateBooleanArray() {
        boolean[] arr;
        for( int i = 0; i < 50; i += 5 ) {
            arr = ad.generateBooleanArray(i);
            assertNotNull("Boolean-Array darf nicht <null> sein.", arr);
            assertEquals("Arraylänge passt nicht.", i, arr.length);
        }

        arr = ad.generateBooleanArray(-4);
        assertEquals("Bei negativer <pNumberOfElements> muss das Array leer sein.", 0, arr.length);
    }

    @Test
    public void testMin() {
        int min;

        min = ad.min(new int[]{1,4,12,15,28});
        assertEquals("min(1,4,12,15,28)", 1, min);

        min = ad.min(new int[]{5,4,3,2,1,0});
        assertEquals("min(5,4,3,2,1,0)", 0, min);

        min = ad.min(new int[]{1,2,3,4,5,-6,-7,-8});
        assertEquals("min(1,2,3,4,5,-6,-7,-8)", -8, min);

        min = ad.min(new int[]{10000});
        assertEquals("min(10000)", 10000, min);

        min = ad.min(new int[]{});
        assertEquals("min()", 0, min);
    }

    @Test
    public void testMax() {
        int max;

        max = ad.max(new int[]{1,4,12,15,28});
        assertEquals("max(1,4,12,15,28)", 28, max);

        max = ad.max(new int[]{5,4,3,2,1,0});
        assertEquals("max(5,4,3,2,1,0)", 5, max);

        max = ad.max(new int[]{1,2,3,4,5,-6,-7,-8});
        assertEquals("max(1,2,3,4,5,-6,-7,-8)", 5, max);

        max = ad.max(new int[]{-10000});
        assertEquals("max(-10000)", -10000, max);

        max = ad.max(new int[]{});
        assertEquals("max()", 0, max);
    }

    @Test
    public void testSum() {
        int sum;

        sum = ad.sum(new int[]{1,4,12,15,28});
        assertEquals(60, sum);

        sum = ad.sum(new int[]{5,4,3,2,1,0});
        assertEquals(15, sum);

        sum = ad.sum(new int[]{1,2,3,4,5,-6,-7,-8});
        assertEquals(-6, sum);

        sum = ad.sum(new int[]{-10000,10000});
        assertEquals(0, sum);

        sum = ad.sum(new int[]{4});
        assertEquals(4, sum);

        sum = ad.sum(new int[]{});
        assertEquals(0, sum);
    }

    @Test
    public void testAvg() {
        double avg;

        avg = ad.avg(new int[]{1,4,12,15,28});
        assertEquals(12.0, avg, 0.0001);

        avg = ad.avg(new int[]{5,4,3,2,1,0});
        assertEquals(2.5, avg, 0.0001);

        avg = ad.avg(new int[]{1,2,3,4,5,-6,-7,-8});
        assertEquals(-0.75, avg, 0.0001);

        avg = ad.avg(new int[]{-10000,10000});
        assertEquals(0.0, avg, 0.0001);

        avg = ad.avg(new int[]{4});
        assertEquals(4.0, avg, 0.0001);

        avg = ad.avg(new int[]{});
        assertEquals(0.0, avg, 0.0001);
    }

    @Test
    public void testAnd() {
        boolean bln;

        bln = ad.and(new boolean[]{true,true,true,true});
        assertTrue(bln);

        bln = ad.and(new boolean[]{true});
        assertTrue(bln);

        bln = ad.and(new boolean[]{true,false,true,true});
        assertFalse(bln);

        bln = ad.and(new boolean[]{false,false,false,false});
        assertFalse(bln);

        bln = ad.and(new boolean[]{false});
        assertFalse(bln);

        bln = ad.and(new boolean[]{});
        assertFalse(bln);
    }

    @Test
    public void testOr() {
        boolean bln;

        bln = ad.or(new boolean[]{true,true,true,true});
        assertTrue(bln);

        bln = ad.or(new boolean[]{true});
        assertTrue(bln);

        bln = ad.or(new boolean[]{true,false,true,true});
        assertTrue(bln);

        bln = ad.or(new boolean[]{false,false,false,false});
        assertFalse(bln);

        bln = ad.or(new boolean[]{false});
        assertFalse(bln);

        bln = ad.or(new boolean[]{});
        assertFalse(bln);
    }

    @Test
    public void testXor() {
        boolean bln;

        bln = ad.xor(new boolean[]{true,true,true,true});
        assertFalse(bln);

        bln = ad.xor(new boolean[]{true});
        assertTrue(bln);

        bln = ad.xor(new boolean[]{true,false,true,true});
        assertFalse(bln);

        bln = ad.xor(new boolean[]{true,false,false,false});
        assertTrue(bln);

        bln = ad.xor(new boolean[]{false,false,false,false});
        assertFalse(bln);

        bln = ad.xor(new boolean[]{false,false,false,true});
        assertTrue(bln);

        bln = ad.xor(new boolean[]{false});
        assertFalse(bln);

        bln = ad.xor(new boolean[]{});
        assertFalse(bln);
    }

    @Test
    public void testConcat() {
        String str;

        str = ad.concat(new String[]{"Foo","Bar"});
        assertEquals("FooBar", str);

        str = ad.concat(new String[]{"Foo","Bar","Foo"});
        assertEquals("FooBarFoo", str);

        str = ad.concat(new String[]{});
        assertEquals("", str);
    }

    @Test
    public void testJoin() {
        String str;

        str = ad.join(new String[]{"Foo","Bar"}, ";");
        assertEquals("Foo;Bar", str);

        str = ad.join(new String[]{"Foo","Bar","Foo"}, "----");
        assertEquals("Foo----Bar----Foo", str);

        str = ad.join(new String[]{}, "");
        assertEquals("", str);
    }

    @Test
    public void testPrefix() {
        String[] arr;

        arr = ad.prefix(new String[]{"Foo","Bar","Hello"}, "--");
        assertNotNull(arr);
        assertEquals(3, arr.length);
        assertEquals("--Foo", arr[0]);
        assertEquals("--Bar", arr[1]);
        assertEquals("--Hello", arr[2]);

        arr = ad.prefix(new String[]{}, "");
        assertNotNull(arr);
        assertEquals(0, arr.length);
    }

    @Test
    public void testReverse() {
        String[] arr;

        arr = ad.reverse(new String[]{"Foo","Bar","Hello"});
        assertNotNull(arr);
        assertEquals(3, arr.length);
        assertEquals("Hello", arr[0]);
        assertEquals("Bar", arr[1]);
        assertEquals("Foo", arr[2]);

        arr = ad.reverse(new String[]{"Foo","Bar"});
        assertNotNull(arr);
        assertEquals(2, arr.length);
        assertEquals("Bar", arr[0]);
        assertEquals("Foo", arr[1]);

        arr = ad.reverse(new String[]{});
        assertNotNull(arr);
        assertEquals(0, arr.length);
    }

    @Test
    public void testOver() {
        int count;

        count = ad.over(new int[]{1,4,12,15,28}, 10);
        assertEquals(3, count);

        count = ad.over(new int[]{1,4,12,15,28}, 2);
        assertEquals(4, count);

        count = ad.over(new int[]{1,4,12,15,28}, 28);
        assertEquals(0, count);

        count = ad.over(new int[]{1,4,12,15,28}, -100);
        assertEquals(5, count);

        count = ad.over(new int[]{1}, 1);
        assertEquals(0, count);

        count = ad.over(new int[]{1}, 2);
        assertEquals(0, count);

        count = ad.over(new int[]{1}, 0);
        assertEquals(1, count);

        count = ad.over(new int[]{}, 0);
        assertEquals(0, count);

        count = ad.over(new int[]{}, 100);
        assertEquals(0, count);

        count = ad.over(new int[]{}, -10);
        assertEquals(0, count);
    }

    @Test
    public void testGenerateFibonacci() {
        int[] fib;

        fib = ad.generateFibonacci(1);
        assertNotNull(fib);
        assertEquals(1, fib.length);
        assertEquals(1, fib[0]);

        fib = ad.generateFibonacci(2);
        assertNotNull(fib);
        assertEquals(2, fib.length);
        assertEquals(1, fib[0]);
        assertEquals(1, fib[1]);

        fib = ad.generateFibonacci(4);
        assertNotNull(fib);
        assertEquals(4, fib.length);
        assertEquals(1, fib[0]);
        assertEquals(1, fib[1]);
        assertEquals(2, fib[2]);
        assertEquals(3, fib[3]);

        fib = ad.generateFibonacci(6);
        assertNotNull(fib);
        assertEquals(6, fib.length);
        assertEquals(1, fib[0]);
        assertEquals(1, fib[1]);
        assertEquals(2, fib[2]);
        assertEquals(3, fib[3]);
        assertEquals(5, fib[4]);
        assertEquals(8, fib[5]);

        fib = ad.generateFibonacci(0);
        assertNotNull(fib);
        assertEquals(0, fib.length);

        fib = ad.generateFibonacci(-4);
        assertNotNull(fib);
        assertEquals(0, fib.length);
    }
}
