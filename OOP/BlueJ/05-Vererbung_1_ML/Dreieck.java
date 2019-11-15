
public class Dreieck extends Form
{

    private Punkt punktB;

    private Punkt punktC;

    public Dreieck( Punkt pPosition, double pRichtung,
            Punkt pPunktB, Punkt pPunktC ) {
        super(pPosition, pRichtung);
        punktB = pPunktB;
        punktC = pPunktC;
    }

    public void setPunktB( Punkt pPunktB ) {
        punktB = pPunktB;
    }

    public Punkt getPunktB() {
        return punktB;
    }

    public void setPunktC( Punkt pPunktC ) {
        punktC = pPunktC;
    }

    public Punkt getPunktC() {
        return punktC;
    }

}
