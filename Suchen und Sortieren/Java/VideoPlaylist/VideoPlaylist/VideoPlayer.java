
/**
 * Eine YouTube-Video-PLayliste.
 */
public class VideoPlayer
{
    
    /**
     * Die Playliste mit Videos. Aufsteigend sortiert nach dem Namen der Videos.
     */
    private Video[] playlist;
    
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
        return null;
    }
    
    /**
     * Sucht in der Playlist nach dem Video mit dem angegeben Namen.
     * 
     * TIPP: mit "String A".compareTo("String B") kann man zwei Strings vergleichen.
     * Siehe dazu auch: http://docs.oracle.com/javase/8/docs/api/java/lang/String.html#compareTo-java.lang.String-
     */
    public Video sucheNachName( String name ) {
        return null;
    }
    
}
