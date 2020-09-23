public class Falke extends Fliegend {

    public Falke( String pName ) {
        super(pName);
    }

    public void sagWas() {
        System.out.println("Ich bin ein Falke.");
        super.sagWas();
    }

}
