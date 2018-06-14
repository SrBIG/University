package view;

import model.Coordinate;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;


public class Graphic extends JPanel {

    private List<Coordinate> coordinates;
    private float scale = 1;
    //private List<Coordinate> coordinates;

    public Graphic(List<Coordinate> coordinates) {
        this.coordinates = new ArrayList<>(coordinates);

        addMouseWheelListener(new MouseAdapter() {

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                double delta = 0.05f * e.getPreciseWheelRotation();
                scale += delta;
                revalidate();
                repaint();
            }

        });
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = new Dimension(200, 200);
        size.width = Math.round(size.width * scale);
        size.height = Math.round(size.height * scale);
        return size;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        g2d.setTransform(at);

        g2d.setColor(Color.RED);

        // This is for demonstration purposes only
        // I prefer to use getWidth and getHeight
        int width = 200;
        int height = 200;
        int popo = 100;

        Coordinate prev = coordinates.get(0);

        for(Coordinate cur : coordinates){
            int x1 = (int) prev.getX() * popo;
            int y1 = (int) prev.getY() * popo;
            int x2 = (int) cur.getX() * popo;
            int y2 = (int) cur.getY() * popo;
            g2d.drawLine(x1,y1,x2,y2);
        }


        /*Path2D.Float path = new Path2D.Float();


        int seg = width / 3;
        path.moveTo(0, height / 2);
        path.curveTo(0, 0, seg, 0, seg, height / 2);
        path.curveTo(
                seg, height,
                seg * 2, height,
                seg * 2, height / 2);
        path.curveTo(
                seg * 2, 0,
                seg * 3, 0,
                seg * 3, height / 2);

        g2d.draw(path);


        g2d.dispose();*/
    }
}