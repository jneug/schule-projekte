import java.util.Random;

public class Skilift {

    private Station talstation;

    private Station bergstation;

    public Skilift() {
        Queue<Gondel> kabelBergauf = new Queue<>();
        Queue<Gondel> kabelBergab = new Queue<>();

        talstation = new Station("Talstation", kabelBergauf, kabelBergab);
        bergstation = new Station("Bergstation", kabelBergab, kabelBergauf);

        Random r = new Random();
        for( int i = 0; i < 28; i += 1 ) {
            Gondel g = new Gondel(i+1, 12, r.nextInt(100)+50);
            //talstation.parkeAufAbstellgleis(g);
            if( i < 14 ) {
                kabelBergauf.enqueue(g);
            } else {
                kabelBergab.enqueue(g);
            }
        }

        for( int i = 0; i < 500; i += 1 ) {
            Passagier p = new Passagier("Passagier "+(i+1), r.nextInt(77)+3);
            if( r.nextBoolean() ) {
                talstation.warten(p);
            } else {
                bergstation.warten(p);
            }
        }

        for( int i = 0; i < 200; i += 1 ) {
            talstation.fahren();
            bergstation.fahren();
        }

        System.out.printf("[%s] auf: %d, ab. %d\n", talstation.getName(), talstation.anzahlBergauf, talstation.anzahlBergab);
        System.out.printf("[%s] auf: %d, ab. %d\n", bergstation.getName(), bergstation.anzahlBergauf, bergstation.anzahlBergab);
    }

    public static void main(String[] args) {
        new Skilift();
    }

}
