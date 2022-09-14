

public class KarnevalDerTiere {

    private Tier[] karneval;

    public KarnevalDerTiere() {
        karneval = new Tier[3];

        /*aufg*
        karneval[0] = new Hai("Harry");
        karneval[1] = new Falke("Freddy");
        karneval[2] = new Hund("Fiffi");
        *aufg*/
        //ml*
        karneval[0] = new Katze("Garfield");
        karneval[1] = new Hai("Harry");
        karneval[2] = new Adler("Achim");
        karneval[3] = new Falke("Freddy");
        karneval[4] = new Hund("Fiffi");
        karneval[5] = new Biene("Maja");
        //*ml
    }

    public void ichBinEin() {
        // Verkürzte Zählschleife für lineare Datenstrukturen
        // Entwpricht bei Arrays dem Code
        /*
        for( int i = 0; i < karneval.length; i++ ) {
            tier = karneval[i];
            System.out.println("Ich bin ein " + tier);
        }
        */
        for( Tier tier : karneval ) {
            System.out.println("Ich bin ein " + tier);
        }
    }

    public void sagtWas() {
        /*aufg*
        // TODO: Lasse alle Tiere etwas sagen!
        *aufg*/
        //ml*
        for( Tier tier : karneval ) {
            tier.sagWas();
        }
        //*ml
    }

}
