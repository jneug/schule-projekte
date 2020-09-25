import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Bauer extends Actor {

    private String name;
    private int milchVorrat;
    private Kuh besitzt;


    public Bauer( String einName ) {
        name = einName;
        milchVorrat = 10;
        besitzt = null;
    }

    public void füttern() {
        if( besitzt != null ) {
            besitzt.fressen();
        }
    }

    public void melken() {
        if( besitzt != null ) {
            milchVorrat += besitzt.milchGeben();
        }
    }

    public void kuhKaufen( Kuh eineKuh ) {
        besitzt = eineKuh;
    }

    public void kuhVerkaufen( Bauer einBauer ) {
        if( besitzt == null ) {
            return;  // Abbruch
        } else {
            einBauer.kuhKaufen(besitzt);
            besitzt.setzeBesitzer(einBauer);
            besitzt = null;
        }
    }

}
