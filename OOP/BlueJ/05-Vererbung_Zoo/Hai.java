/*aufg*
public class Hai extends Tier {
*aufg*/
//ml*
public class Hai extends Schwimmend {
//*ml

    public Hai( String pName ) {
        super(pName);
    }

    public void sagWas() {
        System.out.println("Ich bin ein Hai.");
        super.sagWas();
    }

}
