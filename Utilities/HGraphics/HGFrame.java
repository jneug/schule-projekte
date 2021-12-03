import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HGFrame extends JFrame implements Runnable, MouseInputListener {

    public static final int DEFAULT_WIDHT = 400;

    public static final int DEFAULT_HEIGHT = 400;

    public static final int DEFAULT_FPS = 60;

    private ArrayList<Layer> layers;

    private CanvasComponent drawing;

    private boolean running = false;

    private int framesPerSecond;

    protected long tick;

    protected long runtime;

    protected double delta = 0.0, mouseX = 0.0, mouseY = 0.0, pmouseX = 0.0, pmouseY = 0.0, width = 0.0, height = 0.0;

    protected CanvasLayer canvas;

    protected ShapesLayer shapes;

    protected Object mouseLock = new Object();

    public HGFrame() {
        super("HGraphics");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error setting default look and feel.");
        }

        setSize(DEFAULT_WIDHT, DEFAULT_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        drawing = new CanvasComponent(getWidth(), getHeight());
        setContentPane(drawing);

        framesPerSecond = DEFAULT_FPS;

        canvas = getCanvas();
        shapes = getShapesCanvas();

        settings();

        addMouseListener(this);
        addMouseMotionListener(this);

        //pack();
        requestFocusInWindow();
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                running = false;
                super.windowClosing(e);
            }
        });
        running = true;
        new Thread(this).start();
    }

    public void setSize( int pWidth, int pHeight ) {
        super.setSize(pWidth, pHeight);
        if( drawing != null ) {
            drawing.setSize(pWidth, pHeight);
        }
        width = pWidth;
        height = pHeight;
    }

    public CanvasComponent getDrawing() {
        return drawing;
    }

    public void addLayer( Layer pLayer ) {
        drawing.addLayer(pLayer);
    }

    public CanvasLayer getCanvas() {
        CanvasLayer layer = drawing.findLayer(CanvasLayer.class);
        if( layer == null ) {
            layer = new CanvasLayer(getWidth(), getHeight());
            drawing.addLayer(0, layer);
        }
        return layer;
    }

    public ShapesLayer getShapesCanvas() {
        ShapesLayer layer = drawing.findLayer(ShapesLayer.class);
        if( layer == null ) {
            layer = new ShapesLayer(getWidth(), getHeight());
            drawing.addLayer(layer);
        }
        return layer;
    }

    public int getFramesPerSecond() {
        return framesPerSecond;
    }

    public void setFramesPerSecond( int pFramesPerSecond ) {
        framesPerSecond = pFramesPerSecond;
    }

    @Override
    public final void run() {
        long start = System.currentTimeMillis();
        long current = System.nanoTime();
        long _tick = 0;
        long _runtime = 0;
        tick = 0;
        runtime = 0;

        setup();

        while( running ) {
            int dt = (int) ((System.nanoTime() - current) / 1E6);
            current = System.nanoTime();
            delta = (dt/1000.0);

            saveMousePosition();

            draw();

            if( drawing != null ) {
                //invalidate();
                repaint();
            }

            try {
                int sleep = Math.round(1000/framesPerSecond);
                if( dt >= sleep ) {
                    sleep -= dt%sleep;
                }
                Thread.sleep(Math.max(0, sleep));
            } catch (InterruptedException e) {
                // Interrupt not relevant
            } finally {
                _tick += 1;
                _runtime = System.currentTimeMillis()-start;
                tick = _tick;
                runtime = _runtime;
            }
        }
    }

    public void settings() {

    }

    public void setup() {

    }

    public void draw( ) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        saveMousePosition(e.getPoint());
        mouseClicked();
    }

    public void mouseClicked() {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        saveMousePosition(e.getPoint());
        mousePressed();
    }

    public void mousePressed() {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        saveMousePosition(e.getPoint());
        mouseReleased();
    }

    public void mouseReleased() {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        saveMousePosition(e.getPoint());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        saveMousePosition(e.getPoint());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        saveMousePosition(e.getPoint());
        mouseDragged();
    }

    public void mouseDragged() {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        saveMousePosition(e.getPoint());
        mouseMoved();
    }

    public void mouseMoved() {

    }

    private void saveMousePosition( Point pLocation ) {
        //pmouseX = mouseX;
        //pmouseY = mouseY;
        /*synchronized(mouseLock) {
            mouseX = pLocation.getX()-this.getRootPane().getX();
            mouseY = pLocation.getY()-this.getRootPane().getY();
        }*/
    }

    private void saveMousePosition() {
        pmouseX = mouseX;
        pmouseY = mouseY;

        java.awt.Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
        java.awt.Point compLoc = drawing.getLocationOnScreen();
        mouseX = mouseLoc.x - compLoc.x;
        mouseY = mouseLoc.y - compLoc.y;
    }
}
