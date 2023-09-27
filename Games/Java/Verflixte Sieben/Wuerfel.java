import java.util.Random;

public class Wuerfel {

    private Random zufall;

    public Wuerfel() {
        zufall = new Random();
    }

    public int werfen() {
        return zufall.nextInt(6)+1;
    }

}
