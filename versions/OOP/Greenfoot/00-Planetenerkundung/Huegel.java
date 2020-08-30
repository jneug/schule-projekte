import greenfoot.*;

/**
 * Ein H�gel in der Mars-Welt. Ein H�gel ist ein Hindernis f�r dne Rover, das
 * nicht befahren werden kann.
 */
public class Huegel extends Actor {

    private int steigung;

    public Huegel() {
        this(-1);
    }

    public Huegel( int pSteigung ) {
        if( pSteigung < 31 || pSteigung > 60 ) {
            steigung = Greenfoot.getRandomNumber(30) + 31;
        } else {
            steigung = pSteigung;
        }
        setImage("images/huegel.png");
    }

    /**
     * Act-Methode des H�gels.
     */
    public void act() {
        // Der H�gel hat keine Funktion.
    }

    public int getSteigung() {
        return steigung;
    }

}

