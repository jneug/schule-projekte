public class LuftGehege extends Gehege {

    public LuftGehege( Fliegend pBewohner ) {
        super(pBewohner);
    }

    public Fliegend getBewohner() {
        return (Fliegend)super.getBewohner();
    }

}
