
public class EntscheidungVorhersage2 extends Entscheidung
{
    
    public String entscheide( Datensatz d ) {
        if( d.vorhersage.equals("regnerisch") ) {
            return "links";
        } else {
            return "rechts";
        }
    }
}
