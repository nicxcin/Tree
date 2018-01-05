import java.util.ArrayList;
import java.util.Collection;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class BasicPanel extends JPanel {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            @Override 
            public void run() {
                new DecTreeViewer();
            }
        });
	}

	private static Collection<BasicPanel> elements = new ArrayList<BasicPanel>();

	protected Font font;	

	protected Color BACKGROUND = new Color(39,40,34);
	protected Color GREEN = new Color(118,184,42);

	protected Boolean hasFocus = false;


	public BasicPanel() {
		elements.add(this);

		// this.addMouseListener(new MouseAdapter() {
  //   		@Override
  //   		public void mouseClicked(MouseEvent e) {
  //   			for(BasicPanel element : elements) {
  //   				element.hasFocus = false;
  //   			}

  //   			hasFocus = true;
  //   		}
		// });
	}
}