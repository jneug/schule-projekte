

public class HashtableSimple<KeyType, ContentType> implements Hashtable<KeyType, ContentType> {

    private ContentType[] content;

    public HashtableSimple() {
        this(31);
    }

    public HashtableSimple(int pSize) {
        this.content = (ContentType[]) new Object[pSize];
    }

    @Override
    public boolean hasKey( KeyType pKey ) {
        int i = getIndex(pKey);
        return content[i] != null;
    }

    public void put( KeyType pKey, ContentType pContent ) {
        int i = getIndex(pKey);
        if( content[i] == null ) {
            content[i] = pContent;
        }
    }

    @Override
    public ContentType get( KeyType pKey ) {
        int i = getIndex(pKey);
        if( content[i] != null ) {
            return content[i];
        } else {
            return null;
        }
    }

    @Override
    public void delete( KeyType pKey ) {
        int i = getIndex(pKey);
        if( content[i] != null ) {
            content[i] = null;
        }
    }

    private int getIndex( KeyType pKey ) {
        return pKey.hashCode() % content.length;
    }

}
