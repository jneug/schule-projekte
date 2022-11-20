package roborally.instructions;

import roborally.Robot;
import schule.ngb.zm.layers.DrawingLayer;

public class BackwardsInstruction extends Instruction {

    protected int tiles = 1;

    public BackwardsInstruction() {

    }

    public BackwardsInstruction( int tiles ) {
        this.tiles = tiles;
    }

    @Override
    public void step( Robot robot ) {
        for( int i = 0; i < tiles; i++ ) {
            if( !robot.moveBackward() ) {
                break;
            }
        }
    }

    @Override
    public void draw( int x, int y, DrawingLayer drawing ) {
        drawing.setStrokeColor(DARKGRAY);
        drawing.setStrokeWeight(4);
        drawing.setFillColor(GREEN);
        drawing.roundedRect(x, y, 70, 100, 4, NORTHWEST);
        drawing.image("roborally/assets/arrow-backward.png", x+35, y+50, 0.2);
    }

}
