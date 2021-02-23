import ea.*;

/**
 * Spielfigur des Hauptcharacters.
 *
 * Im Prototypen besitzt die Figur verschiedene Zustände und Aktionen, die
 * ausgeführt werden können. Ansonsten gibt es noch keine Spiellogik.
 */
public class Lunk extends ActionFigur {

    public Lunk() {
        super(new Figur(0, 0, "images/adventurer_idle_right.eaf"), "idle_right");

        neuerZustand(new Figur(0, 0, "images/adventurer_run_right.eaf"), "run_right");
        neuerZustand(new Figur(0, 0, "images/adventurer_die_right.eaf"), "die_right");

        neuerZustand(new Figur(0, 0, "images/adventurer_idle_left.eaf"), "idle_left");
        neuerZustand(new Figur(0, 0, "images/adventurer_run_left.eaf"), "run_left");
        neuerZustand(new Figur(0, 0, "images/adventurer_die_left.eaf"), "die_left");

        neueAktion(new Figur(0, 0, "images/adventurer_slashup_right.eaf"), "slashup_right");
        neueAktion(new Figur(0, 0, "images/adventurer_slashdown_right.eaf"), "slashdown_right");
        neueAktion(new Figur(0, 0, "images/adventurer_slash_right.eaf"), "slash_right");
        neueAktion(new Figur(0, 0, "images/adventurer_jump_right.eaf"), "jump_right");
        neueAktion(new Figur(0, 0, "images/adventurer_hit_right.eaf"), "hit_right");

        neueAktion(new Figur(0, 0, "images/adventurer_slashup_left.eaf"), "slashup_left");
        neueAktion(new Figur(0, 0, "images/adventurer_slashdown_left.eaf"), "slashdown_left");
        neueAktion(new Figur(0, 0, "images/adventurer_slash_left.eaf"), "slash_left");
        neueAktion(new Figur(0, 0, "images/adventurer_jump_left.eaf"), "jump_left");
        neueAktion(new Figur(0, 0, "images/adventurer_hit_left.eaf"), "hit_left");
    }

    public void setzePosition( float newX, float newY ) {
        Figur aktFig = aktuelleFigur();
        verschieben(new Vektor(
            newX - aktFig.getX(),
            newY - aktFig.getY()
        ));
    }

    @Override
    public float getX() {
        return aktuelleFigur().getX();
    }

    @Override
    public float getY() {
        return aktuelleFigur().getY();
    }

}
