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
    public void testCompleteChore(){
        p.assignChore(new Chore("Vacuum", Chore.Difficulty.HARD));
        p.completeChore("Vacuum");
        assertEquals(0, p.getChoreSum());
    }
}
