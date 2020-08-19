
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BesitzerTest {

    @Test
    public void testTankeRoller() {
        Motorroller m = new Motorroller(0, 60, 0);
        Besitzer b = new Besitzer("Besitzer 01", 10, true, m);
        
        b.tankeRoller(5);
        assertEquals("Der Besitzer hat 10 Euro und tankt 5 Liter in einen leeren Tank (Prüfe Geld).", 2.5, b.getGeld(), 0.0);
        assertEquals("Der Besitzer hat 10 Euro und tankt 5 Liter in einen leeren Tank (Prüfe Tankinhalt).", 5, m.getTankinhalt(), 0.0);
        
        b.tankeRoller(5);
        assertEquals("Der Besitzer hat 2,5 Euro und tankt 5 Liter in einen Tank mit 5 Litern (Prüfe Geld).", 0.0, b.getGeld(), 0.0);
        assertEquals("Der Besitzer hat 2,5 Euro und tankt 5 Liter in einen Tank mit 5 Litern (Prüfe Tankinhalt).", 6.666, m.getTankinhalt(), 0.001);
        
        m = new Motorroller(0, 60, 59);
        b = new Besitzer("Besitzer 02", 10, true, m);
        
        b.tankeRoller(5);
        assertEquals("Der Besitzer hat 10 Euro und tankt 5 Liter in einen 60l Tank mit 59 Litern (Prüfe Geld).", 8.5, b.getGeld(), 0.0);
        assertEquals("Der Besitzer hat 10 Euro und tankt 5 Liter in einen 60l Tank mit 59 Litern (Prüfe Tankinhalt).", 60, m.getTankinhalt(), 0.0);
    }
    
    @Test
    public void testFahreRoller() {
        Motorroller m = new Motorroller(0, 60, 60);
        Besitzer b = new Besitzer("Besitzer 01", 10, true, m);
        
        b.fahreRoller(5);
        assertEquals("Der Besitzer fährt 5km mit seinem Roller mit 60L Benzin und 0km auf dem Tacho (Prüfe Kilometerstand).", 5, m.getKilometerstand());
        assertEquals("Der Besitzer fährt 5km mit seinem Roller mit 60L Benzin und 0km auf dem Tacho (Prüfe Tankinhalt).", 56.875, m.getTankinhalt(), 0.0);
        
        m = new Motorroller(0, 60, 60);
        b = new Besitzer("Besitzer 02", 10, true, m);
        
        b.fahreRoller(97);
        assertEquals("Der Besitzer fährt 97km mit seinem Roller mit 60L Benzin und 0km auf dem Tacho (Prüfe Kilometerstand).", 0, m.getKilometerstand());
        assertEquals("Der Besitzer fährt 97km mit seinem Roller mit 60L Benzin und 0km auf dem Tacho (Prüfe Tankinhalt).", 60, m.getTankinhalt(), 0.0);
        
        b.fahreRoller(96);
        assertEquals("Der Besitzer fährt 96km mit seinem Roller mit 60L Benzin und 0km auf dem Tacho (Prüfe Kilometerstand).", 96, m.getKilometerstand());
        assertEquals("Der Besitzer fährt 96km mit seinem Roller mit 60L Benzin und 0km auf dem Tacho (Prüfe Tankinhalt).", 0, m.getTankinhalt(), 0.0);
    }    

}
