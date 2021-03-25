/*aufg*
public class Falke extends Tier {
*aufg*/
//ml*
public class Falke extends Fliegend {
//*ml

    public Falke( String pName ) {
        super(pName);
    }

    public void sagWas() {
        System.out.println("Ich bin ein Falke.");
        super.sagWas();
    }

}
