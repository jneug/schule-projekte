/**
 * Ein Pokemon ist ein "Taschenmonster" (Pocket Monster), dass einen Namen,
 * einen Angriffs- und einen Verteidigungswert besitzt.
 * <p>
 * Die Klasse dient nur als Datenhaltungs-Klasse und <b>muss nicht verändert
 * werden</b>.
 */
public class Pokemon {

    /**
     * Name des Pokemon.
     */
    private String name;

    /**
     * Angriffswert des Pokemon.
     */
    private int attack;

    /**
     * Verteidigungswert des Pokemon.
     */
    private int defense;

    /**
     * Erstellt ein neues Pokemon.
     *
     * @param pName Der Name.
     * @param pAttack Der Angriffswert.
     * @param pDefense Der Verteidigungswert.
     */
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

    public void addAttack( int pAttack ) {
        //ml*
        attack += pAttack;
        //*ml
    }

    /**
     * Gibt eine String-Version des Pokemons zurück.
     * @return
     */
    @Override
    public String toString() {
        return name + "[a:"+attack+"|d:"+defense+"]";
    }

}
