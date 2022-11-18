package ui.buttonhandlers;

import ui.ChoreTrackerMenu;
import ui.InteractionButton;

import javax.swing.*;

//Processes button to exit GUI and program
public class ExitEvent {

    //MODIFIES this, ChoreTrackerMenu, ChoreTracker, Chore, Person
    //EFFECTS processes button to exit GUI and program
    public ExitEvent(ChoreTrackerMenu ct) {
        ct.processCommand(8);
    }
}
