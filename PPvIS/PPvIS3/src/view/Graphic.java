package view;

import model.Coordinate;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


public class Graphic extends JPanel {

    private List<Coordinate> coordinates;
    boolean buildSt = false;

    JScrollPane scrollPane;

    JButton build = new JButton("Build");
    JLabel scope = new JLabel("Scope: 100%");
    JPanel action = new JPanel();

    boolean scrollStatus = false;

    public Graphic(List<Coordinate> coordinates) {
        this.coordinates = new ArrayList<>(coordinates);
        action.setLayout(new BoxLayout(action, BoxLayout.X_AXIS));
        action.add(scope);
        action.add(Box.createHorizontalStrut(20));
        build.addActionListener(new BuildListener());
        action.add(build);
        add(new JLabel("Graphic"));
        scrollPane = new JScrollPane(new GraphicPainter());
        add(scrollPane, BorderLayout.CENTER);
        add(action, BorderLayout.SOUTH);
    }

    public void setScrollStatus(boolean scrollStatus){
        this.scrollStatus = scrollStatus;
    }

    class BuildListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            buildSt = true;
        }
    }

    private class GraphicPainter extends JPanel{
        private float scale = 1.5f;
        public GraphicPainter(){

            addMouseWheelListener(e -> {
                if (e.getModifiers() == InputEvent.CTRL_MASK) {
                        double delta = 0.05f * e.getPreciseWheelRotation();
                        scale += delta;
                        revalidate();
                        int realScope = (int) ((scale / 1.5f) * 100);
                        //scope.setText("Scope: " + realScope + "%");
                        repaint();

                }
            });

            addMouseMotionListener(new MouseAdapter() {

                private int prevX, prevY;

                @Override
                public void mouseDragged(MouseEvent e){
                    super.mouseDragged(e);
                    int dX = prevX - e.getX();
                    int dY = prevY - e.getY();

                    JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();

                    verticalScrollBar.addAdjustmentListener(e1 -> {
                    });

                    JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
                    verticalScrollBar.setValue(verticalScrollBar.getValue() + dY);
                    horizontalScrollBar.setValue(horizontalScrollBar.getValue() + dX);

                    prevX = e.getX();
                    prevY = e.getY();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    prevX = e.getX();
                    prevY = e.getY();
                }
            });

        }
        @Override
        public Dimension getPreferredSize() {
            Dimension size = new Dimension(260, 280);
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

            int width = 200;
            int height = 200;
            int enlarge = 100;
            int nullPoint = 30;
            int arrow = 5;

            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2.0f));
            g2d.drawLine(nullPoint, nullPoint, nullPoint, height+nullPoint*2);
            g2d.drawLine(nullPoint, nullPoint, nullPoint+arrow, nullPoint+arrow);
            g2d.drawLine(nullPoint, nullPoint, nullPoint-arrow, nullPoint+arrow);

            g2d.drawLine(nullPoint, height+nullPoint, width+nullPoint, height+nullPoint);
            g2d.drawLine(width+nullPoint, height+nullPoint, width+nullPoint-arrow, height+nullPoint-arrow);
            g2d.drawLine(width+nullPoint, height+nullPoint, width+nullPoint-arrow, height+nullPoint+arrow);

                int correct = 12;
                g2d.setStroke(new BasicStroke(0.5f));
                g2d.drawString("x",width+nullPoint, height+nullPoint + correct);
                g2d.drawString("y", nullPoint - correct, nullPoint);
                g2d.drawString("0", nullPoint-correct, height+nullPoint+correct);

                g2d.drawString("0,5",nullPoint-2*correct , height-(height/4)+nullPoint+correct/2);
                g2d.drawLine(nullPoint, height-(height/4)+nullPoint, nullPoint+width, height-(height/4)+nullPoint);
                g2d.drawString("1.0",nullPoint-2*correct , height-(height/2)+nullPoint+correct/2);
                g2d.drawLine(nullPoint, height-(height/2)+nullPoint, nullPoint+width, height-(height/2)+nullPoint);
                g2d.drawString("1.5",nullPoint-2*correct , (height/4)+nullPoint+correct/2);
                g2d.drawLine(nullPoint, height/4+nullPoint, nullPoint+width, height/4+nullPoint);

                g2d.drawString("0,5", width/4+nullPoint-correct, height+nullPoint+correct);
                g2d.drawLine(width/4+nullPoint, height+nullPoint, width/4+nullPoint, nullPoint);
                g2d.drawString("1.0", width/2+nullPoint-correct, height+nullPoint+correct);
                g2d.drawLine(width/2+nullPoint, height+nullPoint, width/2+nullPoint, nullPoint);
                g2d.drawString("1,5", width-(width/4)+nullPoint-correct, height+nullPoint+correct);
                g2d.drawLine(width-(width/4)+nullPoint, height+nullPoint, width-(width/4)+nullPoint, nullPoint);

            if (buildSt) {
                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(2.0f));

                Coordinate prev = coordinates.get(0);
                for (Coordinate cur : coordinates) {
                    int x1 = (int) (prev.getX() * enlarge + nullPoint);
                    int y1 = (height + nullPoint) - (int) (prev.getY() * enlarge);
                    int x2 = (int) (cur.getX() * enlarge + nullPoint);
                    int y2 = (height + nullPoint) - (int) (cur.getY() * enlarge);
                    g2d.drawLine(x1, y1, x2, y2);
                    prev = cur;
                }
                
                g2d.setColor(Color.BLACK);
                g2d.drawString("1,75", width-(width/8)+nullPoint-correct, height+nullPoint+correct);
                g2d.drawLine(width-(width/8)+nullPoint, height+nullPoint+5, width-(width/8)+nullPoint, height+nullPoint-5);
            }
            repaint();
        }
    }
}