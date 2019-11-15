
public class Medienplayer
{
    
    private Video videos;
    private Audio audios;
    private Bild bilder;
    
    public Medienplayer()
    {
    }
    
    public void abspielen( Video pVideo )
    {
        System.out.printf("Spiele Medium ab: %s\n", pVideo.getTitel());
    }
    
    public void abspielen( Audio pAudio )
    {
        System.out.printf("Spiele Medium ab: %s\n", pAudio.getTitel());
    }
    
    public void abspielen( Bild pBild )
    {
        System.out.printf("Spiele Medium ab: %s\n", pBild.getTitel());
    }
    
    public void stopp() 
    {
        System.out.println("Wiedergabe gestoppt.");
    }
    
    public void naechsterTitel()
    {
        System.out.println("Springe zur nächsten Mediendatei.");
    }
    
    public void vorherigerTitel()
    {
        System.out.println("Springe zur vorherigen Mediendatei.");
    }
}
