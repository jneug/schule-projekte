/**
 * Eine YouTube-Video-PLayliste.
 */
public class VideoPlayer
{
    
    /**
     * Die Playliste mit Videos. Nicht sortiert!
     */
    private List<Video> playlist;
    
    private int counter;
    
    /**
     * Erstellt einen neuen VideoPLayer.
     */
    public VideoPlayer()
    {
        playlist = new List<Video>();
        
        playlist.append( new Video("Kay One - Louis Louis (Official Video)", "https://www.youtube.com/watch?v=5mqelmYUcI0", "Kay One", 92330, 2372709) );
        playlist.append( new Video("Select-sort with Gypsy folk dance", "https://www.youtube.com/watch?v=Ns4TPTC8whw", "AlgoRythmics", 1844, 464666) );
        playlist.append( new Video("Try Not To Laugh Watching Funny Fails Vines Compilation 2017 | Best Funny Vines 2017 by Life Awesome", "https://www.youtube.com/watch?v=PyBIUrqteXM", "Life Awesome", 6340, 1771805) );
        playlist.append( new Video("7 BEZIEHUNGS ARTEN!", "https://www.youtube.com/watch?v=AGhlkObLFrA", "ApeCrime", 68673, 671520) );
        playlist.append( new Video("TRY NOT TO LAUGH or GRIN: Funny Fails Compilation 2017 |  Best Fails Vines May 2017", "https://www.youtube.com/watch?v=-GQdtFWuEk0", "Life Awesome", 3072, 753644) );
        playlist.append( new Video("Die Highlights: Meyer-Landrut vs. Gercke - Schlag den Star", "https://www.youtube.com/watch?v=l51tB6OBfyg", "MySpass.com", 3052, 362811) );
        playlist.append( new Video("Bubble-sort with Hungarian folk dance", "https://www.youtube.com/watch?v=lyZQPjUT5B4", "AlgoRythmics", 6725, 1213349) );
        playlist.append( new Video("How Big do Tsunamis Get?", "https://www.youtube.com/watch?v=7oaGUg7ik_c", "RealLifeLore", 57469, 4937801) );
        playlist.append( new Video("Try Not To Laugh Watching Funny Fails Compilation October 2016 #4 - Co Vinesâ", "https://www.youtube.com/watch?v=XAo0EXuQXKg", "Co Vines", 35491, 10963751) );
        playlist.append( new Video("This incredible animation shows how deep the ocean really is", "https://www.youtube.com/watch?v=UwVNkfCov1k", "Tech Insider", 116661, 8199071) );
        playlist.append( new Video("The Best Fails Moments - Kids fails compilation", "https://www.youtube.com/watch?v=GIKQqJJq-Fg", "The Best Fails Moments", 48253, 11206905) );
        playlist.append( new Video("Stupid Watergate: Last Week Tonight with John", "https://www.youtube.com/watch?v=FVFdsl29s_Q", "LastWeekTonight", 55852, 1309757) );
        playlist.append( new Video("Insert-sort with Romanian folk dance", "https://www.youtube.com/watch?v=ROalU379l3U", "AlgoRythmics", 3187, 511954) );
        playlist.append( new Video("ULTIMATE FAILS! - March Week 1 - 2017 | Funny Fail Compilation", "https://www.youtube.com/watch?v=KrofmB6WSok", "The Best Fails", 3901, 1488461) );
        playlist.append( new Video("Die 10 gruseligsten \"Wenn du es siehst\"-Bilder!", "https://www.youtube.com/watch?v=BoU_eI2Pm1I", "CreepyPastaPunch", 12732, 390002) );
        playlist.append( new Video("OUTTAKES Stromberg (Staffel 1) HD", "https://www.youtube.com/watch?v=q9qhzqGJjw0", "Serien TV German", 95, 21399) );
    }

    /**
     * Sucht in der Playlist nach dem Video mit der angegeben Anzahl Likes.
     */
    public Video sucheNachLikes( int anzahlLikes ) {
        playlist.toFirst();
        while( playlist.hasAccess() ) {
            if( playlist.getContent().getLikes() == anzahlLikes ) {
                return playlist.getContent();
            }
            playlist.next();
        }
        // Gesuchtes Element wurde nicht gefunden
        return null;
    }
    
    /**
     * Sortiert die Playliste nach dem Namen des Videos. Die Methode nutzt dafür
     * den Quicksort Algorithmus.
     */
    public void sortiereNachLikes() {
        // Start der Rekursion mit der Playliste als Eingabe.
        playlist = quicksortNachLikes(playlist);
    }
    
    /**
     * Hilfsmethode für den Quicksort-Algorithmus. Sortiert die übergebene Liste 
     * von Videos nach der Anzahl likes und gibt die sortierte Liste zurück.
     */
    private List<Video> quicksortNachLikes( List<Video> videos ) {
        if( videos.isEmpty() ) {
            return videos;
        }
        
        // Nimm das erste Element der Liste als Pivot-Element
        videos.toFirst();
        Video pivot = videos.getContent();
        videos.remove(); // Entferne das Pivot-Element aus der Liste
        
        // Erstelle zwei Hilfslisten für Videos mit weniger / mehr Likes
        List<Video> kleiner = new List<Video>();
        List<Video> groesser = new List<Video>();
        
        // Laufe durch die Liste und hänge die Videos an die passenden Listen an
        while( videos.hasAccess() ) {
            Video aktuelles = videos.getContent();
            // Vergleiche aktuelles Element mit dem Pivot-Element
            if( aktuelles.getLikes() < pivot.getLikes() ) {
                kleiner.append(aktuelles);
            } else {
                groesser.append(aktuelles);
            }
            videos.next(); // Setze den Listenzeiger ein Element weiter
        }
        
        // Wende den Algorithmus rekursiv auf die Liste mit "kleineren" Elementen an
        videos = quicksortNachLikes(kleiner);
        // Hänge das Pivot-Element hinter den kleineren Elementen an
        videos.append(pivot);
        // Wende den Algorithmus rekursiv auf die Liste mit "groesseren" Elementen an
        // und hänge die resultierende Liste an die Ergebnisliste an
        videos.concat( quicksortNachLikes(groesser) );
        
        return videos;
    }
    
    /**
     *  Gibt die aktuelle Playliste auf der Konsole aus.
     */
    public void print() {
        playlist.toFirst();
        System.out.println("# Playliste:");
        while( playlist.hasAccess() ) {
            System.out.println("- "+playlist.getContent().toString());
            playlist.next();
        }
    }
}
