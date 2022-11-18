package ui.buttonhandlers;

import model.ChoreTracker;
import ui.ChoreTrackerMenu;
import ui.InteractionButton;

import javax.swing.*;


//Processes button to trigger a new Panel to be created while
//also changing the components to display that a chore has been finished successfully
public class FinishChoreEvent {

    //EFFECTS disables input, enables menu, disables InteractionButton, displays that chore has been completed
    public FinishChoreEvent(ChoreTrackerMenu choreTrackerMenu, ChoreTracker ct, JPanel menuArea, JTextField prompt,
                            JTextField inputArea, InteractionButton button) {
        choreTrackerMenu.setChoreToComplete(inputArea.getText());
        ct.completeChoreOption(choreTrackerMenu.getPersonToComplete(), choreTrackerMenu.getChoreToComplete());
        prompt.setText(choreTrackerMenu.getPersonToComplete() + " completed " + choreTrackerMenu.getChoreToComplete()
                + " successfully.");
        choreTrackerMenu.toggleMenuClickable(true);
        choreTrackerMenu.getInteractionButton().setEnabled(false);
        inputArea.setEditable(false);
    }
}
