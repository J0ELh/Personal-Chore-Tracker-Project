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
    public AddMemberEvent(ChoreTrackerMenu choreTrackerMenu, JTextField prompt,
                          JTextField inputArea, InteractionButton button) {
        this.choreTrackerMenu = choreTrackerMenu;
        inputArea.setEditable(true);
        choreTrackerMenu.toggleMenuClickable(false);
        choreTrackerMenu.getInteractionButton().setEnabled(true);
        choreTrackerMenu.processCommand(1);
    }

//    //MODIFIES ChoreTracker, this
//    //EFFECTS adds member to choreTracker by console input
//    private void addMember() {
//        RightInteractionPanel addMemberPanel = new RightInteractionPanel(new GridBagLayout());
//
//        choreTrackerMenu.getPromptField().setText("Who would you like to add? ");
//        choreTrackerMenu.getInputField().setText("Enter name");
//        choreTrackerMenu.setInteractionButton(new InteractionButton());
//        choreTrackerMenu.getInteractionButton().setText("Add");
//        choreTrackerMenu.getInteractionButton().setActionCommand("addPerson");
//        choreTrackerMenu.getInteractionButton().addActionListener(choreTrackerMenu);
//        addMemberPanel.setupRightPanel(choreTrackerMenu.getPromptField(), choreTrackerMenu.getInputField(),
//                choreTrackerMenu.getI);
//
//
//        choreTrackerMenu.setScreenArea(menuArea, addMemberPanel);
//
//    }
}
