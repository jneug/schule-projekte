package roborally;

import roborally.effects.Effect;
import roborally.instructions.Instruction;
import roborally.tiles.Tile;
import schule.ngb.zm.Color;
import schule.ngb.zm.Constants;
import schule.ngb.zm.Options;
import schule.ngb.zm.layers.DrawingLayer;

public class Robot extends Constants {

    private int x;

    private int y;

    private Options.Direction direction;

    private Color color;

    private Tile tile;

    private Queue<Instruction> instructionQueue;

    private Stack<Effect> effects;

    public Robot( int x, int y ) {
        this.x = x;
        this.y = y;
        direction = RIGHT;
        color = randomNiceColor();

        instructionQueue = new Queue<>();
        effects = new Stack<>();
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile( Tile pTile ) {
        if( this.tile != null ) {
            this.tile.setRobot(null);
        }
        this.tile = pTile;
        this.tile.setRobot(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Options.Direction getDirection() {
        return direction;
    }

    public Color getColor() {
        return color;
    }

    public Queue<Instruction> getInstructionQueue() {
        return instructionQueue;
    }

    public void addInstruction( Instruction instruction ) {
        instructionQueue.enqueue(instruction);
    }

    public boolean push( Options.Direction dir ) {
        Tile target = tile.getMap().getNextTile(tile, dir);
        if( target.isPassable() ) {
            if( !target.hasRobot() ) {
                x += dir.x;
                y += dir.y;
                setTile(target);
                return true;
            }
        }
        return false;
    }

    public boolean move( Options.Direction dir ) {
        Tile target = tile.getMap().getNextTile(tile, dir);
        if( target.isPassable() ) {
            if( !target.hasRobot() || (target.hasRobot() && target.getRobot().push(dir)) ) {
                x += dir.x;
                y += dir.y;
                setTile(target);
                return true;
            }
        }
        return false;
    }

    public boolean moveForward() {
        return move(direction);
    }

    public boolean moveBackward() {
        return move(direction.inverse());
    }

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

    public void executeNextInstruction() {
        if( !instructionQueue.isEmpty() ) {
            instructionQueue.front().step(this);
            instructionQueue.dequeue();
        }
    }

    public void draw( DrawingLayer drawing ) {
        int halfSize = RoboRallye.TILE_SIZE / 2;

        drawing.noStroke();
        drawing.setFillColor(color);
        drawing.circle(halfSize + x * RoboRallye.TILE_SIZE, halfSize + y * RoboRallye.TILE_SIZE, RoboRallye.ROBO_SIZE / 2);
        drawing.setFillColor(color.darker(50));
        drawing.circle(halfSize + RoboRallye.TILE_SIZE * (x + direction.x * .5), halfSize + RoboRallye.TILE_SIZE * (y + direction.y * .5), RoboRallye.ROBO_SIZE / 8);
    }

}
