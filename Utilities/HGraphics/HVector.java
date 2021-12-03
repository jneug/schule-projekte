import java.awt.geom.Point2D;
import java.util.Objects;

public class HVector extends Point2D.Double {


    public HVector() {
        x = 0.0;
        y = 0.0;
    }

    public HVector(double pX, double pY) {
        x = pX;
        y = pY;
    }

    public HVector(Point2D.Double pPoint) {
        x = pPoint.getX();
        x = pPoint.getY();
    }

    public HVector(HVector pOther) {
        x = pOther.x;
        y = pOther.y;
    }

    public HVector copy() {
        return new HVector(x, y);
    }

    public void set(double pX, double pY) {
        x = pX;
        y = pY;
    }

    public void set(HVector pOther) {
        x = pOther.x;
        y = pOther.y;
    }

    public void set(Point2D.Double pPoint) {
        x = pPoint.getX();
        x = pPoint.getY();
    }

    public void setX(double pX) {
        x = pX;
    }

    public void setY(double pY) {
        y = pY;
    }

    public Point2D.Double getPoint() {
        return new Point2D.Double(x, y);
    }

    public double len() {
        return Math.sqrt(x * x + y * y);
    }

    public double lenSq() {
        return x * x + y * y;
    }

    public void setLen(double pLength) {
        normalize();
        scale(pLength);
    }

    public static HVector setLen(HVector pVector, double pLength) {
        HVector vec = pVector.copy();
        vec.setLen(pLength);
        return vec;
    }

    public void normalize() {
        double len = len();
        if (len != 0 && len != 1) {
            x /= len;
            y /= len;
        }
    }

    public static HVector normalize(HVector pVector) {
        HVector vec = pVector.copy();
        vec.normalize();
        return vec;
    }

    public void add(HVector pOther) {
        x += pOther.x;
        y += pOther.y;
    }

    public void add(double pX, double pY) {
        x += pX;
        y += pY;
    }

    public static HVector add(HVector pVector1, HVector pVector2) {
        HVector vec = pVector1.copy();
        vec.add(pVector2);
        return vec;
    }

    public void sub(HVector pOther) {
        x -= pOther.x;
        y -= pOther.y;
    }

    public void sub(double pX, double pY) {
        x -= pX;
        y -= pY;
    }

    public static HVector sub(HVector pVector1, HVector pVector2) {
        HVector vec = pVector1.copy();
        vec.sub(pVector2);
        return vec;
    }

    public void scale(double pScalar) {
        x *= pScalar;
        y *= pScalar;
    }

    public static HVector scale(HVector pVector, double pScalar) {
        HVector vec = pVector.copy();
        vec.scale(pScalar);
        return vec;
    }

    public void div(double pScalar) {
        x /= pScalar;
        y /= pScalar;
    }

    public static HVector div(HVector pVector, double pScalar) {
        HVector vec = pVector.copy();
        vec.div(pScalar);
        return vec;
    }

    public double dist(HVector pOther) {
        double dx = x - pOther.x;
        double dy = y - pOther.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double dist(HVector pVector1, HVector pVector2) {
        return pVector1.dist(pVector2);
    }

    public double dot(HVector pOther) {
        return x * pOther.x + y * pOther.y;
    }

    public double dot(double pX, double pY) {
        return x * pX + y * pY;
    }

    public static double dot(HVector pVector1, HVector pVector2) {
        return pVector1.dot(pVector2);
    }

    // See: http://allenchou.net/2013/07/cross-product-of-2d-vectors/
    public double cross(HVector pOther) {
        return x * pOther.y - pOther.x * y;
    }

    public static double cross(HVector pVector1, HVector pVector2) {
        return pVector1.cross(pVector2);
    }

    public void limit(double pMax) {
        if (lenSq() > pMax * pMax) {
            normalize();
            scale(pMax);
        }
    }

    public void constrain(double pMin, double pMax) {
        if (pMin > pMax) {
            throw new IllegalArgumentException("HVector.constrain(): pMin needs to be smaller than pMax.");
        }
        if (lenSq() < pMin * pMin) {
            normalize();
            scale(pMin);
        }
        if (lenSq() > pMax * pMax) {
            normalize();
            scale(pMax);
        }
    }

    public double heading() {
        double angle = Math.atan2(y, x);
        return angle;
    }

    public void rotate(double pAngle) {
        double temp = x;
        x = x * Math.cos(pAngle) - y * Math.sin(pAngle);
        y = temp * Math.sin(pAngle) + y * Math.cos(pAngle);
    }

    public static HVector rotate(HVector pVector, double pAngle) {
        HVector vec = pVector.copy();
        vec.rotate(pAngle);
        return vec;
    }

    public void lerp(HVector pOther, float t) {
        x = x + (pOther.x - x) * t;
        y = y + (pOther.y - y) * t;
    }

    public static HVector lerp(HVector pVector, HVector pOther, float t) {
        HVector vec = pVector.copy();
        vec.lerp(pOther, t);
        return vec;
    }

    @Override
    public String toString() {
        return "HVec(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HVector)) {
            return false;
        }
        final HVector p = (HVector) obj;
        return x == p.x && y == p.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
