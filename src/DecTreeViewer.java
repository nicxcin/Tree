package src;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.io.*;
import java.util.ArrayList;

public class DecTreeViewer extends JFrame {
    private int H;
    private int W;
    private Font font;
    private TipPanel tipPanel;
    private JPanel sidePanel;
    private JTabbedPane mainPanel;
    private static int treeID = 0;
    private ArrayList<DecisionTree> trees = new ArrayList<>();
    private DecisionTree activeTree;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(DecTreeViewer::new);
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
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createKeyInput();
        getFont();
        try { font = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/RobotoSlab-Regular.ttf")).deriveFont(16f); } catch (IOException | FontFormatException e) {e.printStackTrace();}
        setJMenuBar(createMenu());
        createSidePanel();
        createMainPanel();
        setVisible(true);
        pack();

        //Testing
        createTree("Homepage 1");
        getSelectedTree().addQuestion("What would you like to do?");
        getSelectedTree().addQuestion("");
    }

    private void createMainPanel() {
        mainPanel = new JTabbedPane();
        mainPanel.setFont(font);
        mainPanel.setBackground(Color.GRAY);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void createSidePanel() {
        sidePanel = new JPanel(new BorderLayout());
        sidePanel.setFont(font);
        sidePanel.setBackground(Color.WHITE);
        sidePanel.setPreferredSize(new Dimension(200,H));

        tipPanel = new TipPanel();
        tipPanel.setPreferredSize(new Dimension(200,200));

        JButton b1 = new JButton("New Tree");
        b1.addActionListener(new NewTreeBL());
        JButton b2 = new JButton("New Question");
        b2.addActionListener(new NewQuestionBL());

        b1.setFont(font);
        b2.setFont(font);

        JPanel buttonBox = new JPanel(new BorderLayout());

        buttonBox.add(b1, BorderLayout.NORTH);
        buttonBox.add(b2, BorderLayout.SOUTH);

        sidePanel.add(buttonBox, BorderLayout.NORTH);
        sidePanel.add(tipPanel, BorderLayout.SOUTH);

        add(sidePanel, BorderLayout.LINE_START);
    }

    private DecisionTree getSelectedTree() {
        return (DecisionTree)mainPanel.getSelectedComponent();
    }

    //Button Listener for creating new Trees
    private class NewTreeBL implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            String treeName = JOptionPane.showInputDialog(DecTreeViewer.this,"Give The Decision Tree A Name: ");
            if(treeName!=null && !treeName.equals(""))
                createTree(treeName);
        }
    }

    //Button Listener for creating new Questions
    private class NewQuestionBL implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            String questionTitle = JOptionPane.showInputDialog(DecTreeViewer.this,"Give The Question A Title: ");
            if(questionTitle!=null && !questionTitle.equals(""))
                getSelectedTree().addQuestion(questionTitle);
        }
    }

    private void createTree(String Name) {
        //Each Tab is a Different Decision
        DecisionTree tree = new DecisionTree(treeID, Name, mainPanel.getSize(), font);
        mainPanel.addTab(Name + " *", tree);
        trees.add(tree);
    }

    private void createKeyInput() {
        this.addKeyListener( new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int k = e.getKeyCode();
                //System.out.println("" + k);   
            }
        });
    }
}
