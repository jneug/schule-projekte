import ea.*;

import java.util.Random;

public abstract class Enemy extends Bild {

    protected Karte karte;

    private int hitpoints;

    private int attack;

    private int defense;

    public Enemy( Karte pKarte, String pBild ) {
        super(0, 0, pBild);

        karte = pKarte;
    }

    public Karte getKarte() {
        return karte;
    }


}
