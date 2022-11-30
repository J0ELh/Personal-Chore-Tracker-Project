package ui;

import model.ChoreTracker;
import persistance.JsonReader;
import persistance.JsonWriter;
import ui.buttonhandlers.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.exit;

//Runs the user interface for the chore tracker
public class ChoreTrackerMenu extends JFrame implements ActionListener {
    private JFrame root;
    private static final String JSON_STORE = "./data/ChoreTracker.json";
    private ChoreTracker ct;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public static final int WIDTH = 2560 / 3;
    public static final int HEIGHT = 1440 / 3;
    private static final int LOADING_TIME = 700;
    private JPanel screenArea;
    private JPanel leftPlaceHolder;
    private JPanel rightPlaceHolder;
    private JPanel menuArea;
    private JPanel splashScreen;
    private JTextArea splashText;
    private GridBagConstraints rootConstraints;

    private ArrayList<JButton> listOfMenuButtons = new ArrayList<>();
    private MenuButton addMemberButton;
    private MenuButton addChoreButton;
    private MenuButton completeChoreButton;
    private MenuButton assignRandomlyButton;
    private MenuButton loadPreviousStateButton;
    private MenuButton viewUncompletedButton;
    private MenuButton saveCurrentStateButton;
    private MenuButton exitButton;

    private JTextField inputArea;
    private PromptField promptField;
    private InteractionButton interactionButton;

    private String currentChoreName;
    private String personToComplete;
    private String choreToComplete;

    //MODIFIES this
    //EFFECTS initializes scanner and ChoreTracker object, JSON reader & writer, and launches GUI
    public ChoreTrackerMenu() {
        super("Chore Tracker");

        ct = new ChoreTracker();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        root = new JFrame("Chore Tracker");
        root.setVisible(true);
        setupGraphics();
    }

    //MODIFIES this
    //EFFECTS draws a JFrame window for the ChoreTracker in which the whole application will be run
    //First, display's splashscreen, then displays the menu in which the program is run
    //This method has been inspired by the SimpleDrawingEditor from our 210 course
    private void setupGraphics() {
        //sets root as the frame of the whole application
        root.setLayout(new GridBagLayout());
        rootConstraints = new GridBagConstraints();
        rootConstraints.gridx = 0;
        rootConstraints.gridy = 0;
        root.setResizable(false);
        root.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setLocationRelativeTo(null);
        root.setVisible(true);
        showSplashScreen();
        root.getContentPane().removeAll();
        setupScreenArea();
        setupInteractionFields();
        root.revalidate();
        root.repaint();
    }




    //MODIFIES this, PromptField, InteractionButton
    //EFFECTS sets up the button and fields that are used for interaction
    private void setupInteractionFields() {
        promptField = new PromptField("Prompt");
        inputArea = new JTextField("Get input");
        interactionButton = new InteractionButton("Do task");
        interactionButton.setBackground(Color.WHITE);
    }

