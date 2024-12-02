/**
 *
 */
public class Token {

    private String type;

    private String value;

    public Token( String pType ) {
        type = pType;
        value = null;
    }

    public Token( String pType, String pValue ) {
        type = pType;
        value = pValue;
    }

    public String getType() {
        return type;
    }

    public void setType( String pType ) {
        type = pType;
    }

    public String getValue() {
        return value;
    }

    public void setValue( String pValue ) {
        value = pValue;
    }

}
