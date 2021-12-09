import formen.FormenEbene;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CanvasComponent extends JComponent {

    private ArrayList<Ebene> ebenen;

    public CanvasComponent( int pWidth, int pHeight ) {
        Dimension dim = new Dimension(pWidth, pHeight);
        super.setSize(pWidth, pHeight);
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);

        ebenen = new ArrayList<>(4);
        ebenen.add(new ZeichenEbene(pWidth, pHeight));
        ebenen.add(new FormenEbene(pWidth, pHeight));
        setBackground(Color.BLACK);
    }

    public void setSize(int pWidth, int pHeight) {
        Dimension dim = new Dimension(pWidth, pHeight);
        super.setSize(pWidth, pHeight);
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);

        for( Ebene ebene : ebenen) {
            ebene.setSize(pWidth, pHeight);
        }
    }

    public void addLayer( Ebene pEbene ) {
        if( pEbene != null ) {
            pEbene.setSize(getWidth(), getHeight());
            ebenen.add(pEbene);
        }
    }

    public void addLayer( int pIndex, Ebene pEbene ) {
        if( pEbene != null ) {
            pEbene.setSize(getWidth(), getHeight());
            ebenen.add(pIndex, pEbene);
        }
    }

    public java.util.List<Ebene> getLayers() {
        return ebenen;
    }

    public Ebene getLayer(int i ) {
        if( ebenen.size() > i ) {
            return ebenen.get(i);
        } else {
            throw new IndexOutOfBoundsException("No layer with index " + i + " available.");
        }
    }

    public <L extends Ebene> L findLayer(Class<L> pClazz ) {
        for( Ebene ebene : ebenen) {
            if( ebene.getClass().equals(pClazz) ) {
                return pClazz.cast(ebene);
            }
        }
        return null;
    }

    public <L extends Ebene> java.util.List<L> findLayers(Class<L> pClazz ) {
        ArrayList<L> result = new ArrayList<>(ebenen.size());
        for( Ebene ebene : ebenen) {
            if( ebene.getClass().equals(pClazz) ) {
                result.add(pClazz.cast(ebene));
            }
        }
        return result;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        for (Ebene ebene : ebenen) {
            ebene.zeichnen(g2d);
        }

        g2d.dispose();
    }
}
