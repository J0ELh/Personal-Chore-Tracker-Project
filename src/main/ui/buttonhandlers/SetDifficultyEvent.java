package ui.buttonhandlers;

import model.ChoreTracker;
import ui.ChoreTrackerMenu;
import ui.InteractionButton;

import javax.swing.*;

//Processes button to trigger a new Panel to be created while
//also changing the components to display and add the chore with the given difficulty
public class SetDifficultyEvent {

    //MODIFIES this, ChoreTrackerMenu, ChoreTracker, Chore, Person
    //EFFECTS enables menu, completes chore of selected person, and displays that this has been done to GUI
    public SetDifficultyEvent(ChoreTrackerMenu choreTrackerMenu, ChoreTracker ct, JPanel menuArea, JTextField prompt,
                             JTextField inputArea, InteractionButton button) {


        int currentDifficulty = Integer.parseInt(inputArea.getText());
        ct.addChoreWithDifficulty(choreTrackerMenu.getCurrentChoreName(), currentDifficulty);
        prompt.setText(choreTrackerMenu.getCurrentChoreName() + " added successfully.");
        choreTrackerMenu.toggleMenuClickable(true);
        choreTrackerMenu.getInteractionButton().setEnabled(false);
        inputArea.setEditable(false);

    }
}
