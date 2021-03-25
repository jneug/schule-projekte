

public class Counter extends Thread {

    private String name;

    public Counter(String pName) {
        name = pName;
    }

    @Override
    public void run() {
        for (int count = 0; count <= 100000; count++) {
            System.out.printf("%s: %04d\n", name, count);
        }
    }

    public static void main(String[] args) {
        Counter c1 = new Counter("Counter 1");
        Counter c2 = new Counter("Counter 2");
        Counter c3 = new Counter("Counter 3");
        Counter c4 = new Counter("Counter 4");

        c1.start();
        c2.start();
        c3.start();
        c4.start();
    }
}

