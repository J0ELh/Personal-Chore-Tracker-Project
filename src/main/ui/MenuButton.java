package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuButton extends JButton {
    private static int buttonNumber = 1;

    private boolean isActive = false;
    private String name;
    private JButton button;
    private int currentButtonNumber;



    //EFFECTS creates a new JButton with required name and adds to parent
    public MenuButton(String name) {
        super(buttonNumber + ":  " + name);
        currentButtonNumber = buttonNumber;
        buttonNumber++;
        this.name = currentButtonNumber + ":  " + name;
        setupButton();
    }


    private void setupButton() {
        button = new JButton(name);
//        button.setLayout(); STILL NEED TO MAKE BUTTONS ALIGNED ON LEFT OR LOOK BETTER
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
    }

    public JButton getButton() {
        return button;
    }

    public String getName() {
        return name;
    }

}
