import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Test-Klasse für das ArrayDojo.
 * <p>
 * Starte die Tests mit einem Rechtsklick auf die Klasse im BlueJ Projektfenster
 * und durch Auswahl der passenden Testmethode. Du kannst alle Tests in der
 * linken Seitenleiste starten, indem du auf den kleinen Pfeil klickst und
 * "Tests starten" wählst.
 *
 * <b>Achtung:</b> Verändere diese Klasse nicht! Du brauchst dir
 * diesen Quelltext auch nicht ansehen. Es reicht, die Tests zur Überprüfung
 * deiner Lösungen zu nutzen.
 */
public class ArrayDojoTest {

    private static int n = 0;

    private ArrayDojo ad;

    private int[][] numbers;

    private int numbersLen;

    private MockupDice[] dice;

    private MockupDice[][] dice2dim;

    @Before
    public void setUp() {
        ad = new ArrayDojo();
        // Test-Arrays vorbereiten
        numbers = new int[][]{
            {45, 64, 56, 67, 23, 7, 8, 34, 54, 2, 24, 6, 72, 334, 456},
            {424, 766, 239, 578, 228, 15, 415, 619, 19, 74},
            {31, 65, 819, 484},
            {980, 792, 833, 832, 19, 751, 929, 708, 95, 86, 424, 181, 720, 89, 31, 327, 734, 675, 64, 982}
        };
        for( int[] i : numbers ) {
            numbersLen += i.length;
        }

        dice = new MockupDice[numbers[0].length];
        for( int i = 0; i < dice.length; i++ ) {
            dice[i] = new MockupDice(numbers[0][i]);
        }

        dice2dim = new MockupDice[numbers.length][];
        for( int i = 0; i < numbers.length; i++ ) {
            dice2dim[i] = new MockupDice[numbers[i].length];
            for( int j = 0; j < numbers[i].length; j++ ) {
                dice2dim[i][j] = new MockupDice(numbers[i][j]);
            }
        }
    }

