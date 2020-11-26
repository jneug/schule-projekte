


public class Athlete implements ComparableContent<Athlete> {
    
    private long startTime, finishTime;
    
    private int finalTime;
    
    public Athlete() {
        
    }
    
    public void startRun( long time ) {
        startTime = time;
    }
    
    public void finishRun( long time ) {
        finishTime = time;
        finalTime = Math.round((float) ((finishTime-startTime) / (1000*60)));
    }
    
    public int getFinalTime() {
        return finalTime;
    }
    
    public boolean isGreater( Athlete pOther ) {
        return getFinalTime() > pOther.getFinalTime();
    }
    
    public boolean isLess( Athlete pOther ) {
        return getFinalTime() < pOther.getFinalTime();
    }
    
    public boolean isEqual( Athlete pOther ) {
        return getFinalTime() == pOther.getFinalTime();
    }
    
    public String toString() {
        return "["+hashCode()+"]: "+getFinalTime()+" min";
    }
}
