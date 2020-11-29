

public class HashtableLinear implements Hashtable<Integer, Artikel> {

    class Hashitem {
        Integer key;
        Artikel content;

        public Hashitem( Integer pKey, Artikel pContent ) {
            this.key = pKey;
            this.content = pContent;
        }
    }

    private Hashitem[] content;

    public HashtableLinear() {
        this(31);
    }

    public HashtableLinear(int pSize) {
        this.content = new Hashitem[pSize];
    }

    @Override
    public boolean hasKey( Integer pKey ) {
        int i = getIndex(pKey);
        return (content[i] != null && content[i].key.equals(pKey));
    }

    public void put( Integer pKey, Artikel pContent ) {
        int i = getIndex(pKey);
        if( content[i] == null ) {
            content[i] = new Hashitem(pKey, pContent);
        }
    }

    @Override
    public Artikel get( Integer pKey ) {
        int i = getIndex(pKey);
        if( content[i] != null && content[i].key.equals(pKey) ) {
            return (Artikel) content[i].content;
        } else {
            return null;
        }
    }

    @Override
    public void delete( Integer pKey ) {
        int i = getIndex(pKey);
        if( content[i] != null && content[i].key.equals(pKey) ) {
            content[i] = null;
        }
    }

    private int getIndex( Integer pKey ) {
        int i = pKey.hashCode() % content.length;
        int j = i;

        while( content[j] != null && !content[j].key.equals(pKey) ) {
            j++;
            if( j == content.length ) {
                j = 0;
            }
            if( j == i ) {
                break;
            }
        }

        return j;
    }

}
