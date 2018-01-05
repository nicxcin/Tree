import java.util.ArrayList;
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Question extends DraggableComponent {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            @Override 
            public void run() {
                new DecTreeViewer();
            }
        });
	}
	private static int optionID;

	private int id;
	private String questionTitle;
	private ArrayList<Option> optionList = new ArrayList<Option>();
	private DecisionTree parent;

	private BufferedImage image;
	private Font font;
	private int h = 60;
	private int w = 250;
	private Point pos = new Point(0,0);	
	private Color BACKGROUND = new Color(39,40,34);
	private Color GREEN = new Color(118,184,42);


	private JLabel titleLabel;
	private JTextField titleEdit;
	private CardLayout cl;
	private BasicPanel titleCard;
	private int topBarHeight = 30;

	public Question(DecisionTree parent, int id, Font font) {
		new Question(parent, id, "", font);
	}

	private class xQuestionBL implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
	        remove();
        }
    }

	public Question(DecisionTree parent, int id, String title, Font font) {
		this.id = id;
		this.parent = parent;
		this.font = font;
		setLayout(new BorderLayout());
		setFont(font);
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(GREEN);

		JPanel topBar = new JPanel(new BorderLayout());
		topBar.setBounds(0, 0, w, topBarHeight);
		topBar.setPreferredSize(new Dimension(w, topBarHeight));
		topBar.setBackground(GREEN);
		topBar.setBorder(BorderFactory.createLineBorder(Color.black));

		JButton xBtn = new JButton("x");
		xBtn.setPreferredSize(new Dimension(30,30));
		xBtn.addActionListener(new xQuestionBL());
		topBar.add(xBtn, BorderLayout.EAST);
		

		titleCard = new BasicPanel();
		titleCard.setLayout(new CardLayout());
		cl = (CardLayout)(titleCard.getLayout());


		add(topBar, BorderLayout.NORTH);
		add(titleCard, BorderLayout.CENTER);
		//setLayout(new CardLayout());
		//cl = (CardLayout)(getLayout());

		titleLabel = new JLabel(questionTitle, SwingConstants.CENTER);
		//titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(font);

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

		titleEdit.addKeyListener( new KeyAdapter() {    //Key listener
            public void keyPressed(KeyEvent e) {
                int k = e.getKeyCode();
                if(k==10) {
                	viewQuestion();
                }
            }
        });
		
		titleCard.add(titleEdit);

		titleCard.setBounds(0,topBarHeight,w,h-topBarHeight);
		titleCard.setVisible(true);
    	setBounds(pos.x, pos.y, w, h);
    	setVisible(true);
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

	public void remove() {
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