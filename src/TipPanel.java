package src;

import javax.swing.*;
import java.awt.*;
import static javax.swing.BorderFactory.createLineBorder;

class TipPanel extends JPanel {

    private String text = "";
    private JLabel j;

    TipPanel() {
        super(new BorderLayout());
        setBorder(createLineBorder(Color.black));
        JLabel title = new JLabel("<html><u>Info:</u></html>");
        add(title, BorderLayout.NORTH);

        j = new JLabel();
        add(j, BorderLayout.CENTER);
        setText("This is a tip, I hope Its Helpful");
    }


    private void setText(String text) {
        this.text = text;
        j.setText(text);
    }
}
