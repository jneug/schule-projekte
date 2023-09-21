import schule.ngb.zm.Zeichenfenster;
import schule.ngb.zm.formen.*;

import java.awt.*;

public class ContainerhafenAnzeige extends Zeichenfenster {

    private Containerhafen hafen;

    private KranForm kran;

    private ContainerForm stack1, stack2, stack3, stack4;

    public ContainerhafenAnzeige( Containerhafen pHafen ) {
        hafen = pHafen;
    }

    @Override
    public void vorbereiten() {
        setSize(640, 480);

        kran = new KranForm(390, 100);
        kran.drehen(30);
        formen.anzeigen(kran);

        stack1 = new ContainerForm(290, 100);
        stack2 = new ContainerForm(260, 100);
        stack3 = new ContainerForm(290, 140);
        stack4 = new ContainerForm(260, 140);

        formen.anzeigen(stack1, stack2, stack3, stack4);

        for( int i = 0; i < 8; i++ ) {
            formen.anzeigen(new ContainerForm(80, 40 + i*40));
        }
    }

    @Override
    public void aktualisieren( double delta ) {
        if( kran.getDrehwinkel() > 180 ) {
            kran.drehung = -1 * abs(kran.drehung);
            kran.container.verstecken();
        } else if( kran.getDrehwinkel() < 0 ) {
            kran.drehung = abs(kran.drehung);
            kran.container.zeigen();
        }
        kran.drehen(kran.drehung*delta);
    }

    @Override
    public void zeichnen() {
        formen.leeren();
        zeichnung.clear(128);

        zeichnung.setColor(0, 150,255);
        zeichnung.rect(420, 0, 220, 480, NORDWESTEN);
        zeichnung.setStrokeWeight(2.0);
        zeichnung.setStrokeColor(0);
        zeichnung.line(420,0, 420, 480);


        zeichnung.setColor(33);
        zeichnung.line(40, 0, 40, hoehe);
        zeichnung.line(50, 0, 50, hoehe);
        for( int i = 5; i < hoehe; i += 8 ) {
            zeichnung.line(38, i, 52, i);
        }
    }

    class KranForm extends FormGruppe {
        ContainerForm container;
        double drehung = 80;
        public KranForm( double pX, double pY ) {
            super(pX, pY);

            Linie l1 = new Linie(-30, -10, 60, 0);
            l1.setKonturDicke(2.0);
            Linie l2 = new Linie(-30, 10, 60, 0);
            l2.setKonturDicke(2.0);
            AbgerundetesRechteck r1 = new AbgerundetesRechteck(-20, -15, 40, 30, 8);
            r1.setFuellfarbe(200);
            r1.setKonturDicke(1.8);
            AbgerundetesRechteck r2 = new AbgerundetesRechteck(-15, -10, 30, 20, 4);
            r2.setFuellfarbe(220);
            r2.setKonturDicke(1.2);

            container = new ContainerForm(60,0);

            this.hinzu(r1, container, l1, l2, r2);
        }
    }

    class StaplerForm extends FormGruppe {
        ContainerForm container;
        double drehung = 80;
        public StaplerForm( double pX, double pY ) {
            super(pX, pY);

            Linie l1 = new Linie(-30, -10, 60, 0);
            l1.setKonturDicke(2.0);
            Linie l2 = new Linie(-30, 10, 60, 0);
            l2.setKonturDicke(2.0);
            AbgerundetesRechteck r1 = new AbgerundetesRechteck(-20, -15, 40, 30, 8);
            r1.setFuellfarbe(200);
            r1.setKonturDicke(1.8);
            AbgerundetesRechteck r2 = new AbgerundetesRechteck(-15, -10, 30, 20, 4);
            r2.setFuellfarbe(220);
            r2.setKonturDicke(1.2);

            container = new ContainerForm(60,0);

            this.hinzu(r1, container, l1, l2, r2);
        }
    }

    class ContainerForm extends Rechteck {
        public ContainerForm(double pX, double pY) {
            super(pX, pY, 10, 33);
            setFuellfarbe(0, 73, 81);
            setKonturFarbe(33);
            setAnkerpunkt(ZENTRUM);
        }
    }
}
