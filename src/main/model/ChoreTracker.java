package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//A ChoreTracker helps a household assign chores randomly to people and
public class ChoreTracker implements Writable {
    private final List<Chore> listOfChoresToComplete;
    private final List<Person>  listOfMembers;

    //EFFECTS initializes list of chores to complete, list of members, the scanner, and launches the application
    //in the console
    public ChoreTracker() {
        listOfChoresToComplete = new ArrayList<>();
        listOfMembers = new ArrayList<>();
    }



    //EFFECTS returns list of people who have not completed all their chores to the console
    public String getMembersWithUncompletedChores() {
        String output = "";
        for (int i = 0; i < listOfMembers.size(); i++) {
            if (listOfMembers.get(i).getChoreSum() > 0) {
                output += listOfMembers.get(i).getName() + "\n";
            }
        }
        return output;
    }

    //MODIFIES this
    //EFFECTS adds a specified chore to the list of chores to complete. If difficulty is invalid, does nothing
    public void addChoreWithDifficulty(String choreName, int difficulty) {

        switch (difficulty) {
            case 0:
                this.addChoreToComplete(new Chore(choreName, Chore.Difficulty.EASY));
                break;
            case 1:
                this.addChoreToComplete(new Chore(choreName, Chore.Difficulty.MEDIUM));
                break;
            case 2:
                this.addChoreToComplete(new Chore(choreName, Chore.Difficulty.HARD));
                break;
        }
    }

    //MODIFIES this, Person, Chore
    //EFFECTS completes chores of people specified by user. If chore or user isn't found, does nothing
    public void completeChoreOption(String person, String choreToComplete) {

        for (int i = 0; i < listOfMembers.size(); i++) {
            if (listOfMembers.get(i).getName().equals(person)) {
                listOfMembers.get(i).completeChore(choreToComplete);
                break;
            }
        }
    }

    //REQUIRES name of person added is not already in list
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

    //EFFECTS returns a copy of the list of members of the household
    public ArrayList<Person> getListOfMembers() {
        return new ArrayList<>(listOfMembers);
    }

    //EFFECTS returns a copy of the list of Chores to complete in the household
    public ArrayList<Chore> getListOfChoresToComplete() {
        return new ArrayList<>(listOfChoresToComplete);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listOfChoresToComplete", listOfChoresToJason());
        json.put("listOfMembers", listOfMembersToJason());
        return json;
    }

    // EFFECTS: returns chores in this workroom as a JSON array
    private JSONArray listOfChoresToJason() {
        JSONArray jsonArray = new JSONArray();

        for (Chore c : listOfChoresToComplete) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns chores in this workroom as a JSON array
    private JSONArray listOfMembersToJason() {
        JSONArray jsonArray = new JSONArray();

        for (Person p : listOfMembers) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
