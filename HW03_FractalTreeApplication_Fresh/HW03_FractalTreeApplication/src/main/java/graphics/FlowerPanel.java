package graphics;

import javax.swing.JPanel;
import java.awt.*;

import datastructures.LinkedListQueue; // Assume this is your queue implementation

public class FlowerPanel extends JPanel {

    public FlowerPanel() {
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw grid
        GraphicsEnhancements.drawGrid(g2d, getWidth(), getHeight());

        drawFlower(g, getWidth() / 2, getHeight() / 2, 50, 5);
    }

    protected void drawFlower(Graphics g, int centerX, int centerY, int size, int petals) {

        // Calculate the position of each petal and ADD it to the queue
        LinkedListQueue<Petal> queue = new LinkedListQueue<>();
        double angle = 360.0 / petals;

        for (int i = 0; i < petals; i++) {
            double rad = Math.toRadians(i * angle);
            int x = centerX + (int) (size * Math.cos(rad));
            int y = centerY + (int) (size * Math.sin(rad));
            queue.add(new Petal(x, y, size / 2)); // Example petal size
        }

        // Draw center
        g.fillOval(centerX - size / 4, centerY - size / 4, size / 2, size / 2);

        // Process the queue to draw each petal
        while (!queue.isEmpty()) {
            Petal petal = queue.remove();
            g.drawOval(petal.x - petal.size, petal.y - petal.size, 2 * petal.size, 2 * petal.size);
        }
    }

    private class Petal {
        int x, y, size;

        Petal(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }
}
