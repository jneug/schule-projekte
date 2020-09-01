public class BoringRover extends Rover {

    protected void roverErstellen() {
        name = "Peter";
    }

    public void act() {
        if( getEnergie() < 100 /*&& getWasser() > 0*/ ) {
            ausgabe("Konvertiere 500 Wasser in Energie");
            konvertiereWasser(500, "energie");
        } else if( getWasser() >= 600 ) {
            ausgabe("Konvertiere "+(getWasser() - 200)+" Wasser in Mineralien");
            konvertiereWasser(getWasser() - 200, "mineralien");
        } else if( roverVorhanden("vorne") ) {
            ausgabe("Entziehe anderem Rover Energie");
            entzieheEnergie();
            entzieheEnergie();
        } else if( gesteinVorhanden() ) {
            ausgabe("Analysiere Gestein");
            analysiereGestein();
        } else if( huegelVorhanden("vorne") ) {
            if( !huegelVorhanden("rechts") ) {
                ausgabe("Huegel vor mir, drehe rechts.");
                drehe("rechts");
                fahre();
            } else if( !huegelVorhanden("links") ) {
                ausgabe("Huegel vor mir, drehe links.");
                drehe("links");
                fahre();
            } else {
                ausgabe("Huegel überall, drehe um.");
                drehe("links");
                drehe("links");
            }
        } else {
            ausgabe("Freie fahrt!");
            fahre();
        }
    }

    /*
        Hilfsmethode, um einen Text auszugeben.
     */
    private void ausgabe( String pText ) {
        if( ausgabeEin ) {
            System.out.println("<"+name+"> sagt: "+pText);
        }
    }

    public void ausgabeAktivieren() {
        ausgabeEin = true;
    }

    private boolean ausgabeEin = false;

}
