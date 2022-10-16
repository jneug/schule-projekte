import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class AdtDojoTest {

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

    private static int n = 0;

    @Test
    public void queueToStack() {
        AdtDojo dojo = new AdtDojo();

        Queue<Pokemon> q = new Queue<>();
        Stack<Pokemon> s = new Stack<>();
        for( int i = 0; i < 100; i++ ) {
            Pokemon p = new Pokemon("Pikachu", 10, 10);
            q.enqueue(p);
            s.push(p);
        }

        Stack<Pokemon> r = dojo.queueToStack(q);

        int i = 0;
        for( ; !r.isEmpty(); i++, r.pop() ) {
            assertEquals("queueToStack([..., 100]): Das " + i + "te Element des Stapels ist falsch.", s.top(), r.top());
            s.pop();
        }
        assertEquals("queueToStack([..., 100]): Falsche Anzahl Elemente im Stapel.", 100, i);

        q = new Queue<>();
        r = dojo.queueToStack(q);
        assertEquals("queueToStack([]): Eine leere Schlange sollte einen leeren Stapel erzeugen.", true, r.isEmpty());
    }

    @Test
    public void stackToQueue() {
        AdtDojo dojo = new AdtDojo();

        Stack<Die> q = new Stack<>();
        Stack<Die> s = new Stack<>();
        for( int i = 0; i < 100; i++ ) {
            Die p = new Die("Wuerfel " + i);
            q.push(p);
            s.push(p);
        }

        Queue<Die> r = dojo.stackToQueue(s);

        int i = 0;
        for( ; !r.isEmpty(); i++, r.dequeue() ) {
            assertEquals("stackToQueue([..., 100]): Das " + i + "te Element der Schlange ist falsch.", q.top(), r.front());
            q.pop();
        }
        assertEquals("stackToQueue([..., 100]): Falsche Anzahl Elemente in der Schlange.", 100, i);

        s = new Stack<>();
        r = dojo.stackToQueue(s);
        assertEquals("stackToQueue([]): Eine leerer Stapel sollte eine leere Schlange erzeugen.", true, r.isEmpty());
    }

    @Test
    public void reverse() {
        AdtDojo dojo = new AdtDojo();

        Queue<Integer> q = new Queue<>();
        for( int i = 0; i < 100; i++ ) {
            q.enqueue(i);
        }

        dojo.reverse(q);

        int i = 0;
        for( ; !q.isEmpty(); i++, q.dequeue() ) {
            assertEquals("reverse([0, ..., 99]): Das " + i + "te Element der Schlange ist falsch.",99-i,  (int)q.front());
        }
        assertEquals("queueToStack([..., 100]): Falsche Anzahl Elemente im Stapel.", 100, i);

        q = new Queue<>();
        dojo.reverse(q);
        assertEquals("reverse([]): Eine leere Schlange sollte eine leere Schlange erzeugen.", true, q.isEmpty());
    }

    @Test
    public void removeSecond() {
        AdtDojo dojo = new AdtDojo();

        Stack<Double> s = new Stack<>();
        for( int i = 0; i < 10; i++ ) {
            s.push(i*1.5);
        }

        dojo.removeSecond(s);

        assertFalse("removeSecond([ ..., 10]): Der Stapel darf nicht leer sein.", s.isEmpty());
        assertEquals("removeSecond([ ..., 10]): Erstes Element ist falsch.",  13.5, (double)s.top(), 0.0001);
        s.pop();
        assertEquals("removeSecond([ ..., 10]): Zweites Element ist falsch.", 10.5, (double)s.top(), 0.0001);
        int i = 1;
        for( ; !s.isEmpty(); i++, s.pop() ) {}
        assertEquals("removeSecond([..., 10]): Falsche Anzahl Elemente im Stapel.", 9, i);

        s = new Stack<>();
        dojo.removeSecond(s);
        assertTrue("removeSecond([]): Eine leerer Stapel bleibt leer.", s.isEmpty());

    }

}
