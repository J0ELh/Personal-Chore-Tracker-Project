package ui.buttonhandlers;

import model.ChoreTracker;
import ui.ChoreTrackerMenu;
import ui.InteractionButton;

import javax.swing.*;

//Processes button to trigger a new Panel to be created while
//also changing the components to allow user to enter a chore to be completed
public class SelectChoreEvent {

    //MODIFIES this, ChoreTrackerMenu
    //EFFECTS sets up JPanel so that user can select which chore of given person to complete
    public SelectChoreEvent(ChoreTrackerMenu choreTrackerMenu, JTextField inputArea) {
        choreTrackerMenu.getInteractionButton().setEnabled(true);
        choreTrackerMenu.setPersonToComplete(inputArea.getText());
        choreTrackerMenu.selectChoreOfPerson();
    }
}
