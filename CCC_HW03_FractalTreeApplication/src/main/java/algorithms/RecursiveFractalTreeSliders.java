package algorithms;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;


public class RecursiveFractalTreeSliders extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final double LENGTH_FACTOR = 0.75; // Factor to reduce branch length
    private static final int LINE_THICKNESS = 2;
    private static final int GRID_SPACING = 20; // Size of each grid cell
    private static final int LABEL_SPACING = 40; // Distance between grid labels

    private double branchAngle = 45; // Angle of branch split
    private int treeDepth = 5; // Depth of the tree

    public RecursiveFractalTreeSliders() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private void drawGrid(Graphics2D g2d) {

        g2d.setColor(Color.LIGHT_GRAY);

        // Draw vertical grid lines
        for (int x = 0; x <= getWidth(); x += GRID_SPACING) {
            g2d.drawLine(x, 0, x, getHeight());
            if (x % LABEL_SPACING == 0) {
                g2d.drawString(String.valueOf(x), x, 10);
            }
        }

        // Draw horizontal grid lines
        for (int y = 0; y <= getHeight(); y += GRID_SPACING) {
            g2d.drawLine(0, y, getWidth(), y);
            if (y % LABEL_SPACING == 0 && y != 0) {
                g2d.drawString(String.valueOf(y), 0, y);
            }
        }
    }

    private void drawTree(Graphics2D g, int startX, int startY, double angleDegrees, int depth, double length) {
        if (depth == 0) return;

        g.setColor(Color.BLUE); // Set tree color to blue
        g.setStroke(new BasicStroke(2f)); // Set the line thickness

        double angleRadians = Math.toRadians(angleDegrees);
        int endX = startX + (int) (Math.cos(angleRadians) * length);
        int endY = startY + (int) (Math.sin(angleRadians) * length);

        g.drawLine(startX, startY, endX, endY);

        drawTree(g, endX, endY, angleDegrees - branchAngle, depth - 1, length * LENGTH_FACTOR);
        drawTree(g, endX, endY, angleDegrees + branchAngle, depth - 1, length * LENGTH_FACTOR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawGrid(g2d);

        int startX = WIDTH / 2;
        int startY = HEIGHT;
        double initialAngleDegrees = -90;
        double initialBranchLength = HEIGHT / 4;
        drawTree(g2d, startX, startY, initialAngleDegrees, treeDepth, initialBranchLength);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("L-System Fractal Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        RecursiveFractalTreeSliders treePanel = new RecursiveFractalTreeSliders();
        frame.setContentPane(treePanel);

        JSlider angleSlider = createSlider(0, 90, 45, 10);
        JSlider depthSlider = createSlider(0, 16, 5, 1);
        angleSlider.addChangeListener(e -> updateTreeOnSliderChange(e, treePanel, true));
        depthSlider.addChangeListener(e -> updateTreeOnSliderChange(e, treePanel, false));

        JPanel sliderPanel = new JPanel();
        sliderPanel.add(angleSlider);
        sliderPanel.add(depthSlider);
        frame.add(sliderPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JSlider createSlider(int min, int max, int initial, int majorTickSpacing) {
        JSlider slider = new JSlider(min, max, initial);
        slider.setMajorTickSpacing(majorTickSpacing);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

    private static void updateTreeOnSliderChange(ChangeEvent e, RecursiveFractalTreeSliders panel, boolean isAngleSlider) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            if (isAngleSlider) {
                panel.branchAngle = source.getValue();
            } else {
                panel.treeDepth = source.getValue();
            }
            panel.repaint();
        }
    }
}

