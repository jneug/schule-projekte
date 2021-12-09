package formen;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Objects;

public class Kreis extends Form {

    public double radius;

    public Kreis( double pX, double pY, double pRadius ) {
        super(pX, pY);
        radius = pRadius;
        setAnkerpunkt(CENTER);
    }

    public Kreis( Kreis pKreis ) {
        this(pKreis.x, pKreis.y, pKreis.radius);
        kopiere(pKreis);
    }

    @Override
    public void kopiere( Form pForm ) {
        super.kopiere(pForm);
        if( pForm instanceof Kreis ) {
            radius = ((Kreis) pForm).radius;
        }
    }

    @Override
    public Shape getShape() {
        Shape shape = new Ellipse2D.Double(
            0, 0, radius*2.0, radius*2.0
        );
        return getVerzerrung().createTransformedShape(shape);
    }

    @Override
    public void setAnkerpunkt( byte pAnker ) {
        if( pAnker == CENTER ) {
            anker.x = radius;
            anker.y = radius;
        }
        if( (pAnker & SOUTH)  == SOUTH ) {
            anker.y = radius+radius;
        }
        if( (pAnker & EAST)  == EAST ) {
            anker.x = radius+radius;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kreis kreis = (Kreis) o;
        return Double.compare(kreis.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, radius);
    }

    @Override
    public String toString() {
        return "formen.Kreis{" +
            "x=" + x +
            ", y=" + y +
            ", radius=" + radius +
            '}';
    }
}
