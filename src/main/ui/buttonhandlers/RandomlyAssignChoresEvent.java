package ui.buttonhandlers;

import ui.ChoreTrackerMenu;
import ui.InteractionButton;

import javax.swing.*;

//Processes button to trigger a new Panel to be created while
//also changing the components to display and assign chores randomly
public class RandomlyAssignChoresEvent {

    //MODIFIES this, ChoreTrackerMenu, ChoreTracker, Chore, Person
    //EFFECTS assigns chores randomly, then displays that this has been done to GUI
    public RandomlyAssignChoresEvent(ChoreTrackerMenu ct) {
        ct.processCommand(5);
    }
}
