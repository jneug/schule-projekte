import greenfoot.*;

/**
 * Feuerbots sind die einzigen aktiven Akteure in der Welt.
 */
public class Feuerbot extends Actor {

    /**
     * Act-Methode des Feuerbots. Programmiere hier deinen Algorithmus und starte
     * ihn mit dem "Act"-Button in Greenfoot.
     */
    public void act() {
    }

    public void benutzeBeil() {
        Pflanze p = null;

        switch( getRotation() ) {
            case 0:
            p = (Pflanze) getOneObjectAtOffset(1, 0, Pflanze.class);
            break;
            case 90:
            p = (Pflanze) getOneObjectAtOffset(0, 1, Pflanze.class);
            break;
            case 180:
            p = (Pflanze) getOneObjectAtOffset(-1, 0, Pflanze.class);
            break;
            case 270:
            p = (Pflanze) getOneObjectAtOffset(0, -1, Pflanze.class);
            break;
        }

        if( p != null ) {
            getWorld().removeObject(p);
        } else {
            denke("Da ist keine Pflanze.");
        }
    }

    /**
     * Der Feuerbot bewegt sich ein Feld in Fahrtrichtung weiter.
     * Sollte sich in Fahrtrichtung ein Objekt der Klasse Huegel befinden oder
     * er sich an der Grenze der Welt befinden, dann erscheint eine
     * entsprechende Meldung auf dem Display.
     */
    public void laufe() {
        if( !pflanzeVorhanden("vorne") && !steinVorhanden("vorne") ) {
            move(1);
            Greenfoot.delay(1);
        } else {
            denke("Da ist ein Hindernis.");
        }
    }

    /**
     * Der Feuerbot dreht sich um 90 Grad in die Richtung, die mit <var>richtung</var>
     * ("links" oder "rechts") übergeben wurde. Sollte ein anderer Text (String)
     * als "links" oder "rechts" übergeben werden, dann erscheint eine
     * entsprechende Meldung auf dem Display.
     * @param richtung "links" oder "rechts"
     */
    public void drehe( String richtung ) {
        int rot = getRotation();

        if( richtung == "rechts" ) {
            setRotation(getRotation() + 90);
        } else if( richtung == "links" ) {
            setRotation(getRotation() - 90);
        } else {
            denke("Das verstehe ich nicht.");
        }
    }

    @Override
    public void setRotation( int rotation ) {
        int rot = getRotation();
        
        super.setRotation(rotation);

        if( (getRotation() == 180 && rot != 180) || (getRotation() != 180 && rot == 180) ) {
            getImage().mirrorVertically();
        }
    }

