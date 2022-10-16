import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.function.BiConsumer;

import static org.junit.Assert.*;

public class StackDojoTest {

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

    private StackDojo dojo;

    @Before
    public void setUpDojo() {
        dojo = new StackDojo();
    }

    @Test
    public void printStack() {
        Stack<Integer> s = new Stack<>();
        for( int i = 0; i < 100; i++ ) {
            s.push(i);
        }

        dojo.printStack(s);

        String[] lines = outContent.toString().split(System.lineSeparator());
        assertEquals("printStack([0, ..., 99]): Falsche Anzahl Elemente ausgegeben.", 100, lines.length);
        for( int i = 0; i < lines.length; i++ ) {
            assertEquals("printStack([0, ..., 99]): Falsche Ausgabe für Element " + i + ".", "" + (99 - i), lines[i]);
        }

        s = new Stack<>();
        outContent.reset();
        dojo.printStack(s);
        assertEquals("printStack([]): Falsche Anzahl Elemente ausgegeben.", 0, lines.length - 100);

        outContent.reset();
        dojo.printStack(null);
        assertEquals("printStack(null): Bei <null> muss \"Kein gültiger Stack\" ausgegeben werden.", "Kein gültiger Stack", outContent.toString().trim());
    }

    @Test
    public void rollAndPrint() {
        Stack<Die> s = new Stack<>();
        Stack<Die> bak = new Stack<>();
        for( int i = 0; i < 100; i++ ) {
            s.push(new StackDojoTest.MockupDie(i));
            bak.push(s.top());
        }

        dojo.rollAndPrint(s);

        String[] lines = outContent.toString().split(System.lineSeparator());
        assertEquals("rollAndPrint([..., 100]): Falsche Anzahl Zahlen ausgegeben.", 100, lines.length);
        for( int i = 0; i < lines.length; i++ ) {
            assertEquals("rollAndPrint([..., 100]): Falsches Ergebnis für Würfel " + i + " ausgegeben.", "" + (99 - i), lines[i]);
        }

        countAndAssert(bak, ( i, d ) -> {
            StackDojoTest.MockupDie d2 = (StackDojoTest.MockupDie) d;
            assertTrue("rollAndPrint([..., 100]): Würfel " + i + " sollte mindestens einmal geworfen werden.", d2.rolls > 0);
        });

        s = new Stack<>();
        outContent.reset();
        dojo.rollAndPrint(s);
        assertEquals("rollAndPrint([]): Falsche Anzahl Elemente ausgegeben.", 0, lines.length - 100);
    }

    @Test
    public void pokemonFight() {
        Stack<Pokemon> result = dojo.pokemonFight(null, null);
        assertNotNull("pokemonFight(null, null): Ergebnis darf nicht <null> sein.", result);
        assertTrue("pokemonFight(null, null): Ergebnis muss leer sein.", result.isEmpty());

        Stack<Pokemon> s1 = getTrainer1();
        result = dojo.pokemonFight(s1, null);
        assertNotNull("pokemonFight([...], null): Ergebnis darf nicht <null> sein.", result);
        int n = countAndAssert(result, (i, p) -> { });
        assertEquals("pokemonFight([...], null): Das Ergebnis muss gleich der Eingabe von Trainer 1 sein.", 5, n);

        Stack<Pokemon> s2 = getTrainer2();
        result = dojo.pokemonFight(null, s2);
        assertNotNull("pokemonFight(null, [...]): Ergebnis darf nicht <null> sein.", result);
        n = countAndAssert(result, (i, p) -> { });
        assertEquals("pokemonFight(null, [...]): Das Ergebnis muss gleich der Eingabe von Trainer 2 sein.", 3, n);

        s1 = getTrainer1();
        s2 = getTrainer2();
        result = dojo.pokemonFight(s1, s2);
        assertNotNull("pokemonFight([100/0, 100/0, 10/0, 100/110, 100/50], [100/0, 100/100, 10/0]): Ergebnis darf nicht <null> sein.", result);
        assertFalse("pokemonFight([100/0, 100/0, 10/0, 100/110, 100/50], [100/0, 100/100, 10/0]): Ergebnis darf nicht leer sein.", result.isEmpty());
        n = countAndAssert(result, (i, p) -> {
            assertEquals("pokemonFight([100/0, 100/0, 10/0, 100/110, 100/50], [100/0, 100/100, 10/0]): Das falsche Pokemon hat gewonnen.", "T1 P5", p.getName());
        });
        assertEquals("pokemonFight([100/0, 100/0, 10/0, 100/110, 100/50], [100/0, 100/100, 10/0]): Das Ergebnis darf nur ein Element enthalten.", 1, n);
    }

    private Stack<Pokemon> getTrainer1() {
        Stack<Pokemon> s1 = new Stack<>();
        s1.push(new Pokemon("T1 P5", 100, 50));
        s1.push(new Pokemon("T1 P4", 100, 110));
        s1.push(new Pokemon("T1 P3", 10, 0));
        s1.push(new Pokemon("T1 P2", 100, 0));
        s1.push(new Pokemon("T1 P1", 100, 0));
        return s1;
    }

    private Stack<Pokemon> getTrainer2() {
        Stack<Pokemon> s2 = new Stack<>();
        s2.push(new Pokemon("T2 P3", 10, 0));
        s2.push(new Pokemon("T2 P2", 100, 100));
        s2.push(new Pokemon("T2 P1", 100, 0));
        return s2;
    }


    private static <T> int countAndAssert( Stack<T> pStack, BiConsumer<Integer, T> pAssertions ) {
        Stack<T> backup = new Stack<>();

        // Count and check asserts
        int i = 0;
        for( ; !pStack.isEmpty(); i++, pStack.pop() ) {
            backup.push(pStack.top());
            pAssertions.accept(i, pStack.top());
        }

        // Move everything back into place
        for( ; !backup.isEmpty(); backup.pop() ) {
            pStack.push(backup.top());
        }

        return i;
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
