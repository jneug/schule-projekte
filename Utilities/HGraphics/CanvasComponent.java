import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CanvasComponent extends JComponent {

    private ArrayList<Layer> layers;

    public CanvasComponent( int pWidth, int pHeight ) {
        layers = new ArrayList<>(4);
        layers.add(new CanvasLayer());
        layers.add(new ShapesLayer());
    }

    public void setSize(int pWidth, int pHeight) {
        Dimension dim = new Dimension(pWidth, pHeight);
        super.setSize(pWidth, pHeight);
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);

        for( Layer layer: layers ) {
            layer.setSize(pWidth, pHeight);
        }
    }

    public void addLayer( Layer pLayer ) {
        layers.add(pLayer);
    }

    public void addLayer( int pIndex, Layer pLayer ) {
        layers.add(pIndex, pLayer);
    }

    public java.util.List<Layer> getLayers() {
        return layers;
    }

    public Layer getLayer( int i ) {
        if( layers.size() > i ) {
            return layers.get(i);
        } else {
            throw new IndexOutOfBoundsException("No layer with index " + i + " available.");
        }
    }

    public <L extends Layer> L findLayer( Class<L> pClazz ) {
        for( Layer layer: layers ) {
            if( layer.getClass().equals(pClazz) ) {
                return pClazz.cast(layer);
            }
        }
        return null;
    }

    public <L extends Layer> java.util.List<L> findLayers( Class<L> pClazz ) {
        ArrayList<L> result = new ArrayList<>(layers.size());
        for( Layer layer: layers ) {
            if( layer.getClass().equals(pClazz) ) {
                result.add(pClazz.cast(layer));
            }
        }
        return result;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        for (Layer layer : layers) {
            layer.draw(g2d);
        }

        g2d.dispose();
    }
}
