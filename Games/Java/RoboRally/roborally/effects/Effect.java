package roborally.effects;

import roborally.Robot;
import schule.ngb.zm.Constants;
import schule.ngb.zm.layers.DrawingLayer;

public abstract class Effect extends Constants {

    public abstract void apply( Robot robot );

    /**
     * Zeichnet eine Visualisierung des Effekts auf die Zeichenfläche. Der
     * Effekt sollte in der Regel über den Roboter gezeichnet werden. Die
     * Position kann mit {@code robot.getTile().getX()} und
     * {@code robot.getTile().getY()} ermittelt werden.
     *
     * Nicht jeder Effekt muss eine grafische Darsetllung haben. Daher ist die
     * Methode auch nicht abstrakt, sondern als leer implementiert.
     *
     * @param robot
     * @param drawing
     */
    public void draw( Robot robot, DrawingLayer drawing ) {

    }

}
