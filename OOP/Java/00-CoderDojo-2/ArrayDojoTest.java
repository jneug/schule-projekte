import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Test-Klasse für das ArrayDojo.
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
public class ArrayDojoTest {

    private ArrayDojo ad;

    private int[] numbers;

    private String[] strings;

    @Before
    public void setUp() {
        ad = new ArrayDojo();
        // Einige Zahlen vorbereiten
        numbers = new int[]{45, 64, 56, 67, 23, 7, 8, 34, 54, 2, 24, 6, 72, 334, 456};
        strings = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    }

    @Test
    public void testGetLength() {
        try {
            for( int i = 1; i < 10; i++ ) {
                assertEquals("getLength(...)", strings.length, ad.getLength(strings));
                strings = Arrays.copyOfRange(strings, 1, strings.length);
            }

            assertEquals(0, ad.getLength(new String[]{}));
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testGetFirst() {
        try {
            for( int i = 0; i < 10; i++ ) {
                assertEquals("getFirst({" + numbers[0] + ", ...})", numbers[0], ad.getFirst(numbers));
                numbers = Arrays.copyOfRange(numbers, 1, numbers.length);
            }
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testGetSecond() {
        try {
            for( int i = 0; i < 10; i++ ) {
                assertEquals("getSecond({" + numbers[0] + ", " + numbers[1] + ", ...})", numbers[1], ad.getSecond(numbers));
                numbers = Arrays.copyOfRange(numbers, 1, numbers.length);
            }
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testGetLast() {
        try {
            for( int i = 0; i < 10; i++ ) {
                assertEquals("getLast({..., " + numbers[numbers.length - 1] + "})", numbers[numbers.length - 1], ad.getLast(numbers));
                numbers = Arrays.copyOfRange(numbers, 0, numbers.length - 1);
            }

            assertEquals("getLast() soll für leere Arrays <0> ergeben.", 0, ad.getLast(new int[]{}));
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testGetValue() {
        try {
            for( int i = 0; i < strings.length; i++ ) {
                assertEquals("getValue(..., " + i + ")", strings[i], ad.getValue(strings, i));
            }

            assertNull("getValue() soll für zu große Indizes <null> ergeben.", ad.getValue(strings, strings.length));
            assertNull("getValue() soll für zu große Indizes <null> ergeben.", ad.getValue(strings, 30));
            assertNull("getValue() soll für zu große Indizes <null> ergeben.", ad.getValue(strings, 31));
            assertNull("getValue() soll für leere Arrays <null> ergeben.", ad.getValue(new String[]{}, 0));
            assertNull("getValue() soll für leere Arrays <null> ergeben.", ad.getValue(new String[]{}, 1));
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testGetElement() {
        try {
            for( int i = 1; i <= strings.length; i++ ) {
                assertEquals("getElement(..., " + i + ")", strings[i - 1], ad.getElement(strings, i));
            }

            assertNull("getElement() soll für zu große Nummern <null> ergeben.", ad.getValue(strings, strings.length + 1));
            assertNull("getElement() soll für zu große Nummern <null> ergeben.", ad.getElement(strings, 30));
            assertNull("getElement() soll für zu große Nummern <null> ergeben.", ad.getElement(strings, 31));
            assertNull("getElement() soll für leere Arrays <null> ergeben.", ad.getElement(new String[]{}, 0));
            assertNull("getElement() soll für leere Arrays <null> ergeben.", ad.getElement(new String[]{}, 1));
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testSetFirst() {
        try {
            for( int i = 0; i < 10; i++ ) {
                numbers = ad.setFirst(numbers, 100 + i);
                assertNotNull("setFirst() muss das geänderte Array zurückgeben.", numbers);
                assertEquals(100 + i, numbers[0]);
                numbers = Arrays.copyOfRange(numbers, 1, numbers.length);
            }

            int[] arrEmpty = new int[]{};
            int[] arrEmptyCheck = new int[]{};
            assertArrayEquals(arrEmptyCheck, ad.setFirst(arrEmpty, 0));
            assertArrayEquals(arrEmptyCheck, ad.setFirst(arrEmpty, 20));
            assertArrayEquals(arrEmptyCheck, ad.setFirst(arrEmpty, 333));
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testGenerateIntArray() {
        try {
            int[] arr;
            for( int i = 0; i < 50; i += 5 ) {
                arr = ad.generateIntArray(i, i * 10, i * 10 + 100);
                assertNotNull("generateIntArray(" + i + ", " + i * 10 + ", " + (i * 10 + 100) + ") darf nicht <null> sein.", arr);
                assertEquals("generateIntArray(" + i + ", " + i * 10 + ", " + (i * 10 + 100) + ").length", i, arr.length);
                for( int j : arr ) {
                    if( j < i * 10 || j > i * 10 + 100 ) {
                        System.out.println("generateIntArray(" + i + ", " + i * 10 + ", " + (i * 10 + 100) + ") enthält nicht initialisierte Werte.");
                    }
                }
            }

            arr = ad.generateIntArray(-6, 0, 100);
            assertEquals("Bei negativer Anzahl muss das Array leer sein.", 0, arr.length);
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testGenerateStringArray() {
        try {
            String[] arr;
            for( int i = 0; i < 50; i += 5 ) {
                arr = ad.generateStringArray(i);
                assertNotNull("generateStringArray(" + i + ") darf nicht <null> sein.", arr);
                assertEquals("generateStringArray(" + i + ").length", i, arr.length);
                for( String s : arr ) {
                    if( s == null ) {
                        fail("generateStringArray(" + i + ") enthält <null>");
                    }
                }
            }

            arr = ad.generateStringArray(-8);
            assertEquals("Bei negativer Anzahl muss das Array leer sein.", 0, arr.length);
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testGenerateBooleanArray() {
        try {
            boolean[] arr;
            for( int i = 0; i < 50; i += 5 ) {
                arr = ad.generateBooleanArray(i);
                assertNotNull("generateBooleanArray(" + i + ") darf nicht <null> sein.", arr);
                assertEquals("generateBooleanArray(" + i + ").length", i, arr.length);
            }

            arr = ad.generateBooleanArray(-4);
            assertEquals("Bei negativer <pNumberOfElements> muss das Array leer sein.", 0, arr.length);
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testMin() {
        try {
            int min;

            min = ad.min(new int[]{1, 4, 12, 15, 28});
            assertEquals("min({1,4,12,15,28})", 1, min);

            min = ad.min(new int[]{5, 4, 3, 2, 1, 0});
            assertEquals("min({5,4,3,2,1,0})", 0, min);

            min = ad.min(new int[]{1, 2, 3, 4, 5, -6, -7, -8});
            assertEquals("min({1,2,3,4,5,-6,-7,-8})", -8, min);

            min = ad.min(new int[]{10000});
            assertEquals("min({10000})", 10000, min);

            min = ad.min(new int[]{});
            assertEquals("min({})", 0, min);
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testMax() {
        try {
            int max;

            max = ad.max(new int[]{1, 4, 12, 15, 28});
            assertEquals("max({1,4,12,15,28})", 28, max);

            max = ad.max(new int[]{5, 4, 3, 2, 1, 0});
            assertEquals("max({5,4,3,2,1,0})", 5, max);

            max = ad.max(new int[]{1, 2, 3, 4, 5, -6, -7, -8});
            assertEquals("max({1,2,3,4,5,-6,-7,-8})", 5, max);

            max = ad.max(new int[]{-10000});
            assertEquals("max({-10000})", -10000, max);

            max = ad.max(new int[]{});
            assertEquals("max({})", 0, max);
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testSum() {
        try {
            int sum;

            sum = ad.sum(new int[]{1, 4, 12, 15, 28});
            assertEquals("sum({1,4,12,15,28})", 60, sum);

            sum = ad.sum(new int[]{5, 4, 3, 2, 1, 0});
            assertEquals("sum({5,4,3,2,1,0})", 15, sum);

            sum = ad.sum(new int[]{1, 2, 3, 4, 5, -6, -7, -8});
            assertEquals("sum({1,2,3,4,5,-6,-7,-8})", -6, sum);

            sum = ad.sum(new int[]{-10000, 10000});
            assertEquals("sum({-10000,10000})", 0, sum);

            sum = ad.sum(new int[]{4});
            assertEquals("sum({4})", 4, sum);

            sum = ad.sum(new int[]{});
            assertEquals("sum({})", 0, sum);
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testAvg() {
        try {
            double avg;

            avg = ad.avg(new int[]{1, 4, 12, 15, 28});
            assertEquals("avg({1,4,12,15,28})", 12.0, avg, 0.0001);

            avg = ad.avg(new int[]{5, 4, 3, 2, 1, 0});
            assertEquals("avg({5,4,3,2,1,0})", 2.5, avg, 0.0001);

            avg = ad.avg(new int[]{1, 2, 3, 4, 5, -6, -7, -8});
            assertEquals("avg({1,2,3,4,5,-6,-7,-8})", -0.75, avg, 0.0001);

            avg = ad.avg(new int[]{-10000, 10000});
            assertEquals("avg({-10000,10000})", 0.0, avg, 0.0001);

            avg = ad.avg(new int[]{4});
            assertEquals("avg({4})", 4.0, avg, 0.0001);

            avg = ad.avg(new int[]{});
            assertEquals("avg({})", 0.0, avg, 0.0001);
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testAnd() {
        try {
            boolean bln;

            bln = ad.and(new boolean[]{true, true, true, true});
            assertTrue("and({true,true,true,true})", bln);

            bln = ad.and(new boolean[]{true});
            assertTrue("and({true})", bln);

            bln = ad.and(new boolean[]{true, false, true, true});
            assertFalse("and({true,false,true,true})", bln);

            bln = ad.and(new boolean[]{false, false, false, false});
            assertFalse("and({false,false,false,false})", bln);

            bln = ad.and(new boolean[]{false});
            assertFalse("and({false})", bln);

            bln = ad.and(new boolean[]{});
            assertFalse("and({})", bln);
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testOr() {
        try {
            boolean bln;

            bln = ad.or(new boolean[]{true, true, true, true});
            assertTrue("or({true,true,true,true})", bln);

            bln = ad.or(new boolean[]{true});
            assertTrue("or({true})", bln);

            bln = ad.or(new boolean[]{true, false, true, true});
            assertTrue("or({true,false,true,true})", bln);

            bln = ad.or(new boolean[]{false, false, false, false});
            assertFalse("or({false,false,false,false})", bln);

            bln = ad.or(new boolean[]{false});
            assertFalse("or({false})", bln);

            bln = ad.or(new boolean[]{});
            assertFalse("or({})", bln);
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testXor() {
        try {
            boolean bln;

            bln = ad.xor(new boolean[]{true, true, true, true});
            assertFalse("xor({true,true,true,true})", bln);

            bln = ad.xor(new boolean[]{true});
            assertTrue("xor({true})", bln);

            bln = ad.xor(new boolean[]{true, false, true, true});
            assertFalse("xor({true,false,true,true})", bln);

            bln = ad.xor(new boolean[]{true, false, false, false});
            assertTrue("xor({true,false,false,false})", bln);

            bln = ad.xor(new boolean[]{false, false, false, false});
            assertFalse("xor({false,false,false,false})", bln);

            bln = ad.xor(new boolean[]{false, false, false, true});
            assertTrue("xor({false,false,false,true})", bln);

            bln = ad.xor(new boolean[]{false});
            assertFalse("xor({false})", bln);

            bln = ad.xor(new boolean[]{});
            assertFalse("xor({})", bln);
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testConcat() {
        try {
            String str;

            str = ad.concat(new String[]{"Foo", "Bar"});
            assertEquals("concat({Foo,Bar})", "FooBar", str);

            str = ad.concat(new String[]{"Foo", "Bar", "Foo"});
            assertEquals("concat({Foo,Bar,Foo})", "FooBarFoo", str);

            str = ad.concat(new String[]{});
            assertEquals("concat({})", "", str);
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testJoin() {
        try {
            String str;

            str = ad.join(new String[]{"Foo", "Bar"}, ";");
            assertEquals("join({Foo,Bar}, \";\")", "Foo;Bar", str);

            str = ad.join(new String[]{"Foo", "Bar", "Foo"}, "----");
            assertEquals("join({Foo,Bar,Foo}, \"----\")", "Foo----Bar----Foo", str);

            str = ad.join(new String[]{}, "");
            assertEquals("join({}, \"\")", "", str);
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testPrefix() {
        try {
            String[] arr;

            arr = ad.prefix(new String[]{"Foo", "Bar", "Hello"}, "--");
            assertNotNull("Das Array muss zurückgegeben werden.", arr);
            assertEquals("Das Ergebnis muss dieselbe Länge haben wie das Original.", 3, arr.length);
            assertEquals("prefix({Foo,Bar,Hello}, \"--\")[0]", "--Foo", arr[0]);
            assertEquals("prefix({Foo,Bar,Hello}, \"--\")[1]", "--Bar", arr[1]);
            assertEquals("prefix({Foo,Bar,Hello}, \"--\")[2]", "--Hello", arr[2]);

            arr = ad.prefix(new String[]{}, "");
            assertNotNull("Das Array muss zurückgegeben werden.", arr);
            assertEquals("Das Ergebnis muss dieselbe Länge haben wie das Original.", 0, arr.length);
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testReverse() {
        try {
            String[] arr;

            arr = ad.reverse(new String[]{"Foo", "Bar", "Hello"});
            assertNotNull("Das Ergebnis darf nicht <null> sein.", arr);
            assertEquals("Das Ergebnis muss dieselbe Länge wie das Original haben.", 3, arr.length);
            assertEquals("reverse({Foo,Bar,Hello})[0]", "Hello", arr[0]);
            assertEquals("reverse({Foo,Bar,Hello})[1]", "Bar", arr[1]);
            assertEquals("reverse({Foo,Bar,Hello})[2]", "Foo", arr[2]);

            arr = ad.reverse(new String[]{"Foo", "Bar"});
            assertNotNull("Das Ergebnis darf nicht <null> sein.", arr);
            assertEquals("Das Ergebnis muss dieselbe Länge wie das Original haben.", 2, arr.length);
            assertEquals("reverse({Foo,Bar})[0]", "Bar", arr[0]);
            assertEquals("reverse({Foo,Bar})[1]", "Foo", arr[1]);

            arr = ad.reverse(new String[]{});
            assertNotNull("Das Ergebnis darf nicht <null> sein.", arr);
            assertEquals("Das Ergebnis muss dieselbe Länge wie das Original haben.", 0, arr.length);
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testOver() {
        try {
            int count;

            count = ad.over(new int[]{1, 4, 12, 15, 28}, 10);
            assertEquals("over({1,4,12,15,28}, 10)", 3, count);

            count = ad.over(new int[]{1, 4, 12, 15, 28}, 2);
            assertEquals("over({1,4,12,15,28}, 2)", 4, count);

            count = ad.over(new int[]{1, 4, 12, 15, 28}, 28);
            assertEquals("over({1,4,12,15,28}, 28)", 0, count);

            count = ad.over(new int[]{1, 4, 12, 15, 28}, -100);
            assertEquals("over({1,4,12,15,28}, -100)", 5, count);

            count = ad.over(new int[]{1}, 1);
            assertEquals("over({1}, 1)", 0, count);

            count = ad.over(new int[]{1}, 2);
            assertEquals("over({1}, 2)", 0, count);

            count = ad.over(new int[]{1}, 0);
            assertEquals("over({1}, 0)", 1, count);

            count = ad.over(new int[]{}, 0);
            assertEquals("over({}, 0)", 0, count);

            count = ad.over(new int[]{}, 100);
            assertEquals("over({}, 100)", 0, count);

            count = ad.over(new int[]{}, -10);
            assertEquals("over({}, -10)", 0, count);
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testGenerateFibonacci() {
        try {
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
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

}
