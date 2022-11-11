package ui;

import model.ChoreTracker;
import model.Person;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.jar.Attributes;

//Runs the user interface for the chore tracker
public class ChoreTrackerMenu extends JFrame implements ActionListener {
    private JFrame root;
    private static final String JSON_STORE = "./data/ChoreTracker.json";
    private ChoreTracker ct;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


//    public static final int WIDTH = 2560;
//    public static final int HEIGHT = 1440;
    public static final int WIDTH = 2560 / 5;
    public static final int HEIGHT = 1440 / 5;
    private JPanel screenArea;
    private JPanel leftPlaceHolder;
    private JPanel rightPlaceHolder;
    private JPanel menuArea;
    private JPanel splashScreen;
    private JTextArea splashText;

    private ArrayList<MenuButton> listOfMenuButtons = new ArrayList<MenuButton>();
    private MenuButton addMemberButton;
    private MenuButton addChoreButton;
    private MenuButton completeChoreButton;
    private MenuButton assignRandomlyButton;
    private MenuButton loadPreviousStateButton;
    private MenuButton viewUncompletedButton;
    private MenuButton saveCurrentStateButton;
    private MenuButton exitButton;

    private JTextField inputArea;
    private JTextArea promptField;
    private JButton interactionButton;

    private String currentChoreName;
    private int currentDifficulty;
    private String personToComplete;
    private String choreToComplete;

    //EFFECTS initializes scanner and ChoreTracker objects, JSON reader & writer, and launches console-based program
    public ChoreTrackerMenu() {
        super("Chore Tracker");

        ct = new ChoreTracker();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        root = new JFrame("Chore Tracker");
        root.setVisible(true);
        setupGraphics();
//        runChoreTracker();
    }

