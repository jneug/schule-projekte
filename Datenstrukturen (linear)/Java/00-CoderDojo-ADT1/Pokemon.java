public class Pokemon {

    private String name;

    private int attack;

    private int defense;

    public Pokemon( String pName, int pAttack, int pDefense ) {
        name = pName;
        attack = pAttack;
        defense = pDefense;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

}
