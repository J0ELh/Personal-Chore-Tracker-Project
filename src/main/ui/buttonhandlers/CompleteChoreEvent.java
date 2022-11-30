package ui.buttonhandlers;

import ui.ChoreTrackerMenu;
import ui.InteractionButton;

import javax.swing.*;

//Processes button to trigger a new Panel to be created while
//also changing the components to allow a person's chore to be completed
public class CompleteChoreEvent {

    //MODIFIES this, ChoreTrackerMenu, ChoreTracker, Chore, Person
    //EFFECTS allows input, disables menu, enables InteractionButton,
    //allowing members to be displayed
    public CompleteChoreEvent(ChoreTrackerMenu ct, JTextField inputArea) {
        inputArea.setEditable(true);
        ct.toggleMenuClickable(false);
        ct.getInteractionButton().setEnabled(true);
        ct.processCommand(3);
    }
}
