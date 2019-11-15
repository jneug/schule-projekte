
public class Medienplayer
{
    
    private Mediendatei medien;
    
    public Medienplayer()
    {
    }
    
    public void abspielen( Mediendatei pMedium )
    {
        System.out.printf("Spiele Medium ab: %s\n", pMedium.getTitel());
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
