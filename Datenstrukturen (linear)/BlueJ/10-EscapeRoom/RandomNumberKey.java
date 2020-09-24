import java.util.Random;


public class RandomNumberKey extends Item {
    
    private int number, min, max;
    
    private Random rand;
    
    public RandomNumberKey( int pMin, int pMax ) {
        super("Nummernschlüssel");
        rand = new Random();
        min = pMin;
        max = pMax;
        number = rand.nextInt(pMax) + pMin;
    }
    
    public String use() {
        int n = number;
        number = rand.nextInt(max) + min;
        System.out.println("Du benutzt die kleine Box, aber als du sie bewegst");
        System.out.println("macht sie ein seltsames schnurrendes Geräusch.");
        return Integer.toString(n);
    }
    
    public String hint() {
        return "Du findest eine kleine Box. Ein Feld auf der Vorderseite\nzeigt die Nummer "+ number +" an.";
    }
    
}
