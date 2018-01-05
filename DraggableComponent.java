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
	private Point pos = new Point(0,0);

	private Boolean dragging = false;
	protected Point anchorPoint;

	public DraggableComponent() {
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				anchorPoint = e.getPoint();
			}


			@Override
			public void mouseDragged(MouseEvent e) {
				int anchorX = anchorPoint.x;
                int anchorY = anchorPoint.y;

                Point parentOnScreen = getParent().getLocationOnScreen();
                Point mouseOnScreen = e.getLocationOnScreen();
                pos = new Point(mouseOnScreen.x - parentOnScreen.x - anchorX, mouseOnScreen.y - parentOnScreen.y - anchorY);
                setLocation(pos);
			}
		});
	}
}