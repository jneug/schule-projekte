

public class Form {

    private Punkt position;

    private double richtung;

    public Form( Punkt pPosition, double pRichtung ) {
        position = pPosition;
        richtung = pRichtung;
    }

    public Punkt getPosition() {
        return position;
    }

    public void setPosition( Punkt pPosition ) {
        position = pPosition;
    }

    public double getRichtung() {
        return richtung;
    }

    public void setRichtung( double pRichtung ) {
        richtung = pRichtung;
    }

    public void versetzen( int pX, int pY ) {
        position.setX(pX);
        position.setY(pY);
    }

    public void drehen( double pGrad ) {
        richtung += pGrad;
        if( richtung >= 360 ) {
            richtung = richtung - 360;
        } else if( richtung < 0 ) {
            richtung = 360 + richtung;
        }
    }

}
