import greenfoot.*;

/**
 * Rover sind die einzigen aktiven Akteure in der Mars-Welt.
 */
public class Rover extends Actor {

    /**
     * Act-Methode des Rovers. Programmiere hier deinen Algorithmus und starte
     * ihn mit dem "Act"-Button in Greenfoot.
     */
    public void act() {
        if( !baumVorhanden("vorne") && !felsenVorhanden("vorne") ) {
            laufe();
            if( alarmVorhanden() ) {
                entferneAlarm();
            } else {
                setzeAlarm();
            }
            
            if( feuerVorhanden() ) {
                loescheFeuer();
            }
        } else if( baumVorhanden("vorne") ) {
            benutzeBeil();
        }
    }
    
    public void benutzeBeil() {
        int rot = getRotation();
        Huegel h = null;
        if( rot == 0 ) {
            h = (Huegel) getOneObjectAtOffset(1, 0, Huegel.class);
        }

        if( rot == 180 ) {
            h = (Huegel) getOneObjectAtOffset(-1, 0, Huegel.class);
        }

        if( rot == 90 ) {
            h = (Huegel) getOneObjectAtOffset(0, 1, Huegel.class);

        }

        if( rot == 270 ) {
            h = (Huegel) getOneObjectAtOffset(0, -1, Huegel.class);
        }
        
        if( h != null ) {
            getWorld().removeObject(h);
        }
    }

    /**
     * Der Rover bewegt sich ein Feld in Fahrtrichtung weiter.
     * Sollte sich in Fahrtrichtung ein Objekt der Klasse Huegel befinden oder
     * er sich an der Grenze der Welt befinden, dann erscheint eine
     * entsprechende Meldung auf dem Display.
     */
    public void laufe() {
        if( !baumVorhanden("vorne") && !felsenVorhanden("vorne") ) {
            move(1);
            Greenfoot.delay(1);
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
        }
    }

    /**
     * Der Rover gibt durch einen Wahrheitswert (<code>true</code> oder
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
    public boolean baumVorhanden( String richtung ) {
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
            
        }

        return false;
    }
    
    public boolean felsenVorhanden( String richtung ) {
        int rot = getRotation();

        if( richtung == "vorne" && rot == 0 || richtung == "rechts" && rot == 270 || richtung == "links" && rot == 90 ) {
            if( getOneObjectAtOffset(1, 0, Gestein.class) != null  ) {
                return true;
            }
        }

        if( richtung == "vorne" && rot == 180 || richtung == "rechts" && rot == 90 || richtung == "links" && rot == 270 ) {
            if( getOneObjectAtOffset(-1, 0, Gestein.class) != null  ) {
                return true;
            }
        }

        if( richtung == "vorne" && rot == 90 || richtung == "rechts" && rot == 0 || richtung == "links" && rot == 180 ) {
            if( getOneObjectAtOffset(0, 1, Gestein.class) != null ) {
                return true;
            }

        }

        if( richtung == "vorne" && rot == 270 || richtung == "rechts" && rot == 180 || richtung == "links" && rot == 0 ) {
            if( getOneObjectAtOffset(0, -1, Gestein.class) != null ) {
                return true;
            }

        }

        if( richtung != "vorne" && richtung != "links" && richtung != "rechts" ) {
            
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
        }

        return false;
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
    }

}
