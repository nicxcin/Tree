import javax.swing.*;
import java.awt.*;


public class XIcon implements Icon {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override 
            public void run() {
                new DecTreeViewer();
            }
        });
    }

    private int width = 40;
    private int height = 20;
    private Color GREEN = new Color(118,184,42);
    private Color BACKGROUND;

    private BasicStroke stroke = new BasicStroke(3);

    public XIcon(int state) {
        //super();
        if(state==0) 
            BACKGROUND = Color.BLACK;
        else
            BACKGROUND = Color.RED;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(BACKGROUND);
        g2d.fillRect(x, y, width, height);

        //g2d.setColor(Color.BLACK);
        //g2d.drawRect(x +1 ,y + 1,width -2 ,height -2);

        g2d.setColor(Color.WHITE);

        g2d.setStroke(stroke);
        int k = 6;
        int w = width/2;
        int tx = x + w/2;
        g2d.drawLine(tx + k, y + k, tx + w - k, y + height - k);
        g2d.drawLine(tx + k, y + height - k, tx + w - k, y + k);

        g2d.dispose();
    }

    public int getIconWidth() {
        return width;
    }

    public int getIconHeight() {
        return height;
    }
}