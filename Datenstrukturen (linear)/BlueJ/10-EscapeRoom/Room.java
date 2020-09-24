

public abstract class Room {

    protected Lock[] locks;

    public Room( int pNumberOfLocks ) {
        locks = new Lock[pNumberOfLocks];
    }
    
    public boolean isUnlocked() {
        for( Lock l: locks ) {
            if( !l.isOpen() ) {
                return false;
            }
        }
        return true;
    }
    
    public abstract void play();
    
}
