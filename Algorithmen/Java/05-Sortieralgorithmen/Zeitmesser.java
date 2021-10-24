public class Zeitmesser {

    public static void main(String[] args) {
        int[] zahlen = Zahlengenerator.zufallsArray(20);
        Sortiermaschine.quicksort(zahlen);
        Sortiermaschine.printArray(zahlen);
    }

    public static void messen() {
        Stoppuhr uhr = new Stoppuhr();
        for( int n = 20000 ;n < 100000; n += 1000 ) {
            int[] zahlen = Zahlengenerator.zufallsArray(n);
            uhr.start();
            Sortiermaschine.quicksort(zahlen);
            long time = uhr.stop();

            System.out.printf("%d\t%d\n", n, time/1000);
        }
    }

}
