import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.io.*;
import java.util.ArrayList;

public class DecTreeViewer extends JFrame {
    private Thread gameThread;
    private int H = 600;
    private int W = 1000;
    private Font font;
    private JPanel sidePanel;
    private JTabbedPane mainPanel;
    private static int treeID = 0;
    private ArrayList<DecisionTree> trees = new ArrayList<DecisionTree>();
    private DecisionTree activeTree;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override 
            public void run() {
                new DecTreeViewer();
            }
        });
    }


    private JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        JMenuItem newTreeItem = new JMenuItem("New Tree");
        JMenuItem opnTreeItem = new JMenuItem("Open Tree");
        JMenuItem savTreeItem = new JMenuItem("Save Tree");

        newTreeItem.addActionListener(new NewTreeBL());

        fileMenu.add(newTreeItem);
        fileMenu.add(opnTreeItem);
        fileMenu.add(savTreeItem);

        return menuBar;
    }

    public DecTreeViewer() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        W = (int)screenSize.getWidth();
        H = (int)screenSize.getHeight();

        setPreferredSize(new Dimension(W,H));
        setTitle("Decision Tree");
        //setMinimumSize(new Dimension(W,H));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createKeyInput();
        try { font = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/RobotoSlab-Regular.ttf")).deriveFont(16f); } catch (IOException e) {e.printStackTrace();} catch(FontFormatException e) {e.printStackTrace();}

        setJMenuBar(createMenu());


        sidePanel = new JPanel();
        mainPanel = new JTabbedPane();

        sidePanel.setFont(font);
        sidePanel.setBackground(Color.WHITE);
        sidePanel.setPreferredSize(new Dimension(200,H));

        mainPanel.setFont(font);
        mainPanel.setBackground(Color.GRAY);

        JButton b1 = new JButton("New Tree");
        b1.addActionListener(new NewTreeBL());
        JButton b2 = new JButton("New Question");
        b2.addActionListener(new NewQuestionBL());

        b1.setFont(font);
        b2.setFont(font);

        sidePanel.add(b1);
        sidePanel.add(b2);
        
        add(sidePanel, BorderLayout.LINE_START);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
        pack();

        createTree("Homepage 1");
        getSelectedTree().addQuestion("What would you like to do?");
    }

    private DecisionTree getSelectedTree() {
        return (DecisionTree)mainPanel.getSelectedComponent();
    }

    private class NewTreeBL implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            String treeName = JOptionPane.showInputDialog(DecTreeViewer.this,"Give The Decision Tree A Name: ");
            createTree(treeName);
        }
    }

    private class NewQuestionBL implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            String questionTitle = JOptionPane.showInputDialog(DecTreeViewer.this,"Give The Question A Title: ");
            getSelectedTree().addQuestion(questionTitle);
        }
    }

    private void createTree(String Name) {
        DecisionTree tree = new DecisionTree(treeID, Name, mainPanel.getSize(), font);
        mainPanel.addTab(Name + " *", tree);
        trees.add(tree);
    }

    public void createKeyInput() {
        this.addKeyListener( new KeyAdapter() {    //Key listener
            public void keyPressed(KeyEvent e) {
                int k = e.getKeyCode();
                //System.out.println("" + k);   
            }
        });
    }
}
