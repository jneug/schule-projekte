import java.util.List;
import java.util.ArrayList;

public class QwitterUser implements ComparableContent<QwitterUser> {
    
    private String name;
    
    private String password;
    
    private String address;
    
    public QwitterUser( String pName, String pPassword ) {
        name = pName;
        password = pPassword;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress( String pIp, int pPort ) {
        address = pIp + ":" + pPort;
    }
    
    public void unsetAddress() {
        address = null;
    }
    
    public boolean hasAddress() {
        return (address != null);
    }

    // ############################################################################
    // # Interface ComparableContent<User>                                        #
    // ############################################################################
      
    public boolean isGreater(QwitterUser pContent) {
        if( pContent.hasAddress() && hasAddress() ) {
            return (pContent.getAddress().compareToIgnoreCase(address) < 0);
        } else {
            return false;
        }
    }
    
    public boolean isEqual(QwitterUser pContent) {
        if( pContent.hasAddress() && hasAddress() ) {
            return (pContent.getAddress().compareToIgnoreCase(address) == 0);
        } else {
            return false;
        }
    }
    
    public boolean isLess(QwitterUser pContent) {
        if( pContent.hasAddress() && hasAddress() ) {
            return (pContent.getAddress().compareToIgnoreCase(address) > 0);
        } else {
            return false;
        }
    }

}
