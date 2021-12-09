

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test für die Klasse Calculator.
 */
public class CalculatorTest {

    @Test
    public void testAddEvenNumbers() {
        Calculator calc = new Calculator();

        int[][] tests = new int[][]{
            new int[]{0, 0},
            new int[]{1, 0},
            new int[]{4, 6},
            new int[]{5, 6},
            new int[]{24, 156}
        };
        for (int i = 0; i < tests.length; i++) {
            int sum = calc.addEvenNumbers(tests[i][0]);
            assertEquals("addEvenNumbers(" + tests[i][0] + ")", tests[i][1], sum);
        }
    }

    @Test
    public void testGgT() {
        Calculator calc = new Calculator();

        int[][] tests = new int[][]{
            new int[]{0, 0, 0},
            new int[]{2, 4, 2},
            new int[]{4, 4, 4},
            new int[]{12, 44, 4},
            new int[]{44, 12, 4},
            new int[]{24, 18, 6},
            new int[]{18, 24, 6},
            new int[]{1, 18, 1},
            new int[]{24, 7, 1}
        };
        for (int i = 0; i < tests.length; i++) {
            int ggt = calc.ggT(tests[i][0], tests[i][1]);
            assertEquals("ggT(" + tests[i][0] + "," + tests[i][1] + ")", tests[i][2], ggt);
        }
    }

    @Test
    public void testKgV() {
        Calculator calc = new Calculator();

        int[][] tests = new int[][]{
            new int[]{4, 4, 4},
            new int[]{44, 12, 132},
            new int[]{12, 44, 132},
            new int[]{4, 12, 12},
            new int[]{24, 18, 72},
            new int[]{24, 7, 168}

        };
        for (int i = 0; i < tests.length; i++) {
            int kgv = calc.kgV(tests[i][0], tests[i][1]);
            assertEquals("kgV(" + tests[i][0] + "," + tests[i][1] + ")", tests[i][2], kgv);
        }
    }
}
