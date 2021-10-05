import java.util.Random;

public class Wuerfel {

    private int zahl;

    private Random zufall;

    public Wuerfel( Random pZufall ) {
        zufall = pZufall;
        werfen();
    }

    public void werfen() {
        zahl = zufall.nextInt(6)+1;
    }

    public int getZahl() {
        return zahl;
    }

}
