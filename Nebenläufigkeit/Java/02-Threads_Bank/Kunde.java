

public class Kunde extends Thread {

    private String name;

    private Konto konto;

    public Kunde( String pName, Konto pKonto) {
        super(pName);
        name = pName;
        konto = pKonto;
    }

    @Override
    public void run() {
        for( int i = 0; i < 10; i++ ) {
            System.out.println("KUNDE: " + getName() + " möchte 1000");
            if( konto.abheben(1000, this) ) {
                System.out.println("KUNDE: " + getName() + " hat 1000 erhalten");
            }
        }
    }

    private void wait( int pMillis ) {
        try {
            Thread.sleep(pMillis);
        } catch( InterruptedException ex ) {
            // ignore
        }
    }

}
