import java.util.Random;
import java.util.ArrayList;


public class Racingsimulator {

    private Random rand;

    public Racingsimulator() {
        rand = new Random();
    }

    public void simulate( Athlete pRunner ) {
        long start = System.currentTimeMillis();
        pRunner.setStartTime(start);
        long time = rand.nextInt(120-55) + 55;
        pRunner.setEndTime(start + (time*1000L*60L));
    }

}
