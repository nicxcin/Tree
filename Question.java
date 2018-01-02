import java.util.ArrayList;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Question extends JPanel {
	public static void main(String[] args) {}
	private static int optionID;

	private int id;
	private String questionTitle;
	private ArrayList<Option> optionList = new ArrayList<Option>();
	private DecisionTree parent;

	private BufferedImage image;
	private Font font;
	private int h = 100;
	private int w = 200;
	private int x = 0;
	private int y = 0;
	protected Color BACKGROUND = new Color(39,40,34);

	public Question(DecisionTree parent, int id) {
		this.id = id;
		this.parent = parent;
		//trigger redraw

		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    	setBackground(Color.WHITE);
    	setPrefferedSize();
    	//setBounds(x,y,w,h);



	}

	public void setTitle(String title) {
		questionTitle = title;
		//trigger redraw
	}

	public void addOption() {
		Option o = new Option(this, optionID);
		optionID++;
		optionList.add(o);
	}

	public void remove() {
		parent.removeQuestion(id);
		optionList = null;
	}

	public void removeOption(int id) {
		optionList.remove(id);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}