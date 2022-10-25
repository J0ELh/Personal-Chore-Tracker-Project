package ui;

import model.ChoreTracker;
import model.Person;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Runs the user interface for the chore tracker
public class ChoreTrackerMenu {
    private static final String JSON_STORE = "./data/ChoreTracker.txt";
    private ChoreTracker ct;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS initializes scanner and ChoreTracker objects, JSON reader & writer, and launches console-based program
    public ChoreTrackerMenu() {
        ct = new ChoreTracker();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runChoreTracker();
    }

    //REQUIRES user input from 1, 2, 3, 4, 5, or 6
    //MODIFIES this, Person, Chore
    //EFFECTS runs chore tracker in application
    private void runChoreTracker() {

        Boolean exit = false;
        int userChoice;
        while (!exit) {
            //run the console interface
            displayMenu();
            userChoice = input.nextInt();
            input.nextLine();
            if (userChoice == 8) {
                input.close();
                exit = true;
            } else {
                processCommand(userChoice);
            }
        }

        System.out.println("\n\n\nExiting Application...");

    }

    //EFFECTS displays menu to console
    private void displayMenu() {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Please choose from one of the following options:\n (Enter an integer between 1 and 6)\n");
        System.out.println("1: Add Member");
        System.out.println("2: Add Chore");
        System.out.println("3: Complete Chore");
        System.out.println("4: View Members Who Haven't Completed Their Assigned Chores");
        System.out.println("5: Randomly Assign Chores");
        System.out.println("6: Save current state of chore tracker to file");
        System.out.println("7: Load previous state of chore tracker");
        System.out.println("8: Exit");
        System.out.println("---------------------------------------------------------------------------------------");
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
        }
        System.out.println("Done! \n\n\n");
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
        System.out.println("List of people who haven't completed all their chores");
        System.out.println(ct.getMembersWithUncompletedChores());
    }

    //MODIFIES ChoreTracker, Person, Chore
    //EFFECTS allows user to make chore as completed through console
    private void completeChore() {
        System.out.println("Given the following, please select a person: ");
        for (int i = 0; i < ct.getListOfMembers().size(); i++) {
            System.out.println(ct.getListOfMembers().get(i).listChores());
        }
        String person = input.nextLine();
        System.out.println("Which chore do you want to complete?");
        String choreToComplete = input.nextLine();
        ct.completeChoreOption(person, choreToComplete);
        System.out.println("\n" + person + " completed " + choreToComplete + " successfully");
    }

    //MODIFIES ChoreTracker
    //EFFECTS adds member to choreTracker by console input
    private void addMember() {
        System.out.print("Who would you like to add? ");
        ct.addPerson(new Person(input.next()));
        System.out.println("\nMember added successfully.");
    }

    //MODIFIES ChoreTracker
    //EFFECTS allows to add new chore through console
    private void addNewChore() {
        System.out.print("Please enter the name of the chore: ");
        String choreName = input.nextLine();
        System.out.print("Please enter the difficulty: (0 = easy, 1 = medium, 2 = hard)");
        int difficulty = input.nextInt();
        input.nextLine();
        ct.addChoreWithDifficulty(choreName, difficulty);
    }

}
