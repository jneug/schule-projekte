import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Kuh extends Actor {

    private String name;
    private int milchmenge;
    private Bauer besitzer;


    public Kuh( String einName ) {
        name = einName;
        milchmenge = 0;
    }

    public int milchGeben() {
        int menge = milchmenge;
        milchmenge = 0;
        return menge;
    }

    public Kuh nachwuchsBekommen() {
        return new Kuh("Kalb von " + name);
    }

    public void fressen() {
        milchmenge += 2;
    }

    public void setzeBesitzer( Bauer einBauer ) {
        besitzer = einBauer;
    }

}
