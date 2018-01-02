import javax.swing.*;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.*;

public class DecisionTree extends JPanel {
	public static void main(String[] args) {}
	private static int questionID = 0;

	private ArrayList<Question> questionList = new ArrayList<Question>(); 
	private int id;
	private String name;
	private JPanel gparent;

	private BufferedImage image;
	private Font font;
	private int x = 0;
	private int y = 0;
	private int w = 100;
	private int h = 100;
	private Question parent;
	protected Color BACKGROUND = new Color(39,40,34);

	public DecisionTree(int id, String name) {
		this.id = id;
		this.name = name;
		this.parent = parent;

		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        setBackground(BACKGROUND);
	}

	public void setName(String Name) {
		this.name = name;
	}


	public void addQuestion() {
		addQuestion("");
	}

	public void addQuestion(String title) {
		Question q = new Question(this, questionID);
		q.setTitle(title);
		questionID++;
		questionList.add(q);
		this.add(q);
	}

	public void removeQuestion(int id) {
		questionList.remove(id);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}