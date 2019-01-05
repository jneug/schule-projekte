
/**
 * Eine YouTube-Video-PLayliste.
 */
public class VideoPlayer
{
    
    /**
     * Die Playliste mit Videos. Aufsteigend sortiert nach dem Namen der Videos.
     */
    private Video[] playlist;
    
    private int counter;
    
    /**
     * Erstellt einen neuen VideoPLayer.
     */
    public VideoPlayer()
    {
        playlist = new Video[16];
        
        playlist[0] = new Video("7 BEZIEHUNGS ARTEN!", "https://www.youtube.com/watch?v=AGhlkObLFrA", "ApeCrime", 68673, 671520);
        playlist[1] = new Video("Bubble-sort with Hungarian folk dance", "https://www.youtube.com/watch?v=lyZQPjUT5B4", "AlgoRythmics", 6725, 1213349);
        playlist[2] = new Video("Die 10 gruseligsten \"Wenn du es siehst\"-Bilder!", "https://www.youtube.com/watch?v=BoU_eI2Pm1I", "CreepyPastaPunch", 12732, 390002);
        playlist[3] = new Video("Die Highlights: Meyer-Landrut vs. Gercke - Schlag den Star", "https://www.youtube.com/watch?v=l51tB6OBfyg", "MySpass.com", 3052, 362811);
        playlist[4] = new Video("How Big do Tsunamis Get?", "https://www.youtube.com/watch?v=7oaGUg7ik_c", "RealLifeLore", 57469, 4937801);
        playlist[5] = new Video("Insert-sort with Romanian folk dance", "https://www.youtube.com/watch?v=ROalU379l3U", "AlgoRythmics", 3187, 511954);
        playlist[6] = new Video("Kay One - Louis Louis (Official Video)", "https://www.youtube.com/watch?v=5mqelmYUcI0", "Kay One", 92330, 2372709);
        playlist[7] = new Video("OUTTAKES Stromberg (Staffel 1) HD", "https://www.youtube.com/watch?v=q9qhzqGJjw0", "Serien TV German", 95, 21399);
        playlist[8] = new Video("Select-sort with Gypsy folk dance", "https://www.youtube.com/watch?v=Ns4TPTC8whw", "AlgoRythmics", 1844, 464666);
        playlist[9] = new Video("Stupid Watergate: Last Week Tonight with John", "https://www.youtube.com/watch?v=FVFdsl29s_Q", "LastWeekTonight", 55852, 1309757);
        playlist[10] = new Video("TRY NOT TO LAUGH or GRIN: Funny Fails Compilation 2017 |  Best Fails Vines May 2017", "https://www.youtube.com/watch?v=-GQdtFWuEk0", "Life Awesome", 3072, 753644);
        playlist[11] = new Video("The Best Fails Moments - Kids fails compilation", "https://www.youtube.com/watch?v=GIKQqJJq-Fg", "The Best Fails Moments", 48253, 11206905);
        playlist[12] = new Video("This incredible animation shows how deep the ocean really is", "https://www.youtube.com/watch?v=UwVNkfCov1k", "Tech Insider", 116661, 8199071);
        playlist[13] = new Video("Try Not To Laugh Watching Funny Fails Compilation October 2016 #4 - Co Vinesâ", "https://www.youtube.com/watch?v=XAo0EXuQXKg", "Co Vines", 35491, 10963751);
        playlist[14] = new Video("Try Not To Laugh Watching Funny Fails Vines Compilation 2017 | Best Funny Vines 2017 by Life Awesome", "https://www.youtube.com/watch?v=PyBIUrqteXM", "Life Awesome", 6340, 1771805);
        playlist[15] = new Video("ULTIMATE FAILS! - March Week 1 - 2017 | Funny Fail Compilation", "https://www.youtube.com/watch?v=KrofmB6WSok", "The Best Fails", 3901, 1488461);
    }

    /**
     * Sucht in der Playlist nach dem Video mit der angegeben Anzahl Likes.
     */
    public Video sucheNachLikes( int anzahlLikes ) {
        for( int i = 0; i < playlist.length; i++ ) {
            if( playlist[i].getLikes() == anzahlLikes ) {
                return playlist[i];
            }
        }
        // Gesuchtes Element wurde nicht gefunden
        return null;
    }
    
