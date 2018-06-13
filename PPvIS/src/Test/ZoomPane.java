package Test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ZoomPane {

    public static void main(String[] args) {
        new ZoomPane();
    }

    public ZoomPane() {
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

        private BufferedImage img;
        private float scale = 1;

        public TestPane() {
            try {
                img = ImageIO.read(new File("/home/mrbig/Рабочий стол/котик.jpg"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
            if (img != null) {
                size.width = Math.round(img.getWidth() * scale);
                size.height = Math.round(img.getHeight() * scale);
            }
            return size;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (img != null) {
                Graphics2D g2d = (Graphics2D) g.create();
                AffineTransform at = new AffineTransform();
                at.scale(scale, scale);
                g2d.drawImage(img, at, this);
                g2d.dispose();
            }
        }
    }

}
