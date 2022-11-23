package roborally.instructions;

import roborally.Robot;
import schule.ngb.zm.layers.DrawingLayer;

public class ForwardInstruction extends Instruction {

    protected int tiles = 1;

    public ForwardInstruction() {

    }

    public ForwardInstruction( int pTiles ) {
        this.tiles = pTiles;
    }

    @Override
    public void execute( Robot pRobot ) {
        for( int i = 0; i < tiles; i++ ) {
            if( !pRobot.moveForward() ) {
                return;
            }
        }
    }

    @Override
    public void draw( int pPixelX, int pPixelY, DrawingLayer drawing ) {
        drawing.setStrokeColor(DARKGRAY);
        drawing.setStrokeWeight(4);
        drawing.setFillColor(GREEN);
        drawing.roundedRect(pPixelX, pPixelY, 70, 100, 4, NORTHWEST);
        drawing.setStrokeWeight(1);
        drawing.setFillColor(GREEN.darker());
        drawing.circle(pPixelX +70, pPixelY, 15, NORTHEAST);
        drawing.setFontSize(16);
        drawing.text(tiles+"", pPixelX +55, pPixelY +15);
        drawing.image("roborally/assets/arrow-forward.png", pPixelX +35, pPixelY +50, 0.2);
    }

}