    /**
     * Sucht in der Playlist nach dem Video mit dem angegeben Namen.
     * 
     * TIPP: mit "String A".compareTo("String B") kann man zwei Strings vergleichen.
     * Siehe dazu auch: http://docs.oracle.com/javase/8/docs/api/java/lang/String.html#compareTo-java.lang.String-
     */
    public Video sucheNachName( String name ) {
        // Initialwerte für linken und rechten Zeiger
        int links = 0;
        int rechts = playlist.length-1;

        while( links <= rechts ) {
            // Mitte des aktuellen Bereich festlegen
            // Impliziter Cast zu int (das Ergebnis wird abgerundet)
            int mitte = links + (rechts-links)/2;
            
            // Stringvergleich durchführen
            int compare = playlist[mitte].getName().compareTo(name);
            if( compare < 0 ) {
                // Mittleres Element ist "kleiner" als das Gesuchte
                // Linken Zeiger verschieben
                links = mitte+1;
            } else if( compare > 0 ) {
                // Mittleres Element ist "größer" als das Gesuchte
                // Rechten Zeiger verschieben
                rechts = mitte-1;
            } else {
                // Mittleres Elemetn ist das Gesuchte
                return playlist[mitte];
            }
        }
        
        // Gesuchtes Element wurde nicht gefunden
        return null;
    }
    
    
    /**
     * Sucht in der Playlist rekursiv nach dem Video mit dem angegeben Namen. 
     * 
     * TIPP: mit "String A".compareTo("String B") kann man zwei Strings vergleichen.
     * Siehe dazu auch: http://docs.oracle.com/javase/8/docs/api/java/lang/String.html#compareTo-java.lang.String-
     */
    public Video sucheNachNameRekursiv( String name ) {
        // Initialwerte für linke und rechte Grenze festlegen und
        // Rekursion starten.
        return sucheNachNameHilfe(name, 0, playlist.length-1);
    }
    
    /**
     * Hilfsmethode für den rekursiven Aufruf der binären Suche.
     */
    private Video sucheNachNameHilfe( String name, int links, int rechts ) {
        // Gesuchtes Element wurde nicht gefunden
        if( links > rechts ) {
            return null;
        }
        
        // Mitte des aktuellen Bereich festlegen
        // Impliziter Cast zu int (das Ergebnis wird abgerundet)
        int mitte = links + (rechts-links)/2;
        
        // Stringvergleich durchführen
        int compare = playlist[mitte].getName().compareTo(name);
        if( compare < 0 ) {
            // Mittleres Element ist "kleiner" als das Gesuchte
            // Linken Zeiger verschieben
            return sucheNachNameHilfe(name, mitte+1, rechts);
        } else if( compare > 0 ) {
            // Mittleres Element ist "größer" als das Gesuchte
            // Rechten Zeiger verschieben
            return sucheNachNameHilfe(name, links, mitte-1);
        } else {
            // Mittleres Element ist das Gesuchte
            return playlist[mitte];
        }
    }
    
    /**
     * Sortiert die Playliste nach der Anzahl der Views pro Video. Die Methode nutzt dafür
     * den Bubblesort Algorithmus.
     */
    public void sortiereNachViews() {
        int i = playlist.length-1;
        while( i > 0 ) {
            int j = 0;
            while( j <= i-1 ) {
                if( playlist[j].getViews() > playlist[j+1].getViews() ) {
                    tausche(j+1,j);
                }
                j = j + 1;
            }
            i = i - 1;
        }
    }
    
    /**
     * Sortiert die Playliste nach dem Namen des Autors. Die Methode nutzt dafür
     * den Selectionsort Algorithmus.
     */
    public void sortiereNachAutor() {
        int i = playlist.length-1;
        while( i > 0 ) {
            int j = 0;
            int max = 0;
            while( j <= i ) {
                if( playlist[max].getAutor().compareTo(playlist[j].getAutor()) <= 0) {
                    max = j;
                }
                j = j + 1;
            }
            tausche(i, max);
            i = i - 1;
        }
    }
    
    /**
     * Vertauscht die Videos an den Indizes <code>a</code> und <code>b</code> in der
     * Playliste. Die Indizes dürfen nur zwischen <code>0</code> und <code>playlist.length</code> 
     * liegen.
     */
    private void tausche( int a, int b ) {
        Video tmp = playlist[a];
        playlist[a] = playlist[b];
        playlist[b] = tmp;
    }
    
    /**
     *  Gibt die aktuelle Playlsite auf der Konsole aus.
     */
    public void print() {
        System.out.println("# Playliste:");
        for( Video v: playlist ) {
            System.out.println("- "+v.toString());
        }
    }
}
