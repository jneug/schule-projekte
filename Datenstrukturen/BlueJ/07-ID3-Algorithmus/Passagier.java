

public class Passagier {

    public static final String[] getAttribute() {
        return new String[]{"clazz", "sex", "age", "sibsp", "parch", "embarked"};
    }

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

	public Passagier(String name, String age, String sex, String embarked,
		int clazz, int sibsp, int parch) {
		this(name, age, sex, embarked, clazz,  sibsp,  parch, 0);
	}

	public Passagier(String name, String age, String sex, String embarked,
		int clazz, int sibsp, int parch, int survived) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.embarked = embarked;

		this.clazz = clazz;
		this.sibsp = sibsp;
		this.parch = parch;

		this.survived = survived;
	}

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