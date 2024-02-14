package graphics; // Assuming the extended panel is still part of the graphics package

import java.awt.*;
import javax.swing.JPanel;
import java.awt.Color;

public class CustomFlowerPanel extends FlowerPanel {
    // New attributes for customization
    private Color petalColor = Color.PINK; // Default petal color
    private Color centerColor = Color.YELLOW; // Default center color
    private int numberOfPetals = 5; // Default number of petals as in FlowerPanel

    // Constructor
    public CustomFlowerPanel() {
        super(); // Call the parent constructor
    }

    // Setter methods for new attributes
    public void setPetalColor(Color color) {
        this.petalColor = color;
    }

    public void setCenterColor(Color color) {
        this.centerColor = color;
    }

    public void setNumberOfPetals(int petals) {
        this.numberOfPetals = petals;
    }

    // Override the paintComponent to use the new attributes
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Draw the basic components (like the grid)
        // Use the custom attributes to draw the flower
        drawFlowers(g, getWidth() / 2, getHeight() / 2, 50, numberOfPetals, petalColor, centerColor);
    }

    // Additional methods to support new features can be added here
}
