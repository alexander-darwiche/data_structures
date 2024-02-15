package applications;

import graphics.FlowerPanel;

import javax.swing.*;

public class FlowerApplication {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Fractal Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new FlowerPanel());
        frame.setSize(800, 600); // Adjust size as needed
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
