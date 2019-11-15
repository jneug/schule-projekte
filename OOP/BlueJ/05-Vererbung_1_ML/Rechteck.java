import java.awt.*;

public class Rechteck extends Form
{
    private int a;

    private int b;

    public Rechteck( Punkt pPosition, double pRichtung, int pA, int pB ) {
        super(pPosition, pRichtung);
        a = pA;
        b = pB;
    }

    public void setA( int pA ) {
        a = pA;
    }

    public int getA() {
        return a;
    }

    public void setX( int pB ) {
        b = pB;
    }

    public int getB() {
        return b;
    }

}
