import greenfoot.*;

/**
 * Ein Gestein in der Mars-Welt. Ein Gestein hat einen zufällig bestimmten
 * Wassergehalt.
 */
public class Gestein extends Actor {

    private String farbe;

    private int wassergehalt;

    public Gestein() {
        this(-1);
    }

    public Gestein( int pWassergehalt ) {
        if( pWassergehalt < 0 || pWassergehalt > 19 ) {
            wassergehalt = Greenfoot.getRandomNumber(20);
        } else {
            wassergehalt = pWassergehalt;
        }
        setImage("images/felsen.png");
    }

    /**
     * Act-Methode des Gesteins.
     */
    public void act() {
        // Das Gestein hat keine Funktion.
    }

    public int getWassergehalt() {
        return wassergehalt;
    }

}
