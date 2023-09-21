public class Arztpraxis {

    private Queue<Patient> warteschlange;
    
    private int nummer = 0;

    public Arztpraxis() {
        warteschlange = new Queue<Patient>();
    }

    public Patient neuerPatient(String pName) {
        Patient p = new Patient(pName, ++nummer, 0);
        warteschlange.enqueue(p);
        return p;
    }
    
    public Patient behandeleNaechstenPatienten() {
        Patient p = warteschlange.front();
        warteschlange.dequeue();
        return p;
    }

    public void einTagBeimArzt() {
        // TODO:
        // Implementiere die Situation vom AB.
        // Ergänze auch sinnvolle Ausgaben.
    }
    
    
    private void printQueue( Queue<Patient> warten ) {
        Queue<Patient> tmp = new Queue<Patient>();
        while( !warten.isEmpty() ) {
            Patient p = warten.front();
            System.out.printf("Patient %s | ", p.getName());
            
            tmp.enqueue(p);
            warten.dequeue();
        }
        System.out.println();
        while( !tmp.isEmpty() ) {
            warten.enqueue(tmp.front());
            tmp.dequeue();
        }
    }

}