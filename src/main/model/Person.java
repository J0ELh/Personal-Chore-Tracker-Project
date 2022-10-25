package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.List;

//A person is a member of a household and can execute and complete Chores.
public class Person implements Writable {
    private final String name;
    private List<Chore> assignedChores;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("choresAssigned", assignedChoresToJason());
        json.put("choreSum", choreSum);

        return json;
    }

    private JSONArray assignedChoresToJason() {
        JSONArray jsonArray = new JSONArray();

        for (Chore c : assignedChores) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    //REQUIRES choreSum >= 0 and choreSum is the choreSum saved in JSON
    //EFFECTS sets choreSum to value
    public void setChoreSum(int choreSum) {
        this.choreSum = choreSum;
    }

    //REQUIRES choreList is the arraylist saved in JSON
    //EFFECTS sets the list of chores the person has had assigned to them
    public void setChoreList(ArrayList<Chore> choreList) {
        this.assignedChores = choreList;
    }
}
