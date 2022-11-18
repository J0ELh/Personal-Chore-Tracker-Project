package ui.buttonhandlers;

import ui.ChoreTrackerMenu;

//Processes button to trigger a new Panel to be created while
//also changing the components to display that data has been saved
public class SaveDataEvent {

    //MODIFIES this, ChoreTrackerMenu, ChoreTracker, Chore, Person
    //EFFECTS Saves data, then displays that data was loaded in GUI
    public SaveDataEvent(ChoreTrackerMenu ct) {
        ct.processCommand(6);
    }
}
