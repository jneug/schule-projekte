public class MogelRover extends Rover {

    public void roverErstellen() {
        name = "Mowgli";
        bild = "images/roverViolet.png";
    }

    public void act() {
        if( getMineralien() < 10000 ) {
            while( !gesteinVorhanden() ) {
                Gestein g = new Gestein();
                if( g.getWassergehalt() >= 250 ) {
                    getWorld().addObject(g, getX(), getY());
                }
            }

            analysiereGestein();

            if( getEnergie() <= 10 ) {
                konvertiereWasser(getWasser(), "energie");
            } else {
                konvertiereWasser(getWasser(), "mineralien");
            }
        }
    }

}
