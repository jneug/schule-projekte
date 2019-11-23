import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Rover extends Actor {

    private int wasser = 0;

    private int energie = 1000;

    private int mineralien = 0;

    protected String name, bild;

    public Rover() {
        roverErstellen();
    }

    protected void roverErstellen() {
        name = "Rover " + Utils.zufallsInt(50);
        bild = "images/rover.png";
    }

    public void act() {};

    /**
     * Der Rover bewegt sich ein Feld in Fahrtrichtung weiter.
     * Sollte sich in Fahrtrichtung ein Objekt der Klasse Huegel befinden oder
     * er sich an der Grenze der Welt befinden, dann bewegt sich der Rover nicht.
     */
    public final void fahre() {
        Referee.getInstance().fahre(this);
    }

    /**
     * Der Rover dreht sich um 90 Grad in die Richtung, die mit <var>richtung</var>
     * (<code>"links"</code> oder <code>"rechts"</code>) übergeben wurde.
     * Sollte ein anderer Text (String) übergeben werden, dann passiert nichts.
     */
    public final void drehe( String pRichtung ) {
        Referee.getInstance().drehe(this, pRichtung);
    }

    /**
     * Der Rover ?berpr?ft, ob sich in richtung ("rechts", "links", oder "vorne") ein Objekt der Klasse Huegel befindet.
     * Das Ergebnis wird auf dem Display angezeigt.
     * Sollte ein anderer Text (String) als "rechts", "links" oder "vorne" ?bergeben werden, dann erscheint eine entsprechende Meldung auf dem Display.
     */
    public final boolean huegelVorhanden( String richtung ) {
        int rot = getRotation();

        if( richtung == "vorne" && rot == 0 || richtung == "rechts" && rot == 270
            || richtung == "links" && rot == 90 ) {
            if( getOneObjectAtOffset(1, 0, Huegel.class) != null && ((Huegel) getOneObjectAtOffset(1, 0, Huegel.class)).getSteigung() > 30 ) {
                return true;
            }
        }

        if( richtung == "vorne" && rot == 180 || richtung == "rechts" && rot == 90
            || richtung == "links" && rot == 270 ) {
            if( getOneObjectAtOffset(-1, 0, Huegel.class) != null && ((Huegel) getOneObjectAtOffset(-1, 0, Huegel.class)).getSteigung() > 30 ) {
                return true;
            }
        }

        if( richtung == "vorne" && rot == 90 || richtung == "rechts" && rot == 0
            || richtung == "links" && rot == 180 ) {
            if( getOneObjectAtOffset(0, 1, Huegel.class) != null && ((Huegel) getOneObjectAtOffset(0, 1, Huegel.class)).getSteigung() > 30 ) {
                return true;
            }

        }

        if( richtung == "vorne" && rot == 270 || richtung == "rechts" && rot == 180
            || richtung == "links" && rot == 0 ) {
            if( getOneObjectAtOffset(0, -1, Huegel.class) != null && ((Huegel) getOneObjectAtOffset(0, -1, Huegel.class)).getSteigung() > 30 ) {
                return true;
            }

        }

        return false;
    }

    public final boolean roverVorhanden( String richtung ) {
        int rot = getRotation();

        if( richtung == "vorne" && rot == 0 || richtung == "rechts" && rot == 270
            || richtung == "links" && rot == 90 ) {
            return (getOneObjectAtOffset(1, 0, Rover.class) != null);
        }

        if( richtung == "vorne" && rot == 180 || richtung == "rechts" && rot == 90
            || richtung == "links" && rot == 270 ) {
            return (getOneObjectAtOffset(-1, 0, Rover.class) != null);
        }

        if( richtung == "vorne" && rot == 90 || richtung == "rechts" && rot == 0
            || richtung == "links" && rot == 180 ) {
            return (getOneObjectAtOffset(0, 1, Huegel.class) != null);
        }

        if( richtung == "vorne" && rot == 270 || richtung == "rechts" && rot == 180
            || richtung == "links" && rot == 0 ) {
            return (getOneObjectAtOffset(0, -1, Huegel.class) != null);
        }

        return false;
    }

    /**
     * Wenn sich auf dem Feld vor dem Rover ein anderer Rover befindet, wird dem
     * anderen Rover ein Teil seiner Energie aus der Batterie abgezogen.
     */
    public final void energieAbziehen() {
        Referee.getInstance().energieAbziehen(this);
    }

    /**
     * Der Rover ermittelt den Wassergehalt des Gesteins auf seiner Position und gibt diesen auf dem Display aus.
     * Sollte kein Objekt der Klasse Gestein vorhanden sein, dann erscheint eine entsprechende Meldung auf dem Display.
     */
    public final void analysiereGestein() {
        Referee.getInstance().analysiereGestein(this);
    }

    public final void konvertiereWasser( int menge, String produkt ) {
        Referee.getInstance().konvertiereWasser(this, menge, produkt);
    }

    /**
     * Der Rover erzeugt ein Objekt der Klasse ?Markierung? auf seiner Position.
     */
    public final void setzeMarke() {
        Referee.getInstance().setzeMarke(this);
    }

    /**
     * Der Rover gibt durch einen Wahrheitswert (true oder false )zur?ck, ob sich auf seiner Position ein Objekt der Klasse Gestein befindet.
     * Eine entsprechende Meldung erscheint auch auf dem Display.
     */
    public final boolean gesteinVorhanden() {
        return (getOneObjectAtOffset(0,0,Gestein.class) != null);
    }

    /**
     * *Der Rover gibt durch einen Wahrheitswert (true oder false )zur?ck, ob sich auf seiner Position ein Objekt der Marke befindet.
     * Eine entsprechende Meldung erscheint auch auf dem Display.
     */
    public final boolean markeVorhanden() {
        return (getOneObjectAtOffset(0,0,Marke.class) != null);
    }

    public final boolean meineMarkeVorhanden() {
        Actor m = getOneObjectAtOffset(0,0,Marke.class);
        if( m != null ) {
            return this.equals( ((Marke)m).getBesitzer() );
        } else {
            return false;
        }
    }

    public final void entferneMarke() {
        Referee.getInstance().entferneMarke(this);
    }

    protected final void addedToWorld( World world ) {
        setImage(bild);
        Referee.getInstance().addRover(this);
    }

    public final int getWasser() {
        return wasser;
    }

    public final void setWasser( int wasser ) {
        this.wasser = wasser;
    }

    public final int getEnergie() {
        return energie;
    }

    public final void setEnergie( int energie ) {
        this.energie = energie;
    }

    public final int getMineralien() {
        return mineralien;
    }

    public final void setMineralien( int mineralien ) {
        this.mineralien = mineralien;
    }

    public final String getName() {
        return name;
    }
}
