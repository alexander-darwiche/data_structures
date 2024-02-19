package applications;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import graphics.FractalTreePanel;
import graphics.FractalTreePanelExtension;

import java.awt.*;



public class FractalTreeApplication {

        public FractalTreeApplication() {}

        public static void main(String[] args) {
        // Setup and display the JFrame containing FractalTreePanel
        JFrame frame = new JFrame("Fractal Tree");
        frame.setDefaultCloseOperation(3);

        FractalTreePanelExtension fractalTreePanel = new FractalTreePanelExtension();
        fractalTreePanel.setTreeDepth(8);
        fractalTreePanel.enableRandomBranchColors(true);


        frame.add(fractalTreePanel);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo((Component)null);
        frame.setVisible(true);

    };
}
