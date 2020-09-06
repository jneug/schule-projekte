import greenfoot.*;

/**
 * Ein Hügel in der Mars-Welt. Ein Hügel ist ein Hindernis für dne Rover, das
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
     * Act-Methode des Hügels.
     */
    public void act() {
        // Der Hügel hat keine Funktion.
    }

    public int getSteigung() {
        return steigung;
    }

}

