package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChoreTest {
    Chore c;

    @BeforeEach
    public void setup() {
        c = new Chore("Vacuum", Chore.Difficulty.MEDIUM);
    }

    @Test
    public void testConstructor() {
        assertEquals("Vacuum", c.getChoreName());
        assertEquals(Chore.Difficulty.MEDIUM, c.getDifficulty());
    }

    @Test
    public void testCompleteChore(){
        assertFalse(c.isCompleted());
        c.completeChore();
        assertTrue(c.isCompleted());
    }

    @Test
    public void testCompareToPositive(){
        Chore c1 = new Chore("test", Chore.Difficulty.EASY);
        assertEquals(1, c.compareTo(c1));
        Chore c2 = new Chore("hard chore", Chore.Difficulty.HARD);
        assertEquals(2, c2.compareTo(c1));
    }

    @Test
    public void testCompareToEqual(){
        assertEquals(0, c.compareTo(c));
    }

    @Test
    public void testCompareToNegative(){
        Chore c1 = new Chore("test", Chore.Difficulty.EASY);
        assertEquals(-1, c1.compareTo(c));
        Chore c2 = new Chore("hard chore", Chore.Difficulty.HARD);
        assertEquals(-2, c1.compareTo(c2));
    }

    @Test
    public void testGetDifficultyByInt(){
        assertEquals(2, c.getDifficultyByInt());
        Chore c2 = new Chore("easy difficulty", Chore.Difficulty.EASY);
        assertEquals(1, c2.getDifficultyByInt());
        Chore c3 = new Chore("hard difficulty", Chore.Difficulty.HARD);
        assertEquals(3, c3.getDifficultyByInt());
    }

    @Test
    public void testGetChoreName(){
        assertEquals("Vacuum", c.getChoreName());
    }

    @Test
    public void testToString(){
        assertEquals("Vacuum MEDIUM", c.toString());
    }



}