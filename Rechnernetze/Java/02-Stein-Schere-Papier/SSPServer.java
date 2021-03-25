
public class SSPServer extends Server
{

    private List<Player> players;

    private Player waiting;

    public SSPServer( int port )
    {
        super(port);
        players = new List<Player>();
    }

    public void processNewConnection(String pClientIP, int pClientPort)
    {
        Player newPlayer = new Player(pClientIP, pClientPort);
        players.append(newPlayer);

        if( waiting == null ) {
            waiting = newPlayer;
        } else {
            waiting.setOpponent(newPlayer);
            newPlayer.setOpponent(waiting);

            waiting = null;
        }

        send(pClientIP, pClientPort, "+OK what's your name?");
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage)
    {
        if( pMessage.startsWith("NICK ") ) {
            Player p = findPlayer(pClientIP, pClientPort);
            if( p.hasNickname() ) {
                send(pClientIP, pClientPort, "-ERR you already choose a nickname");
            } else {
                String nickname = pMessage.substring(5);
                p.setNickname(nickname);
                send(pClientIP, pClientPort, "+OK hello "+nickname);
            }
        } else if( pMessage.startsWith("SYMBOL ") ) {
            Player p = findPlayer(pClientIP, pClientPort);
            String symbol = pMessage.substring(7);
            p.setSymbol(symbol);
            send(pClientIP, pClientPort, "+OK you choose "+symbol);

            if( p.hasOpponent() && p.getOpponent().hasSymbol() ) {
                Player o = p.getOpponent();

                if( evalGame(symbol, o.getSymbol()) == 1 ) {
                    send(p.getIp(), p.getPort(), "+OK you won against "+o.getNickname());
                    send(o.getIp(), o.getPort(), "+OK you lost against "+p.getNickname());

                    closeConnection(p.getIp(), p.getPort());
                    closeConnection(o.getIp(), o.getPort());
                } else if( evalGame(symbol, o.getSymbol()) == -1 ) {
                    send(p.getIp(), p.getPort(), "+OK you lost against "+o.getNickname());
                    send(o.getIp(), o.getPort(), "+OK you won against "+p.getNickname());

                    closeConnection(p.getIp(), p.getPort());
                    closeConnection(o.getIp(), o.getPort());
                } else {
                    p.setSymbol(null);
                    o.setSymbol(null);

                    send(p.getIp(), p.getPort(), "-ERR draw agains "+o.getNickname());
                    send(o.getIp(), o.getPort(), "-ERR draw agains "+p.getNickname());
                }
            }
        } else if( pMessage.equals("QUIT") ) {
            closeConnection(pClientIP, pClientPort);
        } else {
            send(pClientIP, pClientPort, "+ERR unknown command");
        }
    }

    public void processClosingConnection(String pClientIP, int pClientPort)
    {
        players.toFirst();
        while( players.hasAccess() ) {
            Player p = players.getContent();
            if( p.getIp().equals(pClientIP) && p.getPort() == pClientPort ) {
                players.remove();
                send(p.getIp(), p.getPort(), "+OK bye");
                break;
            }
            players.next();
        }
    }

    public int evalGame( String r1, String r2 ) {
        int a = evalResult( r1 );
        int b = evalResult( r2 );
        if( a == b ) {
            return 0;
        } else {
            if( a == 0 && b == 1 )
                return 1;
            else if( a == 1 && b == 2 )
                return 1;
            else if( a == 2 && b == 0 )
                return 1;
            else
                return -1;
        }
    }

    public int evalResult( String r ) {
        if( r.toLowerCase().equals("stein") ) {
            return 1;
        }
        if( r.toLowerCase().equals("schere") ) {
            return 2;
        }
        return 0;
    }

    private Player findPlayer(String pClientIP, int pClientPort) {
        players.toFirst();
        while( players.hasAccess() ) {
            Player p = players.getContent();
            if( p.getIp().equals(pClientIP) && p.getPort() == pClientPort ) {
                return p;
            }
            players.next();
        }
        return null;
    }

    public void quit() {
        close();
    }
}
