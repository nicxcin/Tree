package src;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Question extends DraggableComponent {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(DecTreeViewer::new);
	}
	private static int optionID;

	private int id;
	private String questionTitle;
	private ArrayList<Option> optionList = new ArrayList<>();
	private DecisionTree parent;

	private BufferedImage image;
	private Font font;
	private int h = 100;
	private int w = 250;
	private Point pos = new Point(0,0);	
	private Color BACKGROUND = new Color(39,40,34);
	private Color GREEN = new Color(118,184,42);


	private JLabel titleLabel;
	private JTextField titleEdit;
	private CardLayout cl;
	private BasicPanel titleCard;
	private int barHeight = 20;

	private class xQuestionBL implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
	        remove();
        }
    }

	Question(DecisionTree parent, int id, Font font) {
		this.id = id;
		this.parent = parent;
		this.font = font;
		setLayout(new BorderLayout());
		setFont(font);
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(GREEN);

		createTopBar();
		createBottomBar();
		createTitleCard();

    	setBounds(pos.x, pos.y, w, h);
    	setVisible(true);
	}

	private void createTitleCard() {
		titleCard = new BasicPanel();
		titleCard.setLayout(new CardLayout());
		cl = (CardLayout)(titleCard.getLayout());

		add(titleCard, BorderLayout.CENTER);

		titleLabel = new JLabel(questionTitle, SwingConstants.CENTER);
		titleLabel.setFont(font);

		//if the user double clicks, the question will become editable
		titleLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					editQuestion();
				}
			}
		});


		titleCard.add(titleLabel);


		titleEdit = new JTextField();
		titleEdit.setForeground(Color.GRAY);

		//If the user presses enter the question will stop being editable
		titleEdit.addKeyListener( new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int k = e.getKeyCode();
				if(k==10) {
					viewQuestion();
				}
			}
		});

		titleCard.add(titleEdit);

		titleCard.setBounds(0, barHeight,w,h- barHeight);
		titleCard.setVisible(true);
	}

	private void createBottomBar() {
		JPanel bottomBar = new JPanel(new BorderLayout());
		bottomBar.setPreferredSize(new Dimension(w, barHeight));
		bottomBar.setBackground(GREEN);

		JLabel info1 = new JLabel("Options");
		info1.setForeground(Color.WHITE);
		bottomBar.add(info1, BorderLayout.WEST);

		JButton optBtn = new JButton(new ExIcon(0));
		optBtn.setBorderPainted(false);
		optBtn.setPreferredSize(new Dimension(40,20));
		optBtn.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {
				optBtn.setIcon(new ExIcon(0));
			}
			public void mousePressed(MouseEvent e) {
				optBtn.setIcon(new ExIcon(1));
			}
		});
		bottomBar.add(optBtn, BorderLayout.EAST);

		add(bottomBar, BorderLayout.SOUTH);
	}

	private void createTopBar() {
		JPanel topBar = new JPanel(new BorderLayout());
		topBar.setPreferredSize(new Dimension(w, barHeight));
		topBar.setBackground(GREEN);
		//topBar.setBorder(BorderFactory.createLineBorder(Color.black));

		JLabel info = new JLabel("Question:");
		info.setForeground(Color.WHITE);
		topBar.add(info, BorderLayout.WEST);

		JButton xBtn = new JButton(new XIcon(0));
		xBtn.setBackground(Color.BLACK);
		xBtn.setBorderPainted(false);
		xBtn.setPreferredSize(new Dimension(40,20));
		xBtn.addActionListener(new xQuestionBL());
		xBtn.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {
				xBtn.setIcon(new XIcon(0));
			}
			public void mousePressed(MouseEvent e) {
				xBtn.setIcon(new XIcon(1));
			}
		});
		topBar.add(xBtn, BorderLayout.EAST);

		add(topBar, BorderLayout.NORTH);
	}

	private void editQuestion() {
		cl.next(titleCard);
	}

	private void viewQuestion() {
		questionTitle = titleEdit.getText();
		titleLabel.setText(questionTitle);
		cl.next(titleCard);
	}

	public void setTitle(String title) {
		questionTitle = title;
		titleLabel.setText(title);
		titleEdit.setText(title);
	}

	public void addOption() {
		Option o = new Option(this, optionID);
		optionID++;
		optionList.add(o);
	}

	private void remove() {
		optionList = null;
		parent.removeQuestion(this);
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