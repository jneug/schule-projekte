import greenfoot.*;

/**
 * Eine Pflanze in der Welt. Eine Pflanze ist ein Hindernis für Brenndon/Brennda,
 * das erstmal nicht überquert werden kann. Pflanzen können aber durch den
 * Einsatz des Beils entfernt werden.
 */
public class Pflanze extends Actor {

    public Pflanze() {
        int num = Greenfoot.getRandomNumber(47)+1;
        setImage("images/trees/tree"+num+".png");
    }

    /**
     * Act-Methode der Pflanze.
     */
    public void act() {
        // Die Pflanze hat keine Funktion.
    }

}

