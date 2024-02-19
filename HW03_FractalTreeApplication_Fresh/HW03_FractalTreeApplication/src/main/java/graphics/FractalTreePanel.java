package graphics;

import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;

import datastructures.IStack;
import datastructures.LinkedListStack;
import org.w3c.dom.Node;

public class FractalTreePanel extends JPanel {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final double LENGTH_FACTOR = 0.75;
    private final double ANGLE = Math.toRadians(25.0);

    public FractalTreePanel() {
        this.setPreferredSize(new Dimension(800, 600));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.BLUE);
    }

    /***
     * This method builds a fractal tree by iterating through a linked list stack. This method will iterate through the entire depth
     * as specified in the inputs, until the stack is empty.
     * @param g graphics object that will be manipulated
     * @param x starting x position for the tree
     * @param y starting y position for the tree
     * @param angleMin the minimum angle bound for random branching angles
     * @param angleMax the maximum angle bound for random branching angles
     * @param depth the depth of the tree that this method will traverse
     * @param length the length of the branch, specifically the initial branch
     * @param thicknessMin the minimum thickness for random branch thickness
     * @param thicknessMax the maximum thickness for random branch thickness
     * @param randomColors boolean to decide whether coloring will be random or set.
     */
    protected void drawTree(Graphics g, int x, int y, double angleMin, double angleMax, int depth, double length, float thicknessMin, float thicknessMax, boolean randomColors) {

        double length_ratio = 1.3; // ratio for the amount to reduce the length of each successive branch

        // Draw Grid
        Graphics2D g2d = (Graphics2D)g;
        GraphicsEnhancements.drawGrid(g2d, this.getWidth(), this.getHeight());
        g2d.rotate(Math.toRadians(180.0), x, y);

        // Initialize stack
        LinkedListStack<FractalTreePanelExtension> stack = new LinkedListStack<>();

        // Initialize First Branch
        FractalTreePanelExtension initialBranch = new FractalTreePanelExtension();
        initialBranch.setTreeDepth(depth); // set tree depth with the given depth
        initialBranch.setInitialAngle(90); // set stand the initial angle of the "trunk"
        initialBranch.setInitialLength(length); // set the length with the given length
        initialBranch.setX0Y0(x,y); // set the starting point in the middle of the chat (given)

        // Push First Branch to Stack
        stack.push(initialBranch);

        // Current branch that we are looking at
        FractalTreePanelExtension current;

        // Create a random object for later usage
        Random rand = new Random();
        while (!stack.isEmpty()){
            // pop the top branch on the stack
            current = stack.pop();

            // Set the deviation branch angle
            current.setRandomAngleRange(angleMin,angleMax);
            current.setRandomThicknessRange(thicknessMin,thicknessMax);
            current.enableRandomBranchColors(randomColors);

            // create x,y coordinates to draw branches. The cosine and sine functions allow us to take an angle and length, and convert that in rise/run additions for x,y.
            int x2 = current.x0 + (int)(Math.cos(Math.toRadians(current.angle)) * current.length);
            int y2 = current.y0 + (int)(Math.sin(Math.toRadians(current.angle)) * current.length);

            // if randomColor is true, randomize the color pallete for the tree
            if (current.randomColors) {
                float r_color = rand.nextFloat(); // random red float
                float g_color = rand.nextFloat(); // random green float
                float b_color = rand.nextFloat(); // random blue float

                g.setColor(new Color(r_color,g_color,b_color)); // 3 random color weights come together to form random color
                // Draw leaves when the depth is 1 or 0, this will draw circles at the end of the tree branches, simulating leaves.
                if (current.treeDepth < 2) {
                    drawCircle(g, x2, y2, 4);
                }

            } else {
                // Brown color for branches in the event they are not randomized colors
                g.setColor(new Color(111, 78, 55));

                // Draw leaves when the depth is 1 or 0. These leaves AND branches are green at the outer part of the tree to simulate leaves.
                if (current.treeDepth < 2) {
                    g.setColor(new Color(50,150,50));
                    drawCircle(g, x2, y2, 4);
                }
            }

            // set the thickness of the branch
            ((Graphics2D) g).setStroke(new BasicStroke(current.thickness));
            // Draw the branch from x0,y0 to x2,x2.
            g.drawLine(current.x0, current.y0, x2, y2);

            // This next section is the Stack manipulation needed to build a tree.
            // If the depth of the tree is not 0 where you currently are, ie you're not at the end
            // of a branch, you should branch off 2 times from the branch. Thus, we continue adding 2 new branches.
            // These new branches are pushed onto the stack. As we return to the top of this while loop, we will traverse
            // a specific branch to its final branches, and then return to a higher branch. This simulates a Depth First Search
            // algorithm/heuristic.
            if (current.treeDepth > 0) {
                // Push the left branch onto the stack
                FractalTreePanelExtension leftBranch = new FractalTreePanelExtension();
                leftBranch.setTreeDepth(current.treeDepth - 1); // reduce the depth by 1
                leftBranch.setInitialAngle(current.angle + current.branchAngle); // take the current angle and increment it a "random" amount
                leftBranch.setInitialLength(current.length/length_ratio); // reduce the length of the following branches using length ratio
                leftBranch.setX0Y0(x2,y2); // set the x0,y0 for these new branches at the ends of the previous branch
                stack.push(leftBranch);

                // Push the right branch onto the stack
                FractalTreePanelExtension rightBranch = new FractalTreePanelExtension();
                rightBranch.setTreeDepth(current.treeDepth - 1); // reduce the depth by 1
                rightBranch.setInitialAngle(current.angle - current.branchAngle); // take the current angle and decrement it a "random" amount
                rightBranch.setInitialLength(current.length/length_ratio); // reduce the length of the following branches using length ratio
                rightBranch.setX0Y0(x2,y2); // set the x0,y0 for these new branches at the ends of the previous branch
                stack.push(rightBranch);
            }
        }
    }


    // TODO: circle place holder =
    private void drawCircle(Graphics g, int x, int y, int radius) {
        // Placeholder for drawing a simple circle
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }
}
