/**
 * Passagiere sind die Datensätze für den Entschiedungsbaum.
 * Sie enthalten die Attributswerte, nach denen die Entscheidungen
 * im Baum gefällt werden.
 */
public class Passagier {

    /**
     * Klassenmethode die ein String-Arrays der Attributnamen zurück gibt.
     * @return
     */
    public static final String[] getAttribute() {
        return new String[]{"clazz", "sex", "age", "sibsp", "parch", "embarked"};
    }

    /**
     * Klassenmethode die ein String-Arrays der möglichen Werte eines
     * Attributs zurück gibt.
     * @param pAttribut
     * @return
     */
    public static final String[] getWerte( String pAttribut ) {
        if( pAttribut.equals("clazz") ) {
            return new String[]{"1", "2", "3"};
        } else if( pAttribut.equals("age") ) {
            return new String[]{"unter 20", "über 20"};
        } else if( pAttribut.equals("sex") ) {
            return new String[]{"male", "female"};
        } else if( pAttribut.equals("embarked") ) {
            return new String[]{"S", "C", "Q"};
        } else if( pAttribut.equals("sibsp") ) {
            return new String[]{"0", "1", "2"};
        } else if( pAttribut.equals("parch") ) {
            return new String[]{"0", "1", "2"};
        } else {
            return null;
        }
    }

    public String name, age, sex, embarked;

    public int clazz, sibsp, parch, survived;


    /**
     * Erstellt einen Passagier mit den angegebenen Werten, ohne das Attribut "survived".
     * @param name
     * @param age
     * @param sex
     * @param embarked
     * @param clazz
     * @param sibsp
     * @param parch
     */
    public Passagier( String name, String age, String sex, String embarked,
                      int clazz, int sibsp, int parch ) {
        this(name, age, sex, embarked, clazz, sibsp, parch, 0);
    }

    /**
     * Erstellt einen Passagier mit den angegebenen Werten.
     * @param name
     * @param age
     * @param sex
     * @param embarked
     * @param clazz
     * @param sibsp
     * @param parch
     * @param survived
     */
    public Passagier( String name, String age, String sex, String embarked,
                      int clazz, int sibsp, int parch, int survived ) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.embarked = embarked;

        this.clazz = clazz;
        this.sibsp = sibsp;
        this.parch = parch;

        this.survived = survived;
    }

    /**
     * Getter für Attribute basierend auf dem String-Namen des Attributs.
     * @param pAttribut
     * @return
     */
    public String get( String pAttribut ) {
        if( pAttribut.equals("clazz") ) {
            return String.valueOf(clazz);
        } else if( pAttribut.equals("age") ) {
            return age;
        } else if( pAttribut.equals("sex") ) {
            return sex;
        } else if( pAttribut.equals("embarked") ) {
            return embarked;
        } else if( pAttribut.equals("sibsp") ) {
            return String.valueOf(sibsp);
        } else if( pAttribut.equals("parch") ) {
            return String.valueOf(parch);
        } else {
            return "";
        }
    }

}