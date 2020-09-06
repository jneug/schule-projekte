import greenfoot.*;

/**
 * Rover sind die einzigen aktiven Akteure in der Mars-Welt.
 */
public class Rover extends Actor {

    private Display anzeige;

    /**
     * Act-Methode des Rovers. Programmiere hier deinen Algorithmus und starte
     * ihn mit dem "Act"-Button in Greenfoot.
     */
    public void act() {

    }

    /**
     * Der Rover bewegt sich ein Feld in Fahrtrichtung weiter.
     * Sollte sich in Fahrtrichtung ein Objekt der Klasse Huegel befinden oder
     * er sich an der Grenze der Welt befinden, dann erscheint eine
     * entsprechende Meldung auf dem Display.
     */
    public void fahre() {
        int posX = getX();
        int posY = getY();

        if( huegelVorhanden("vorne") ) {
            nachricht("Zu steil!");
        } else if( getRotation() == 270 && getY() == 1 ) {
            nachricht("Ich kann mich nicht bewegen");
        } else {
            move(1);
            Greenfoot.delay(1);
        }

        if( posX == getX() && posY == getY() && !huegelVorhanden("vorne") ) {
            nachricht("Ich kann mich nicht bewegen");
        }
    }

    /**
     * Der Rover dreht sich um 90 Grad in die Richtung, die mit <var>richtung</var>
     * ("links" oder "rechts") übergeben wurde. Sollte ein anderer Text (String)
     * als "links" oder "rechts" übergeben werden, dann erscheint eine
     * entsprechende Meldung auf dem Display.
     * @param richtung "links" oder "rechts"
     */
    public void drehe( String richtung ) {
        if( richtung == "rechts" ) {
            setRotation(getRotation() + 90);
        } else if( richtung == "links" ) {
            setRotation(getRotation() - 90);
        } else {
            nachricht("Befehl nicht korrekt!");
        }
    }

    /**
     * Der Rover gibt durch einen Wahrheitswert (<code>true</code> oder
     * <code>false</code>) zurück, ob sich auf seiner Position ein Objekt der
     * Klasse {@link Gestein} befindet. Eine entsprechende Meldung erscheint
     * auch auf dem Display.
     */
    public boolean gesteinVorhanden() {
        if( getOneIntersectingObject(Gestein.class) != null ) {
            nachricht("Gestein gefunden!");
            return true;
        }
        return false;
    }

    /**
     * Der Rover überprüft, ob sich in <var>richtung</var> ("rechts", "links"
     * oder "vorne") ein Objekt der Klasse {@link Huegel} befindet.
     * Das Ergebnis wird auf dem Display angezeigt. Sollte ein anderer Text (String)
     * als "rechts", "links" oder "vorne" übergeben werden, dann erscheint eine
     * entsprechende Meldung auf dem Display.
     * @param richtung "links", "rechts" oder "vorne"
     */
    public boolean huegelVorhanden( String richtung ) {
        int rot = getRotation();

        if( richtung == "vorne" && rot == 0 || richtung == "rechts" && rot == 270 || richtung == "links" && rot == 90 ) {
            if( getOneObjectAtOffset(1, 0, Huegel.class) != null && ((Huegel) getOneObjectAtOffset(1, 0, Huegel.class)).getSteigung() > 30 ) {
                return true;
            }
        }

        if( richtung == "vorne" && rot == 180 || richtung == "rechts" && rot == 90 || richtung == "links" && rot == 270 ) {
            if( getOneObjectAtOffset(-1, 0, Huegel.class) != null && ((Huegel) getOneObjectAtOffset(-1, 0, Huegel.class)).getSteigung() > 30 ) {
                return true;
            }
        }

        if( richtung == "vorne" && rot == 90 || richtung == "rechts" && rot == 0 || richtung == "links" && rot == 180 ) {
            if( getOneObjectAtOffset(0, 1, Huegel.class) != null && ((Huegel) getOneObjectAtOffset(0, 1, Huegel.class)).getSteigung() > 30 ) {
                return true;
            }

        }

        if( richtung == "vorne" && rot == 270 || richtung == "rechts" && rot == 180 || richtung == "links" && rot == 0 ) {
            if( getOneObjectAtOffset(0, -1, Huegel.class) != null && ((Huegel) getOneObjectAtOffset(0, -1, Huegel.class)).getSteigung() > 30 ) {
                return true;
            }

        }

        if( richtung != "vorne" && richtung != "links" && richtung != "rechts" ) {
            nachricht("Befehl nicht korrekt!");
        }

        return false;
    }

