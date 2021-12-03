import java.awt.*;
import java.util.ArrayList;

public class ShapesLayer extends Layer {

    private ArrayList<HShape> shapes;

    public ShapesLayer() {
        super();
        shapes = new ArrayList<HShape>(32);
    }

    public ShapesLayer(int pWidth, int pHeight) {
        super(pWidth, pHeight);
        shapes = new ArrayList<HShape>(32);
    }

    public void clear() {

    }

    public java.util.List<HShape> getShapes() {
        return shapes;
    }

    public void draw(Graphics2D pGraphics) {
        for (HShape gobject : shapes) {
            gobject.draw(pGraphics);
        }
    }
}
