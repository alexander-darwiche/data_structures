package graphics;

import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;

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

        //drawFlower(g, getWidth() / 2, getHeight() / 2, 50, 5, Color.RED, Color.GREEN);
    }

    protected void drawFlowers(Graphics g, int centerX, int centerY, int size, int petals, Color petalColor, Color centerColor){
        Random rand = new Random();
        int rand_x = -300;
        int rand_y = 0;

        int numberOfFlowers = rand.nextInt(100);
        for (int i = 0;i<10;i++){
            /*int rand_x = rand.nextInt(-300,300);
            int rand_y = rand.nextInt(-200,200);*/

//            if (i%3 == 0){
//                rand_y = -200;
//                rand_x = rand_x+150;
//            }
            if (i < 5){
                rand_x = rand_x+150;
            } else if (i==5){
                rand_x = -300;
            } else {
                rand_x = rand_x+150;
            }

            if (i < 3) {
                rand_y = rand_y+150;
            } else if (i < 5){
                rand_y = rand_y-150;
            } else if (i == 5) {
                rand_y = 0;
            } else if (i < 8){
                rand_y = rand_y-150;
            } else {
                rand_y = rand_y+150;
            }


            // Java 'Color' class takes 3 floats, from 0 to 1.
            float r1 = rand.nextFloat();
            float g1 = rand.nextFloat();
            float b1 = rand.nextFloat();
            Color randomPetalColor = new Color(r1, g1, b1);

            // Java 'Color' class takes 3 floats, from 0 to 1.
            float r2 = rand.nextFloat();
            float g2 = rand.nextFloat();
            float b2 = rand.nextFloat();
            Color randomCenterColor = new Color(r2, g2, b2);

            // petalColor = randomPetalColor;
            // centerColor = randomCenterColor;
            int randPetalNumber = rand.nextInt(5,8);


            drawFlower(g,centerX+rand_x-200,centerY+rand_y-100,size,randPetalNumber,petalColor,centerColor);
        }
    }

    protected void drawFlower(Graphics g, int centerX, int centerY, int size, int petals, Color petalColor, Color centerColor) {

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
        g.setColor(centerColor);
        g.fillOval(centerX - size / 4, centerY - size / 4, size / 2, size / 2);
        g.setColor(Color.BLACK);
        g.drawOval(centerX - size / 4, centerY - size / 4, size / 2, size / 2);

        // Process the queue to draw each petal
        while (!queue.isEmpty()) {
            Petal petal = queue.remove();
            g.setColor(petalColor);
            g.fillOval(petal.x - petal.size, petal.y - petal.size, 2 * petal.size, 2 * petal.size);
            g.setColor(Color.BLACK);
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
