package model;

//A chore is a task that a Person from the household has to execute and complete. It has a difficulty rating included.
public class Chore implements Comparable<Chore> {


    public enum Difficulty  { EASY, MEDIUM, HARD }

    private final Difficulty difficulty;
    private final String taskName;
    private Boolean isCompleted = false;

    //REQUIRES 0 <= difficulty 2
    public Chore(String name, Difficulty difficulty) {
        this.taskName = name;
        this.difficulty = difficulty;
    }


    //MODIFIES this
    //EFFECTS completes chore
    public void completeChore() {
        isCompleted = true;
    }


    //REQUIRES Object c is a Chore
    //EFFECTS returns comparison based on standard collection compareTo method
    @Override
    public int compareTo(Chore c) {
        return this.getDifficultyByInt() - c.getDifficultyByInt();
    }



    //getters and setters
    public String getChoreName() {
        return this.taskName;
    }

    public Boolean isCompleted() {
        return this.isCompleted;
    }

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
            case HARD:
                return 3;
        }

        return -1;
    }

    @Override
    public String toString() {
        return this.getChoreName() + " " + this.getDifficulty();
    }




}
