public class Playlist {

    private String name;
    private List<Track> tracks;
    private int trackCount;

    public Playlist(String pName) {
        name = pName;
        tracks = new List<Track>();
        trackCount = 0;
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