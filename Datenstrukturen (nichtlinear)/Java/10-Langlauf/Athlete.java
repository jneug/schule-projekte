


public class Athlete implements ComparableContent<Athlete> {

    private String name;

    private long startTime, endTime;

    public Athlete( String pName ) {
        name = pName;
    }

    public String getName() {
        return name;
    }

    public void setStartTime( long time ) {
        startTime = time;
    }

    public void setEndTime( long time ) {
        endTime = time;
    }

    public int getFinalTime() {
        return Math.round((float) ((endTime-startTime) / (1000*60)));
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
        return "%s(%.2f)".format(name, getFinalTime());
    }
}
