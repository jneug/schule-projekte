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

        if( Greenfoot.getRandomNumber(2) == 0 ) {
            farbe = "rot";
            setImage("images/gesteinRot.png");
        } else {
            farbe = "blau";
            setImage("images/gesteinBlau.png");
        }
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
