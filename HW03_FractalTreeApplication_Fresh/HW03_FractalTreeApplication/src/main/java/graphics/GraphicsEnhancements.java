package graphics;

import java.awt.Color;
import java.awt.Graphics2D;

public class GraphicsEnhancements {
    public static final int GRID_SPACING = 20;
    public static final int LABEL_SPACING = 40;

    public static void drawGrid(Graphics2D g2d, int width, int height) {

        Color originalColor = g2d.getColor();

        g2d.setColor(Color.LIGHT_GRAY);

        // Draw vertical grid lines
        for (int x = 0; x <= width; x += GRID_SPACING) {
            g2d.drawLine(x, 0, x, height);
            if (x % LABEL_SPACING == 0) {
                g2d.drawString(String.valueOf(x), x, 10);
            }
        }

        // Draw horizontal grid lines
        for (int y = 0; y <= height; y += GRID_SPACING) {
            g2d.drawLine(0, y, width, y);
            if (y % LABEL_SPACING == 0 && y != 0) {
                g2d.drawString(String.valueOf(y), 0, y);
            }
        }

        // Restore the original color
        g2d.setColor(originalColor);

    }
}