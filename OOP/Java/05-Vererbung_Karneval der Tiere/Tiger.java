public class Tiger extends Tier {

    public Tiger( String pName, int alter ) {
        super(pName);
        alter = 15;
    }

    @Override
    public void sagWas() {
        System.out.println("Ich bin ein Tiger.");
        super.sagWas();
        System.out.printf("Ich bin %d Jahre alt.", alter);
        System.out.println();
    }

}
