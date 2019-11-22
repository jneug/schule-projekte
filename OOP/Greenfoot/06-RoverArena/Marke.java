import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Marke
 */
public class Marke extends Actor {

    private Rover besitzer = null;

    public Marke() {
        setImage("images/marke.png");
    }

    public Marke( Rover pRover ) {
        this();
        besitzer = pRover;
    }

    public void act() {}

    public Rover getBesitzer() {
        return besitzer;
    }

}
