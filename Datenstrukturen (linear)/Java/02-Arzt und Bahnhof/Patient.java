public class Patient {

    private String name;
    private int wartenummer;
    private long ankunftszeit;

    public Patient(String pName, int pWartenummer, long pAnkunftszeit) {
        name = pName;
        wartenummer = pWartenummer;
        ankunftszeit = pAnkunftszeit;
    }

    public String getName() {
        return name;
    }

    public int getWartenummer() {
        return wartenummer;
    }

    public void setWartenummer(int pWartenummer) {
        wartenummer = pWartenummer;
    }

    public long getAnkunftszeit() {
        return ankunftszeit;
    }

}