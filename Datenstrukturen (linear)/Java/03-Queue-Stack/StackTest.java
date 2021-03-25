

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StackTest
{

    @Test
    public void testePush() {
        Stack<String> stapel = new Stack<String>();

        stapel.push("Hallo, ");

        assertEquals("Nach einmal push darf der Stapel nicht leer sein.",
            false, stapel.isEmpty()); // erwartet: false
        assertEquals("Nach dem LIFO-Prinzip muss top 'Hallo, ' sein",
            "Hallo, ", stapel.top()); // erwartet: Hallo,

        stapel.push("Welt!");

        assertEquals("Nach nocheinmal push darf der Stapel weiter nicht leer sein.",
            false, stapel.isEmpty()); // erwartet: false
        assertEquals("Nach dem LIFO-Prinzip muss top nun 'Welt!' sein",
            "Welt!", stapel.top()); // erwartet: Welt!
    }


    @Test
    public void testePop() {
        Stack<String> stapel = new Stack<String>();

        stapel.push("Hallo, ");
        stapel.push("Welt!");

        stapel.pop();
        assertEquals("Nach zweimal push und einmal pop darf der Stapel noch nicht leer sein.",
            false, stapel.isEmpty()); // erwartet: false
        assertEquals("Top muss nun 'Hallo, ' sein.",
            "Hallo, ", stapel.top()); // erwartet: Hallo,

        stapel.pop();
        assertEquals("Nach zweimal pop muss der Stapel leer sein.",
            true, stapel.isEmpty()); // erwartet: true
        assertNull("Es gibt kein erstes Element mehr,",
            stapel.top()); // erwartet: null
    }


    @Test
    public void testeTop() {
        Stack<Integer> stapel = new Stack<Integer>();

        assertNull("Top eines leeren Stapels ist null.",
            stapel.top()); // erwartet: null

        // Die folgenden Tests benötigen funktionierende push und pop Operationen
        stapel.push(99);
        assertEquals("Top nach einmal push muss das einzige Element sein.",
            new Integer(99), stapel.top()); // erwartet: 99

        stapel.push(99);
        stapel.push(88);
        stapel.push(77);
        assertEquals("Nach mehrmals push muss top das letzt Element sein.",
            new Integer(77), stapel.top()); // erwartet: 77
    }



    @Test
    public void testeIsEmpty() {
        Stack<String> stapel = new Stack<String>();

        assertEquals("Ein neuer Stapel muss leer sein.",
            true, stapel.isEmpty()); // erwartet: true

        // Die folgenden Tests benötigen funktionierende push und pop Operationen
        stapel.push("Hallo, ");
        assertEquals("Nach einmal push darf der Stapel nicht leer sein.",
            false, stapel.isEmpty()); // erwartet: false

        stapel.pop();
        assertEquals("Nach einmal pop muss der Stapel wieder leer sein.",
            true, stapel.isEmpty()); // erwartet: true

        stapel.push("Hallo, ");
        stapel.push("Welt!");
        stapel.pop();
        assertEquals("Nach zweimal push und einmal pop darf der Stapel nicht leer sein.",
            false, stapel.isEmpty()); // erwartet: false
    }
}
