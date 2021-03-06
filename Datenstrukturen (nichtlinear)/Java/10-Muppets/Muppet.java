/**
 * Ein Muppet der Muppet-Show:
 * https://de.wikipedia.org/wiki/Die_Muppet_Show
 */
public class Muppet implements ComparableContent<Muppet> {

    private String name;

    private String color;

    private double height;

    /**
     * Konstruktor für Muppets.
     *
     * @param pName
     * @param pColor
     * @param pHeight
     */
    public Muppet( String pName, String pColor, double pHeight ) {
        name = pName;
        color = pColor;
        height = pHeight;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight( double height ) {
        this.height = height;
    }

    public String toString() {
        return String.format("%s[%d]", name, height);
    }

    /*
    Vergleichsmethoden zum Sortieren von Muppets.
    Die Muppets sollen zunächst nach Namen sortiert werden.
    */
    /*aufg*
    // TODO: Implementiere die drei Vergleichsmethoden
    @Override
    public boolean isGreater( Muppet pMuppet ) {
        return false;
    }

    @Override
    public boolean isEqual( Muppet pMuppet ) {
        return false;
    }

    @Override
    public boolean isLess( Muppet pMuppet ) {
        return false;
    }
    *aufg*/
    //ml*
    /*
    @Override
    public boolean isGreater( Muppet pMuppet ) {
        return name.compareToIgnoreCase(pMuppet.getName()) > 0;
    }

    @Override
    public boolean isEqual( Muppet pMuppet ) {
        return name.compareToIgnoreCase(pMuppet.getName()) == 0;
    }

    @Override
    public boolean isLess( Muppet pMuppet ) {
        return name.compareToIgnoreCase(pMuppet.getName()) < 0;
    }
    */

    /*
    Vergleichsmethoden zum Sortieren von Muppets.
    Die Muppets werden nach Größe sortiert.
    */
    @Override
    public boolean isGreater( Muppet pMuppet ) {
        return height > pMuppet.getHeight();
    }

    @Override
    public boolean isEqual( Muppet pMuppet ) {
        return height == pMuppet.getHeight();
    }

    @Override
    public boolean isLess( Muppet pMuppet ) {
        return height < pMuppet.getHeight();
    }
    //*ml

}
