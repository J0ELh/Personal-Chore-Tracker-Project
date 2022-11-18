package ui.buttonhandlers;

import model.ChoreTracker;
import ui.ChoreTrackerMenu;
import ui.InteractionButton;
import ui.RightInteractionPanel;

import javax.swing.*;
import java.awt.*;

//Processes button to trigger a new Panel to be created while
//also changing the components to allow difficulty of chore to be specified
//and new action listener to be enabled
public class ProcessChoreEvent {
    private String currentChoreName;
    private ChoreTrackerMenu choreTrackerMenu;
    private ChoreTracker ct;
    private JTextField promptField;
    private JTextField inputArea;
    private JPanel menuArea;
    private InteractionButton interactionButton;

    //MODIFIES this, ChoreTrackerMenu, ChoreTracker, Chore, Person
    //EFFECTS receives required data from ChoreTrackerMenu to add panel
    //that allows user to set difficulty of chore
    public ProcessChoreEvent(ChoreTrackerMenu choreTrackerMenu, ChoreTracker ct, JPanel menuArea, JTextField prompt,
                             JTextField inputArea, InteractionButton button) {
        this.ct = ct;
        this.menuArea = menuArea;
        this.promptField = prompt;
        this.inputArea = inputArea;
        this.interactionButton = button;
        this.choreTrackerMenu = choreTrackerMenu;
        this.currentChoreName = inputArea.getText();
        choreTrackerMenu.setCurrentChoreName(currentChoreName);
        setAddedChoreDifficulty();
    }

    //MODIFIES this, ChoreTrackerMenu, ChoreTracker, Chore, Person
    //EFFECTS sets up new panel that allows user to specify difficulty of chore to add
    //and adds event listener
    private void setAddedChoreDifficulty() {
        RightInteractionPanel addChoreDifficultyPanel = new RightInteractionPanel(new GridBagLayout());
        promptField.setText("Please enter the difficulty:  ");
        inputArea.setText("0 = easy, 1 = medium, 2 = hard");
        interactionButton = new InteractionButton();
        interactionButton.setText("Set Difficulty");
        interactionButton.setActionCommand("setDifficultyOfAdded");
        interactionButton.addActionListener(choreTrackerMenu);
        addChoreDifficultyPanel.setupRightPanel(promptField, inputArea, interactionButton);
        addChoreDifficultyPanel.setVisible(true);

        choreTrackerMenu.setScreenArea(menuArea, addChoreDifficultyPanel);
    }

}
