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

    /**
     * Addiert <var>pValue</var> zum Angriffswert.
     * @param pValue
     */
    public void addAttack( int pValue ) {
        //ml*
        attack += pValue;
        //*ml
    }

}
