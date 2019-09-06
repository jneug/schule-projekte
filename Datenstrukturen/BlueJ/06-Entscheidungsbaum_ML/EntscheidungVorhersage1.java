
public class EntscheidungVorhersage1 extends Entscheidung
{
    
    public String entscheide( Datensatz d ) {
        if( d.vorhersage.equals("sonnig") ) {
            return "links";
        } else {
            return "rechts";
        }
    }
}
