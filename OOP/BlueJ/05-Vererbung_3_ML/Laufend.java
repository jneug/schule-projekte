public class Laufend extends Tier {
    public Laufend( String pName ) {
        super(pName);
    }

    public void sagWas() {
        super.sagWas();
        System.out.println("Ich laufe auf dem Boden.");
    }
}
