package ui;

import javax.swing.*;
import java.awt.*;

//Button that has the desired formatting for Chore Tracker GUI
public class InteractionButton extends JButton {

    //MODIFIES this
    //EFFECTS sets up normal JButton with desired format
    public InteractionButton(String text) {
        super(text);
        this.setBackground(Color.WHITE);
    }

    //MODIFIES this
    //EFFECTS calls other constructor with an empty string
    public  InteractionButton() {
        this("");
    }
}
