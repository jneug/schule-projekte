import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ein Gestein in der Rover-Welt.
 *
 * Gesteine enthalten Wasser, das ein Rover durch Analyse extrahieren kann.
 */
public class Gestein extends Actor {

    private static int MIN_WASSER = 50, MAX_WASSER = 300;

    private int wassergehalt;

    public  Gestein() {
        wassergehalt = Utils.zufallsInt(MIN_WASSER, MAX_WASSER);

        if( wassergehalt > (MAX_WASSER-MIN_WASSER)*.75 ) {
            setImage("images/gesteinRot.png");
        } else {
            setImage("images/gesteinBlau.png");
        }
    }

    public void act() {}

    public int getWassergehalt()
    {
        return wassergehalt;
    }

}
