
public class Player
{
    private String ip;
    private int port;
    
    private String nickname;
    
    private Player opponent;
    
    private String symbol;

    public Player( String pIp, int pPort )
    {
        ip = pIp;
        port = pPort;
    }
    
    public String getIp() {
        return ip;
    }
    
    public int getPort() {
        return port;
    }
    
    public void setSymbol( String pSymbol ) {
        symbol = pSymbol;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public boolean hasSymbol() {
        return (symbol != null);
    }
    
    public void setNickname( String pNickname ) {
        nickname = pNickname;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public boolean hasNickname() {
        return (nickname != null);
    }

    public void setOpponent(Player pPlayer)
    {
        opponent = pPlayer;
    }
    
    public Player getOpponent() {
        return opponent;
    }
    
    public boolean hasOpponent() {
        return (opponent != null);
    }
}
