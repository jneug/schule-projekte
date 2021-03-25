public class Competition {

    private List<Athlete> athlets = new List<>();

    private BinarySearchTree<Athlete> race = new BinarySearchTree<>();

    public Competition() {}

    public void addAthlete( String pName ) {
        this.athlets.append(new Athlete(pName));
    }

    public void simulateRace() {
        
    }

}
