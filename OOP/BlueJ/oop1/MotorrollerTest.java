

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Test-Klasse MotorrollerTest.
 */
public class MotorrollerTest
{

    @Test
    public void testeTanke() {
        Motorroller m = new Motorroller(1000, 25, 10);

        m.tanke(10);
        assertEquals("Tanke 10L in einen 25L-Tank mit 10L Inhalt", 20, m.getTankinhalt(), 0.0);
        m.tanke(10);
        assertEquals("Tanke 10L in einen 25L-Tank mit 20L Inhalt", 25, m.getTankinhalt(), 0.0);

        m = new Motorroller(1000, 25, 10);
        m.tanke(-10);
        assertEquals("Tanke -10L in einen 25L-Tank mit 10L Inhalt", 0, m.getTankinhalt(), 0.0);
        m.tanke(-10);
        assertEquals("Tanke -10L in einen 25L-Tank mit 0L Inhalt", 0, m.getTankinhalt(), 0.0);
    }


    @Test
    public void testeFahre() {
        float verbrauch = 0.625f;

        Motorroller m = new Motorroller(1000, 25, 10);

        assertEquals("Der Stand sollte 1000 km betragen", 1000, m.getKilometerstand());
        m.fahre(16);
        assertEquals("Der Stand sollte 1016 km betragen", 1016, m.getKilometerstand());
        m.fahre(1);
        assertEquals("Der Stand sollte 1016 km betragen", 1016, m.getKilometerstand());


        m = new Motorroller(1000, 25, 10);

        assertEquals("Der Stand sollte 1000 km betragen", 1000, m.getKilometerstand());
        m.fahre(17);
        assertEquals("Der Stand sollte 1016 km betragen", 1016, m.getKilometerstand());
    }
}
