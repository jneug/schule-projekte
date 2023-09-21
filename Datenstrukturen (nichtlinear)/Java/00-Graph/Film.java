import java.util.Objects;

public class Film {

    private String name;

    public Film( String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals( Object po ) {
        if( this == po ) return true;
        if( po == null || getClass() != po.getClass() ) return false;

        Film ppFilm = (Film) po;

        return Objects.equals(name, ppFilm.name);
    }


    @Override
    public String toString() {
        return getClass().getCanonicalName() + '[' +
            "name='" + name + '\'' +
            ']';
    }

}
