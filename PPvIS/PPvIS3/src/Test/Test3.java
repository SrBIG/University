package Test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Test3 {

    public static void main(String[] args) {
        new Test3();
    }

    public Test3() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new JScrollPane(new TestPane()));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private float scale = 1;

        public TestPane() {
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

            Path2D.Float path = new Path2D.Float();
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


            g2d.dispose();
        }
    }
}