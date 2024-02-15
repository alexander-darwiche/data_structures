package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class FractalTreePanelExtension extends FractalTreePanel {
    private float thickness = 1.0F;
    private int treeDepth = 1;
    private boolean randomColors = false;
    private double randomAngle = 90.0;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final double LENGTH_FACTOR = 0.75;
    private final double ANGLE = Math.toRadians(25.0);

    public FractalTreePanelExtension() {
    }

    public void setRandomThicknessRange(float minThickness, float maxThickness) {
        Random rand = new Random();
        this.thickness = rand.nextFloat(minThickness, maxThickness);
    }

    public void setTreeDepth(int depth) {
        this.treeDepth = depth;
    }

    public void enableRandomBranchColors(boolean enable) {
        this.randomColors = enable;
    }

    public void setRandomAngleRange(double minAngle, double maxAngle) {
        Random rand = new Random();
        this.randomAngle = rand.nextDouble(minAngle, maxAngle);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.BLUE);
        int x = 440;
        int y = 440;
        drawTree(g2d, x, y, this.randomAngle, this.treeDepth, this.thickness);
    }
}

