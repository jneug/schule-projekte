public class BoringRover extends Rover {

    protected void roverErstellen() {
        name = "Peter";
        bild = "images/roverOrange.png";
    }

    public void act() {
        if( getEnergie() < 100 /*&& getWasser() > 0*/ ) {
            konvertiereWasser(500, "energie");
        } else if( getWasser() >= 600 ) {
            konvertiereWasser(getEnergie()-200, "mineralien");
        } else if( gesteinVorhanden() ) {
            analysiereGestein();
        } else if( huegelVorhanden("vorne") ) {
            if( !huegelVorhanden("rechts") ) {
                drehe("rechts");
                fahre();
            } else if( !huegelVorhanden("links") ) {
                drehe("links");
                fahre();
            } else {
                drehe("links");
                drehe("links");
            }
        } else {
            fahre();
        }
    }

}
