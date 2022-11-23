package roborally;

import roborally.effects.Effect;
import roborally.instructions.Instruction;
import roborally.tiles.Tile;
import schule.ngb.zm.Color;
import schule.ngb.zm.Constants;
import schule.ngb.zm.Options.Direction;
import schule.ngb.zm.layers.DrawingLayer;

public class Robot extends Constants {

    /**
     * Konfiguration: Größe eines Roboters.
     */
    public static final int ROBO_SIZE = Tile.TILE_SIZE - 10;

    private Tile tile;

    private Direction direction;

    private Color color;

    private Queue<Instruction> instructionQueue;

    private List<Effect> effects;

    public Robot( Tile pTile ) {
        direction = RIGHT;
        color = randomNiceColor();

        instructionQueue = new Queue<>();
        effects = new List<>();

        setTile(pTile);
    }

    public Tile getTile() {
        return tile;
    }

    /**
     * Setzt die Referenz auf die Kachel, auf der sich dieser Roboter befindet.
     * <p>
     * Ein Roboter befindet sich während des Spiels immer auf genau einer
     * Kachel. Die Referenz wird aktualisiert, wenn sich der Roboter bewegt oder
     * bewegt wird.
     * <p>
     * Dazu wird {@link Tile#setRobot(Robot)} der alten Kachel mit {@code null}
     * aufgerufen und dann die gleiche Methode von {@code pTile} mit diesem
     * Roboter als Parameter.
     *
     * @param pTile Die neue Kachel, auf der sich der Roboter befindet
     */
    public void setTile( Tile pTile ) {
        if( this.tile != null ) {
            this.tile.setRobot(null);
        }
        this.tile = pTile;
        this.tile.setRobot(this);
    }

    public Direction getDirection() {
        return direction;
    }

    public Color getColor() {
        return color;
    }


    /**
     * Fügt eine Anweisung in die Anweisungsschlange ein.
     *
     * @param pInstruction Die neue Anweisung.
     */
    public void addInstruction( Instruction pInstruction ) {
        instructionQueue.enqueue(pInstruction);
    }

    public Queue<Instruction> getInstructionQueue() {
        return instructionQueue;
    }

    /**
     * Fügt einen Effekt in die Liste der aktiven Effekte ein.
     * <p>
     * Ein neuer Effekt wird immer vorne in die Liste eingefügt und wird das
     * neue erste Element.
     *
     * @param pEffect Der neue Effekt.
     */
    public void addEffect( Effect pEffect ) {
        effects.toFirst();
        effects.insert(pEffect);
    }

    public List<Effect> getEffects() {
        return effects;
    }

    /**
     * Schiebt den Roboter eine Kachel in die angegebene Richtung, sofern die
     * Kachel in der Richtung befahrbar ist. Die Kachel ist nicht befahrbar,
     * wenn sich in der Richtung eine Wand befindet, die Zielkachel nicht
     * befahrbar ist oder sich ein anderer Roboter auf der Zielkachel befindet.
     *
     * @param pDir
     * @return
     */
    public boolean push( Direction pDir ) {
        Factory pFactory = tile.getFactory();

        if( pFactory.isPassable(tile, pDir) ) {
            Tile targetTile = pFactory.getNextTile(tile, pDir);
            if( !targetTile.hasRobot() ) {
                this.setTile(targetTile);
                return true;
            }
        }

        return false;
    }

    /**
     * Bewegt den Roboter eine Kachel in die angegebene Richtung, sofern die
     * Kachel in der Richtung befahrbar ist. Die Kachel ist nicht befahrbar,
     * wenn sich in der Richtung eine Wand befindet, oder die Zielkachel nicht
     * befahrbar ist.
     * <p>
     * Befindet sich auf der Zielkachel ein anderer Roboter, wird versucht
     * diesen in die Fahrtrichtung zu verschieben. Wenn dies scheitert, kann
     * sich dieser Roboter nicht bewegen.
     *
     * @param pDir
     * @return
     */
    public boolean move( Direction pDir ) {
        Factory pFactory = tile.getFactory();

        if( pFactory.isPassable(tile, pDir) ) {
            Tile targetTile = pFactory.getNextTile(tile, pDir);
            if( !targetTile.hasRobot()
                || (targetTile.hasRobot() && targetTile.getRobot().push(pDir)) ) {
                this.setTile(targetTile);
                return true;
            }
        }

        return false;
    }