    //MODIFIES this
    //EFFECTS displays a splash screen composed of an image and a text that updates 3 times.
    private void showSplashScreen() {
        splashScreen = new JPanel(new GridBagLayout());
        GridBagConstraints panelConstraint = new GridBagConstraints();
        panelConstraint.anchor = GridBagConstraints.BASELINE;
        panelConstraint.gridx = 0;
        panelConstraint.gridy = 0;
        panelConstraint.gridheight = 2;
        panelConstraint.gridwidth = GridBagConstraints.REMAINDER;
        splashText = new JTextArea("Setting Up the Chore Tracker");
        splashText.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        splashScreen.add(splashText, panelConstraint);
        JLabel splashPicture = null;
        try {
            Image splashImage = ImageIO.read(new File("./data/SplashScreen/WelcomeScreen.png"));
            splashImage = splashImage.getScaledInstance(WIDTH, (int)(HEIGHT * 0.8), Image.SCALE_SMOOTH);
            splashPicture = new JLabel(new ImageIcon(splashImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panelConstraint.gridy = 2;
        panelConstraint.gridheight = 1;

        splashScreen.add(splashPicture, panelConstraint);
        root.add(splashScreen);
        updateSplashScreen();
    }

    //MODIFIES this
    //EFFECTS updates the loading text of the splash screen
    private void updateSplashScreen() {
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(LOADING_TIME);
                splashText.setText(splashText.getText() + ".");
            }
            Thread.sleep(LOADING_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //MODIFIES this
    //EFFECTS sets up the JPanel that holds all other components of the application
    private void setupScreenArea() {
        screenArea = new JPanel(new GridBagLayout());
        GridBagConstraints screenAreaConstraints = new GridBagConstraints();
        screenAreaConstraints.anchor = GridBagConstraints.WEST;
        screenAreaConstraints.gridy = 0;
        screenAreaConstraints.gridx = 0;
        screenAreaConstraints.gridwidth = 2;
        screenAreaConstraints.fill = GridBagConstraints.WEST;
        drawMenu();
        leftPlaceHolder = menuArea;
        rightPlaceHolder = new JPanel();

        screenArea.add(leftPlaceHolder, screenAreaConstraints);
        screenAreaConstraints.gridx = 1;
        screenAreaConstraints.gridwidth = 1;
        screenAreaConstraints.fill = GridBagConstraints.EAST;
        screenArea.add(rightPlaceHolder, screenAreaConstraints);
        screenArea.setVisible(true);
        root.setVisible(true);
        root.add(screenArea, rootConstraints);
    }

    //MODIFIES this
    //sets updates the screen area putting component a onto the left side and component b
    // onto the right side of the screen
    public void setScreenArea(Component a, Component b) {
        root.getContentPane().removeAll();
        screenArea.removeAll();
        GridBagConstraints screenConstraints = new GridBagConstraints();
        screenConstraints.insets = new Insets(0,5,0,5);
        screenConstraints.gridx = 0;

        if (a != null) {
            screenArea.add(a, screenConstraints);
        } else {
            screenArea.add(leftPlaceHolder, screenConstraints);
        }

        screenConstraints.gridx = 1;

        if (b != null) {
            screenArea.add(b, screenConstraints);
        } else {
            screenArea.add(rightPlaceHolder, screenConstraints);
        }
        screenArea.setVisible(true);
        root.add(screenArea);
        root.setVisible(true);
    }

    //MODIFIES this
    //EFFECTS draws a menu on the screen of the JPanel and sets up the
    //actionlisteners of the buttons
    private void drawMenu() {
        menuArea = new JPanel();
        GridLayout menuLayout = new GridLayout(0,1);
        menuArea.setLayout(menuLayout);
        menuLayout.setVgap(5);


        JTextField header = new JTextField("Menu Options");
        header.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        header.setEditable(false);
        header.setHorizontalAlignment(JTextField.CENTER);
        menuArea.add(header, BorderLayout.CENTER);

        createMenuButtons();
        setupButtonHandling();
        addMemberButton.setBackground(Color.WHITE);
        addChoreButton.setBackground(Color.WHITE);
        completeChoreButton.setBackground(Color.WHITE);
        viewUncompletedButton.setBackground(Color.WHITE);
        assignRandomlyButton.setBackground(Color.WHITE);
        saveCurrentStateButton.setBackground(Color.WHITE);
        loadPreviousStateButton.setBackground(Color.WHITE);
        exitButton.setBackground(Color.red);

    }

    //MODIFIES this
    //EFFECTS hooks up menu buttons to the listeners
    private void setupButtonHandling() {
        addMemberButton.getButton().setActionCommand(addMemberButton.getName());
        addMemberButton.addActionListener(this);

        addChoreButton.getButton().setActionCommand(addChoreButton.getName());
        addChoreButton.addActionListener(this);

        completeChoreButton.getButton().setActionCommand(completeChoreButton.getName());
        completeChoreButton.addActionListener(this);

        viewUncompletedButton.getButton().setActionCommand(viewUncompletedButton.getName());
        viewUncompletedButton.addActionListener(this);

        assignRandomlyButton.getButton().setActionCommand(assignRandomlyButton.getName());
        assignRandomlyButton.addActionListener(this);

        saveCurrentStateButton.getButton().setActionCommand(saveCurrentStateButton.getName());
        saveCurrentStateButton.addActionListener(this);

        loadPreviousStateButton.getButton().setActionCommand(loadPreviousStateButton.getName());
        loadPreviousStateButton.addActionListener(this);

        exitButton.getButton().setActionCommand(exitButton.getName());
        exitButton.addActionListener(this);
    }

    //MODIFIES this, MenuButton
    //EFFECTS creates new MenuButtons for the menu and adds them to the menu JPanel
    private void createMenuButtons() {
        addMemberButton = new MenuButton("Add Member");
        menuArea.add(addMemberButton);

        addChoreButton = new MenuButton("Add Chore");
        menuArea.add(addChoreButton);

        completeChoreButton = new MenuButton("Complete Chore");
        menuArea.add(completeChoreButton);

        viewUncompletedButton = new MenuButton("View Members with Uncompleted Chores");
        menuArea.add(viewUncompletedButton);

        assignRandomlyButton = new MenuButton("Randomly Assign Chores");
        menuArea.add(assignRandomlyButton);

        saveCurrentStateButton = new MenuButton("Save Current Chore Tracker State");
        menuArea.add(saveCurrentStateButton);

        loadPreviousStateButton = new MenuButton("Load Previous Chore Tracker State");
        menuArea.add(loadPreviousStateButton);

        exitButton = new MenuButton("Exit");
        menuArea.add(exitButton);

        addButtonsToList();
    }

    //MODIFIES this, MenuButton
    //EFEFCTS adds menubuttons to a list
    private void addButtonsToList() {
        listOfMenuButtons.add(addMemberButton);
        listOfMenuButtons.add(addChoreButton);
        listOfMenuButtons.add(viewUncompletedButton);
        listOfMenuButtons.add(completeChoreButton);
        listOfMenuButtons.add(assignRandomlyButton);
        listOfMenuButtons.add(saveCurrentStateButton);
        listOfMenuButtons.add(loadPreviousStateButton);
        listOfMenuButtons.add(exitButton);
    }


    //REQUIRES input within the options (1, 2, 3, 4, 5, 6, 7, 8)
    //MODIFIES this, Chore, Person, all classes in "buttonhandlers" package
    //EFFECTS executes menu choices
    public void processCommand(int choice) {


        if (choice == 1) {
            //add member
            addMember();
        } else if (choice == 2) {
            //add chore
            addNewChore();
        } else if (choice == 3) {
            //complete a chore
            completeChore();
        } else if (choice == 4) {
            //view members who haven't completed their chores
            viewMembersWithUncompletedChores();
        } else if (choice == 5) {
            //randomly assign chores
            randomlyAssignChores();
        } else if (choice == 6) {
            //save current chore tracker state
            saveState();
        } else if (choice == 7) {
            //load previous chore tracker stateS
            loadState();
        } else if (choice == 8) {
            //exit program
            System.out.println(ct.finishExecuting());;
            exit(0);
        }
    }

    // MODIFIES: this, ChoreTracker, Chore, Person
    // EFFECTS: loads ChoreTracker from file and sets up right panel appropriately
    private void loadState() {

        RightInteractionPanel loadStatePanel = new RightInteractionPanel(new GridBagLayout());


        try {
            ct = jsonReader.read();
            promptField.setText("Loaded Chore Tracker from File.");
        } catch (IOException e) {
            promptField.setText("Unable load Chore Tracker from " + JSON_STORE);
        }

        inputArea.setText("Please select a different option");
        inputArea.setEditable(false);
        interactionButton = new InteractionButton();
        interactionButton.setText("No Action Possible");
        interactionButton.setEnabled(false);
        loadStatePanel.setupRightPanel(promptField, inputArea, interactionButton);

        setScreenArea(menuArea, loadStatePanel);
    }

    //MODIFIES this, ChoreTracker, Chore, Person
    //EFFECTS saves the ChoreTracker to file and sets up right panel appropriately
    private void saveState() {
        RightInteractionPanel saveStatePanel = new RightInteractionPanel(new GridBagLayout());

        try {
            jsonWriter.open();
            jsonWriter.write(ct);
            jsonWriter.close();
            promptField.setText("Saved Chore Tracker to File.");
        } catch (FileNotFoundException e) {
            promptField.setText("Unable to save chore tracker to " + JSON_STORE);
        }

        inputArea.setText("Please select a different option");
        inputArea.setEditable(false);
        interactionButton = new InteractionButton();
        interactionButton.setText("No Action Possible");
        interactionButton.setEnabled(false);
        saveStatePanel.setupRightPanel(promptField, inputArea, interactionButton);

        setScreenArea(menuArea, saveStatePanel);
    }

    //MODIFIES ChoreTracker
    //EFFECTS assigns chores to complete semi-randomly to members and sets up right panel appropriately
    public void randomlyAssignChores() {
        ct.assignChoresRandomly();
        RightInteractionPanel assignChoresPanel = new RightInteractionPanel(new GridBagLayout());
        promptField.setText("All Chores Randomly Assigned!\n\n");
        inputArea.setText("Please select a different option");
        inputArea.setEditable(false);
        interactionButton = new InteractionButton();
        interactionButton.setText("No Action Possible");
        interactionButton.setEnabled(false);

        assignChoresPanel.setupRightPanel(promptField, inputArea, interactionButton);

        setScreenArea(menuArea, assignChoresPanel);
    }

    //MODIFIES ChoreTracker, this
    //EFFECTS prints a list of members who haven't completed all their chores to the console
    @SuppressWarnings("methodlength") //using suppressWarnings because this method is complex
    //but it doesn't make sense to decompose it
    private void viewMembersWithUncompletedChores() {
        //getting members with uncompleted chores
        int numOfPeopleWithUncompletedChores = 0;
        ArrayList<String> listOfNames = new ArrayList<>();
        for (int i = 0; i < ct.getListOfMembers().size(); i++) {
            if (ct.getListOfMembers().get(i).getChoreSum() > 0) {
                numOfPeopleWithUncompletedChores++;
                listOfNames.add(ct.getListOfMembers().get(i).getName());
            }
        }

//        if (numOfPeopleWithUncompletedChores == 0) {
//            inputArea.setText("No members to show.");
//            inputArea.setEditable(false);
//            interactionButton = new InteractionButton();
//            interactionButton.setText("No Action Possible");
//            interactionButton.setEnabled(false);
//            // TO BE COMPLETED. SHOW A SPECIFIC SCREEN WHEN THERE ARE NO PEOPLE TO SHOW
//        }


        //setting up JTable
        String[] columnName = { "Select Name for More Details: "};
        Object[][] data = new Object[listOfNames.size()][1];
        for (int i = 0; i < listOfNames.size(); i++) {
            data[i][0] = listOfNames.get(i);
        }


        JTable table = new JTable(data, columnName) {
            //THIS FOLLOWING CODE IS STRONGLY INSPIRED BY Rahul Borkar's answer at
            //https://stackoverflow.com/questions/9919230/disable-user-edit-in-jtable
            //The code overrides the isCellEditable function of JTable to disable
            //editing in all fields of the table
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setAlignmentX(JTable.CENTER_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        table.setFillsViewportHeight(true);

        //Following code strongly inspired by ProgrammingWizards TV
        //at https://www.youtube.com/watch?v=5dK4dA39INk
        //The code handels displaying the list of chores to complete for each person once they are selected
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!selectionModel.isSelectionEmpty()) {
                    String output = ct.getListOfMembers().get(selectionModel.getMinSelectionIndex()).listChores();
                    JOptionPane.showMessageDialog(new JTextField("More Information: "), output);
                }
            }
        });


        setScreenArea(menuArea, scrollPane);
    }

    //MODIFIES ChoreTracker, Person, Chore
    //EFFECTS allows user to mark chore as completed through GUI
    private void completeChore() {

        if (ct.getMembersWithUncompletedChores() == null || ct.getMembersWithUncompletedChores() == "") {
            //displays a window showing that no chores can be completed at this state
            displayNoChoresToComplete();
            return;
        }

        RightInteractionPanel completeChorePanel = new RightInteractionPanel(new GridBagLayout());
        promptField.setText("Please select a person: ");
        JTextArea listOfPeople = new JTextArea();
        for (int i = 0; i < ct.getListOfMembers().size(); i++) {
            listOfPeople.append(promptField.getText() + "\n" + ct.getListOfMembers().get(i).listChores());
        }
        inputArea.setText("Enter Name");
        interactionButton = new InteractionButton();
        interactionButton.setText("Enter");
        interactionButton.setActionCommand("completeChoreOfPerson");
        interactionButton.addActionListener(this);
        completeChorePanel.setupRightPanel(promptField, listOfPeople, inputArea, interactionButton);

        setScreenArea(menuArea, completeChorePanel);
    }

    //MODIFIES this
    //EFFECTS displays that everyone has completed their chores
    private void displayNoChoresToComplete() {
        RightInteractionPanel allChoresCompleted = new RightInteractionPanel(new GridBagLayout());
        promptField.setText("Everyone has completed their chores!");
        inputArea.setEditable(false);
        inputArea.setText("Select Different Option");
        interactionButton.setEnabled(false);
        allChoresCompleted.setupRightPanel(promptField, inputArea, interactionButton);
        toggleMenuClickable(true);
        setScreenArea(menuArea, allChoresCompleted);
    }

    //MODIFIES this
    //EFFECTS displays options to complete a chore entered by the user
    public void selectChoreOfPerson() {
        RightInteractionPanel completeChorePanel = new RightInteractionPanel(new GridBagLayout());
        promptField.setText("Which chore was completed?\n\n");
        for (int i = 0; i < ct.getListOfMembers().size(); i++) {
            if (ct.getListOfMembers().get(i).getName().equals(personToComplete)) {
                ct.getListOfMembers().get(i).listChores();
                break;
            }
        }
        inputArea.setText("Enter Name of Chore");
        interactionButton = new InteractionButton();
        interactionButton.setText("Complete");
        interactionButton.setActionCommand("completeChore");
        interactionButton.addActionListener(this);
        completeChorePanel.setupRightPanel(promptField, inputArea, interactionButton);

        setScreenArea(menuArea, completeChorePanel);
    }

    //MODIFIES ChoreTracker
    //EFFECTS allows user to input member to add to chore tracker
    private void addMember() {
        RightInteractionPanel addMemberPanel = new RightInteractionPanel(new GridBagLayout());

        promptField.setText("Who would you like to add? ");
        inputArea.setText("Enter name");
        interactionButton = new InteractionButton();
        interactionButton.setText("Add");
        interactionButton.setActionCommand("addPerson");
        interactionButton.addActionListener(this);
        addMemberPanel.setupRightPanel(promptField, inputArea, interactionButton);


        setScreenArea(menuArea, addMemberPanel);

    }

    //MODIFIES this
    //EFFECTS allows new chore name to be entered to be completed
    private void addNewChore() {

        RightInteractionPanel addChorePanel = new RightInteractionPanel(new GridBagLayout());
        promptField.setText("What chore would you like to add? ");
        inputArea.setText("Enter chore");
        interactionButton = new InteractionButton();
        interactionButton.setText("Add");
        interactionButton.setActionCommand("addChore");
        interactionButton.addActionListener(this);
        addChorePanel.setupRightPanel(promptField, inputArea, interactionButton);

        setScreenArea(menuArea, addChorePanel);
    }


    //MODIFIES this, ChoreTracker, Chore, Person
    //EFFECTS handles all button inputs in the UI
    @Override
    @SuppressWarnings("methodlength")
    //Suppressing warning because menu actions should be handled in the same place.
    //I already have shortened the handlers using different classes
    public void actionPerformed(ActionEvent e) {
        /**
         * DON'T NEED TO DRAW DEPENDENCY ARROWS OR ASSOCIATIONS SO NOT HERE BC NOT ASSIGNING TO LOCAL VARIABLE
         * 
         */
        if (e.getActionCommand().equals("1:  Add Member")) {
            new AddMemberEvent(this, inputArea);
        } else if (e.getActionCommand().equals("2:  Add Chore")) {
            new AddChoreEvent(this, inputArea);
        } else if (e.getActionCommand().equals("3:  Complete Chore")) {
            new CompleteChoreEvent(this, inputArea);
        } else if (e.getActionCommand().equals("4:  View Members with Uncompleted Chores")) {
            new ViewUncompletedChoresEvent(this);
        } else if (e.getActionCommand().equals("5:  Randomly Assign Chores")) {
            new RandomlyAssignChoresEvent(this);
        } else if (e.getActionCommand().equals("6:  Save Current Chore Tracker State")) {
            new SaveDataEvent(this);
        } else if (e.getActionCommand().equals("7:  Load Previous Chore Tracker State")) {
            new LoadDataEvent(this);
        } else if (e.getActionCommand().equals("8:  Exit")) {
            new ExitEvent(this);
        } else if (e.getActionCommand().equals("addPerson")) {
            new AddPersonEvent(this, ct, inputArea);
        } else if (e.getActionCommand().equals("addChore")) {
            new ProcessChoreEvent(this, ct, menuArea, promptField, inputArea, interactionButton);
        } else if (e.getActionCommand().equals("setDifficultyOfAdded")) {
            new SetDifficultyEvent(this, ct, inputArea);
        } else if (e.getActionCommand().equals(("completeChoreOfPerson"))) {
            new SelectChoreEvent(this, inputArea);
        } else if (e.getActionCommand().equals(("completeChore"))) {
            new FinishChoreEvent(this, ct, inputArea);
        }

    }

    //MODIFIES this
    //EFFECTS enables or disables menu buttons to limit what the user can do
    public void toggleMenuClickable(Boolean enabled) {
        for (JButton button: listOfMenuButtons) {
            if (button.getName().equals("8:  Exit")) {
                continue;
            }
            button.setEnabled(enabled);
        }
        root.repaint();
    }


    //GETTERS AND SETTERS
    public InteractionButton getInteractionButton() {
        return interactionButton;
    }

    public void setCurrentChoreName(String currentChoreName) {
        this.currentChoreName = currentChoreName;
    }

    public String getCurrentChoreName() {
        return currentChoreName;
    }

    public void setPersonToComplete(String person) {
        this.personToComplete = person;
    }

    public String getPersonToComplete() {
        return personToComplete;
    }

    public void setChoreToComplete(String choreName) {
        this.choreToComplete = choreName;
    }

    public String getChoreToComplete() {
        return choreToComplete;
    }

    public PromptField getPromptField() {
        return promptField;
    }

    public JTextField getInputField() {
        return inputArea;
    }

    public void setInteractionButton(InteractionButton b) {
        this.interactionButton = b;
    }

    public JPanel getMenuArea() {
        return menuArea;
    }










}




