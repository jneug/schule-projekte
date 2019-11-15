


public class Hund extends Tier {
    
    public Hund( String pName ) {
        super(pName);
    }
    
    public void sagWas() {
        System.out.println("Ich bin ein Hund.");
        super.sagWas();
    }
}
