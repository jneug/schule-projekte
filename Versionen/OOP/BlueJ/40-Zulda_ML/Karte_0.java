/**
 * Beispiel einer "von Hand" erstellten Karte in der Spielwelt.
 */
public class Karte_0 extends Karte {

    public Karte_0( int x, int y ) {
        super(x,y);

        for( int i = 0; i < felder.length; i++ ) {
            if( i < 9 || i > 10 ) {
                felder[i][0] = new Feld(i * 48, 0, "stein");
                add(felder[i][0]);
                felder[i][14] = new Feld(i * 48, 14 * 48, "stein");
                add(felder[i][14]);
            }
        }
        for (int j = 0; j < felder[0].length; j++) {
            if( j != 7 ){
                felder[0][j] = new Feld(0, j * 48, "stein");
                add(felder[0][j]);
                felder[19][j] = new Feld(19 * 48, j * 48, "stein");
                add(felder[19][j]);
            }
        }
    }

}
