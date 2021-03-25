

public interface Hashtable<KeyType, ContentType> {

    public boolean hasKey( KeyType pKey );

    public void put( KeyType pKey, ContentType pContent );

    public ContentType get( KeyType pKey );

    public void delete( KeyType pKey );

}
