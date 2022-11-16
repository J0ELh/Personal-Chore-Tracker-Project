package ui;

import javax.swing.*;
import java.awt.*;

public class InteractionButton extends JButton {

    public InteractionButton(String text) {
        super(text);
        this.setBackground(Color.WHITE);
    }

    public  InteractionButton() {
        this("");
    }
}
