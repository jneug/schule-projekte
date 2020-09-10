import greenfoot.*;

/**
 * Ein Hügel in der Mars-Welt. Ein Hügel ist ein Hindernis für dne Rover, das
 * nicht befahren werden kann.
 */
public class Pflanze extends Actor {

    public Pflanze() {
        int num = Greenfoot.getRandomNumber(47)+1;
        setImage("images/trees/tree"+num+".png");
    }

    /**
     * Act-Methode des Hügels.
     */
    public void act() {
        // Der Hügel hat keine Funktion.
    }

}

