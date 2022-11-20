package roborally.instructions;

import roborally.Robot;
import schule.ngb.zm.layers.DrawingLayer;

public class ForwardInstruction extends Instruction {

    protected int tiles = 1;

    public ForwardInstruction() {

    }

    public ForwardInstruction( int tiles ) {
        this.tiles = tiles;
    }

    @Override
    public void step( Robot robot ) {
        for( int i = 0; i < tiles; i++ ) {
            if( !robot.moveForward() ) {
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
        drawing.setStrokeWeight(1);
        drawing.setFillColor(GREEN.darker());
        drawing.circle(x+70, y, 15, NORTHEAST);
        drawing.setFontSize(16);
        drawing.text(tiles+"", x+55, y+15);
        drawing.image("roborally/assets/arrow-forward.png", x+35, y+50, 0.2);
    }

}
