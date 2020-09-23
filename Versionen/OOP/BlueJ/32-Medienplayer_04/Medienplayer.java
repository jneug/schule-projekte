
public class Medienplayer {

    private Queue<Mediendatei> playlist;

    private int anzahlMediendateien = 0;

    public Medienplayer() {
        playlist = new Queue<Mediendatei>();
    }

    public void mediendateiEinreihen( Mediendatei medium ) {
        playlist.enqueue(medium);
        anzahlMediendateien += 1;
    }

    public int getAnzahlMediendateien() {
        return anzahlMediendateien;
    }

    public void abspielen() {
        playlist.front().abspielen();
    }

    public void stopp() {
        playlist.front().stoppen();
    }

    public void naechsterTitel() {
        if( !playlist.isEmpty() ) {
            playlist.dequeue();
        }
    }

    public void wiedergabelisteAusgeben() {
        int i = 1;
        Queue<Mediendatei> temp = new Queue<Mediendatei>();
        while( !playlist.isEmpty() ) {
            System.out.printf("%04d: %s\n", i, playlist.front().toString());
            temp.enqueue(playlist.front());
        }
        while( !temp.isEmpty() ) {
            playlist.enqueue(temp.front());
        }
    }

}
