

public abstract class Item extends WithHint {

    private String name;
    
    public Item( String pName ) {
        name = pName;
    }
    
    public String getName() {
        return name;
    }
    
    public abstract String use();

}
