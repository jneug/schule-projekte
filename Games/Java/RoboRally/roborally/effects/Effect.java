package roborally.effects;

import roborally.Robot;
import schule.ngb.zm.Constants;
import schule.ngb.zm.layers.DrawingLayer;

/**
 * Basisklasse für einen Effekt, der auf einen Roboter angewandt werden kann.
 * <p>
 * {@link Robot Roboter} können Effekte erhalten, die sich auf ihre Funktion
 * auswirken. Ein {@link ConfusedEffect verwirrter} Roboter könnte sich
 * beispielsweise nach jeder Runde eine Kachel in eine zufällige Richtung
 * bewegen. Effekte können positive, neutrale oder negative Effekte haben.
 * <p>
 * Alle Effekte eines Roboters werden ausgeführt, nachdem er
 * <em>alle</em> Instruktionen dieser Runde ausgeführt hat.
 * <p>
 * Effekte können durch besondere Kacheln, Interaktionen mit anderen Robotern
 * oder andere Gründe ausgelöst werden. Ebenso können Effekte so wieder entfernt
 * werden.
 * <p>
 * Roboter verwalten Effekte nach dem LIFO-Prinzip. Ein Effekt kann also erst
 * entfernt werden, wenn alle nach ihm hinzugefügten Effekte entfernt wurden.
 */
public abstract class Effect extends Constants {

    /**
     * Wendet den Effekt auf den angegebenen Roboter an.
     *
     * @param robot Der Roboter, auf den der Effekt wirkt.
     */
    public abstract void apply( Robot robot );

    /**
     * Zeichnet eine Visualisierung des Effekts auf die Zeichenfläche. Der
     * Effekt sollte in der Regel über den Roboter gezeichnet werden. Die
     * Pixelkoordinaten der Kachel können mit
     * {@code robot.getTile().getPixelX()} und
     * {@code robot.getTile().getPixelY()} ermittelt werden.
     * <p>
     * Nicht jeder Effekt muss eine grafische Darsetllung haben. Daher ist die
     * Methode auch nicht abstrakt, sondern als leer implementiert.
     *
     * @param robot Der Roboter, auf den der Effekt wirkt.
     * @param drawing Die Zeichenfläche.
     */
    public void draw( Robot robot, DrawingLayer drawing ) {

    }

}
