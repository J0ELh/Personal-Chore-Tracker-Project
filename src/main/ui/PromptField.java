package ui;

import javax.swing.*;
import java.awt.*;

public class PromptField extends JTextField {

    public PromptField(String text) {
        super(text);
        setupFormatting();
    }

    private void setupFormatting() {
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setEditable(false);
        this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        this.setSize(new Dimension(WIDTH, (int)(HEIGHT / 3)));
    }

    public PromptField() {
        this("");
    }
}
