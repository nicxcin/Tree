import java.util.ArrayList;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;

public class DraggableComponent extends JPanel {
	public static void main(String[] args) {}
	private static int optionID;

	private BufferedImage image;
	private Font font;
	private int x = 0;
	private int y = 0;
	private Color BACKGROUND = new Color(39,40,34);

	private Boolean dragging = false;

	public DraggableComponent() {
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//System.out.println("Mouse Moved");
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getPoint());
			}
		});
	}
}