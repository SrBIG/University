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
import java.util.Arrays;
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
        private int[] x;
        private double[] y;
        private int[] yTrue;
        private Dimension size = new Dimension(500,300); //рабочая область
        private Dimension startPointXoY = new Dimension(40,250); //начало координат
        private int scale = 30; //масштаб

        public Draw(int[] x,double[] y) {
            this.x = x;
            this.y = y;
            yTrue = new int[x.length];
            reBuildArreys();
            initInterface();
        }

        @Override
        public void paint(Graphics g) {
            g.setColor(Color.BLACK);
            g.fillRect(0,0,size.width,size.height);

            g.setColor(Color.WHITE);

            for (int i = 1; i <= x.length; i++) {
                g.drawString(String.valueOf(i),startPointXoY.width + (startPointXoY.width - 10) * i,startPointXoY.height + (startPointXoY.width - 25));
                if (i < 7){g.drawString(String.valueOf(i),25,startPointXoY.height - (startPointXoY.width - 10) * i);}
            }

            g.drawLine(startPointXoY.width,startPointXoY.width,startPointXoY.width,startPointXoY.height);
            g.drawLine(startPointXoY.width,startPointXoY.height,400,startPointXoY.height);

            g.drawPolyline(x,yTrue,x.length);
        }

        private void initInterface() {
            setSize(size);
            setVisible(true);
        }

        private void reBuildArreys() {
            for (int i = 0; i < x.length; i++) {
                x[i] *= scale;
                x[i] += startPointXoY.width;
                y[i] *= scale;
                yTrue[i] = startPointXoY.height - ((int) y[i]);
            }
        }

        private float scale = 1;

        public TestPane() {
            addMouseWheelListener(new MouseAdapter() {

                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    double delta = 0.05 * e.getPreciseWheelRotation();
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
            g.setColor(Color.BLACK);
            g.fillRect(0,0,size.width,size.height);

            g.setColor(Color.WHITE);

            for (int i = 1; i <= x.length; i++) {
                g.drawString(String.valueOf(i),startPointXoY.width + (startPointXoY.width - 10) * i,startPointXoY.height + (startPointXoY.width - 25));
                if (i < 7){g.drawString(String.valueOf(i),25,startPointXoY.height - (startPointXoY.width - 10) * i);}
            }

            g.drawLine(startPointXoY.width,startPointXoY.width,startPointXoY.width,startPointXoY.height);
            g.drawLine(startPointXoY.width,startPointXoY.height,400,startPointXoY.height);

            g.drawPolyline(x,yTrue,x.length);
        }
    }

    class Draw extends javax.swing.JFrame {

        private int[] x;
        private double[] y;
        private int[] yTrue;
        private Dimension size = new Dimension(500,300); //рабочая область
        private Dimension startPointXoY = new Dimension(40,250); //начало координат
        private int scale = 30; //масштаб

        public Draw(int[] x,double[] y) {
            this.x = x;
            this.y = y;
            yTrue = new int[x.length];
            reBuildArreys();
            initInterface();
        }

        @Override
        public void paint(Graphics g) {
            g.setColor(Color.BLACK);
            g.fillRect(0,0,size.width,size.height);

            g.setColor(Color.WHITE);

            for (int i = 1; i <= x.length; i++) {
                g.drawString(String.valueOf(i),startPointXoY.width + (startPointXoY.width - 10) * i,startPointXoY.height + (startPointXoY.width - 25));
                if (i < 7){g.drawString(String.valueOf(i),25,startPointXoY.height - (startPointXoY.width - 10) * i);}
            }

            g.drawLine(startPointXoY.width,startPointXoY.width,startPointXoY.width,startPointXoY.height);
            g.drawLine(startPointXoY.width,startPointXoY.height,400,startPointXoY.height);

            g.drawPolyline(x,yTrue,x.length);
        }

        private void initInterface() {
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setSize(size);
            setResizable(false);
            setTitle("График функции");
            setVisible(true);
        }

        private void reBuildArreys() {
            for (int i = 0; i < x.length; i++) {
                x[i] *= scale;
                x[i] += startPointXoY.width;
                y[i] *= scale;
                yTrue[i] = startPointXoY.height - ((int) y[i]);
            }
        }
    }
}