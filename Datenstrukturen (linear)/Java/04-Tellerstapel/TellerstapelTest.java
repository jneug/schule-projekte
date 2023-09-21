import org.junit.Test;

import static org.junit.Assert.*;

public class TellerstapelTest {

    @Test
    public void testTellerstapel() {
        runStapelTest("fhhfhffh", "gkkgkggk", true);
        runStapelTest("hffhfhhf", "gkkgkggk", false);
        runStapelTest("hffhfhhf", "kggkg", false);

        runStapelTest("ff", "kg", false);
        runStapelTest("f", "g", true);
        runStapelTest("f", "k", false);
        runStapelTest("h", "k", true);
        runStapelTest("h", "g", false);

        runStapelTest("", "g", false);
        runStapelTest("", "", true);
    }

    @Test
    public void testZaehleFalsche() {
        runZaehleTest("fhhfhffh", "gkkgkggk", 0);
        runZaehleTest("hffhfhhf", "gkkgkggk", 8);
        runZaehleTest("hffhfhhf", "kggkg", 3);

        runZaehleTest("ff", "kg", 1);
        runZaehleTest("f", "g", 0);
        runZaehleTest("f", "k", 1);
        runZaehleTest("h", "k", 0);
        runZaehleTest("h", "g", 1);

        runZaehleTest("", "g", 1);
        runZaehleTest("", "", 0);
    }

    @Test
    public void erstelleTellerstapel() {
        runErstelleTest("gkkgkggk", "hffhfhhf");
        runErstelleTest("gggkkk", "hhhfff");
        runErstelleTest("kk", "hh");
        runErstelleTest("g", "f");
        runErstelleTest("", "");
    }

    @Test
    public void ordneBiber() {
        runOrdneTest("hfhf", "kk", "gg", "kgkg");
        runOrdneTest("hh", "kk", "gg", "kk");
        runOrdneTest("ff", "kk", "gg", "gg");
        runOrdneTest("hfhf", "k", "ggg", "kg");
        runOrdneTest("hhfffh", "kkk", "g", "kkg");
        runOrdneTest("", "", "", "");
        runOrdneTest("hfhf", "", "gg", "");
        runOrdneTest("hfhf", "kk", "", "k");
    }


    /*
    Private Hilfsmethoden
     */

    private void runStapelTest( String pTeller, String pBiber, boolean pExpected ) {
        Stack<Teller> teller = buildTellerstapel(pTeller);
        Queue<Biber> biber = buildBiberschlange(pBiber);

        Tellerstapel ts = new Tellerstapel();
        if( pExpected ) {
            assertTrue("testTellerstapel([" + pTeller + "],[" + pBiber + "]): Sollte <" + pExpected + "> ergeben.", ts.testeTellerstapel(teller, biber));
        } else {
            assertFalse("testTellerstapel([" + pTeller + "],[" + pBiber + "]): Sollte <" + pExpected + "> ergeben.", ts.testeTellerstapel(teller, biber));
        }
    }

    private void runZaehleTest( String pTeller, String pBiber, int pExpected ) {
        Stack<Teller> teller = buildTellerstapel(pTeller);
        Queue<Biber> biber = buildBiberschlange(pBiber);

        Tellerstapel ts = new Tellerstapel();
        assertEquals("zaehleFalsche([" + pTeller + "],[" + pBiber + "]): Sollte <" + pExpected + "> Falsche ermitteln.", pExpected, ts.zaehleFalsche(teller, biber));
    }

    private void runErstelleTest( String pBiber, String pExpected ) {
        Queue<Biber> biber = buildBiberschlange(pBiber);

        Tellerstapel ts = new Tellerstapel();
        Stack<Teller> teller = ts.erstelleTellerstapel(biber);
        assertNotNull("erstelleTeller([" + pBiber + "]): Tellerstapel darf nicht <null> sein.", teller);

        if( pExpected.length() == 0 ) {
            assertTrue("erstelleTeller([" + pBiber + "]): Tellerstapel muss leer sein.", biber.isEmpty());
            return;
        }

        String result = "";
        for( ; !teller.isEmpty(); teller.pop() ) {
            result += teller.top().istHoch() ? 'h' : 'f';
        }
        assertEquals("erstelleTeller([" + pBiber + "]): Falsche Teller im Stapel.", pExpected, result);
    }

    private void runOrdneTest( String pTeller, String pKleine, String pGrosse, String pExpected ) {
        Stack<Teller> teller = buildTellerstapel(pTeller);
        Queue<Biber> biberKlein = buildBiberschlange(pKleine);
        Queue<Biber> biberGross = buildBiberschlange(pGrosse);

        Tellerstapel ts = new Tellerstapel();
        Queue<Biber> biber = ts.ordneBiber(teller, biberKlein, biberGross);
        assertNotNull("ordneBiber([" + pTeller + "], [" + pKleine + "], [" + pGrosse + "]): Biberschlange darf nicht <null> sein.", biber);

        if( pExpected.length() == 0 ) {
            assertTrue("ordneBiber([" + pTeller + "], [" + pKleine + "], [" + pGrosse + "]): Biberschlange muss leer sein.", biber.isEmpty());
            return;
        }

        String result = "";
        for( ; !biber.isEmpty(); biber.dequeue() ) {
            result += biber.front().istGross() ? 'g' : 'k';
        }
        assertEquals("ordneBiber([" + pTeller + "], [" + pKleine + "], [" + pGrosse + "]): Falsche Biberschlange.", pExpected, result);
    }

    private Stack<Teller> buildTellerstapel( String pAnordnung ) {
        Stack<Teller> teller = new Stack<>();
        for( int i = 0; i < pAnordnung.length(); i++ ) {
            teller.push(new Teller(pAnordnung.charAt(pAnordnung.length() - 1 - i) == 'h'));
        }
        return teller;
    }

    private Queue<Biber> buildBiberschlange( String pAnordnung ) {
        Queue<Biber> biber = new Queue<>();
        for( int i = 0; i < pAnordnung.length(); i++ ) {
            biber.enqueue(new Biber(pAnordnung.charAt(i) == 'g'));
        }
        return biber;
    }

}
