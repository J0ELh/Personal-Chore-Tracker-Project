package model;

import java.util.ArrayList;
import java.util.List;

//A person is a member of a household and can execute and complete Chores.
public class Person {
    private final String name;
    private final List<Chore> assignedChores;
    private int choreSum;

    //EFFECTS initializes Person object with name "name," initializes arrayList and sets the sum of chore points to 0
    public Person(String name) {
        this.name = name;
        assignedChores = new ArrayList<>();
        choreSum = 0;
    }

    //getters and setters
    public String getName() {
        return this.name;
    }

    //EFFECTS returns the sum of chore difficulty points of the chores that still need to be completed by the person
    public int getChoreSum() {
        return choreSum;
    }

    //MODIFIES this
    //EFFECTS assigns chore to this person and increases their total difficulty sum
    public void assignChore(Chore c) {
        assignedChores.add(c);
        choreSum += c.getDifficultyByInt();
    }


    //REQUIRES choreName of a chore that matches at least one of the names in assignedChores
    //MODIFIES this
    //EFFECTS deducts int value of difficulty from choreSum and marks chore as completed
    public void completeChore(String choreName) {
        for (int i = 0; i < assignedChores.size(); i++) {
            if (assignedChores.get(i).getChoreName().equals(choreName) && !assignedChores.get(i).isCompleted()) {
                assignedChores.get(i).completeChore();
                choreSum -= assignedChores.get(i).getDifficultyByInt();
                break;
            }
        }
    }

    //EFFECTS prints a list of chores this person still needs to complete
    public String listChores() {
        //use string builder
        StringBuilder output = new StringBuilder();
        output.append("Person " + this.getName() + ":\nChores to complete:\n");
        for (int i = 0; i < assignedChores.size(); i++) {
            if (!assignedChores.get(i).isCompleted()) {
                output.append(assignedChores.get(i) + "\n");
            }
        }
        output.append("\n");
        return output.toString();
    }

}
