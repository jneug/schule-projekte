public class Passagier {

    private String name;
    private int age;

    public Passagier() {
        name = "Passagier";
        age = 45;
    }

    public Passagier(String pName, int pAge) {
        name = pName;
        age = pAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int pAge) {
        age = pAge;
    }

}
