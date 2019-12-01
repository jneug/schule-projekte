import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Rover extends Actor {

    // Wasservorrat des Rovers
    private int wasser = 0;

    // Energievorrat des Rovers
    private int energie = 0;

    // Mineralien des Rovers
    private int mineralien = 0;

    // Name des Rovers
    protected String name;


    public Rover() {
        roverErstellen();
    }

    /**
     * Wird beim erstellen aufgerufen und sollte <var>name</var>
     * und <var>bild</var> des Rovers setzen.
     *
     * Üblicherweise hat die Methode in Unterklassen die Form
     * <pre>
     * name = "Mein Rover";
     * bild = "images/roverBlau.png";
     * </pre>
     */
    protected void roverErstellen() {
        name = "Rover " + Utils.zufallsInt(50);
    }

    public void act() {};

    /**
     * Der Rover bewegt sich ein Feld in Fahrtrichtung weiter.
     * Sollte sich in Fahrtrichtung ein Objekt der Klasse Huegel befinden oder
     * er sich an der Grenze der Welt befinden, dann bewegt sich der Rover nicht.
     * <p>
     * Dieser Auftrag kostet <b>4 Energie</b>.
     */
    public final void fahre() {
        Referee.getInstance().fahre(this);
    }

    /**
     * Der Rover dreht sich um 90 Grad in die Richtung, die mit <var>richtung</var>
     * (<code>"links"</code> oder <code>"rechts"</code>) übergeben wurde.
     * Sollte ein anderer Text (String) übergeben werden, dann passiert nichts.
     * <p>
     * Dieser Auftrag kostet <b>2 Energie</b>.
     */
    public final void drehe( String pRichtung ) {
        Referee.getInstance().drehe(this, pRichtung);
    }

    /**
     * Der Rover überprüft, ob sich in Richtung ("rechts", "links", oder "vorne")
     * ein Objekt der Klasse Huegel befindet.
     * Sollte ein unbekannter Text (String) übergeben werden, dann wird
     * <code>false</code> zurück gegeben.
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

    /**
     * Der Rover überprüft, ob sich in Richtung ("rechts", "links", oder "vorne")
     * ein Objekt der Klasse Rover befindet.
     * Sollte ein unbekannter Text (String) übergeben werden, dann wird
     * <code>false</code> zurück gegeben.
     */
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
     * Sofern auf seiner Position ein Gestein vorhanden ist ermittelt der Rover
     * den Wassergehalt und nimmt diesen in seinen Wasserspeicher auf.
     * <p>
     * Dieser Auftrag kostet <b>4 Energie</b>.
     */
    public final void analysiereGestein() {
        Referee.getInstance().analysiereGestein(this);
    }

    /**
     * Der Rover konvertiert eine <var>menge</var> an Wasser in ein <var>produkt</var>.
     * Das Produkt wird als Text (String) übergeben und kann entweder "energie"
     * oder "mineralien" sein.
     * <li>Wasser wird 1-zu-1 in Energie umgewandelt.
     * <li>Wasser wird 1-zu-2 in Mineralien umgewandelt, wobei gleichzeitig 1-zu-1
     * Energie abgezogen wird (5 Wasser und 5 Energie werden in 10 Mineralien konvertiert)
     */
    public final void konvertiereWasser( int menge, String produkt ) {
        Referee.getInstance().konvertiereWasser(this, menge, produkt);
    }

    /**
     * Der Rover erzeugt ein Objekt der Klasse Marke auf seiner Position, sofern
     * er noch Marken setzen kann. Jeder Rover kann fünf Marken setzen, sofern
     * er nicht andere Marken aufnimmt.
     * <p>
     * Dieser Auftrag kostet <b>1 Energie</b>.
     */
    public final void setzeMarke() {
        Referee.getInstance().setzeMarke(this);
    }

    /**
     * Der Rover überprüft, ob sich an seiner Position ein Objekt der Klasse
     * Gestein befindet.
     */
    public final boolean gesteinVorhanden() {
        return (getOneObjectAtOffset(0,0,Gestein.class) != null);
    }

    /**
     * Der Rover überprüft, ob sich an seiner Position ein Objekt der Klasse
     * Marke befindet.
     */
    public final boolean markeVorhanden() {
        return (getOneObjectAtOffset(0,0,Marke.class) != null);
    }

    /**
     * Der Rover überprüft, ob sich an seiner Position ein Objekt der Klasse
     * Marke befinde, das er selber dort erzeugt hat. Um zu ermitteln, ob eine
     * Marke vorhanden ist, die von einem anderen Rover stammt, kann diese Anfrage mit
     * <code>markeVorhanden()</code> kombiniert werden:
     * <pre>
     *     if( markeVorhanden() &6 !meineMarkeVorhanden() ) { ... }
     * </pre>
     */
    public final boolean meineMarkeVorhanden() {
        Actor m = getOneObjectAtOffset(0,0,Marke.class);
        if( m != null ) {
            return this.equals( ((Marke)m).getBesitzer() );
        } else {
            return false;
        }
    }

    /**
     * Entfernt eine Marke von der Position des Rovers, sofern eine vorhanden ist
     * und der Rover noch Platz für Marken hat. Jeder Rover startet mit 5 Marken
     * und kann maximal 8 tragen. Konnte der Rover die Marke entfernen, dann kann
     * er sie mit <code>setzeMarke()</code> wieder ablegen.
     * <p>
     * Dieser Auftrag kostet <b>2 Energie</b>.
     */
    public final void entferneMarke() {
        Referee.getInstance().entferneMarke(this);
    }


    protected final void addedToWorld( World world ) {
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
