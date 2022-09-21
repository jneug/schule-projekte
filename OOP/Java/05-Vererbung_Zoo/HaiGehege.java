public class HaiGehege extends WasserGehege {

    public HaiGehege( Hai pBewohner ) {
        super(pBewohner);
    }

    public Hai getBewohner() {
        return (Hai)super.getBewohner();
    }

    public void setBewohner( Schwimmend pBewohner ) {
        if( pBewohner instanceof Hai ) {
            super.setBewohner(pBewohner);
        } else {
            throw new IllegalArgumentException("Im HaiGehege dürfen nur Haie gehalten werden.");
        }
    }

}
