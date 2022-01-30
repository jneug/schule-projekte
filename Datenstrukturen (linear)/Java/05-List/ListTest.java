import static org.junit.Assert.*;

import org.junit.Test;

public class ListTest {

    @Test
    public void testeAppend() {
        List<String> liste = new List<String>();
        assertTrue("Eine neue Liste sollte leer sein.",
            liste.isEmpty());

        liste.append("Erstes");

        assertFalse("Nach append(\"Erstes\") auf der leeren Liste sollte sie nicht leer sein.",
            liste.isEmpty());
        assertFalse("Nach append(\"Erstes\") auf der leeren Liste sollte das Aktuelle Element nicht verändert werden.",
            liste.hasAccess());

        liste.toFirst();
        assertTrue("Nach append(\"Erstes\"), toFirst() auf der leeren Liste sollte es ein aktuelles Element geben.",
            liste.hasAccess());
        assertEquals("Nach append(\"Erstes\"), toFirst() auf der leeren Liste sollte das aktuelles Element \"Erstes\" sein.",
            "Erstes", liste.getContent());

        liste.append("Zweites");

        assertEquals("Nach append(\"Erstes\"), toFirst(), append(\"Zweites\") sollte das aktuelles Element \"Erstes\" sein.",
            "Erstes", liste.getContent());

        liste.next();
        assertEquals("Nach append(\"Erstes\"), toFirst(), append(\"Zweites\"), next() sollte das aktuelles Element \"Zweites\" sein.",
            "Zweites", liste.getContent());

        liste.next();
        assertFalse("Nach append(\"Erstes\"), toFirst(), append(\"Zweites\"), next(), next() sollte es kein aktuelles Element geben.",
            liste.hasAccess());
    }

    @Test
    public void testeInsert() {
        List<String> liste = new List<String>();
        assertTrue("Eine neue Liste sollte leer sein.",
            liste.isEmpty());

        liste.insert("Erstes");

        assertFalse("Nach insert(\"Erstes\") auf der leeren Liste sollte sie nicht leer sein.",
            liste.isEmpty());
        assertFalse("Nach insert(\"Erstes\") auf der leeren Liste sollte das Aktuelle Element nicht verändert werden.",
            liste.hasAccess());

        liste.toFirst();
        assertTrue("Nach insert(\"Erstes\"), toFirst() auf der leeren Liste sollte es ein aktuelles Element geben.",
            liste.hasAccess());
        assertEquals("Nach insert(\"Erstes\"), toFirst() auf der leeren Liste sollte das aktuelles Element \"Erstes\" sein.",
            "Erstes", liste.getContent());

        liste.insert("Zweites");

        assertEquals("Nach insert(\"Erstes\"), toFirst(), insert(\"Zweites\") sollte das aktuelles Element \"Erstes\" sein.",
            "Erstes", liste.getContent());

        liste.next();
        assertFalse("Nach insert(\"Erstes\"), toFirst(), insert(\"Zweites\"), next() sollte es kein aktuelles Element geben..",
            liste.hasAccess());

        liste.toFirst();
        assertTrue("Nach insert(\"Erstes\"), toFirst(), insert(\"Zweites\"), next(), toFirst() sollte das aktuelle Element \"Zweites\" sein.",
            liste.hasAccess());
        assertEquals("Nach insert(\"Erstes\"), toFirst(), insert(\"Zweites\"), next(), toFirst() sollte das aktuelle Element \"Zweites\" sein.",
            "Zweites", liste.getContent());
    }

    @Test
    public void testeRemove() {
        List<String> liste = new List<String>();
        liste.append("Erstes");
        liste.append("Zweites");
        liste.append("Drittes");
        liste.toFirst();
        liste.next();

        assertFalse("Die Liste wurde mit drei Elementen befüllt.",
            liste.isEmpty());
        assertTrue("Das zweite Element (\"Zweites\") ist das Aktuelle.",
            liste.hasAccess());
        assertEquals("Das zweite Element (\"Zweites\") ist das Aktuelle.",
            "Zweites", liste.getContent());

        liste.remove();
        assertTrue("Nach remove() sollte das aktuelle Element \"Drittes\" sein.",
            liste.hasAccess());
        assertEquals("Nach remove() sollte das aktuelle Element \"Drittes\" sein.",
            "Drittes", liste.getContent());

        liste.toFirst();
        liste.next();
        assertTrue("Nach remove() sollte das zweite Element in der Liste \"Drittes\" sein.",
            liste.hasAccess());
        assertEquals("Nach remove() sollte das zweite Element in der Liste \"Drittes\" sein.",
            "Drittes", liste.getContent());

        liste.remove();
        assertFalse("Nach remove() auf \"Drittes\" sollte es kein aktuelles Element geben.",
            liste.hasAccess());
        assertFalse("Nach remove() ohne aktuellem Element, sollte nichts passieren.",
            liste.isEmpty());

        liste.remove();
        assertFalse("Nach remove() ohne aktuellem Element, sollte nichts passieren.",
            liste.hasAccess());
        assertFalse("Nach remove() ohne aktuellem Element, sollte nichts passieren.",
            liste.isEmpty());

        liste.toFirst();
        liste.remove();
        assertFalse("Nach remove() auf dem ersten (und einzigsten) Element, sollte die Liste leer sein.",
            liste.hasAccess());
        assertTrue("Nach remove() auf dem ersten (und einzigsten) Element, sollte die Liste leer sein.",
            liste.isEmpty());
    }
}
