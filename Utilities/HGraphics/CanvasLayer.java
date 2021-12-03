import java.awt.*;
import java.awt.geom.*;
import java.util.Stack;

public class CanvasLayer extends Layer {

    public static final int CENTER = 0;
    public static final int NORTHWEST = 1;
    public static final int WEST = 2;
    public static final int SOUTHWEST = 3;


    private int default_anchor = CENTER;

    private Stack<AffineTransform> transformStack;

    public CanvasLayer() {
        super();
        transformStack = new Stack<>();
        transformStack.push(new AffineTransform());
    }

    public void setAnchor( int pAnchor ) {
        default_anchor = pAnchor;
    }

    public CanvasLayer(int pWidth, int pHeight) {
        super(pWidth, pHeight);
    }

    public void clear() {
        clear(200);
    }

    public void clear(int gray) {
        clear(gray, gray, gray, 255);
    }

    public void clear(int gray, int alpha) {
        clear(gray, gray, gray, alpha);
    }

    public void clear(int red, int green, int blue) {
        clear(red, green, blue, 255);
    }

    public void clear(int red, int green, int blue, int alpha) {
        clear(new Color(red, green, blue, alpha));
    }

    public void clear(Color pColor) {
        /*graphics.setBackground(pColor);
        graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());*/
        Color currentColor = graphics.getColor();
        graphics.setColor(pColor);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        graphics.setColor(currentColor);
    }

    public void line(double x1, double y1, double x2, double y2) {
        if (strokeColor != null && strokeColor.getAlpha() > 0.0) {
            Shape line = new Line2D.Double(x1, y1, x2, y2);
            line = transformToCanvas(line);

            graphics.setColor(strokeColor);
            graphics.draw(line);
        }
    }

    public void pixel(double x, double y) {
        square(x, y, 1);
    }

    public void square(double x, double y, double w) {
        rect(x, y, w, w);
    }

    public void square(double x, double y, double w, int anchor) {
        rect(x, y, w, w, anchor);
    }

    public void rect(double x, double y, double w, double h) {
        rect(x, y, w, h, default_anchor);
    }

    public void rect(double x, double y, double w, double h, int anchor) {
        Point2D.Double anchorPoint = getAnchorPoint(x, y, w, h, anchor);
        Shape rect = new Rectangle2D.Double(
            anchorPoint.getX(), anchorPoint.getY(), w, h
        );
        rect = transformToCanvas(rect);

        if (fillColor != null && fillColor.getAlpha() > 0.0) {
            graphics.setColor(fillColor);
            graphics.fill(rect);
        }
        if (strokeColor != null && strokeColor.getAlpha() > 0.0) {
            graphics.setColor(strokeColor);
            graphics.draw(rect);
        }
    }

    public void point(double x, double y) {
        circle(x - 1, y - 1, 2);
    }

    public void circle(double x, double y, double d) {
        ellipse(x, y, d, d, default_anchor);
    }

    public void circle(double x, double y, double d, int anchor) {
        ellipse(x, y, d, d, anchor);
    }

    public void ellipse(double x, double y, double w, double h) {
        ellipse(x, y, w, h, default_anchor);
    }

    public void ellipse(double x, double y, double w, double h, int anchor) {
        Point2D.Double anchorPoint = getAnchorPoint(x, y, w, h, anchor);
        Shape ellipse = new Ellipse2D.Double(
            anchorPoint.x, anchorPoint.y, w, h
        );
        ellipse = transformToCanvas(ellipse);

        if (fillColor != null && fillColor.getAlpha() > 0.0) {
            graphics.setColor(fillColor);
            graphics.fill(ellipse);
        }
        if (strokeColor != null && strokeColor.getAlpha() > 0.0) {
            graphics.setColor(strokeColor);
            graphics.draw(ellipse);
        }
    }

    public void arc( double x, double y, double d, double angle1, double angle2 ) {
        while (angle2 < angle1) angle2 += 360.0;

        Point2D.Double anchorPoint = getAnchorPoint(x, y, d, d, CENTER);
        Shape arc = new Arc2D.Double(
            anchorPoint.x,
            anchorPoint.y,
            d, d,
            angle1, angle2 - angle1,
            Arc2D.OPEN
        );
        arc = transformToCanvas(arc);

        if (strokeColor != null && strokeColor.getAlpha() > 0.0) {
            graphics.setColor(strokeColor);
            graphics.draw(arc);
        }
    }

