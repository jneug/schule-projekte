import ea.*;

public class TrankAngriff extends Gegenstand implements Ticker {

    private int wirkung = 100;

    // Steuerung der Animation
    private int delta = 0;
    private int speed = 2;

    public TrankAngriff( Karte pKarte ) {
        super(pKarte);
        add(new Bild(0,0,"images/trank_lila.gif"));

        // Anmelden, sodass die tick()-Methode alle 100 ms ausgeführt wird
        Manager.standard.anmelden(this, 100);
    }

    @Override
    public void tick() {
        if( delta >= 5*Math.abs(speed) || delta <= -5*Math.abs(speed) )
            speed = -1*speed;
        delta += speed;
        verschieben(0, speed);
    }


    @Override
    public void benutzen( Lunk pLunk ) {
        pLunk.setAttack( pLunk.getAttack() + wirkung );
        zerstoeren();
    }


    @Override
    public void anwenden( Lunk pLunk, Gegner pGegner ) {
        pGegner.setAttack( pGegner.getAttack() + wirkung );
        zerstoeren();
    }

    @Override
    public void einsammeln(Lunk pLunk) {
        benutzen(pLunk);
    }

    @Override
    public void ablegen(Lunk pLunk, Karte pKarteNeu) {
        setKarte(pKarteNeu);
        pKarteNeu.verschiebeZuFeldAnKoordinate(this, pLunk.aktuelleFigur().zentrum().x, pLunk.aktuelleFigur().zentrum().x);
    }

    @Override
    public void zerstoeren() {
        // Abmelden, sodass die tick()-Methode nicht mehr ausgeführt wird.
        Manager.standard.abmelden(this);
        // Gegenstand aus dem Spiel entfernen
        karte.entferneGegenstand(this);
        leeren();
    }
}
