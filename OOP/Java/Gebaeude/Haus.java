
public class Haus extends Gebaeude
{
    protected int stockwerke;
    
    public Haus( double pHoeheProStockwerk, int pStockwerke, int pBesucher )
    {
        super(pHoeheProStockwerk, pBesucher);
        stockwerke = pStockwerke;
    }
    
    public double getHoehe() {
        return hoehe * stockwerke;
    }
}
