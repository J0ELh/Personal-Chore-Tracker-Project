package ui;

import javax.swing.*;
import java.awt.*;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

//RightInteractionPanel is a JPanel that represents the panel that is displayed on the
//right side of the ChoreTrackerMenu GUI and has a given layout
public class RightInteractionPanel extends JPanel {

    //EFFECTS creates new JPanel
    public RightInteractionPanel(GridBagLayout layout) {
        super(layout);
    }


    //MODIFIES this, ChoreTrackerMenu
    //EFFECTS formats given JTextFields and JButtons according to application standard
    public void setupRightPanel(JTextField f, JTextField a, JButton b) {
        f.setBackground(Color.LIGHT_GRAY);
        f.setHorizontalAlignment(JTextField.CENTER);
        a.setPreferredSize(new Dimension(50, 30));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = 5;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(f, constraints);
        constraints.gridy = 1;
        constraints.gridheight = 2;
        this.add(a, constraints);
        constraints.gridy = 3;
        constraints.gridheight = 1;
        this.add(b, constraints);
        this.setVisible(true);
    }


    //MODIFIES this, ChoreTrackerMenu
    //EFFECTS formats given JTextFields and JButtons according to application standard
    //and inserts a scrollable ScrollPane for text a1
    public void setupRightPanel(JTextField f, JTextArea a1, JTextField a2, JButton b) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 5;
        this.add(f, constraints);
        constraints.gridy = 1;

        a1.setEditable(false);
        a1.setCaretPosition(0);
        JScrollPane scrollPane = new JScrollPane(a1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBar(new JScrollBar());
        scrollPane.setPreferredSize(new Dimension(500, 100));
        this.add(scrollPane, constraints);
        constraints.gridy = 2;
        this.add(a2, constraints);
        constraints.gridy = 3;
        this.add(b, constraints);
    }
}
