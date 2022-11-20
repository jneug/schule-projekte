package roborally.tiles;

import roborally.Map;
import roborally.RoboRallye;
import roborally.Robot;
import schule.ngb.zm.Constants;
import schule.ngb.zm.layers.DrawingLayer;

public abstract class Tile extends Constants {

    private Map map;

    protected int x, y;

    protected Robot robot;

    protected boolean passable;

    public Tile( int x, int y, Map map ) {
        this.x = x;
        this.y = y;
        this.map = map;
        passable = true;
    }

    public Tile( int x, int y, boolean passable, Map map ) {
        this.x = x;
        this.y = y;
        this.map = map;
        this.passable = passable;
    }

    public Map getMap() {
        return map;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isPassable() {
        return passable;
    }

    public boolean hasRobot() {
        return robot != null;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot( Robot pRobot ) {
        this.robot = pRobot;
    }

    public abstract void step();

    public void draw( DrawingLayer drawing ) {
        drawing.setStrokeColor(DARKGRAY);
        drawing.setStrokeWeight(1);
        drawing.setFillColor(LIGHTGRAY);
        drawing.square(x * RoboRallye.TILE_SIZE, y * RoboRallye.TILE_SIZE, RoboRallye.TILE_SIZE, NORTHWEST);
    }

}
