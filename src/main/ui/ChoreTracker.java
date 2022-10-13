package ui;

import model.Chore;
import model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


//A ChoreTracker helps a household assign chores randomly to people and
public class ChoreTracker {
    private final List<Chore> listOfChoresToComplete;
    private final List<Person>  listOfMembers;
    private final Scanner input;

    //EFFECTS initializes list of chores to complete, list of members, the scanner, and launches the application
    //in the console
    public ChoreTracker() {
        listOfChoresToComplete = new ArrayList<>();
        listOfMembers = new ArrayList<>();
        input = new Scanner(System.in);
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
            if (userChoice == 6) {
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
        System.out.println("6: Exit");
        System.out.println("---------------------------------------------------------------------------------------");
    }

    //REQUIRES input within the options (1, 2, 3, 4, 5)
    //MODIFIES this, Chore, Person
    //EFFECTS executes menu choices
    private void processCommand(int choice) {

        if (choice == 1) {
            //add member
            System.out.print("Who would you like to add? ");
            this.addPerson(new Person(input.next()));
        } else if (choice == 2) {
            //add chore
            addChoreOption();
        } else if (choice == 3) {
            completeChoreOption();
            //complete a chore
        } else if (choice == 4) {
            viewMembersWithUncompletedChores();
            //view members who haven't completed their chores
        } else if (choice == 5) {
            this.assignChoresRandomly();
            //randomly assign chores
        }
        System.out.println("Done! \n\n\n");
    }

    //EFFECTS prints list of people who have not completed all their chores to the console
    private void viewMembersWithUncompletedChores() {
        System.out.println("List of people who haven't completed all their chores");
        for (int i = 0; i < listOfMembers.size(); i++) {
            if (listOfMembers.get(i).getChoreSum() > 0) {
                System.out.println(listOfMembers.get(i).getName());
            }
        }
    }

    //MODIFIES this
    //EFFECTS adds a specified chore to the list of chores to complete
    private void addChoreOption() {
        System.out.print("Please enter the name of the chore: ");
        String choreName = input.nextLine();
        System.out.print("Please enter the difficulty: (0 = easy, 1 = medium, 2 = hard)");
        int difficulty = input.nextInt();
        input.nextLine();
        switch (difficulty) {
            case 0:
                this.addChoreToComplete(new Chore(choreName, Chore.Difficulty.EASY));
                break;
            case 1:
                this.addChoreToComplete(new Chore(choreName, Chore.Difficulty.MEDIUM));
                break;
            default:
                this.addChoreToComplete(new Chore(choreName, Chore.Difficulty.HARD));
                break;
        }
    }

    //MODIFIES this, Person, Chore
    //EFFECTS completes chores of people specified by user
    private void completeChoreOption() {
        System.out.println("Given the following, please select a person: ");
        for (int i = 0; i < listOfMembers.size(); i++) {
            System.out.println(listOfMembers.get(i).listChores());
        }
        String person = input.nextLine();
        System.out.println("Which chore do you want to complete?");
        String choreToComplete = input.nextLine();
        for (int i = 0; i < listOfMembers.size(); i++) {
            if (listOfMembers.get(i).getName().equals(person)) {
                listOfMembers.get(i).completeChore(choreToComplete);
                break;
            }
        }
    }

    //MODIFIES this
    //adds a new person to the list of members of the household
    public void addPerson(Person p) {
        listOfMembers.add(p);
    }

    //MODIFIES this
    //adds a new Chore to the list of chores to complete
    public void addChoreToComplete(Chore c) {
        listOfChoresToComplete.add(c);
    }


    //MODIFIES this, Person, Chore
    //EFFECTS removes all chores to complete and instead assigns all those
    //chores to the people in the household. The chores are on average distributed
    //randomly but in the short-term some people may receive more chores than others
    @SuppressWarnings("methodlength")
    public void assignChoresRandomly() {
        //randomize people in arraylist to prevent later people from getting fewer tasks
        //and to make sure people get different chores
        Collections.shuffle(listOfMembers);
        int upperBound = findUpperBound();
        //sort tasks from smallest to biggest size so that they can be distributed easily
        //loop through people and each time you pass through assign task randomly as long as their number of tasks
        //plus the current task is not > average
        Collections.sort(listOfChoresToComplete);
        while (!listOfChoresToComplete.isEmpty()) {
            int memberIndex = -1;
            int choreIndex = -1;
            while (memberIndex + 1 < listOfMembers.size() && choreIndex < listOfChoresToComplete.size()) {
                if (listOfChoresToComplete.isEmpty()) {
                    break;
                }
                memberIndex++;
                choreIndex++;
                Chore tempChore = listOfChoresToComplete.get(choreIndex);
                if (listOfMembers.get(memberIndex).getChoreSum() + tempChore.getDifficultyByInt()  <= upperBound) {
                    listOfMembers.get(memberIndex).assignChore(tempChore);
                    listOfChoresToComplete.remove(choreIndex);
                    choreIndex--; //lower i since we removed one element
                } else { //to avoid infinite loop, dump chores on remaining people
                    for (int j = 0; j < listOfMembers.size(); j++, choreIndex++) {
                        if (listOfChoresToComplete.isEmpty()) {
                            break;
                        }
                        listOfMembers.get(j).assignChore(listOfChoresToComplete.get(choreIndex));
                        listOfChoresToComplete.remove(choreIndex);
                        choreIndex--;
                    }
                }
            }
        }


    }

    //EFFECTS finds the upper bound that decides approximately what number and difficulty of tasks each person receives
    private int findUpperBound() {
        //add up task difficulties of new chores
        double totalNumberOfChorePoints = 0.0;
        for (int i = 0; i < listOfChoresToComplete.size(); i++) {
            totalNumberOfChorePoints += listOfChoresToComplete.get(i).getDifficultyByInt();
        }
        //add up task difficulties of old chores
        for (int i = 0; i < listOfMembers.size(); i++) {
            totalNumberOfChorePoints += listOfMembers.get(i).getChoreSum();
        }
        //divide number of task points by number of people
        //set average as inclusive max number of task points a person can get
        return (int)Math.ceil(totalNumberOfChorePoints / listOfMembers.size());
    }


}
