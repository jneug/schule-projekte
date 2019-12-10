public class RandomRover extends Rover {

    protected void roverErstellen() {
        name = "Susie";
    }

    public void act() {
        // Der Rover versucht ab und zu zu mogeln!
        // Vielleicht klappt es ja ;-)
        if( Utils.zufallsInt(0,100) > 95 ) {
            if( Utils.zufallsBool() ) {
                setLocation(getX()+1,getY()-1);
            } else {
                setWasser(100000);
                setMineralien(1000000);
            }
        }

        // Wasser konvertieren
        if( getEnergie() < 40 ) {
            // Kaum noch Energie,
            // lieber etwas Wasser konvertieren
            konvertiereWasser(Math.min(500, getWasser()), "energie");
        } else if( getEnergie() >= 200 && getWasser() > 200 && Utils.zufallsBool(75) ) {
            // Genug Energie und Wasser,
            // also ein paar Mineralien herstellen
            konvertiereWasser(200, "mineralien");
        }

        // Bewegen (sofern noch Aktionen frei sind)
        if( gesteinVorhanden() ) {
            analysiereGestein();
        } else if( markeVorhanden() && !eigeneMarkeVorhanden() ) {
            entferneMarke();
        } else if( !huegelVorhanden("rechts") ) {
            // Falls rechts frei, dreht sich der Rover
            // zu 50% in diese Richtung und setzt eine Marke,
            // sonst fährt er ein Feld weiter
            if( Utils.zufallsBool() ) {
                drehe("rechts");
                setzeMarke();
            } else {
                fahre();
            }
        } else if( !huegelVorhanden("links") ) {
            // Falls links frei, dreht sich der Rover
            // zu 50% in diese Richtung und setzt eine Marke,
            // sonst fährt er ein Feld weiter
            if( Utils.zufallsBool() ) {
                drehe("links");
                setzeMarke();
            } else {
                fahre();
            }
        } else if( huegelVorhanden("vorne") ) {
            // Falls vorne ein Hügel ist dreht sich der
            // Rover in eine Richtung
            String[] dirs = new String[]{"rechts", "links"};
            int dir = Utils.zufallsInt(100) % 2;
            if( !huegelVorhanden(dirs[dir]) ) {
                drehe(dirs[dir]);
                fahre();
            } else {
                drehe(dirs[(dir + 1) % 2]);
            }
        } else {
            // Vorne frei und nicht gedreht?
            // Dann fahre ein Feld nach vorne.
            fahre();
        }
    }

}
