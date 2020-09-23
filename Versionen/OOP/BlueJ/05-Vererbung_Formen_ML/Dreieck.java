

public class Dreieck extends Form {

    private Punkt punktB;

    private Punkt punktC;

    public Dreieck( Punkt pPosition, double pRichtung,
                    Punkt pPunktB, Punkt pPunktC ) {
        super(pPosition, pRichtung);
        punktB = pPunktB;
        punktC = pPunktC;
    }

    public Punkt getPunktB() {
        return punktB;
    }

    public void setPunktB( Punkt pPunktB ) {
        punktB = pPunktB;
    }

    public Punkt getPunktC() {
        return punktC;
    }

    public void setPunktC( Punkt pPunktC ) {
        punktC = pPunktC;
    }

}
