package ui;

import javax.swing.*;

//MenuButton is a JButton with desired formatting
public class MenuButton extends JButton {
    private static int buttonNumber = 1;

    private boolean isActive = false;
    private String name;
    private JButton button;
    private int currentButtonNumber;


    //MODIFIES this
    //EFFECTS creates a new JButton with required name
    public MenuButton(String name) {
        super(buttonNumber + ":  " + name);
        currentButtonNumber = buttonNumber;
        buttonNumber++;
        this.name = currentButtonNumber + ":  " + name;
        setupButton();
    }


    //MODIFIES this
    //EFFECTS sets format of button
    private void setupButton() {
        button = new JButton(name);
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
    }

    //getters
    public JButton getButton() {
        return button;
    }

    public String getName() {
        return name;
    }

}
