package roborally.effects;

import roborally.Robot;
import schule.ngb.zm.Constants;
import schule.ngb.zm.layers.DrawingLayer;

public abstract class Effect extends Constants {

    private Robot robot;

    public Effect( Robot robot ) {
        this.robot = robot;
    }

    public Robot getRobot() {
        return robot;
    }

    public abstract void step();

    public void draw( DrawingLayer drawing ) {

    }

}
