package roborally.instructions;

import roborally.Robot;
import schule.ngb.zm.Constants;
import schule.ngb.zm.layers.DrawingLayer;

public abstract class Instruction extends Constants {

    public static final int CARD_WIDTH = 70;

    public static final int CARD_HEIGHT = 100;

    public abstract void execute( Robot robot );

    public void draw( int x, int y, DrawingLayer drawing ) {
        drawing.setStrokeColor(DARKGRAY);
        drawing.setStrokeWeight(4);
        drawing.setFillColor(LIGHTGRAY);
        drawing.roundedRect(x, y, CARD_WIDTH, CARD_HEIGHT, 4, NORTHWEST);
    }

}
