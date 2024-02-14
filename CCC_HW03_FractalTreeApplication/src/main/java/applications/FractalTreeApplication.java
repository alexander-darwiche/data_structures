package applications;

import javax.swing.JFrame;

import graphics.CustomFlowerPanel;
import graphics.FractalTreePanel;
import graphics.FractalTreePanelExtension;

import java.awt.*;

public class FractalTreeApplication {
    public static void main(String[] args) {
        // Setup and display the JFrame containing FractalTreePanel

        JFrame frame = new JFrame("Fractal Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use the extended CustomFlowerPanel
        FractalTreePanelExtension fractalTreePanel = new FractalTreePanelExtension();
        // Here, students can invoke setter methods on flowerPanel to customize it before adding it to the frame
        fractalTreePanel.setRandomAngleRange(0,90);
        fractalTreePanel.setRandomThicknessRange(30,50);
        fractalTreePanel.setTreeDepth(10);
        fractalTreePanel.enableRandomBranchColors(true); // Example customization

        frame.add(fractalTreePanel);
        frame.setSize(1000, 1000); // Adjust size as needed
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);

    }
}
