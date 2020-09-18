import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class ArraysTest {

    private static int n = 0;

    private class MockupWuerfel extends Wuerfel {

        public int mockupValue;

        public int rolls = 0;

        public MockupWuerfel(int pValue) {
            super("W" + (n++));
            mockupValue = pValue;
            rolls = 0;
        }

        @Override
        public int getAugenzahl() {
            return mockupValue;
        }

        @Override
        public void werfen() {
            rolls = rolls + 1;
        }
    }

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
    public void testDiceSum() {
        Random rand = new Random();

        Wuerfel[] dice;

        dice = new Wuerfel[]{
            new MockupWuerfel(1),
            new MockupWuerfel(6),
            new MockupWuerfel(4),
        };
        assertEquals("Falsche Summe berechnet.", 11, arrays.diceSum(dice));

        for( int i = 0; i < 10; i++ ) {
            int sum = 0;
            dice = new Wuerfel[rand.nextInt(18)+2];
            for( int j = 0; j < dice.length; j++ ) {
                int w = rand.nextInt(6)+1;
                dice[j] = new MockupWuerfel(w);
                sum += w;
            }

            assertEquals("Falsche Summe berechnet.", sum, arrays.diceSum(dice));
        }
    }

    @Test
    public void testRollAll() {
        Wuerfel[] dice = new Wuerfel[50];
        for( int i = 0; i < dice.length; i++ ) {
            dice[i] = new MockupWuerfel(0);
        }

        arrays.rollAll(dice);

        for( int i = 0; i < dice.length; i++ ) {
            MockupWuerfel die = (MockupWuerfel)dice[i];
            assertTrue( "Der Würfel "+die.getName()+" wurde nicht geworfen.", die.rolls == 1);
        }
    }

    @Test
    public void testTwoDimSum() {
        Random rand = new Random();

        Wuerfel[][] dice;

        dice = new Wuerfel[][]{
            new Wuerfel[]{
                new MockupWuerfel(1),
                new MockupWuerfel(6),
                new MockupWuerfel(4)
            },
            new Wuerfel[]{
                new MockupWuerfel(5),
                new MockupWuerfel(5),
                new MockupWuerfel(5)
            },
            new Wuerfel[]{
                new MockupWuerfel(1),
                new MockupWuerfel(1),
                new MockupWuerfel(0)
            },
            new Wuerfel[]{
                new MockupWuerfel(-3),
                new MockupWuerfel(-2),
                new MockupWuerfel(-4)
            }
        };
        assertEquals("Falsche Summe berechnet.", 19, arrays.twoDimSum(dice));

        for( int i = 0; i < 10; i++ ) {
            int sum = 0;
            dice = new Wuerfel[rand.nextInt(18)+2][rand.nextInt(18)+2];
            for( int j = 0; j < dice.length; j++ ) {
                for( int k = 0; k < dice[j].length; k++ ) {
                    int w = rand.nextInt(6)+1;
                    dice[j][k] = new MockupWuerfel(w);
                    sum += w;
                }
            }

            assertEquals("Falsche Summe berechnet.", sum, arrays.twoDimSum(dice));
        }
    }

    @Test
    public void testTwoDimRoll() {
        Wuerfel[][] dice = new Wuerfel[50][45];
        for( int i = 0; i < dice.length; i++ ) {
            for( int j = 0; j < dice[i].length; j++ ) {
                dice[i][j] = new MockupWuerfel(0);
            }
        }

        arrays.twoDimRoll(dice);

        for( int i = 0; i < dice.length; i++ ) {
            for( int j = 0; j < dice[i].length; j++ ) {
                MockupWuerfel die = (MockupWuerfel)dice[i][j];
                assertTrue( "Der Würfel "+die.getName()+" wurde nicht geworfen.", die.rolls == 1);
            }
        }
    }

    @Test
    public void testRowSum() {
        Wuerfel[][] dice;

        dice = new Wuerfel[][]{
            new Wuerfel[]{
                new MockupWuerfel(1),
                new MockupWuerfel(6),
                new MockupWuerfel(4)
            },
            new Wuerfel[]{
                new MockupWuerfel(5),
                new MockupWuerfel(5),
                new MockupWuerfel(5)
            },
            new Wuerfel[]{
                new MockupWuerfel(1),
                new MockupWuerfel(1),
                new MockupWuerfel(0)
            },
            new Wuerfel[]{
                new MockupWuerfel(-3),
                new MockupWuerfel(-2),
                new MockupWuerfel(-4)
            }
        };

        int[] sums = arrays.rowSum(dice);
        assertEquals("Ergebnisarray hat die falsche Größe", 4, sums.length);
        assertEquals("Erste Zeilensumme nicht korrekt", 11, sums[0]);
        assertEquals("Zweite Zeilensumme nicht korrekt", 15, sums[1]);
        assertEquals("Dritte Zeilensumme nicht korrekt", 2, sums[2]);
        assertEquals("Vierte Zeilensumme nicht korrekt", -9, sums[3]);
    }

}
