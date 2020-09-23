public class Biene extends Fliegend {

    public Biene( String pName ) {
        super(pName);
    }

    public void sagWas() {
        System.out.println("Ich bin eine Biene.");
        super.sagWas();
    }

}
