/*aufg*
public class Hund extends Tier {
*aufg*/
//ml*
public class Hund extends Laufend {
//*ml
    public Hund( String pName ) {
        super(pName);
    }

    public void sagWas() {
        System.out.println("Ich bin ein Hund.");
        super.sagWas();
    }

}
