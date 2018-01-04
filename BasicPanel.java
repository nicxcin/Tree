import java.util.ArrayList;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BasicPanel extends JPanel {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            @Override 
            public void run() {
                new DecTreeViewer();
            }
        });
	}

	protected Font font;	

	protected Color BACKGROUND = new Color(39,40,34);
	protected Color GREEN = new Color(118,184,42);


	public BasicPanel() {}
}