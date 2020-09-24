


public class FixedNumberKey extends Item {
    
    private int number;
    
    public FixedNumberKey( int pNumber ) {
        super("Nummernschlüssel");
        number = pNumber;
    }
    
    public String use() {
        return Integer.toString(number);
    }
    
    public String hint() {
        return "Du findest ein Stück Papier auf das die Zahl "+ number +" gekritzelt ist.";
    }
    
}
