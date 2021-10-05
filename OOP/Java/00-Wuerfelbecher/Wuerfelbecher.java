import java.util.Random;

public class Wuerfelbecher {

    private Wuerfel[] wuerfel;

    public Wuerfelbecher() {
        wuerfel = new Wuerfel[5];
    }

    public void neueWuerfel() {
/*aufg* >1
        Random r = new Random();
*aufg*/
/*aufg* 3
        Wuerfel w = new Wuerfel(r);
*aufg*/
        for( int x = 0; x < wuerfel.length; x += 1 ) {
/*aufg* 1
            Random r = new Random();
*aufg*/
/*aufg* <=2
            Wuerfel w = new Wuerfel(r);
*aufg*/
/*aufg*
            wuerfel[x] = w;
*aufg*/
        }
    }

    public void werfen() {
        for( int y = 0; y < wuerfel.length; y += 1 ) {
            wuerfel[y].werfen();
        }
    }

    public int getWuerfelsumme() {
        int summe = 0;
        for( int i = 0; i < wuerfel.length; i += 1 ) {
            summe += wuerfel[i].getZahl();
        }
        return summe;
    }

}
