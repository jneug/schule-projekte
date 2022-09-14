/*aufg*
public class Hund extends Tier {
*aufg*/
//ml*
public class Hund extends Laufend {
//*ml
    public Hund( String pName ) {
        /*aufg*
        // TODO: Fehler?
        name = pName;
        *aufg*/
        //ml*
        super(pName);
        //*ml
    }

    public void sagWas() {
        System.out.println("Ich bin ein Hund.");
        //ml*
        super.sagWas();
        //*ml
    }

}