    /**
     * Der Feuerbot gibt durch einen Wahrheitswert (<code>true</code> oder
     * <code>false</code>) zurück, ob sich auf seiner Position ein Objekt der
     * Klasse {@link Gestein} befindet. Eine entsprechende Meldung erscheint
     * auch auf dem Display.
     */
    public boolean feuerVorhanden() {
        if( getOneIntersectingObject(Feuer.class) != null ) {
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
    public boolean pflanzeVorhanden( String richtung ) {
        int rot = getRotation();

        if( richtung == "vorne" && rot == 0 || richtung == "rechts" && rot == 270 || richtung == "links" && rot == 90 ) {
            if( getOneObjectAtOffset(1, 0, Pflanze.class) != null ) {
                return true;
            }
        }
        else if( richtung == "vorne" && rot == 180 || richtung == "rechts" && rot == 90 || richtung == "links" && rot == 270 ) {
            if( getOneObjectAtOffset(-1, 0, Pflanze.class) != null ) {
                return true;
            }
        }
        else if( richtung == "vorne" && rot == 90 || richtung == "rechts" && rot == 0 || richtung == "links" && rot == 180 ) {
            if( getOneObjectAtOffset(0, 1, Pflanze.class) != null ) {
                return true;
            }

        }
        else if( richtung == "vorne" && rot == 270 || richtung == "rechts" && rot == 180 || richtung == "links" && rot == 0 ) {
            if( getOneObjectAtOffset(0, -1, Pflanze.class) != null ) {
                return true;
            }

        }

        return false;
    }

    public boolean steinVorhanden( String richtung ) {
        int rot = getRotation();

        if( richtung == "vorne" && rot == 0 || richtung == "rechts" && rot == 270 || richtung == "links" && rot == 90 ) {
            if( getOneObjectAtOffset(1, 0, Stein.class) != null  ) {
                return true;
            }
        }

        else if( richtung == "vorne" && rot == 180 || richtung == "rechts" && rot == 90 || richtung == "links" && rot == 270 ) {
            if( getOneObjectAtOffset(-1, 0, Stein.class) != null  ) {
                return true;
            }
        }

        else if( richtung == "vorne" && rot == 90 || richtung == "rechts" && rot == 0 || richtung == "links" && rot == 180 ) {
            if( getOneObjectAtOffset(0, 1, Stein.class) != null ) {
                return true;
            }

        }

        else if( richtung == "vorne" && rot == 270 || richtung == "rechts" && rot == 180 || richtung == "links" && rot == 0 ) {
            if( getOneObjectAtOffset(0, -1, Stein.class) != null ) {
                return true;
            }

        }

        return false;
    }

    /**
     * Der Rover ermittelt den Wassergehalt des Gesteins auf seiner Position und
     * gibt diesen auf dem Display aus. Sollte kein Objekt der Klasse {@link Gestein}
     * vorhanden sein, dann erscheint eine entsprechende Meldung auf dem Display.
     */
    public void loescheFeuer() {
        if( feuerVorhanden() ) {
            Greenfoot.delay(1);
            removeTouching(Feuer.class);
        } else {
            denke("Hier brennt nichts.");
        }
    }

    /**
     * Der Rover erzeugt ein Objekt der Klasse {@link Marke} auf seiner Position.
     */
    public void setzeAlarm() {
        getWorld().addObject(new Alarm(), getX(), getY());
    }

    /**
     * Der Rover gibt durch einen Wahrheitswert (<code>true</code> oder
     * <code>false</code>) zurück, ob sich auf seiner Position ein Objekt der
     * Klasse {@link Marke} befindet.
     * Eine entsprechende Meldung erscheint auch auf dem Display.
     */
    public boolean alarmVorhanden() {
        if( getOneIntersectingObject(Alarm.class) != null ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Wenn sich auf dem aktuellen Feld ein Objekt der Klasse {@link Marke}
     * befindet, dann wird die Markierung aus der Welt entfernt. Sollte keine
     * Marke vorhanden sein, dann erscheint eine entsprechende Meldung auf dem
     * Display.
     */
    public void entferneAlarm() {
        if( alarmVorhanden() ) {
            removeTouching(Alarm.class);
        } else {
            denke("Hier ist kein Alarm.");
        }
    }

    @Override
    protected void addedToWorld( World world ) {
        if( Greenfoot.getRandomNumber(2) == 0 ) {
            setImage("images/brenndon.png");
        } else {
            setImage("images/brennda.png");
        }
        world = getWorld();
        gedanke = new Gedanke();
    }

    public void denke( String text ) {
        gedanke.denke(text);
        if( getY() == 0 ) {
            getWorld().addObject(gedanke, getX(), getY()+1);
        } else {
            getWorld().addObject(gedanke, getX(), getY()-1);
        }
        Greenfoot.delay(50);
        getWorld().removeObject(gedanke);
    }

    private Gedanke gedanke;

    public class Gedanke extends Actor {
        private GreenfootImage nachricht;

        public void denke( String text ) {
            nachricht = new GreenfootImage(text, 25, Color.BLACK, new Color(255,255,255,80), Color.BLACK);
            this.setImage(nachricht);
        }
    }

}
