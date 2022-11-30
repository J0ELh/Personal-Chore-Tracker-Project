package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventLogExecutionTest {
    ChoreTracker c;

    @BeforeEach
    public void setup() {
        c = new ChoreTracker();
    }

    @Test
    public void addPersonTest(){
        c.addPerson(new Person("Peter"));
        assertTrue(c.finishExecuting().contains("Person: Peter added to chore tracker"));
    }

    @Test
    public void addChoreTest(){
        c.addChoreToComplete(new Chore("chore1", Chore.Difficulty.HARD));
        assertTrue(c.finishExecuting().contains("HARD chore (chore1) added to chore tracker"));
    }

    @Test
    public void addChoreWithDifficultyTestHard(){
        c.addChoreWithDifficulty("chore1", 2);
        assertTrue(c.finishExecuting().contains("HARD chore (chore1) added to chore tracker"));
    }

    @Test
    public void addChoreWithDifficultyTestMedium(){
        c.addChoreWithDifficulty("chore1", 1);
        assertTrue(c.finishExecuting().contains("MEDIUM chore (chore1) added to chore tracker"));
    }

    @Test
    public void addChoreWithDifficultyTestEasy(){
        c.addChoreWithDifficulty("chore1", 0);
        assertTrue(c.finishExecuting().contains("EASY chore (chore1) added to chore tracker"));
    }

    @Test
    public void assignChoresRandomlyTest(){
        c.addChoreWithDifficulty("chore1", 0);
        c.addPerson(new Person("p"));
        c.assignChoresRandomly();
        String s = c.finishExecuting();
        assertTrue(s.contains("Outstanding chores randomly assigned to different people"));
        assertTrue(s.contains("chore1 assigned to p"));
    }
}
