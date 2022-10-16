import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class QueueDojoTest {

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

    private QueueDojo dojo;

    @Before
    public void setUpDojo() {
        dojo = new QueueDojo();
    }

    @Test
    public void generateBooleans() {
        Queue<Boolean> q = dojo.generateBooleans(20, 80);
        assertNotNull("generateBooleans(20, 80): Ergebnis darf nicht <null> sein.", q);
        assertFalse("generateBooleans(20, 80): Ergebnis darf nicht leer sein.", q.isEmpty());

        int t = 0, f = 0;
        while( !q.isEmpty() ) {
            if( q.front() ) {
                t++;
            } else {
                f++;
            }
            q.dequeue();
        }
        assertEquals("generateBooleans(20, 80): Schlange muss 20 <true> Elemente enthalten.", 20, t);
        assertEquals("generateBooleans(20, 80): Schlange muss 80 <false> Elemente enthalten.", 80, f);

        q = dojo.generateBooleans(1, 0);
        assertNotNull("generateBooleans(1, 0): Ergebnis darf nicht <null> sein.", q);
        assertFalse("generateBooleans(1, 0): Ergebnis darf nicht leer sein.", q.isEmpty());
        assertTrue("generateBooleans(1, 0): Schlange muss genau ein <true> Element enthalten.", q.front());
        q.dequeue();
        assertTrue("generateBooleans(1, 0): Schlange muss genau ein Element enthalten.", q.isEmpty());

        q = dojo.generateBooleans(0, 1);
        assertNotNull("generateBooleans(0, 1): Ergebnis darf nicht <null> sein.", q);
        assertFalse("generateBooleans(0, 1): Ergebnis darf nicht leer sein.", q.isEmpty());
        assertFalse("generateBooleans(0, 1): Schlange muss genau ein <false> Element enthalten.", q.front());
        q.dequeue();
        assertTrue("generateBooleans(0, 1): Schlange muss genau ein Element enthalten.", q.isEmpty());

        q = dojo.generateBooleans(0, 0);
        assertNotNull("generateBooleans(0, 0): Ergebnis darf nicht <null> sein.", q);
        assertTrue("generateBooleans(0, 0): Ergebnis muss leer sein.", q.isEmpty());

        q = dojo.generateBooleans(-9, 0);
        assertNotNull("generateBooleans(-9, 0): Ergebnis darf nicht <null> sein.", q);
        assertTrue("generateBooleans(-9, 0): Ergebnis muss leer sein.", q.isEmpty());
    }

    @Test
    public void fillQueue() {
        QueueDojo dojo = new QueueDojo();
        Queue<Integer> q = dojo.fillQueue(100);
        assertNotNull("fillQueue(100): Ergebnis darf nicht <null> sein.", q);
        assertFalse("fillQueue(100): Ergebnis darf nicht leer sein.", q.isEmpty());

        int i = 0;
        while( !q.isEmpty() ) {
            i += 1;
            q.dequeue();
        }
        assertEquals("fillQueue(100): Schlange muss 100 Elemente enthalten.", 100, i);

        q = dojo.fillQueue(1);
        assertNotNull("fillQueue(1): Ergebnis darf nicht <null> sein.", q);
        assertFalse("fillQueue(1): Ergebnis darf nicht leer sein.", q.isEmpty());
        q.dequeue();
        assertTrue("fillQueue(1): Schlange muss genau ein Element enthalten.", q.isEmpty());

        q = dojo.fillQueue(0);
        assertNotNull("fillQueue(0): Ergebnis darf nicht <null> sein.", q);
        assertTrue("fillQueue(0): Ergebnis muss leer sein.", q.isEmpty());

        q = dojo.fillQueue(-99);
        assertNotNull("fillQueue(-99): Ergebnis darf nicht <null> sein.", q);
        assertTrue("fillQueue(-99): Ergebnis muss leer sein.", q.isEmpty());
    }

    @Test
    public void testPrintQueue() {
        Queue<String> q = new Queue<>();
        for( int i = 0; i < 100; i++ ) {
            q.enqueue("String " + i);
        }

        dojo.printQueue(q);

        String[] lines = outContent.toString().split(System.lineSeparator());
        assertEquals("printQueue([\"String 0\",...,\"String 99\"]): Falsche Anzahl Elemente ausgegeben.", 100, lines.length);
        for( int i = 0; i < lines.length; i++ ) {
            assertEquals("printQueue([\"String 0\",...,\"String 99\"]): Falsche Ausgabe für Element "+i+".", "String "+i, lines[i]);
        }

        q = new Queue<>();
        outContent.reset();
        dojo.printQueue(q);
        assertEquals("printQueue([]): Falsche Anzahl Elemente ausgegeben.", 0, lines.length - 100);
    }

    @Test
    public void testArrayToQueue() {
        double[] pool = new double[100];
        for( int i = 0; i < 100; i++ ) {
            pool[i] = i * 2.1;
        }

        Queue<Double> q = dojo.arrayToQueue(pool);
        assertNotNull("arrayToQueue([0, ... , 207.9]): Ergebnis darf nicht <null> sein.", q);
        assertFalse("arrayToQueue([0, ... , 207.9]): Ergebnis darf nicht leer sein.", q.isEmpty());

        for( int i = 0; !q.isEmpty(); i++, q.dequeue() ) {
            assertEquals("arrayToQueue([0, ... , 207.9]): Das " + i + "te Element der Schlange ist falsch.", pool[i], (double) q.front(), 0.00001);
        }

        q = dojo.arrayToQueue(new double[0]);
        assertNotNull("arrayToQueue([]): Ergebnis darf nicht <null> sein.", q);
        assertTrue("arrayToQueue([]): Ergebnis muss leer sein.", q.isEmpty());
    }

    @Test
    public void testCountElements() {
        Queue<Pokemon> q = new Queue<>();
        for( int i = 0; i < 100; i++ ) {
            q.enqueue(new Pokemon("Pikachu", 10, 10));
        }

        assertEquals("countElements([..., 100]): Falsche Anzahl Elemente ausgegeben.", 100, dojo.countElements(q));

        q = new Queue<>();
        assertEquals("countElements([]): Falsche Anzahl Elemente ausgegeben.", 0, dojo.countElements(q));
    }

    @Test
    public void countElements2() {
        Pokemon[] pool = new Pokemon[100];
        for( int i = 0; i < 100; i++ ) {
            pool[i] = new Pokemon("Pokemon " + i, i * 10, i * 2);
        }

        Queue<Pokemon> q = new Queue<>();
        for( int i = 0; i < pool.length; i++ ) {
            q.enqueue(pool[i]);
        }

        assertEquals("countElements2([..., 100]): Falsche Anzahl Elemente ausgegeben.", 100, dojo.countElements2(q));
        assertFalse("countElements2([..., 100]): Die Schlange darf am Ende nicht leer sein!", q.isEmpty());

        for( int i = 0; !q.isEmpty(); i++, q.dequeue() ) {
            assertSame("countElements2([..., 100]): Das " + i + "te Element der Schlange ist anders.", pool[i], q.front());
        }

        q = new Queue<>();
        assertEquals("countElements2([]): Falsche Anzahl Elemente ausgegeben.", 0, dojo.countElements2(q));
        assertTrue("countElements2([]): Die Schlange muss am Ende leer sein!", q.isEmpty());
    }

    @Test
    public void filterAbove() {
        Queue<Die> q = new Queue<>();
        for( int i = 0; i < 100; i++ ) {
            q.enqueue(new QueueDojoTest.MockupDie(i));
        }

        Queue<Die> r = dojo.filterAbove(q, 50);
        assertNotNull("filterAbove([0, ..., 100], 50): Ergebnis darf nicht <null> sein.", r);
        assertFalse("filterAbove([0, ..., 100], 50): Ergebnis darf nicht leer sein.", r.isEmpty());

        int i = 0;
        for( ; !r.isEmpty(); i++, r.dequeue() ) {
            assertTrue("countElements2([0, ..., 100], 50): Das " + i + "te Element der Schlange ist größer als 50.", r.front().getValue() <= 50);
        }
        assertEquals("countElements2([0, ..., 100], 50): Zu wenig Elemente in der Schlange.", 51, i);

        q = new Queue<>();
        for( int j = 0; j < 100; j++ ) {
            q.enqueue(new QueueDojoTest.MockupDie(j));
        }
        r = dojo.filterAbove(q, 0);
        assertNotNull("filterAbove([0, ..., 100], 0): Ergebnis darf nicht <null> sein.", r);
        assertFalse("filterAbove([0, ..., 100], 0): Ergebnis darf nicht leer sein.", r.isEmpty());
        assertEquals("countElements2([0, ..., 100], 0): Das erste Element der Schlange muss <0> sein.", r.front().getValue(), 0);
        r.dequeue();
        assertTrue("filterAbove([0, ..., 100], 0): Schlange darf nur ein Element enthalten.", r.isEmpty());

        q = new Queue<>();
        for( int j = 0; j < 100; j++ ) {
            q.enqueue(new QueueDojoTest.MockupDie(j));
        }
        r = dojo.filterAbove(q, -1);
        assertNotNull("filterAbove([0, ..., 100], -1): Ergebnis darf nicht <null> sein.", r);
        assertTrue("filterAbove([0, ..., 100], -1): Ergebnis muss leer sein.", r.isEmpty());
    }

    @Test
    public void filterAboveInPlace() {
        Queue<Die> q = new Queue<>();
        for( int i = 0; i < 100; i++ ) {
            q.enqueue(new QueueDojoTest.MockupDie(i));
        }

        dojo.filterAboveInPlace(q, 50);
        assertFalse("filterAbove([0, ..., 100], 50): Schlange darf nicht leer sein.", q.isEmpty());

        int i = 0;
        for( ; !q.isEmpty(); i++, q.dequeue() ) {
            assertTrue("countElements2([0, ..., 100], 50): Das " + i + "te Element der Schlange ist größer als 50.", q.front().getValue() <= 50);
        }
        assertEquals("countElements2([0, ..., 100], 50): Zu wenig Elemente in der Schlange.", 51, i);

        q = new Queue<>();
        for( int j = 0; j < 100; j++ ) {
            q.enqueue(new QueueDojoTest.MockupDie(j));
        }
        dojo.filterAboveInPlace(q, 0);
        assertFalse("filterAbove([0, ..., 100], 0): Ergebnis darf nicht leer sein.", q.isEmpty());
        assertEquals("countElements2([0, ..., 100], 0): Das erste Element der Schlange muss <0> sein.", q.front().getValue(), 0);
        q.dequeue();
        assertTrue("filterAbove([0, ..., 100], 0): Schlange darf nur ein Element enthalten.", q.isEmpty());

        q = new Queue<>();
        for( int j = 0; j < 100; j++ ) {
            q.enqueue(new QueueDojoTest.MockupDie(j));
        }
        dojo.filterAboveInPlace(q, -1);
        assertNotNull("filterAbove([0, ..., 100], -1): Ergebnis darf nicht <null> sein.", q);
        assertTrue("filterAbove([0, ..., 100], -1): Ergebnis muss leer sein.", q.isEmpty());
    }

    private static int n = 0;

    private class MockupDie extends Die {

        public int mockupValue;

        public int rolls = 0;

        public MockupDie( int pValue ) {
            super("W" + (n++));
            mockupValue = pValue;
            rolls = 0;
        }

        @Override
        public int getValue() {
            return mockupValue;
        }

        @Override
        public void roll() {
            rolls = rolls + 1;
        }

    }

}