    @Test
    public void testRollAll() {
        try {
            ad.rollAll(dice);

            // Check dice for number of rolls (need to be 1)
            for( int i = 0; i < dice.length; i++ ) {
                assertTrue("Der " + (i + 1) + "-te wurde nicht geworfen (Array ohne null).", dice[i].rolls == 1);
            }

            // Set some references to null and roll again
            for( int i = 0; i < dice.length; i += 3 ) {
                dice[i] = null;
            }
            ad.rollAll(dice);

            // Check for number of rolls (needs to be 2 now)
            for( int i = 0; i < dice.length; i++ ) {
                if( dice[i] != null ) {
                    assertTrue("Der " + (i + 1) + "-te wurde nicht geworfen (Array mit null).", dice[i].rolls == 2);
                }
            }
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testDiceSum() {
        try {
            Random rand = new Random();

            Dice[] dice;

            dice = new Dice[]{
                new MockupDice(1),
                new MockupDice(6),
                new MockupDice(4),
            };
            assertEquals("diceSum({W1<1>,W2<6>,W3<4>}).", 11, ad.diceSum(dice));

            for( int i = 0; i < 10; i++ ) {
                int sum = 0;
                dice = new Dice[rand.nextInt(18) + 2];
                String[] values = new String[dice.length];
                for( int j = 0; j < dice.length; j++ ) {
                    int w = rand.nextInt(7) + 1;
                    if( w == 7 ) {
                        dice[j] = null;
                        values[j] = "null";
                    } else {
                        dice[j] = new MockupDice(w);
                        values[j] = Integer.toString(w);
                        sum += w;
                    }
                }

                assertEquals("diceSum({" + String.join(",", values) + "})", sum, ad.diceSum(dice));
            }
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testTwoDimRoll() {
        try {
            ad.twoDimRoll(dice2dim);

            // Check for number of rolls (needs to be 1)
            for( int i = 0; i < dice2dim.length; i++ ) {
                for( int j = 0; j < dice2dim[i].length; j++ ) {
                    MockupDice die = dice2dim[i][j];
                    assertTrue("Der Würfel [" + i + "][" + j + "] wurde nicht geworfen (Array ohne null).", die.rolls == 1);
                }
            }

            // Set some references to null and roll again
            for( int i = 0; i < dice2dim.length; i += 3 ) {
                for( int j = 0; j < dice2dim[i].length; j += 3 ) {
                    dice2dim[i][j] = null;
                }
            }
            ad.twoDimRoll(dice2dim);

            // Check for number of rolls (needs to be 2 now)
            for( int i = 0; i < dice2dim.length; i++ ) {
                for( int j = 0; j < dice2dim[i].length; j++ ) {
                    MockupDice die = dice2dim[i][j];
                    if( die != null ) {
                        assertTrue("Der Würfel [" + i + "][" + j + "] wurde nicht geworfen (Array mit null).", die.rolls == 2);
                    }
                }
            }
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testTwoDimSum() {
        try {
            Random rand = new Random();

            Dice[][] dice;

            dice = new Dice[][]{
                new Dice[]{
                    new MockupDice(1),
                    new MockupDice(6),
                    new MockupDice(4)
                },
                new Dice[]{
                    new MockupDice(5),
                    new MockupDice(5),
                    new MockupDice(5)
                },
                new Dice[]{
                    new MockupDice(1),
                    new MockupDice(1),
                    new MockupDice(0)
                },
                new Dice[]{
                    new MockupDice(-3),
                    new MockupDice(-2),
                    new MockupDice(-4)
                }
            };
            assertEquals("twoDimSum({{1,6,4},{5,5,5},{1,1,0},{-3,-2,-4}})", 19, ad.twoDimSum(dice));

            for( int i = 0; i < 10; i++ ) {
                int sum = 0;
                dice = new Dice[rand.nextInt(18) + 2][rand.nextInt(18) + 2];
                for( int j = 0; j < dice.length; j++ ) {
                    for( int k = 0; k < dice[j].length; k++ ) {
                        int w = rand.nextInt(7) + 1;
                        if( w < 7 ) {
                            dice[j][k] = new MockupDice(w);
                            sum += w;
                        }
                    }
                }
                assertEquals("Falsche Summe berechnet (Array mit null-Referenzen).", sum, ad.twoDimSum(dice));
            }
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testRowSum() {
        try {
            Dice[][] dice;

            dice = new Dice[][]{
                new Dice[]{
                    new MockupDice(1),
                    new MockupDice(6),
                    new MockupDice(4)
                },
                new Dice[]{
                    new MockupDice(5),
                    new MockupDice(5),
                    new MockupDice(5)
                },
                new Dice[]{
                    new MockupDice(1),
                    new MockupDice(1),
                    new MockupDice(0)
                },
                new Dice[]{
                    new MockupDice(-3),
                    new MockupDice(-2),
                    new MockupDice(-4)
                }
            };

            int[] sums = ad.rowSum(dice);
            assertNotNull(sums);
            assertEquals("Ergebnisarray hat die falsche Größe.", 4, sums.length);
            assertEquals("Erste Zeilensumme nicht korrekt.", 11, sums[0]);
            assertEquals("Zweite Zeilensumme nicht korrekt.", 15, sums[1]);
            assertEquals("Dritte Zeilensumme nicht korrekt.", 2, sums[2]);
            assertEquals("Vierte Zeilensumme nicht korrekt.", -9, sums[3]);
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    @Test
    public void testFlatten() {
        try {
            int[] flattened;

            flattened = ad.flatten(numbers);
            assertNotNull("Das Ergebnis darf nicht <null> sein", flattened);
            assertEquals("Das Ergebnis muss alle Elemente enthalten.", numbersLen, flattened.length);

            int k = 0;
            for( int i = 0; i < numbers.length; i++ ) {
                for( int j = 0; j < numbers[i].length; j++ ) {
                    assertEquals((k + 1) + "-tes Element nicht korrekt", numbers[i][j], flattened[k]);
                    k += 1;
                }
            }

            numbersLen -= numbers[1].length;
            numbers[1] = null;

            flattened = ad.flatten(numbers);
            assertNotNull("Das Ergebnis darf nicht <null> sein", flattened);
            assertEquals("Das Ergebnis muss alle Elemente enthalten.", numbersLen, flattened.length);

            k = 0;
            for( int i = 0; i < numbers.length; i++ ) {
                if( i != 1 ) {
                    for( int j = 0; j < numbers[i].length; j++ ) {
                        assertEquals((k + 1) + "-tes Element nicht korrekt", numbers[i][j], flattened[k]);
                        k += 1;
                    }
                }
            }
        } catch( ArrayIndexOutOfBoundsException ex ) {
            fail("Zugriff auf einen nicht vorhandenen Index: " + ex.getLocalizedMessage());
        }
    }

    private class MockupDice extends Dice {

        public int mockupValue;

        public int rolls = 0;

        public MockupDice( int pValue ) {
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
