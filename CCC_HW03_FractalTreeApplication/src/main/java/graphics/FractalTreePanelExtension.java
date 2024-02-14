package graphics;

import java.awt.*;
import java.util.Random;

public class FractalTreePanelExtension extends FractalTreePanel {
    // // In theFractalTreePanelExtensionclass or a similar class
    private float thickness = 1.0F; // Default thickness
    private int treeDepth = 1; // Default treeDepth
    private boolean randomColors = false; // Default randomColors
    private double randomAngle = 90.0; // Default angle

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final double LENGTH_FACTOR = 0.75; // Factor to reduce the branch length
    private final double ANGLE = Math.toRadians(25); // The angle of the branch split

    /**
     * Sets the range for random branch thickness.
     * @param minThickness the minimum thickness for a branch.
     * @param maxThickness the maximum thickness for a branch.
     */
    public void setRandomThicknessRange(float minThickness, float maxThickness) {
        // Implementation goes here
        Random rand = new Random();
        this.thickness = rand.nextFloat(minThickness,maxThickness);
    }


    /**
     * Sets the maximum depth of the tree, which indirectly controls the number of branches.
     * @param depth the maximum depth of the tree.
     */
    public void setTreeDepth(int depth) {
        // Implementation goes here
        this.treeDepth = depth;
    }


    /**
     * Enables or disables random coloring of the branches.
     * @param enable if true, branches will be colored randomly; if false, a default color will be used.
     */
    public void enableRandomBranchColors(boolean enable) {
        // Implementation goes here
        this.randomColors = enable;
    }


    /**
     * Sets the range for random branching angles.
     * @param minAngle the minimum angle for a branch.
     * @param maxAngle the maximum angle for a branch.
     */
    public void setRandomAngleRange(double minAngle, double maxAngle) {
        // Implementation goes here
        Random rand = new Random();
        this.randomAngle = rand.nextDouble(minAngle,maxAngle);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);

        // Initial values for the root of the tree
        int x = 440;
        int y = 440;

        drawTree(g2d, x, y, this.randomAngle, this.treeDepth, this.thickness);
    }
}
