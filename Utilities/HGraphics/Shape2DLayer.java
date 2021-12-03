import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Shape2DLayer extends Layer {

    private ArrayList<java.awt.Shape> shapes;

    public Shape2DLayer() {
        super();
        shapes = new ArrayList<java.awt.Shape>(32);
    }

    public Shape2DLayer(int pWidth, int pHeight) {
        super(pWidth, pHeight);
        shapes = new ArrayList<java.awt.Shape>(32);
    }

    public void clear() {
        graphics.setBackground(new Color(0, 0, 0, 0));
        graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public java.util.List<java.awt.Shape> getShapes() {
        return shapes;
    }

    public void add(java.awt.Shape s) {
        shapes.add(s);

        if (fillColor != null && fillColor.getAlpha() > 0.0) {
            graphics.setColor(fillColor);
            graphics.fill(s);
        }
        if (strokeColor != null && strokeColor.getAlpha() > 0.0) {
            graphics.setColor(strokeColor);
            graphics.draw(s);
        }
    }

}
