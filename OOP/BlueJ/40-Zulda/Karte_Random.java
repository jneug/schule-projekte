import java.util.Random;

/**
 * Pseudozufällig generierte Karte, basierend auf der x- und y-Koordinate der
 * Karte in der Welt.
 */
public class Karte_Random extends Karte {

    public Karte_Random(int x, int y, Welt pWelt) {
        super(x, y, pWelt);

        Random rand = new Random((x+1)*(y+1)*1592873L);

        for( int i = 0; i < felder.length; i++ ) {
            for (int j = 0; j < felder[0].length; j++) {
                int typ = rand.nextInt(4);
                switch( typ ) {
                    case 1:
                        felder[i][j] = new Feld(i*48,j*48, "stein");
                        break;
                    case 2:
                        felder[i][j] = new Feld(i*48,j*48, "sand");
                        break;
                    case 3:
                        felder[i][j] = new Feld(i*48,j*48, "wasser");
                        break;
                    default:
                        felder[i][j] = new Feld(i*48,j*48, "gras");
                        break;
                }
                add(felder[i][j]);
            }
        }
    }

}
