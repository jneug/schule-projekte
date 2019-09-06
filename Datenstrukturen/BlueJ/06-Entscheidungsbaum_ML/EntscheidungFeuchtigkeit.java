
public class EntscheidungFeuchtigkeit extends Entscheidung
{
    
    public String entscheide( Datensatz d ) {
        if( d.feuchtigkeit.equals("hoch") ) {
            return "links";
        } else {
            return "rechts";
        }
    }
}
