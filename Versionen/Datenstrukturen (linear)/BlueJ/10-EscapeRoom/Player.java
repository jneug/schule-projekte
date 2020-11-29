/**
 * Spieleklasse für das Spiel.
 */
public class Player {

    private String name;

    private List<Item> items;

    public Player( String pName ) {
        name = pName;
        items = new List<Item>();
    }

    public String getName() {
        return name;
    }

    public void addItem( Item pItem ) {
        items.append(pItem);
    }

    public boolean hasItem( Item pItem ) {
        items.toFirst();
        while( items.hasAccess() ) {
            if( pItem.equals(items.getContent()) ) {
                return true;
            }
            items.next();
        }
        return false;
    }

    public void removeItem( Item pItem ) {
        items.toFirst();
        while( items.hasAccess() ) {
            if( pItem.equals(items.getContent()) ) {
                items.remove();
            }
            items.next();
        }
    }

    public Item getItemByName( String name ) {
        items.toFirst();
        while( items.hasAccess() ) {
            if( items.getContent().getName().equals(name) ) {
                items.getContent();
            }
            items.next();
        }
        return null;
    }

    public Item getFirstItem() {
        items.toFirst();
        if( items.hasAccess() ) {
            return items.getContent();
        } else {
            return null;
        }
    }

    public Item[] getAllItems() {
        int i = 0;
        items.toFirst();
        while( items.hasAccess() ) {
            i += 1;
            items.next();
        }

        Item[] result = new Item[i];
        int j = 0;
        items.toFirst();
        while( items.hasAccess() ) {
            result[j] = items.getContent();
            j += 1;
            items.next();
        }

        return result;
    }

}
