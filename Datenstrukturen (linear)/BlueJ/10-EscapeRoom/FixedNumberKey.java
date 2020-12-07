/**
 * Ein Schlüsse-Item, der eine feste Zahl enthält.
 */
public class FixedNumberKey extends Item {

    private int number;

    private String type;

    public FixedNumberKey( int pNumber ) {
        super("Nummernschlüssel");
        number = pNumber;
        type = EscapeRoom.randElement(new String[]{
            "Stück Papier", "Stück menschliche Haut",
            "Blatt Pergament", "dunkles Etwas",
            "tierisches Ohr", "blutiges Pflaster"
        });
    }

    public String use() {
        return Integer.toString(number);
    }

    public void printHint() {
        EscapeRoom.println("Du findest ein " +type+ " auf das die Zahl "+ number +" gekritzelt ist.");
    }

}
