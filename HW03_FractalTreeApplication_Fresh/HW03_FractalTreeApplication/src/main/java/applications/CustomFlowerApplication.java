package applications;

import graphics.CustomFlowerPanel;
import javax.swing.JFrame;
import java.awt.*;

public class CustomFlowerApplication {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Flower");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use the extended CustomFlowerPanel
        CustomFlowerPanel flowerPanel = new CustomFlowerPanel();
        // Here, students can invoke setter methods on flowerPanel to customize it before adding it to the frame
        flowerPanel.setPetalColor(Color.MAGENTA);
        flowerPanel.setCenterColor(Color.ORANGE);
        flowerPanel.setNumberOfPetals(8); // Example customization

        frame.add(flowerPanel);
        frame.setSize(800, 600); // Adjust size as needed
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }
}
