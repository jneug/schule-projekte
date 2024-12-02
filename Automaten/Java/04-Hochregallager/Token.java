/**
 *
 */
public class Token {

	private String type;

	private String token;

	public Token(String pType) {
		type = pType;
        token = null;
	}

	public Token(String pType, String pToken) {
		type = pType;
		token = pToken;
	}

	public String getType() {
		return type;
	}

	public void setType(String pType) {
		type = pType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String pToken) {
		token = pToken;
	}

}
