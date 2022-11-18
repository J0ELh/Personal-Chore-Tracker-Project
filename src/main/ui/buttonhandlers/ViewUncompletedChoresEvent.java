package ui.buttonhandlers;

import ui.ChoreTrackerMenu;
import ui.InteractionButton;

import javax.swing.*;

//Processes button to trigger a new Panel to be created while
//also changing the components to display people with uncompleted chores and let user interact with them
public class ViewUncompletedChoresEvent {

    //MODIFIES this, ChoreTrackerMenu
    //EFFECTS shows table of people with uncompleted chores.
    //lets user click on row of choice to see chore that user has to complete
    public ViewUncompletedChoresEvent(ChoreTrackerMenu ct, JPanel menuArea, JTextField prompt, JTextField inputArea,
                                      InteractionButton button) {
        ct.toggleMenuClickable(true);
        ct.getInteractionButton().setEnabled(true);
        ct.processCommand(4);
    }
}
