public class ListCompiler2 {

    private String sourcecode;

    private List<Token> tokenlist;

    public ListCompiler2( String pSourceFile ) {
        sourcecode = "";

        List<String> lines = FileSystem.getFileContents(pSourceFile);
        lines.toFirst();
        while( lines.hasAccess() ) {
            sourcecode += lines.getContent() + "\n";
            lines.next();
        }
        sourcecode = sourcecode.trim();
    }

    public boolean scan() {
        int state = 0;

        // Split sourcecode at whitespace
        String[] words = this.splitAtWhitespace(sourcecode);

        tokenlist = new List<>();

        // Run lexer
        for (String word : words) {
            switch( state ) {
                case 0:
                    if( word.equals("list") || word.equals("from") || word.equals("to") ) {
                        tokenlist.append(new Token("KEYWORD", word));
                    } else if( word.equals("by") ) {
                        tokenlist.append(new Token("KEYWORD", word));
                        state = 1;
                    } else if( this.isNumber(word) ) {
                        tokenlist.append(new Token("NUMBER", word));
                    } else {
                        return false;
                    }
                break;

                case 1:
                    if( this.isNumber(word) ) {
                        tokenlist.append(new Token("NUMBER", word));
                        state = 2;
                    }
                break;

                case 2:
                    if( word.equals("end") ) {
                        tokenlist.append(new Token("KEYWORD", word));
                        state = 3;
                    } else if( word.startsWith("{") && word.endsWith("}") ) {
                        tokenlist.append(new Token("VARIABLE", word));
                    } else {
                        tokenlist.append(new Token("STRING", word));
                    }
                break;

                default:
                return false;
            }
        }

        return state == 3;
    }

    public boolean parse() {
        int state = 0;

        tokenlist.toFirst();
        while( tokenlist.hasAccess() ) {
            Token t = tokenlist.getContent();

            switch( state ) {
                case 0:
                    if( t.getToken().equals("list") ) {
                        state = 1;
                    } else {
                        return false;
                    }
                break;

                case 1:
                    if( t.getToken().equals("from") ) {
                        state = 2;
                    } else {
                        return false;
                    }
                break;

                case 2:
                    if( t.getType().equals("NUMBER") ) {
                        state = 3;
                    } else {
                        return false;
                    }
                break;

                case 3:
                    if( t.getToken().equals("to") ) {
                        state = 4;
                    } else {
                        return false;
                    }
                break;

                case 4:
                    if( t.getType().equals("NUMBER") ) {
                        state = 5;
                    } else {
                        return false;
                    }
                break;

                case 5:
                    if( t.getType().equals("STRING") || t.getType().equals("VARIABLE") ) {
                        state = 5;
                    } else if( t.getToken().equals("end") ) {
                        state = 6;
                    } else {
                        return false;
                    }
                break;

                default:
                    return false;
            }

            tokenlist.next();
        }

        return state == 6;
    }

    public void interpret() {
        int from = 0, to = -1, by = 1;
        List<Token> out = new List<>();

        tokenlist.toFirst();
        while( tokenlist.hasAccess() ) {
            Token t = tokenlist.getContent();

            if( t.getToken().equals("from") ) {
                tokenlist.next();
                from = Integer.parseInt(tokenlist.getContent().getToken());
            } else if( t.getToken().equals("to") ) {
                tokenlist.next();
                to = Integer.parseInt(tokenlist.getContent().getToken());
            } else if( t.getToken().equals("by") ) {
                tokenlist.next();
                by = Integer.parseInt(tokenlist.getContent().getToken());
            } else if( t.getType().equals("STRING") || t.getType().equals("VARIABLE") ) {
                out.append(t);
            }

            tokenlist.next();
        }

        for(; from <= to; from += by ) {
            out.toFirst();
            while( out.hasAccess() ) {
                if( out.getContent().getType().equals("STRING") ) {
                    System.out.print(out.getContent().getToken());
                } else {
                    System.out.print(from);
                }
                System.out.print(" ");
                out.next();
            }
            System.out.println();
        }
    }

    public void printTokens() {
        tokenlist.toFirst();
        while( tokenlist.hasAccess() ) {
            Token t = tokenlist.getContent();
            System.out.println(t.getType() + "=" + t.getToken());
            tokenlist.next();
        }
    }

    /**
     * Hilfsmethode, um einen String an den Leerstellen in einzelne
     * "Wörter" zu trennen. Die Teile werden als String-Array zurückgegeben.
     * @param pString
     * @return
     */
    private String[] splitAtWhitespace( String pString ) {
        return pString.split("[\\s\\n\\r]+");
    }

    /**
     * Prüft, ob ein String nur aus Ziffer (0-9) besteht. Dezimalzahlen
     * mit Nachkommastellen werden nicht erkannt.
     * @param pString
     * @return
     */
    private boolean isNumber( String pString ) {
        return pString.matches("\\d+");
    }

    public static void main(String[] args) {
        ListCompiler2 lc = new ListCompiler2("programs/example2.list");
        lc.scan();
        lc.parse();
        lc.interpret();
        //lc.printTokens();
    }

}
