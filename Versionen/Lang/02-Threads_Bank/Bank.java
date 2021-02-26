

public class Bank {

    public static void main(String[] args) {
        Konto konto = new Konto();

        Kunde[] kunden = new Kunde[]{
            new Kunde("Kunde 1", konto),
            new Kunde("Kunde 2", konto),
            new Kunde("Kunde 3", konto),
            new Kunde("Kunde 4", konto),
            new Kunde("Kunde 5", konto)
        };
        for( Kunde k: kunden ) {
            k.start();
        }

    }

}
