package model;

import org.json.JSONObject;
import persistance.Writable;

//A chore is a task that a Person from the household has to execute and complete. It has a difficulty rating included.
public class Chore implements Comparable<Chore>, Writable {


    //Difficulty describes the difficulty of a chore on a scale from easy, medium to hard
    public enum Difficulty  { EASY, MEDIUM, HARD }

    private final Difficulty difficulty;
    private final String taskName;
    private Boolean isCompleted = false;

    //test
    //REQUIRES 0 <= difficulty 2
    //EFFECTS initializes new Chore object with given difficulty rating and name
    public Chore(String name, Difficulty difficulty) {
        this.taskName = name;
        this.difficulty = difficulty;
    }


    //MODIFIES this
    //EFFECTS completes chore
    public void completeChore() {
        isCompleted = true;
    }


    //EFFECTS returns comparison based on standard collection compareTo method
    @Override
    public int compareTo(Chore c) {
        return this.getDifficultyByInt() - c.getDifficultyByInt();
    }



    //getters and setters
    public String getChoreName() {
        return this.taskName;
    }

    //EFFECTS returns whether the task has been completed
    public Boolean isCompleted() {
        return this.isCompleted;
    }

    //EFFECTS returns the difficulty of the Chore
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    //EFFECTS returns difficulty in terms of integers from 1-3 (EASY - HARD)
    public int getDifficultyByInt() {
        switch (difficulty) {
            case EASY:
                return 1;
            case MEDIUM:
                return 2;
            default:
                return 3;
        }
    }

    //EFFECTS returns the description of the chore and its difficulty
    @Override
    public String toString() {
        return this.getChoreName() + " " + this.getDifficulty();
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("taskName", taskName);
        json.put("difficulty", difficulty);
        json.put("isCompleted?", isCompleted);

        return json;
    }

}
