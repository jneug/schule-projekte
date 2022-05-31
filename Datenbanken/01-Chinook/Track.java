public class Track {

    private int id;

    private String name;

    private long milliseconds;

    private long bytes;

    private double unitPrice;

    public Track(int pID, String pName, long pMilliseconds, long pBytes, double pUnitPrice) {
        id = pID;
        name = pName;
        milliseconds = pMilliseconds;
        bytes = pBytes;
        unitPrice = pUnitPrice;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(long pMilliseconds) {
        milliseconds = pMilliseconds;
    }

    public long getBytes() {
        return bytes;
    }

    public void setBytes(long pBytes) {
        bytes = pBytes;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double pUnitPrice) {
        unitPrice = pUnitPrice;
    }


}
