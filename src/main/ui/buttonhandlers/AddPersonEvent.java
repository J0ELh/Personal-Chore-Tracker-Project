package ui.buttonhandlers;

import model.ChoreTracker;
import model.Person;
import ui.ChoreTrackerMenu;
import ui.InteractionButton;
import javax.swing.*;

//Processes button to trigger a new Panel to be created while
//also changing the components to display Chore has been added to chore tracker
public class AddPersonEvent {

    //MODIFIES this, ChoreTrackerMenu
    //EFFECTS adds person to choreTracker, enables menu, disables InteractionButton, adds chore and notifies user
    public AddPersonEvent(ChoreTrackerMenu choreTrackerMenu, ChoreTracker ct,
                          JPanel menuArea, JTextField prompt, JTextField inputArea, InteractionButton button) {
        ct.addPerson(new Person(inputArea.getText()));
        prompt.setText(inputArea.getText() + " added successfully.");
        button.setEnabled(false);
        choreTrackerMenu.toggleMenuClickable(true);
        inputArea.setEditable(false);
    }

}