    /**
     * Bewegt den Roboter eine Kachel entgegen der Blickrichtung rückwärts.
     * <p>
     * Falls die Bewegung ausgeführt werden konnte, wird {@code true}
     * zurückgegeben, sonst {@code false}. Die Bewegung kann nicht ausgeführt
     * werden, wenn sich hinter dem Roboter eine Wand zwischen den Kacheln
     * befindet, oder die Zielkachel nicht befahrbar ist.
     *
     * @return {@code true}, wenn die Bewegung ausgeführt wurde, {@code false}
     *     sonst.
     */
    public boolean moveForward() {
        return move(direction);
    }

    /**
     * Bewegt den Roboter eine Kachel in Blickrichtung vorwärts.
     * <p>
     * Falls die Bewegung ausgeführt werden konnte, wird {@code true}
     * zurückgegeben, sonst {@code false}. Die Bewegung kann nicht ausgeführt
     * werden, wenn sich in Blickrichtung eine Wand zwischen den Kacheln
     * befindet, oder die nächste Kachel nicht befahrbar ist.
     *
     * @return {@code true}, wenn die Bewegung ausgeführt wurde, {@code false}
     *     sonst.
     */
    public boolean moveBackward() {
        return move(direction.inverse());
    }

    /**
     * Dreht den Roboter nach links.
     */
    public void turnLeft() {
        switch( direction ) {
            case UP:
                direction = LEFT;
                break;
            case RIGHT:
                direction = UP;
                break;
            case DOWN:
                direction = RIGHT;
                break;
            case LEFT:
                direction = DOWN;
                break;
        }
    }

    /**
     * Dreht den Roboter nach rechts.
     */
    public void turnRight() {
        switch( direction ) {
            case UP:
                direction = RIGHT;
                break;
            case RIGHT:
                direction = DOWN;
                break;
            case DOWN:
                direction = LEFT;
                break;
            case LEFT:
                direction = UP;
                break;
        }
    }

    /**
     * Führt die nächste Anweisung in der Anweisungsschlange aus.
     * <p>
     * Für die erste Anweisung in der Schlange wird
     * {@link Instruction#execute(Robot)} mit diesem Roboter als Argument
     * aufgerufen. Die Anweisung wird danach aus der Schlange entfernt.
     */
    public void executeNextInstruction() {
        if( !instructionQueue.isEmpty() ) {
            instructionQueue.front().execute(this);
            instructionQueue.dequeue();
        }
    }

    /**
     * Wendet alle aktiven Effekte auf diesen Roboter an.
     * <p>
     * Für jeden Effekt in der Liste wird die Methode
     * {@link Effect#apply(Robot)} mit diesem Roboter als Parameter aufgerufen.
     */
    public void applyEffects() {
        effects.toFirst();
        if( effects.hasAccess() ) {
            effects.getContent().apply(this);
            effects.next();
        }
    }

    /**
     * Zeichnet den Roboter auf die Zeichenfläche.
     *
     * @param drawing Die Zeichenfläche.
     */
    public void draw( DrawingLayer drawing ) {
        int halfSize = Tile.TILE_SIZE / 2;

        // x- und y-Nummer der Kachel
        int x = tile.getX();
        int y = tile.getY();

        // Körper zeichnen
        drawing.noStroke();
        drawing.setFillColor(color);
        drawing.circle(halfSize + x * Tile.TILE_SIZE, halfSize + y * Tile.TILE_SIZE, ROBO_SIZE / 2);
        drawing.setFillColor(color.darker(50));
        drawing.circle(halfSize + Tile.TILE_SIZE * (x + direction.x * .2), halfSize + Tile.TILE_SIZE * (y + direction.y * .2), ROBO_SIZE / 6);
        // Kettenräder zeichnen
        if( direction == UP || direction == DOWN ) {
            drawing.rect(halfSize + x * Tile.TILE_SIZE - ROBO_SIZE / 2, halfSize + y * Tile.TILE_SIZE, ROBO_SIZE / 4, ROBO_SIZE, EAST);
            drawing.rect(halfSize + x * Tile.TILE_SIZE + ROBO_SIZE / 2, halfSize + y * Tile.TILE_SIZE, ROBO_SIZE / 4, ROBO_SIZE, WEST);
        } else {
            drawing.rect(halfSize + x * Tile.TILE_SIZE, halfSize + y * Tile.TILE_SIZE - ROBO_SIZE / 2, ROBO_SIZE, ROBO_SIZE / 4, NORTH);
            drawing.rect(halfSize + x * Tile.TILE_SIZE, halfSize + y * Tile.TILE_SIZE + ROBO_SIZE / 2, ROBO_SIZE, ROBO_SIZE / 4, SOUTH);
        }

        // Effekte zeichnen
        effects.toFirst();
        if( effects.hasAccess() ) {
            effects.getContent().draw(this, drawing);
            effects.next();
        }
    }

}
