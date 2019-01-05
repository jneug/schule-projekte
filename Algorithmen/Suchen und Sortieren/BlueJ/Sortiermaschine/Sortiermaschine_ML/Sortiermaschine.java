
/**
 * Eine Sortiermaschine für Arrays.
 * 
 * @author J. Neugebauer 
 * @version 1.0
 */
public class Sortiermaschine
{
    public Sortiermaschine()
    {
    }
    
    /**
     * Generiert ein Array mit <code>anzahl</code> Zufallszahlen im
     * Bereich von 0 bis 100.
     */
    public int[] zufallszahlen( int anzahl )
    {
        return zufallszahlen(anzahl, 0, 100);
    }
    
    /**
     * Generiert ein Array mit <code>anzahl</code> Zufallszahlen im
     * Bereich von <code>min</code> bis <code>max</code>.
     */
    public int[] zufallszahlen( int anzahl, int min, int max )
    {
        // Negative Anzahlen abfangen
        if( anzahl < 0 ) 
        {
            anzahl = 0;
        }
        
        // Neues int-Array der Größe "anzahl" initialisieren
        // und mit Zahlen befüllen
        int[] zahlen = new int[anzahl];
        for( int i = 0; i < anzahl; i++ ) 
        {
            zahlen[i] = (int) (Math.random() * max + min);
        }
        
        return zahlen;
    }

    /**
     * Gibt ein Array mit Zahlen auf der Konsole aus. Besser nicht mit zu großen
     * Arrays (> 100 Elemente) verwenden.
     */
    public void gibArrayAus( int[] zahlen ) 
    {
        System.out.print("[");
        for( int i = 0; i < zahlen.length; i++ ) 
        {
            System.out.print(zahlen[i]);
            
            // Kein Komma hinter der letzten Zahl
            if( i < zahlen.length-1 ) 
            {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    /**
     * Prüft, ob das übergebene Array mit Zahlen sortiert ist oder nicht.
     * @return <code>true</code>, wenn das Array sortiert ist
     */
    public boolean pruefeObSortiert( int[] zahlen )
    {
        for( int i = 0; i < zahlen.length-1; i++ ) 
        {
            if( zahlen[i] > zahlen[i+1] ) 
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * ###################################################################
     * # Diese Methode starten, um eine Zeitmessung vorzunehmen          #
     * ###################################################################
     */
    public void zeitmessung()
    {
        //int anzahl = 1000;
        int anzahl = 5000;
        //int anzahl = 10000;
        //int anzahl = 50000;
        
        // Array mit anzahl Zufallszahlen generieren
        int[] zahlen = zufallszahlen(anzahl);
        
        // Stoppuhr erstellen und starten
        Stoppuhr stoppuhr = new Stoppuhr();
        stoppuhr.start();
                
        // Sortieren !!
        zahlen = bubblesort(zahlen);
        //zahlen = selectionsort(zahlen);
        //zahlen = insertionsort(zahlen);
        
        // Stoppuhr anhalten
        stoppuhr.stop();
        
        System.out.println(anzahl + " Zahlen sortiert in " + stoppuhr.hole_letzte_messung() + " Millisekunden");
    }
    
    /**
     * ###################################################################
     * # Diese Methode starten, um den Sortieralgorithmus zu testen!     #
     * ###################################################################
     * 
     * Bei großen Arrays sollte die Ausgabe der Arrays auskommentiert werden.
     */
    public void sortierentest()
    {
        // Array mit zehn Zufallszahlen genereieren
        int[] zahlen = zufallszahlen(10);
        
        // Ausgabe des unsortierten Arrays
        System.out.println("Unsortiertes Array:");
        gibArrayAus(zahlen);
        
        // Sortieren !!
        //zahlen = bubblesort(zahlen);
        //zahlen = selectionsort(zahlen);
        zahlen = insertionsort(zahlen);
        
        // Ausgabe des (hoffentlich) sortierten Arrays
        System.out.println("Array nach dem Sortieren:");
        gibArrayAus(zahlen);
        
        // Test, ob das Array wirklich sortiert ist
        if( pruefeObSortiert(zahlen) )
        {
            System.out.println("Das Array ist sortiert!");
        }
        else 
        {
            System.out.println("Das Array ist nicht sortiert!");
        }
        
    }
    
    /**
     * Sortiert das Array mit dem Insertionsort Algorithmus
     */
    public int[] insertionsort( int[] zahlen )
    {
        for( int i = 1; i < zahlen.length; i++ )
        {
            int j = i-1;
            
            while( j >= 0 && zahlen[j] > zahlen[j+1] )
            {
                    // Tausch der Wert an Stelle i und j mit Hilfe
                    // eines Zwischenspeichers (tmp)
                    int tmp = zahlen[j];
                    zahlen[j] = zahlen[j+1];
                    zahlen[j+1] = tmp;
                    
                    j = j-1;
            }
        }
        return zahlen;
    }
    
    /**
     * Sortiert das Array mit dem Selectionsort Algorithmus
     */
    public int[] selectionsort( int[] zahlen )
    {
        int blau = 0;
        while( blau < zahlen.length )
        {
            int rot = blau;
            int gruen = blau;
            
            while( gruen < zahlen.length )
            {
                if( zahlen[gruen] < zahlen[rot] )
                {
                    rot = gruen;
                }
                
                gruen = gruen+1;
            }
            
            // Tausch der Wert an Stelle blau und rot mit Hilfe
            // eines Zwischenspeichers (tmp)
            int tmp = zahlen[blau];
            zahlen[blau] = zahlen[rot];
            zahlen[rot] = tmp;
            
            blau = blau+1;
        }
        return zahlen;
    }
    
    /**
     * Sortiert das Array mit dem Bubblesort Algorithmus
     */
    public int[] bubblesort( int[] zahlen )
    {
        int i = zahlen.length-1;
        while( i > 0 )
        {
            int j = 0;
            while( j < i )
            {
                if( zahlen[j] > zahlen[j+1] ) {
                    // Tausch der Wert an Stelle i und j mit Hilfe
                    // eines Zwischenspeichers (tmp)
                    int tmp = zahlen[j];
                    zahlen[j] = zahlen[j+1];
                    zahlen[j+1] = tmp;
                }
                
                j = j+1;
            }
            
            i = i-1;
        }
        return zahlen;
    }
}