public abstract class Tier {

    private String name;

    protected int alter;

    public Tier( String pName ) {
        name = pName;
    }

    public void sagWas() {
        System.out.println("Mein Name ist " + name);
    }

}
