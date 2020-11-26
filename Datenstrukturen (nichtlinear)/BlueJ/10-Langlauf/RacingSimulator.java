import java.util.Random;
import java.util.ArrayList;


public class RacingSimulator {

    private float timeFactor = 0.01f;
    
    private Random rand;
    
    private ArrayList<Athlete> athletes;
    
    private BinarySearchTree<Athlete> race;
    
    public RacingSimulator() {
        rand = new Random();
        
        athletes = new ArrayList<Athlete>();
        for( int i = 0; i < 10; i++ ) {
            athletes.add(new Athlete());
        }
        
        race = new BinarySearchTree<Athlete>();
    }
    
    public void simulateRun() {
        for( Athlete a: athletes ) {
            simulateRun(a);
        }
        
        SearchTrees.printPretty(race);
    }
    
    public void simulateRun( Athlete pRunner ) {
        long start = System.currentTimeMillis();
        pRunner.startRun(start);
        long time = rand.nextInt(120-55) + 55;
        pRunner.finishRun(start + (time*1000L*60L));
        
        race.insert(pRunner);
    }
    
    private void wait( int pTime ) {
        try {
            Thread.sleep(pTime);
        } catch( InterruptedException ex ) {
        }
    }
    
    public void printRace() {
        SearchTrees.printPretty(race);
    }
    
    public void printRanking() {
        SearchTrees.printInorder(race);
    }
    
}
