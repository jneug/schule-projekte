
public class Medienplayer
{
    
    private Mediendatei[] medien;
    
    private int indexAktuelleDatei = 0;
    
    private int anzahlMediendateien = 0;
    
    public Medienplayer()
    {
        medien = new Mediendatei[30];
    }
    
    public void mediendateiEinreihen( Mediendatei medium )
    {
        if( anzahlMediendateien < medien.length )
        {
            medien[anzahlMediendateien] = medium;
            anzahlMediendateien += 1;
        }
    }
    
    public int getAnzahlMediendateien()
    {
        return anzahlMediendateien;
    }
    
    public int getMaximaleAnzahlMediendateien()
    {
        return medien.length;
    }

    public void abspielen()
    {
        medien[indexAktuelleDatei].abspielen();
    }
    
    public void stopp() 
    {
        medien[indexAktuelleDatei].stoppen();
    }
    
    public void naechsterTitel()
    {
        indexAktuelleDatei += 1;
        if( indexAktuelleDatei == medien.length )
        {
            indexAktuelleDatei = 0;
        }
    }
    
    public void vorherigerTitel()
    {
        indexAktuelleDatei -= 1;
        if( indexAktuelleDatei < 0 )
        {
            indexAktuelleDatei = medien.length-1;
        }
    }
    
    public void wiedergabelisteAusgeben()
    {
        for( int i = 0; i < anzahlMediendateien; i++ )
        {
            System.out.printf("%04d: %s\n", i+1, medien[i].toString());
        }
    }
}