    //MODIFIES this
    //EFFECTS draws a JFrame window for the ChoreTracker in which the whole application will be run
    // and sets up SPLASH SCREEN and menu
    //This method has been inspired by the SimpleDrawingEditor from our 210 course
    private void setupGraphics() {
        root.setLayout(new BorderLayout());
        root.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        showSplashScreen();
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setLocationRelativeTo(null);
        root.setVisible(true);
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(250);
                splashText.setText(splashText.getText() + ".");
            }
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        root.getContentPane().removeAll();
        setupScreenArea();
        setupInteractionFields();
        root.revalidate();
        root.repaint();

    }

    private void setupInteractionFields() {
        promptField = new JTextArea("Prompt");
        promptField.setEditable(false);
        inputArea = new JTextField("Get input");
        interactionButton = new JButton("Do task");

    }

    private void showSplashScreen() {
        splashScreen = new JPanel(new GridLayout(1, 1));
        splashText = new JTextArea("Setting Up the Chore Tracker");
        splashText.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        splashScreen.add(splashText);
        root.add(splashScreen);

    }

    private void setupScreenArea() {
        screenArea = new JPanel(new GridLayout(1, 2));
        drawMenu();
        leftPlaceHolder = menuArea;
        rightPlaceHolder = new JPanel();
//        screenArea.setSize(new Dimension(5, 5));
        screenArea.add(leftPlaceHolder);
        screenArea.add(rightPlaceHolder);
        screenArea.setVisible(true);
        root.setVisible(true);
        root.add(screenArea);
    }

    private void setScreenArea(Component a, Component b) {
        root.getContentPane().removeAll();
        screenArea.removeAll();

        if (a != null) {
            screenArea.add(a);
        } else {
            screenArea.add(leftPlaceHolder);
        }

        if (b != null) {
            screenArea.add(b);
        } else {
            screenArea.add(rightPlaceHolder);
        }
        screenArea.setVisible(true);
        root.add(screenArea);
        root.setVisible(true);
    }

    //MODIFIES this
    //Draws a menu on the screen of the JPanel
    private void drawMenu() {
        menuArea = new JPanel();
        menuArea.setLayout(new GridLayout(0,1));
        menuArea.setSize(new Dimension(10, 10));

        add(menuArea, BorderLayout.WEST);

        createMenuButtons();

        //STILL NEED TO ADD HEADER TEXT BOX
        addMemberButton.getButton().setActionCommand(addMemberButton.getName());
        addMemberButton.getButton().addActionListener(this);
//        addMemberButton.setMargin(new Insets(50, 5, 5, 5));


        addChoreButton.getButton().setActionCommand(addChoreButton.getName());
        addChoreButton.getButton().addActionListener(this);
//        addChoreButton.setMargin(new Insets(1, 1, 1, 1));

        completeChoreButton.getButton().setActionCommand(completeChoreButton.getName());
        completeChoreButton.getButton().addActionListener(this);
//        completeChoreButton.setMargin(new Insets(1, 1, 1, 1));

        viewUncompletedButton.getButton().setActionCommand(viewUncompletedButton.getName());
        viewUncompletedButton.getButton().addActionListener(this);
//        viewUncompletedButton.setMargin(new Insets(1, 1, 1, 1));

        assignRandomlyButton.getButton().setActionCommand(assignRandomlyButton.getName());
        assignRandomlyButton.getButton().addActionListener(this);
//        assignRandomlyButton.setMargin(new Insets(1, 1, 1, 1));

        saveCurrentStateButton.getButton().setActionCommand(saveCurrentStateButton.getName());
        saveCurrentStateButton.getButton().addActionListener(this);
//        saveCurrentStateButton.setMargin(new Insets(1, 1, 1, 1));

        loadPreviousStateButton.getButton().setActionCommand(loadPreviousStateButton.getName());
        loadPreviousStateButton.getButton().addActionListener(this);
//        loadPreviousStateButton.setMargin(new Insets(1, 1, 1, 1));

        exitButton.getButton().setActionCommand(exitButton.getName());
        exitButton.getButton().addActionListener(this);
//        exitButton.setMargin(new Insets(1, 1, 1, 1));


    }

    private void createMenuButtons() {
        addMemberButton = new MenuButton("Add Member", menuArea);
        addChoreButton = new MenuButton("Add Chore", menuArea);
        completeChoreButton = new MenuButton("Complete Chore", menuArea);
        viewUncompletedButton = new MenuButton("View Members with Uncompleted Chores", menuArea);
        assignRandomlyButton = new MenuButton("Randomly Assign Chores", menuArea);
        saveCurrentStateButton = new MenuButton("Save Current Chore Tracker State", menuArea);
        loadPreviousStateButton = new MenuButton("Load Previous Chore Tracker State", menuArea);
        exitButton = new MenuButton("Exit", menuArea);
        listOfMenuButtons.add(addMemberButton);
        listOfMenuButtons.add(addChoreButton);
        listOfMenuButtons.add(viewUncompletedButton);
        listOfMenuButtons.add(assignRandomlyButton);
        listOfMenuButtons.add(saveCurrentStateButton);
        listOfMenuButtons.add(loadPreviousStateButton);
        listOfMenuButtons.add(exitButton);
    }





    //REQUIRES input within the options (1, 2, 3, 4, 5)
    //MODIFIES this, Chore, Person
    //EFFECTS executes menu choices
    private void processCommand(int choice) {

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
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads ChoreTracker from file
    private void loadState() {
        try {
            ct = jsonReader.read();
            System.out.println("Loaded chore tracker from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the ChoreTracker to file
    private void saveState() {
        try {
            jsonWriter.open();
            jsonWriter.write(ct);
            jsonWriter.close();
            System.out.println("Saved chore tracker to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES ChoreTracker
    //EFFECTS assigns chores to complete semi-randomly to members
    public void randomlyAssignChores() {
        ct.assignChoresRandomly();
        System.out.println("Chores randomly assigned!");
    }


    //EFFECTS prints a list of members who haven't completed all their chores to the console
    private void viewMembersWithUncompletedChores() {

        JPanel addMemberPanel = new JPanel(new GridLayout(0, 1));
        promptField.setText("List of people who haven't completed all their chores\n\n");
        promptField.setText(promptField.getText() + ct.getMembersWithUncompletedChores());
        System.out.println(ct.getMembersWithUncompletedChores());
        ct.getListOfMembers().get(0).listChores();
        addMemberPanel.add(promptField);

        addMemberPanel.setVisible(true);
        toggleMenuClickable();
        setScreenArea(menuArea, addMemberPanel);
    }

    //MODIFIES ChoreTracker, Person, Chore
    //EFFECTS allows user to make chore as completed through console
    private void completeChore() {
//        System.out.println("Given the following, please select a person: ");
//        for (int i = 0; i < ct.getListOfMembers().size(); i++) {
//            System.out.println(ct.getListOfMembers().get(i).listChores());
//        }
//        String person = input.nextLine();
//        System.out.println("Which chore do you want to complete?");
//        String choreToComplete = input.nextLine();
//        ct.completeChoreOption(person, choreToComplete);
//        System.out.println("\n" + person + " completed " + choreToComplete + " successfully");

        JPanel completeChorePanel = new JPanel(new GridLayout(3, 1));
        promptField.setText("Give the following, please select a person: ");
        for (int i = 0; i < ct.getListOfMembers().size(); i++) {
            promptField.setText(promptField.getText() + "\n" + ct.getListOfMembers().get(i).listChores());
        }
        inputArea.setText("Enter Name");
        interactionButton = new JButton();
        interactionButton.setText("Enter");
        interactionButton.setActionCommand("completeChoreOfPerson");
        interactionButton.addActionListener(this);
        completeChorePanel.add(promptField);
        completeChorePanel.add(inputArea);
        completeChorePanel.add(interactionButton);
        completeChorePanel.setVisible(true);

        setScreenArea(menuArea, completeChorePanel);
    }

    private void selectChoreOfPerson() {
        JPanel completeChorePanel = new JPanel(new GridLayout(3, 1));
        promptField.setText("Which chore do you want to complete?\n\n");
        for (int i = 0; i < ct.getListOfMembers().size(); i++) {
            if (ct.getListOfMembers().get(i).getName().equals(personToComplete)) {
                ct.getListOfMembers().get(i).listChores();
                break;
            }
        }
        inputArea.setText("Enter Name of Chore");
        interactionButton = new JButton();
        interactionButton.setText("Complete");
        interactionButton.setActionCommand("completeChore");
        interactionButton.addActionListener(this);
        completeChorePanel.add(promptField);
        completeChorePanel.add(inputArea);
        completeChorePanel.add(interactionButton);
        completeChorePanel.setVisible(true);

        setScreenArea(menuArea, completeChorePanel);
    }

    //MODIFIES ChoreTracker
    //EFFECTS adds member to choreTracker by console input
    private void addMember() {
        JPanel addMemberPanel = new JPanel(new GridLayout(3, 1));
        promptField.setText("Who would you like to add? ");
        inputArea.setText("Enter name");
        interactionButton = new JButton();
        interactionButton.setText("Add");
        interactionButton.setActionCommand("addPerson");
        interactionButton.addActionListener(this);
        addMemberPanel.add(promptField);
        addMemberPanel.add(inputArea);
        addMemberPanel.add(interactionButton);
        addMemberPanel.setVisible(true);

        setScreenArea(menuArea, addMemberPanel);

    }

    //MODIFIES ChoreTracker
    //EFFECTS allows to add new chore through console
    private void addNewChore() {

        JPanel addChorePanel = new JPanel(new GridLayout(3, 1));
        promptField.setText("What chore would you like to add? ");
        inputArea.setText("Enter chore");
        interactionButton = new JButton();
        interactionButton.setText("Add");
        interactionButton.setActionCommand("addChore");
        interactionButton.addActionListener(this);
        addChorePanel.add(promptField);
        addChorePanel.add(inputArea);
        addChorePanel.add(interactionButton);
        addChorePanel.setVisible(true);

        setScreenArea(menuArea, addChorePanel);
    }

    private void setAddedChoreDifficulty() {
        JPanel addChoreDifficultyPanel = new JPanel(new GridLayout(3, 1));
        promptField.setText("Please enter the difficulty: (0 = easy, 1 = medium, 2 = hard) ");
        inputArea.setText("0");
        interactionButton = new JButton();
        interactionButton.setText("Set Difficulty");
        interactionButton.setActionCommand("setDifficultyOfAdded");
        interactionButton.addActionListener(this);
        addChoreDifficultyPanel.add(promptField);
        addChoreDifficultyPanel.add(inputArea);
        addChoreDifficultyPanel.add(interactionButton);
        addChoreDifficultyPanel.setVisible(true);

        setScreenArea(menuArea, addChoreDifficultyPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        toggleMenuClickable();

        if (e.getActionCommand().equals("1:  Add Member")) {;
            processCommand(1);
        } else if (e.getActionCommand().equals("2:  Add Chore")) {
            processCommand(2);
        } else if (e.getActionCommand().equals("3:  Complete Chore")) {
            processCommand(3);
        } else if (e.getActionCommand().equals("4:  View Members with Uncompleted Chores")) {
            processCommand(4);
        } else if (e.getActionCommand().equals("5:  Randomly Assign Chores")) {
            processCommand(5);
        } else if (e.getActionCommand().equals("6:  Save Current Chore Tracker State")) {
            processCommand(6);
        } else if (e.getActionCommand().equals("7:  Load Previous Chore Tracker State")) {
            processCommand(7);
        } else if (e.getActionCommand().equals("8:  Exit")) {
            processCommand(8);
        } else if (e.getActionCommand().equals("addPerson")) {
            ct.addPerson(new Person(inputArea.getText()));
            promptField.setText(inputArea.getText() + " added successfully.");
            toggleMenuClickable();
            return;
        } else if (e.getActionCommand().equals("addChore")) {
            currentChoreName = inputArea.getText();
            setAddedChoreDifficulty();
            return;
        } else if (e.getActionCommand().equals("setDifficultyOfAdded")) {
            currentDifficulty = Integer.parseInt(inputArea.getText());
            ct.addChoreWithDifficulty(currentChoreName, currentDifficulty);
            System.out.println("NAME OF CHORE" + currentChoreName);
            promptField.setText(currentChoreName + " added successfully.");
            System.out.println("NAME OF CHORE2" + currentChoreName);
            toggleMenuClickable();
            return;
        } else if (e.getActionCommand().equals(("completeChoreOfPerson"))) {
            personToComplete = inputArea.getText();
            selectChoreOfPerson();
            return;
        } else if (e.getActionCommand().equals(("completeChore"))) {
            choreToComplete = inputArea.getText();
            ct.completeChoreOption(personToComplete, choreToComplete);
            promptField.setText(personToComplete + " completed " + choreToComplete + " successfully.");
            toggleMenuClickable();
            return;
        }

    }

    private void toggleMenuClickable() {
        //NOT WORKING PROPERLY
        for (MenuButton button: listOfMenuButtons) {
            if (button.getName().equals("8:  Exit")) {
                break;
            }
            button.setEnabled(!button.isEnabled());
        }
    }


}