    public void pie( double x, double y, double d, double angle1, double angle2 ) {
        while (angle2 < angle1) angle2 += 360.0;

        Point2D.Double anchorPoint = getAnchorPoint(x, y, d, d, CENTER);
        Shape arc = new Arc2D.Double(
            anchorPoint.x,
            anchorPoint.y,
            d, d,
            angle1, angle2 - angle1,
            Arc2D.PIE
        );
        arc = transformToCanvas(arc);

        if (fillColor != null && fillColor.getAlpha() > 0.0) {
            graphics.setColor(fillColor);
            graphics.fill(arc);
        }
        if (strokeColor != null && strokeColor.getAlpha() > 0.0) {
            graphics.setColor(strokeColor);
            graphics.draw(arc);
        }
    }

    private Point2D.Double transformToCanvas(double x, double y) {
        return transformToCanvas(new Point2D.Double(x, y));
    }

    private Point2D.Double transformToCanvas( Point2D.Double pPoint ) {
        AffineTransform matrix = getMatrix();
        matrix.transform(pPoint, pPoint);
        return pPoint;
    }

    private Shape transformToCanvas( Shape pShape ) {
        AffineTransform matrix = getMatrix();
        return matrix.createTransformedShape(pShape);
    }

    private Point2D.Double transformToUser(double x, double y) {
        return transformToUser(new Point2D.Double(x, y));
    }

    private Point2D.Double transformToUser( Point2D.Double pPoint ) {
        AffineTransform matrix = getMatrix();

        try {
            matrix.inverseTransform(pPoint, pPoint);
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }

        return pPoint;
    }

    private Shape transformToUser( Shape pShape ) {
        AffineTransform matrix = getMatrix();
        try {
            matrix = matrix.createInverse();
            pShape = matrix.createTransformedShape(pShape);
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }
        return pShape;
    }

    private AffineTransform getAnchorTransform( Shape pShape, int anchor  ) {
        AffineTransform at = new AffineTransform();
        Rectangle2D bounds = pShape.getBounds2D();
        switch(anchor) {
            case CENTER:
                at.translate(
                    bounds.getWidth() / -2.0,
                    bounds.getHeight() / -2.0
                );
                break;

            case WEST:
                at.translate(
                    0, bounds.getHeight() / -2.0
                );
                break;

            case SOUTHWEST:
                at.translate(
                    0, -1.0 * bounds.getHeight()
                );
                break;
        }

        return at;
    }

    private Point2D.Double getAnchorPoint(double x, double y, double w, double h, int anchor) {
        switch(anchor) {
            case CENTER:
                x -= w / 2.0;
                y -= h / 2.0;
                break;

            case WEST:
                y -= h / 2.0;
                break;

            case SOUTHWEST:
                y -= h;
                break;
        }

        return new Point2D.Double(x, y);
    }

    private Point2D.Double getAnchorPoint(Shape pShape, int anchor) {
        Rectangle2D bounds = pShape.getBounds2D();
        return getAnchorPoint(
            bounds.getX(), bounds.getY(),
            bounds.getWidth(), bounds.getHeight(), anchor
        );
    }

    public void translate( double dx, double dy ) {
        getMatrix().translate(dx, dy);
    }

    public void scale( double factor ) {
        getMatrix().scale(factor, factor);
    }

    public void rotate( double pAngle ) {
        getMatrix().rotate(Math.toRadians(pAngle));
    }

    public void shear( double dx, double dy ) {
        getMatrix().shear(dx, dy);
    }

    public void pushMatrix() {
        transformStack.push(new AffineTransform(transformStack.peek()));
    }

    public void popMatrix() {
        transformStack.pop();
        // Ensure at least one (identity) matrix is on the stack
        if( transformStack.isEmpty() ) {
            pushMatrix();
        }
    }

    public AffineTransform getMatrix() {
        return transformStack.peek();
    }

}
