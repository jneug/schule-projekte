
public class EntscheidungWind extends Entscheidung
{
    
    public String entscheide( Datensatz d ) {
        if( d.wind.equals("stark") ) {
            return "links";
        } else {
            return "rechts";
        }
    }
}