    /**
     * Der Rover ermittelt den Wassergehalt des Gesteins auf seiner Position und
     * gibt diesen auf dem Display aus. Sollte kein Objekt der Klasse {@link Gestein}
     * vorhanden sein, dann erscheint eine entsprechende Meldung auf dem Display.
     */
    public void analysiereGestein() {
        if( gesteinVorhanden() ) {
            nachricht("Gestein untersucht! Wassergehalt ist " + ((Gestein) getOneIntersectingObject(Gestein.class)).getWassergehalt() + "%.");
            Greenfoot.delay(1);
            removeTouching(Gestein.class);
        } else {
            nachricht("Hier ist kein Gestein");
        }
    }

    /**
     * Der Rover erzeugt ein Objekt der Klasse {@link Marke} auf seiner Position.
     */
    public void setzeMarke() {
        getWorld().addObject(new Marke(), getX(), getY());
    }

    /**
     * Der Rover gibt durch einen Wahrheitswert (<code>true</code> oder
     * <code>false</code>) zurück, ob sich auf seiner Position ein Objekt der
     * Klasse {@link Marke} befindet.
     * Eine entsprechende Meldung erscheint auch auf dem Display.
     */
    public boolean markeVorhanden() {
        if( getOneIntersectingObject(Marke.class) != null ) {
            return true;
        }

        return false;
    }

    /**
     * Wenn sich auf dem aktuellen Feld ein Objekt der Klasse {@link Marke}
     * befindet, dann wird die Markierung aus der Welt entfernt. Sollte keine
     * Marke vorhanden sein, dann erscheint eine entsprechende Meldung auf dem
     * Display.
     */
    public void entferneMarke() {
        if( markeVorhanden() ) {
            removeTouching(Marke.class);
        } else {
            nachricht("Hier ist keine Marke");
        }
    }

    /**
     * Interne Mathode, um eine Nachricht auf dem Display anzuzeigen.
     * @param pText Eine Zeile Text
     */
    private void nachricht( String pText ) {
        if( anzeige != null ) {
            anzeige.anzeigen(pText);
            Greenfoot.delay(1);
            anzeige.loeschen();
        }
    }

    /**
     * Versteckt das Display des Rovers.
     */
    private void displayAusschalten() {
        getWorld().removeObject(anzeige);

    }

    @Override
    protected void addedToWorld( World world ) {
        setImage("images/rover.png");
        world = getWorld();
        anzeige = new Display();
        anzeige.setImage("images/nachricht.png");
        world.addObject(anzeige, 7, 0);
        if( getY() == 0 ) {
            setLocation(getX(), 1);
        }
        anzeige.anzeigen("Ich bin bereit");

    }

    /**
     * Interne Klasse, um eine Text-Display in der Welt anzuzeigen.
     */
    class Display extends Actor {

        GreenfootImage bild;

        public Display() {
            bild = getImage();
        }

        public void act() {

        }

        public void anzeigen( String pText ) {
            loeschen();
            getImage().drawImage(new GreenfootImage(pText, 25, Color.BLACK, new Color(0, 0, 0, 0)), 10, 10);

        }

        public void loeschen() {
            getImage().clear();
            setImage("images/nachricht.png");
        }

    }

}
