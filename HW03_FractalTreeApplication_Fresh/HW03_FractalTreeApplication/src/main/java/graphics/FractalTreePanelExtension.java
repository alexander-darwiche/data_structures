package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class FractalTreePanelExtension extends FractalTreePanel {
    float thickness = 1.0F;
    int treeDepth = 1;
    double length = 100;
    boolean randomColors = false;
    double angle = 10.0;
    double branchAngle = 20.0;
    int x0 = 0;
    int y0 = 0;

    private Box controls; // box to store the slides/inputs
    private JPanel graphPanel; // panel for the graph
    private JSlider depthSlider; // slider for tree depth
    private JLabel depthLabel, angleMaxLabel, angleMinLabel, thicknessMinLabel, thicknessMaxLabel; // labels for all elements
    private JCheckBox randomColorsCheckBox; // checkbox for toggling on/off randomize colors
    double angleMinValue = 20; // min angle to start at
    double angleMaxValue = 30; // max angle to start at
    float thicknessMinValue = 1; // min thickness to start at
    float thicknessMaxValue = 3; // max angle to start at
    boolean randomColorsValue; // boolean for randomizing colors
    SpinnerModel angleMin, angleMax, thicknessMax, thicknessMin; // spinner models for numeric inputs that can be clicked through

    public FractalTreePanelExtension() {
        // initialize and format depth slider
        depthSlider = new JSlider(JSlider.HORIZONTAL, 0, 15, 10);
        depthSlider.setMinorTickSpacing(1);
        depthSlider.setPaintTicks(true);
        depthSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        depthLabel = new JLabel("Tree Depth: 10");

        // create "spinnerNumberModels" for angleMin, angleMax, thicknessMin and thicknessMax
        angleMin = new SpinnerNumberModel(20, 1, 90, 0.1);
        JSpinner angleMin2 = new JSpinner(angleMin);
        angleMinLabel = new JLabel("Min Angle");
        angleMax = new SpinnerNumberModel(30, 1, 90, 0.1);
        JSpinner angleMax2 = new JSpinner(angleMax);
        angleMaxLabel = new JLabel("Max Angle");

        thicknessMin = new SpinnerNumberModel(1F, 1F, 5F, 0.1F);
        JSpinner thicknessMin2 = new JSpinner(thicknessMin);
        thicknessMinLabel = new JLabel("Min Thickness");
        thicknessMax = new SpinnerNumberModel(3F, 1F, 5F, 0.1F);
        JSpinner thicknessMax2 = new JSpinner(thicknessMax);
        thicknessMaxLabel = new JLabel("Max Thickness");

        // create button that updates tree when pressed
        JButton button = new JButton("Update Tree");
        randomColorsCheckBox = new JCheckBox("Toggle Randomize Colors");
        button.addActionListener(event -> {
            treeDepth = depthSlider.getValue();
            depthLabel.setText("Tree Depth: " + treeDepth);
            angleMinValue = (double) angleMin.getValue();
            angleMaxValue = (double) angleMax.getValue();
            thicknessMinValue = (float) (double) thicknessMin.getValue();
            thicknessMaxValue = (float) (double) thicknessMax.getValue();
            randomColorsValue = randomColorsCheckBox.isSelected();
            repaint(); // re-call paint component
        });

        // Building up a Controls Section of the Application
        controls = new Box(BoxLayout.Y_AXIS);
        controls.add(randomColorsCheckBox);
        controls.add(depthLabel);
        controls.add(depthSlider);
        controls.add(angleMinLabel);
        controls.add(angleMin2);
        controls.add(angleMaxLabel);
        controls.add(angleMax2);
        controls.add(thicknessMinLabel);
        controls.add(thicknessMin2);
        controls.add(thicknessMaxLabel);
        controls.add(thicknessMax2);
        controls.add(button);

        controls.add(Box.createRigidArea(new Dimension(0, 20)));

        graphPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1000, 1000);
            }
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D)g;
                g2d.setColor(Color.DARK_GRAY);
                int x = 440; // where the tree begins
                int y = 600; // where the tree begins
                drawTree(g2d, x, y, angleMinValue, angleMaxValue, treeDepth, length, thicknessMinValue, thicknessMaxValue, randomColorsValue);
            }
        };
        graphPanel.setBackground(Color.white);
        add(controls);
        add(graphPanel);
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
        this.branchAngle = rand.nextDouble(minAngle, maxAngle);
    }

    public void setInitialAngle(double angle) {
        this.angle = angle;
    }

    public void setInitialLength(double length) {
        this.length = length;
    }

    public void setX0Y0(int x, int y) {
        this.x0 = x;
        this.y0 = y;
    }
}


