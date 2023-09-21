public class Bahnhof {

    private String ort;
    private Stack<Waggon> gleis1;
    private Stack<Waggon> gleis2;

    public Bahnhof(String pOrt) {
        ort = pOrt;
        gleis1 = new Stack<Waggon>();
        gleis2 = new Stack<Waggon>();
    }

    public String getOrt() {
        return ort;
    }

    public void rangieren() {
        gleis1.push(new Waggon(104, "München", "Leer"));
        printStack(gleis1);
        gleis1.push(new Waggon(236, "München", "Leer"));
        printStack(gleis1);
        gleis1.push(new Waggon(98, "München", "Leer"));
        printStack(gleis1);
        gleis1.pop();
        printStack(gleis1);
        gleis1.push(new Waggon(74, "München", "Leer"));
        printStack(gleis1);
    }
    
    private void printStack( Stack<Waggon> gleis ) {
        Stack<Waggon> tmp = new Stack<Waggon>();
        while( !gleis.isEmpty() ) {
            Waggon w = gleis.top();
            System.out.printf("Waggon %d | ", w.getNummer());
            
            tmp.push(w);
            gleis.pop();
        }
        System.out.println();
        while( !tmp.isEmpty() ) {
            gleis.push(tmp.top());
            tmp.pop();
        }
    }

}