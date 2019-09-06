
public abstract class Knoten
{
    public Knoten() {
    }

    /*
     * Führt den Test der Entscheidung durch. Die Methode
     * bekommt den Datensatz als Parameter und gibt einen
     * von drei Strings zurück:
     * 1. "links" - Folge dem linken Teilbaum
     * 2. "rechts" - Folge dem rechten Teilbaum
     * 3. Die finale Entscheidung, wenn dies ein Blatt des Baumes 
     *      ist (z.B. "Ja" oder "Nein")
     */
    public abstract String entscheide( Datensatz d );
    
}
