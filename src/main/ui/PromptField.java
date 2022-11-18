package ui;

import javax.swing.*;
import java.awt.*;

//PromptField is a JTextField with the desired layout and formatting
public class PromptField extends JTextField {

    //EFFECTS constructs JTextField and changes layout and formatting
    public PromptField(String text) {
        super(text);
        setupFormatting();
    }

    //MODIFIES this
    //EFFECTS changes layout and formatting of button
    private void setupFormatting() {
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setEditable(false);
        this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        this.setSize(new Dimension(WIDTH, (int)(HEIGHT / 3)));
    }

}
