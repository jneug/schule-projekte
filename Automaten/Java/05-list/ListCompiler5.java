public class ListCompiler5 {

    private String error;

    private String sourcecode;

    private List<Token> tokenlist;

    public ListCompiler5( String pSourceFile ) {
        sourcecode = FileSystem.getFileContents(pSourceFile).trim();
    }

    public boolean scan() {
        int state = 0;

        // Split sourcecode at whitespace
        String[] words = this.splitAtWhitespace(sourcecode);

        // Create empty tokenlist
        tokenlist = new List<>();

        // Run lexer
        // Translate words into proper tokens
        for( String word : words ) {
            if( isNumber(word) ) {
                tokenlist.append(new Token("NUMBER", word));
            } else if(
                word.equalsIgnoreCase("list") ||
                    word.equalsIgnoreCase("from") ||
                    word.equalsIgnoreCase("to") ||
                    word.equalsIgnoreCase("by") ||
                    word.equalsIgnoreCase("end")
            ) {
                tokenlist.append(new Token("KEYWORD", word));
            } else if( word.startsWith("{") && word.endsWith("}") ) {
                tokenlist.append(new Token("VARIABLE", word));
            } else {
                tokenlist.append(new Token("STRING", word));
            }
        }

        return true;
    }

    public boolean parse() {
        int state = 0;
        Stack<Character> stack = new Stack<>();
        stack.push('#');

        tokenlist.toFirst();
        while( tokenlist.hasAccess() ) {
            Token t = tokenlist.getContent();

            switch( state ) {
                //ml*
                case 0:
                    if( t.getToken().equalsIgnoreCase("list") ) {
                        // stack.push('L');
                        state = 10;
                    } else {
                        error = "LIST programms need to start with \"list\".";
                        return false;
                    }
                    break;

                case 10:
                    if( t.getType().equals("VARIABLE") ) {
                        t.setType("COUNTER");
                        state = 1;
                    } else {
                        error = "LIST programms need to define a counting variable.";
                        return false;
                    }
                    break;

                case 1:
                    if( t.getToken().equalsIgnoreCase("from") ) {
                        state = 2;
                    } else if( t.getToken().equalsIgnoreCase("to") ) {
                        state = 4;
                    } else {
                        error = "LIST programms need a \"to\" statement.";
                        return false;
                    }
                    break;

                case 2:
                    if( t.getType().equals("NUMBER") ) {
                        state = 3;
                    } else {
                        error = "\"from\" needs to be followed by a number.";
                        return false;
                    }
                    break;

                case 3:
                    if( t.getToken().equalsIgnoreCase("to") ) {
                        state = 4;
                    } else {
                        error = "LIST programms need a \"to\" statement.";
                        return false;
                    }
                    break;

                case 4:
                    if( t.getType().equals("NUMBER") ) {
                        state = 5;
                    } else {
                        error = "\"to\" needs to be followed by a number.";
                        return false;
                    }
                    break;

                case 5:
                    if( t.getToken().equalsIgnoreCase("by") ) {
                        state = 6;
                    } else if( t.getToken().equalsIgnoreCase("list") ) {
                        stack.push('L');
                        state = 10;
                    } else if( t.getType().equals("STRING") || t.getType().equals("VARIABLE") ) {
                        state = 8;
                    } else {
                        error = "\"to\" needs to be followed by \"by\" or the list body.";
                        return false;
                    }
                    break;

                case 6:
                    if( t.getType().equals("NUMBER") ) {
                        state = 7;
                    } else {
                        error = "\"by\" needs to be followed by a number.";
                        return false;
                    }
                    break;

                case 7:
                    if( t.getType().equals("STRING") || t.getType().equals("VARIABLE")) {
                        state = 8;
                    } else if( t.getToken().equalsIgnoreCase("list") ) {
                        stack.push('L');
                        state = 10;
                    } else {
                        error = "List body may not be empty.";
                        return false;
                    }
                    break;

                case 8:
                    if( t.getType().equals("STRING") ) {
                        state = 8;
                    } else if( t.getType().equals("VARIABLE") ) {
                        state = 8;
                    } else if( t.getToken().equalsIgnoreCase("list") ) {
                        stack.push('L');
                        state = 10;
                    } else if( t.getToken().equals("end") ) {
                        char top = stack.top();
                        stack.pop();

                        switch( top ) {
                            case 'L':
                                state = 8;
                                break;
                            case '#':
                                stack.push('#');
                                state = 0;
                                break;
                            default:
                                error = "Missing end statement.";
                                return false;
                        }
                    } else {
                        error = "Wrong token in list body.";
                        return false;
                    }
                    break;

                //*ml
                default:
                    return false;
            }

            tokenlist.next();
        }

        return state == 0;
    }

    public void interpret() {
        //ml*
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
            } else if( t.getToken().equals("end") ) {
                this.run(from, to, by, out);

                from = 0;
                to = -1;
                by = 1;
                out = new List<>();
            }

            tokenlist.next();
        }
        //*ml
    }

    private void run( int from, int to, int by, List<Token> out ) {
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

    private void showError() {
        System.err.println("Fehler beim Compilieren:");
        System.err.println(error);
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
     * Hilfsmethode, um einen String an den Leerstellen in einzelne "Wörter" zu
     * trennen. Die Teile werden als String-Array zurückgegeben.
     *
     * @param pString
     * @return
     */
    private String[] splitAtWhitespace( String pString ) {
        return pString.split("[\\s\\n\\r]+");
    }

    /**
     * Prüft, ob ein String nur aus Ziffer (0-9) besteht. ezimalzahlen mit
     * Nachkommastellen werden nicht erkannt.
     *
     * @param pString Ein Text, der geprüft werden soll.
     * @return {@code true}, wenn der Text nur aus Ziffern besteht, {@code false} sonst.
     */
    public static boolean isNumber( String pString ) {
        return pString.matches("(0|[1-9]\\d*)");
    }

    /**
     * Formt einen Wert vom Typ String in einen Integer um.
     *
     * @param pString Ein Text, der nur aus Ziffern besteht.
     * @return Der int-Wert des Textes.
     * @see Integer#parseInt(String)
     */
    public static int toInt( String pString ) {
        return Integer.parseInt(pString);
    }

    public static void main( String[] args ) {
        ListCompiler5 lc = new ListCompiler5("programs/example5.list");
        if( !lc.scan() ) {
            lc.showError();
            return;
        }
        if( !lc.parse() ) {
            lc.showError();
            lc.printTokens();
            return;
        }
        lc.interpret();
    }

}
