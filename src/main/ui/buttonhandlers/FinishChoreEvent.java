package ui.buttonhandlers;

import model.ChoreTracker;
import model.Person;
import ui.ChoreTrackerMenu;
import ui.InteractionButton;

import javax.swing.*;


//Processes button to trigger a new Panel to be created while
//also changing the components to display that a chore has been finished successfully
public class FinishChoreEvent {

    //EFFECTS disables input, enables menu, disables InteractionButton, displays that chore has been completed
    @SuppressWarnings("methodlength") //suppressing warning for method length since it doesn't make sense to break
    //this method down further
    public FinishChoreEvent(ChoreTrackerMenu choreTrackerMenu, ChoreTracker ct, JTextField inputArea) {
        JTextField prompt = choreTrackerMenu.getPromptField();
        choreTrackerMenu.setChoreToComplete(inputArea.getText());
        ct.completeChoreOption(choreTrackerMenu.getPersonToComplete(), choreTrackerMenu.getChoreToComplete());
        Boolean nameValid = false;
        int index = 0;
        Boolean choreValid = false;
        for (int i = 0; i < ct.getListOfMembers().size(); i++) {
            if (ct.getListOfMembers().get(i).getName().equals(choreTrackerMenu.getPersonToComplete())) {
                nameValid = true;
                index = i;
                break;
            }
        }
        Person p = ct.getListOfMembers().get(index);
        for (int i = 0; nameValid && i < p.getChoreList().size(); i++) {

            if (p.getChoreList().get(i).getChoreName().equals(choreTrackerMenu.getChoreToComplete())) {
                choreValid = true;
            }
        }


        if (nameValid && choreValid) {
            prompt.setText(choreTrackerMenu.getPersonToComplete() + " completed "
                    + choreTrackerMenu.getChoreToComplete() + " successfully.");
        } else {
            prompt.setText("Either the name or chore were invalid. No chore was completed.");
            prompt.setAutoscrolls(true);
        }
        choreTrackerMenu.toggleMenuClickable(true);
        choreTrackerMenu.getInteractionButton().setEnabled(false);
        inputArea.setEditable(false);
    }
}
