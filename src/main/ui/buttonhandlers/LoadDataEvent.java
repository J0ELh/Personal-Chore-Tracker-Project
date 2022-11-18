package ui.buttonhandlers;

import ui.ChoreTrackerMenu;


//Processes button to trigger a new Panel to be created while
//also changing the components to display that data has been loaded
public class LoadDataEvent {

    //MODIFIES this, ChoreTrackerMenu, ChoreTracker, Chore, Person
    //EFFECTS Loads data, then displays that data was loaded in GUI
    public LoadDataEvent(ChoreTrackerMenu ct) {
        ct.processCommand(7);
    }
}
