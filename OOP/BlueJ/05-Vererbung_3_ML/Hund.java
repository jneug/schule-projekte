


public class Hund extends Laufend {

    public Hund( String pName ) {
        super(pName);
    }

    public void sagWas() {
        System.out.println("Ich bin ein Hund.");
        super.sagWas();
    }
}
