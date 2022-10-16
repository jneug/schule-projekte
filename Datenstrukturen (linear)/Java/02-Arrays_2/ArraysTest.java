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
        assertEquals("Falsche Summe berechnet für [1,6,4].", 11, arrays.diceSum(dice));

        for( int i = 0; i < 10; i++ ) {
            int sum = 0;
            dice = new Wuerfel[rand.nextInt(18)+2];
            String[] values = new String[dice.length];
            for( int j = 0; j < dice.length; j++ ) {
                int w = rand.nextInt(7)+1;
                if( w == 7 ) {
                    dice[j] = null;
                    values[j] = "null";
                } else {
                    dice[j] = new MockupWuerfel(w);
                    values[j] = Integer.toString(w);
                    sum += w;
                }
            }

            assertEquals("Falsche Summe berechnet für [" + String.join(",", values) + "].", sum, arrays.diceSum(dice));
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
            assertTrue( "Der Würfel "+die.getName()+" (von 50) wurde nicht geworfen (Array ohne null-Referenzen).", die.rolls == 1);
        }

        for( int i = 0; i < dice.length; i+=3 ) {
            dice[i] = null;
        }

        arrays.rollAll(dice);

        for( int i = 0; i < dice.length; i++ ) {
            if( dice[i] != null ) {
                MockupWuerfel die = (MockupWuerfel) dice[i];
                assertTrue("Der Würfel " + die.getName() + " wurde nicht geworfen (Array mit null-Referenzen).", die.rolls == 2);
            }
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
        assertEquals("Falsche Summe berechnet [[1,6,4], [5,5,5], [1,1,0], [-3,-2,-4]].", 19, arrays.twoDimSum(dice));

        for( int i = 0; i < 10; i++ ) {
            int sum = 0;
            dice = new Wuerfel[rand.nextInt(18)+2][rand.nextInt(18)+2];
            for( int j = 0; j < dice.length; j++ ) {
                for( int k = 0; k < dice[j].length; k++ ) {
                    int w = rand.nextInt(7)+1;
                    if( w < 7 ) {
                        dice[j][k] = new MockupWuerfel(w);
                        sum += w;
                    }
                }
            }

            assertEquals("Falsche Summe berechnet (Array mit null-Referenzen).", sum, arrays.twoDimSum(dice));
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
		assertEquals("Ergebnisarray hat die falsche Größe (4x3 Matrix)", 4, sums.length);
		assertEquals("Erste Zeilensumme nicht korrekt (4x3 Matrix)", 11, sums[0]);
		assertEquals("Zweite Zeilensumme nicht korrekt (4x3 Matrix)", 15, sums[1]);
		assertEquals("Dritte Zeilensumme nicht korrekt (4x3 Matrix)", 2, sums[2]);
		assertEquals("Vierte Zeilensumme nicht korrekt (4x3 Matrix)", -9, sums[3]);
    }

}
