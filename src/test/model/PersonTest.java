package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersonTest {
    Person p;

    @BeforeEach
    public void setup(){
       p = new Person("Peter");
    }

    @Test
    public void testConstructor(){
        assertEquals("Peter", p.getName());
        assertEquals(0, p.getChoreSum());
    }

    @Test
    public void testAssignChore(){
        p.assignChore(new Chore("Vacuum", Chore.Difficulty.HARD));
        assertEquals(3, p.getChoreSum());
    }


    @Test
    public void testCompleteChoreNoChore(){
        p.completeChore("test");
        assertEquals(0, p.getChoreSum());
    }

    @Test
    public void testCompleteChoreNameNotEquals(){
        p.assignChore(new Chore("notVacuum", Chore.Difficulty.HARD));
        p.completeChore("Vacuum");
        assertEquals(3, p.getChoreSum());
    }

    @Test
    public void testCompleteChoreOneNotCompleted(){
        p.assignChore(new Chore("Vacuum", Chore.Difficulty.HARD));
        p.completeChore("Vacuum");
        assertEquals(0, p.getChoreSum());
    }

    @Test
    public void testCompletedChoreAlreadyCompleted(){
        p.assignChore(new Chore("Vacuum", Chore.Difficulty.HARD));
        p.completeChore("Vacuum");
        p.completeChore("Vacuum");
        assertEquals(0, p.getChoreSum());
    }

    @Test
    public void testListChoresNoChores(){
        assertEquals("Person Peter:\n" + "Chores to complete:\n\n", p.listChores());
    }
    @Test
    public void testListChoresOneChore(){
        p.assignChore(new Chore("Vacuum", Chore.Difficulty.HARD));
        assertEquals("Person Peter:\n" +
                "Chores to complete:\n" +
                "Vacuum HARD\n\n", p.listChores());
    }

    @Test
    public void testListChoresCompletedChore(){
        p.assignChore(new Chore("Vacuum", Chore.Difficulty.HARD));
        p.completeChore("Vacuum");
        assertEquals("Person Peter:\n" +
                "Chores to complete:\n\n", p.listChores());
    }

}
