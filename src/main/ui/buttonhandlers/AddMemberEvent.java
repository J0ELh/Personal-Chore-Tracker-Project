package ui.buttonhandlers;

import ui.ChoreTrackerMenu;
import ui.InteractionButton;
import javax.swing.*;

//Processes button to trigger a new Panel to be created while
//also changing the components to allow Member to be added to chore tracker
public class AddMemberEvent {
    private ChoreTrackerMenu choreTrackerMenu;

    //MODIFIES this, ChoreTrackerMenu, ChoreTracker, Person
    //EFFECTS allows input, disables menu, allows InteractionButton, processes adding of member
    public AddMemberEvent(ChoreTrackerMenu choreTrackerMenu, JTextField inputArea) {
        this.choreTrackerMenu = choreTrackerMenu;
        inputArea.setEditable(true);
        choreTrackerMenu.toggleMenuClickable(false);
        choreTrackerMenu.getInteractionButton().setEnabled(true);
        choreTrackerMenu.processCommand(1);
    }


}
