package src;

import javax.swing.*;
import java.awt.*;

public class ExIcon implements Icon {

    private int state;
    private Color BACKGROUND;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DecTreeViewer::new);
    }

    private int width = 40;
    private int height = 20;
    private Color GREEN = new Color(118,184,42);

    private BasicStroke stroke = new BasicStroke(3);

    ExIcon(int state) {
        if(state==0)
            BACKGROUND = Color.BLACK;
        else
            BACKGROUND = Color.GRAY;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(BACKGROUND);
        g2d.fillRect(x, y, width, height);

        g2d.setColor(Color.WHITE);

        int u = width / 7;
        g2d.fillRect((int)(width/2-(u*1.5)), (height-u)/2+1, u*3, u);
        g2d.fillRect((width-u)/2, (int)(height/2-(u*1.5))+1, u, u*3);
        g2d.dispose();
    }

    public int getIconWidth() {
        return width;
    }

    public int getIconHeight() {
        return height;
    }
}