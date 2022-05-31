public class Playlist {

    private int id;
    private String name;
    private List<Track> tracks;
    private int trackCount;

    public Playlist(int pID, String pName) {
        id = pID;
        name = pName;
        tracks = new List<Track>();
        trackCount = 0;
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

    public List<Track> getTracks() {
        return tracks;
    }

    public void addTrack(Track pTrack) {
        tracks.append(pTrack);
        trackCount++;
    }

    public int getTrackCount() {
        return trackCount;
    }

}
