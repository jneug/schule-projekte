

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QueueTest
{
    
    @Test
    public void testeEnqueue() {
        Queue<String> schlange = new Queue<String>();
        
        schlange.enqueue("Hallo, ");
        
        assertEquals("Nach einmal enqueue darf die Schlange nicht leer sein.",
            false, schlange.isEmpty()); // erwartet: false
        assertEquals("Nach dem FIFO-Prinzip muss front 'Hallo, ' sein",
            "Hallo, ", schlange.front()); // erwartet: Hallo, 
            
        schlange.enqueue("Welt!");
        
        assertEquals("Nach nocheinmal enqueue darf die Schlange weiter nicht leer sein.",
            false, schlange.isEmpty()); // erwartet: false
        assertEquals("Nach dem FIFO-Prinzip muss front weiter 'Hallo, ' sein",
            "Hallo, ", schlange.front()); // erwartet: Hallo, 
    }
        
    
    @Test
    public void testeDequeue() {
        Queue<String> schlange = new Queue<String>();
        
        schlange.enqueue("Hallo, ");
        schlange.enqueue("Welt!");
        
        schlange.dequeue();
        assertEquals("Nach zweimal enqueue und einmal dequeue darf die Schlange noch nicht leer sein.",
            false, schlange.isEmpty()); // erwartet: false
        assertEquals("Top muss nun 'Welt!' sein.",
            "Welt!", schlange.front()); // erwartet: Welt!
        
        schlange.dequeue();
        assertEquals("Nach zweimal dequeue muss die Schlange leer sein.",
            true, schlange.isEmpty()); // erwartet: true
        assertNull("Es gibt kein erstes Element mehr,",
            schlange.front()); // erwartet: null
    }
    
    
    @Test
    public void testeFront() {
        Queue<Integer> schlange = new Queue<Integer>();
        
        assertNull("Front einer leeren Schlange ist null.",
            schlange.front()); // erwartet: null
        /* Ab hier benoetigen die Tests funktionierende enqueue und dequeue Operationen
        schlange.enqueue(99);
        assertEquals("Front nach einmal enqueue muss das einzige Element sein.",
            new Integer(99), schlange.front()); // erwartet: 99
        
        schlange.enqueue(88);
        schlange.enqueue(77);
        assertEquals("Nach mehrmals enqueue muss front weiter das erste Element sein.",
            new Integer(99), schlange.front()); // erwartet: 99
            
        schlange.dequeue();
        assertEquals("Nach dequeue muss front das zweite Element sein.",
            new Integer(88), schlange.front()); // erwartet: 88
        */
    }
    
    
    
    @Test
    public void testeIsEmpty() {
        Queue<String> schlange = new Queue<String>();
        
        assertEquals("Eine neue Schlange muss leer sein.",
            true, schlange.isEmpty()); // erwartet: true
        /* Ab hier benoetigen die Tests funktionierende enqueue und dequeue Operationen
        schlange.enqueue("Hallo, ");
        assertEquals("Nach einmal enqueue darf die Schlange nicht leer sein.",
            false, schlange.isEmpty()); // erwartet: false
        
        schlange.dequeue();
        assertEquals("Nach einmal dequeue muss die Schlange wieder leer sein.",
            true, schlange.isEmpty()); // erwartet: true
            
        schlange.enqueue("Hallo, ");
        schlange.enqueue("Welt!");
        schlange.dequeue();
        assertEquals("Nach zweimal enqueue und einmal dequeue darf die Schlange nicht leer sein.",
            false, schlange.isEmpty()); // erwartet: false
        */
    }
    
}
