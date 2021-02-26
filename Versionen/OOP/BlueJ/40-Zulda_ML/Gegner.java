import ea.*;

/**
 * Abstrakte Basiklasse für Gegner.
 *
 * Gegener kennen immer die Karte, auf der sie sich befinden. Sie können ihre Karte
 * nicht verlassen.
 */
public abstract class Gegner extends Bild {

    protected Karte karte;

    private int hitpoints;

    private int attack;

    private int defense;

    public Gegner(int pHitpoints, int pAttack, int pDefense, Karte pKarte, String pBild ) {
        super(0, 0, pBild);

        karte = pKarte;

        hitpoints = pHitpoints;
        attack    = pAttack;
        defense   = pDefense;
    }

    public Karte getKarte() {
        return karte;
    }

    public void addHitpoints( int pHp ) {
        hitpoints += pHp;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

}
