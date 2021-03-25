
public class Hochhaus extends Haus
{
    protected int stockwerke;
    
    public Hochhaus( double pHoeheProStockwerk, int pStockwerke, int pBesucherProStockwerk )
    {
        super(pHoeheProStockwerk, pStockwerke, pBesucherProStockwerk);
    }
    
    public int anzahlBesucher() {
        return stockwerke * besucher;
    }
}
