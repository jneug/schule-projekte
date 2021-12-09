import java.awt.*;
import java.awt.geom.*;
import java.util.Stack;

public class ZeichenEbene extends Ebene {

    private int default_anchor = CENTER;

    protected Color strokeColor;

    protected Color fillColor;

    protected double strokeWeight;

    protected int konturArt = DURCHGEZOGEN;

    public Color getColor() {
        return fillColor;
    }

    public void noFill() {
        fillColor = null;
    }

    public void setColor(int gray) {
        setColor(gray, gray, gray, 255);
    }

    public void setColor(int gray, int alpha) {
        setColor(gray, gray, gray, alpha);
    }

    public void setColor(int red, int green, int blue) {
        setColor(red, green, blue, 255);
    }

    public void setColor(int red, int green, int blue, int alpha ) {
        if (red   < 0 || red   >= 256) throw new IllegalArgumentException("red must be between 0 and 255");
        if (green < 0 || green >= 256) throw new IllegalArgumentException("green must be between 0 and 255");
        if (blue  < 0 || blue  >= 256) throw new IllegalArgumentException("blue must be between 0 and 255");
        if (alpha  < 0 || alpha  >= 256) throw new IllegalArgumentException("alpha must be between 0 and 255");

        setColor(new Color(red, green, blue, alpha));
    }

    public void setColor(Color pColor) {
        fillColor = pColor;
        leinwand.setColor(pColor);
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void noStroke() {
        strokeColor = null;
    }

    public void setStrokeColor(int gray) {
        setStrokeColor(gray, gray, gray, 255);
    }

    public void setStrokeColor(int gray, int alpha) {
        setStrokeColor(gray, gray, gray, alpha);
    }

    public void setStrokeColor(int red, int green, int blue) {
        if (red   < 0 || red   >= 256) throw new IllegalArgumentException("red must be between 0 and 255");
        if (green < 0 || green >= 256) throw new IllegalArgumentException("green must be between 0 and 255");
        if (blue  < 0 || blue  >= 256) throw new IllegalArgumentException("blue must be between 0 and 255");
        setStrokeColor(red, green, blue, 255);
    }

    public void setStrokeColor(int red, int green, int blue, int alpha ) {
        if (red   < 0 || red   >= 256) throw new IllegalArgumentException("red must be between 0 and 255");
        if (green < 0 || green >= 256) throw new IllegalArgumentException("green must be between 0 and 255");
        if (blue  < 0 || blue  >= 256) throw new IllegalArgumentException("blue must be between 0 and 255");
        if (alpha  < 0 || alpha  >= 256) throw new IllegalArgumentException("alpha must be between 0 and 255");

        setStrokeColor(new Color(red, green, blue, alpha));
    }

    public void setStrokeColor(Color pColor) {
        strokeColor = pColor;
        leinwand.setColor(pColor);
    }

    public void setStrokeWeight( double pWeight ) {
        strokeWeight = pWeight;
        leinwand.setStroke(createStroke());
    }

    protected Stroke createStroke() {
        switch(konturArt) {
            case GEPUNKTET:
                return new BasicStroke(
                    (float) strokeWeight,
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND,
                    10.0f, new float[]{1.0f, 5.0f}, 0.0f);
            case GESTRICHELT:
                return new BasicStroke(
                    (float) strokeWeight,
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND,
                    10.0f, new float[]{5.0f}, 0.0f);
            default:
                return new BasicStroke(
                    (float) strokeWeight,
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND);
        }
    }

    public int getKonturArt() {
        return konturArt;
    }

    public void setKonturArt(int konturArt) {
        this.konturArt = konturArt;
    }

    private Stack<AffineTransform> transformStack = new Stack<>();

    public ZeichenEbene() {
        super();
        transformStack.push(new AffineTransform());

        strokeColor = Color.BLACK;
        fillColor = Color.WHITE;
        strokeWeight = 1.0;
    }

    public ZeichenEbene(int pWidth, int pHeight) {
        super(pWidth, pHeight);
    }

    public void setAnchor( int pAnchor ) {
        default_anchor = pAnchor;
    }

    public void leeren() {
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
        Color currentColor = leinwand.getColor();
        pushMatrix();
        resetMatrix();
        leinwand.setColor(pColor);
        leinwand.fillRect(0, 0, puffer.getWidth(), puffer.getHeight());
        leinwand.setColor(currentColor);
        popMatrix();
    }

    public void line(double x1, double y1, double x2, double y2) {
        Shape line = new Line2D.Double(x1, y1, x2, y2);
        //line = transformToCanvas(line);

        drawShape(line);
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
        //rect = transformToCanvas(rect);

        fillShape(rect);
        drawShape(rect);
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
        //ellipse = transformToCanvas(ellipse);

        fillShape(ellipse);
        drawShape(ellipse);
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
        //arc = transformToCanvas(arc);

        drawShape(arc);
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
        //arc = transformToCanvas(arc);

        fillShape(arc);
        drawShape(arc);
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

    private void fillShape( Shape pShape ) {
        if (fillColor != null && fillColor.getAlpha() > 0.0) {
            leinwand.setColor(fillColor);
            leinwand.fill(pShape);
        }
    }

    private void drawShape( Shape pShape ) {
        if (strokeColor != null && strokeColor.getAlpha() > 0.0
            && strokeWeight > 0.0 ) {
            leinwand.setColor(strokeColor);
            leinwand.draw(pShape);
        }
    }

    public void translate( double dx, double dy ) {
        leinwand.translate(dx, dy);
    }

    public void scale( double factor ) {
        leinwand.scale(factor, factor);
    }

    public void rotate( double pAngle ) {
        leinwand.rotate(Math.toRadians(pAngle));
    }

    public void shear( double dx, double dy ) {
        leinwand.shear(dx, dy);
    }

    public AffineTransform getMatrix() {
        return leinwand.getTransform();
    }

    public void pushMatrix() {
        transformStack.push(leinwand.getTransform());
    }

    public void popMatrix() {
        if( transformStack.isEmpty() ) {
            resetMatrix();
        } else {
            leinwand.setTransform(transformStack.pop());
        }
    }

    public void resetMatrix() {
        leinwand.setTransform(new AffineTransform());
    }

}
