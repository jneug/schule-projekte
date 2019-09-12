import java.util.Random;

public class Die {

    private int maxValue, minValue;

    private Random rand;

    public Die( int pMaxValue ) {
        this(1, pMaxValue);
    }

    public Die( int pMinValue, int pMaxValue ) {
        maxValue = pMaxValue;
        minValue = pMinValue;
        rand = new Random();
    }

    public int roll() {
        return rand.nextInt(maxValue) + minValue;
    }

    public int[] roll( int pTimes ) {
        int[] results = new int[pTimes];
        for(; pTimes > 0; pTimes-- ) {
            results[pTimes-1] = roll();
        }
        return results;
    }

}
