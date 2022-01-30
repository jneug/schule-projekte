

public class Gondel {

    private int nummer;

    private int abfahrten;

    private int anzahlPassagiere;

    private Passagier[] passagiere;

    public Gondel( int pNummer, int pMax ) {
       this(pNummer, pMax, 0);
    }

    public Gondel( int pNummer, int pMaxPassagiere, int pAbfahrten ) {
        nummer = pNummer;
        passagiere = new Passagier[pMaxPassagiere];
        abfahrten = pAbfahrten;
        anzahlPassagiere = 0;
    }

    public int getNummer() {
        return nummer;
    }

    public int getMaxPassagiere() {
        return passagiere.length;
    }

    public int getAnzahlPassagiere() {
        return anzahlPassagiere;
    }

    public Passagier[] getPassagiere() {
        return passagiere;
    }

    public void einsteigen( Passagier pPassagier ) {
        if( anzahlPassagiere < passagiere.length ) {
            passagiere[anzahlPassagiere] = pPassagier;
            anzahlPassagiere += 1;
        }
    }

    public boolean isVoll() {
        return anzahlPassagiere >= passagiere.length;
    }

    public void leeren() {
        passagiere = new Passagier[passagiere.length];
    }

    public int getAbfahrten() {
        return abfahrten;
    }

    public void abfahrtVollendet() {
        abfahrten += 1;
    }

    public void reparieren() {
        abfahrten -= 5;
    }

}
