public class Track {

    private String name;
    private long milliseconds;
    private long bytes;
    private double unitPrice;

    public Track(String pName, long pMilliseconds, long pBytes, double pUnitPrice) {
        name = pName;
        milliseconds = pMilliseconds;
        bytes = pBytes;
        unitPrice = pUnitPrice;
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