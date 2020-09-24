


public abstract class Lock extends WithHint {
    
    protected boolean open;
    
    public Lock() {
        open = false;
    }
    
    public boolean isOpen() {
        return open;
    }
    
    public abstract boolean unlock( Item pItem );
    
}
