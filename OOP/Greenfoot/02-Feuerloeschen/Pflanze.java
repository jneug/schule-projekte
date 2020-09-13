import greenfoot.*;

/**
 * Eine Pflanze in der Welt. Eine Pflanze ist ein Hindernis für Brenndon/Brennda,
 * das erstmal nicht überquert werden kann. Pflanzen können aber durch den
 * Einsatz des Beils entfernt werden.
 */
public class Pflanze extends Actor {

    public Pflanze() {
        int num = Greenfoot.getRandomNumber(25)+1;
        if( num < 10 )
            setImage("images/tree0"+num+".png");
        else
            setImage("images/tree"+num+".png");
    }

    /**
     * Act-Methode der Pflanze.
     */
    public void act() {
        // Die Pflanze hat keine Funktion.
    }

}

