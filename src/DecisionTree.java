package src;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.ArrayList;

public class DecisionTree extends JPanel {
	public static void main(String[] args) {}
	private static int questionID = 0;

	private ArrayList<Question> questionList = new ArrayList<>();
	private int id;
	private String name;
	private JPanel gparent;

	private BufferedImage image;
	private Font font;
	private Dimension size = new Dimension(100,100);
	private Question parent;
	protected Color BACKGROUND = new Color(39,40,34);

	DecisionTree(int id, String name, Dimension parentSize, Font font) {
		this.id = id;
		this.name = name;
		this.font = font;
		this.size = parentSize;

		setFont(font);
		setLayout(null);

		image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        setBackground(new Color(243,245,246));
        //setOpaque(true);
        setVisible(true);
	}

	public void setName(String Name) {
		this.name = name;
	}


	public void addQuestion() {
		addQuestion("");
	}

	public void addQuestion(String title) {
		Question q = new Question(this, questionID, font);
		questionID++;
		questionList.add(q);
		q.setTitle(title);
		add(q);
		repaint();
	}

	public void removeQuestion(Question q) {
		remove(q);
		questionList.remove(q);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}