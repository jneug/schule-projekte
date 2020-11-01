import java.util.ArrayList;
import java.util.List;

public class HashtableLinked implements Hashtable<Integer, Artikel> {

    private class Hashitem {
        Integer key;
        Artikel content;

        public Hashitem( Integer pKey, Artikel pContent ) {
            this.key = pKey;
            this.content = pContent;
        }

        public boolean equals( Object o ) {
            return o.equals(key);
        }
    }

    private int size;
    private List<List<Hashitem>> content;

    public HashtableLinked() {
        this(31);
    }

    public HashtableLinked(int pSize) {
        this.size = pSize;
        this.content = new ArrayList<List<Hashitem>>(pSize);
        
        this.put(32, new Artikel(32, "Art1", 0));
        this.put(63, new Artikel(63, "Art2", 0));
    }

    @Override
    public boolean hasKey( Integer pKey ) {
        int i = getIndex(pKey);
        return false;
    }

    public void put( Integer pKey, Artikel pContent ) {
        int i = getIndex(pKey);
        if( content.get(i) == null ) {
            content.set(i, new ArrayList<>());
            content.get(i).add(new Hashitem(pKey, pContent));
        } else {
            for( Hashitem item : content.get(i) ) {
                if( item.key.equals(pKey) ) {
                    item.content = pContent;
                    return; // Aus der Schleife aussteigen
                }
            }
            content.get(i).add(new Hashitem(pKey, pContent));
        }
    }

    @Override
    public Artikel get( Integer pKey ) {
        int i = getIndex(pKey);
        if( content.get(i) != null ) {
            for( Hashitem item : content.get(i) ) {
                if( item.key.equals(pKey) ) {
                    return item.content;
                }
            }
            return null;
        } else {
            return null;
        }
    }

    @Override
    public void delete( Integer pKey ) {
        int i = getIndex(pKey);

        if( content.get(i) != null ) {
            content.get(i).remove(new Hashitem(pKey, null));
        }
    }

    private int getIndex( Integer pKey ) {
        int i = pKey.hashCode() % size;
        return i;
    }

}
