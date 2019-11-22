import greenfoot.Greenfoot;

public class Utils {

    public static int zufallsInt( int max ) {
        return Greenfoot.getRandomNumber(max);
    }

    public static int zufallsInt( int min, int max ) {
        return Greenfoot.getRandomNumber(max-min+1)+min;
    }

    public static boolean zufallsBool() {
        return Greenfoot.getRandomNumber(2) == 0;
    }

    public static boolean zufallsBool( int wahrscheinlichkeit ) {
        return Greenfoot.getRandomNumber(100) < wahrscheinlichkeit;
    }
}
