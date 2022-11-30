package ui.buttonhandlers;

import ui.ChoreTrackerMenu;
import ui.InteractionButton;

import javax.swing.*;

//Processes button to trigger a new Panel to be created while
//also changing the components to allow Chore to be added to chore tracker
public class AddChoreEvent {

    //MODIFIES this, ChoreTrackerMenu, ChoreTracker, Chore, Person
    //EFFECTS allows input, disables menu, allows InteractionButton, processes adding of chore
    public AddChoreEvent(ChoreTrackerMenu ct, JTextField inputArea) {
        inputArea.setEditable(true);
        ct.toggleMenuClickable(false);
        ct.getInteractionButton().setEnabled(true);
        ct.processCommand(2);
    }
}
