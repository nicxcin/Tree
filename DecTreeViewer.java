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

public class DecTreeViewer extends JFrame {

    private Thread gameThread;
    private int H = 600;
    private int W = 1000;
    private Font font;


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


        JPanel sidePanel = new JPanel();
        JPanel mainPanel = new JPanel();

        sidePanel.setBackground(Color.YELLOW);
        sidePanel.setPreferredSize(new Dimension(200,H));
        
        add(sidePanel, BorderLayout.LINE_START);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
        pack();
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
