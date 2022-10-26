package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChoreTrackerTest {
    public ChoreTracker ct;

    @BeforeEach
    public void setup() {
        ct = new ChoreTracker();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, ct.getListOfMembers().size());
    }

    @Test
    public void testReturnMembersWithUncompletedChoresOnEmptyList() {
        assertEquals("", ct.getMembersWithUncompletedChores());
    }

    @Test
    public void testReturnMembersWithUncompletedChoresOneMember() {
        ct.addPerson(new Person("Jake"));
        ct.addChoreToComplete(new Chore("FDS", Chore.Difficulty.EASY));
        ct.assignChoresRandomly();
        assertEquals("Jake\n", ct.getMembersWithUncompletedChores());
        assertEquals(1, ct.getListOfMembers().size());
    }

    @Test
    public void testReturnMembersWithUncompletedChoresTwoMembers() {
        Person jake = new Person("Jake");
        ct.addPerson(jake);
        Person josh = new Person("Josh");
        ct.addPerson(josh);
        assertEquals(2, ct.getListOfMembers().size());
        assertTrue(ct.getListOfMembers().contains(josh));
        assertTrue(ct.getListOfMembers().contains(jake));

    }

    @Test
    public void testReturnMembersWithUncompletedChoresAfterAllChoresCompleted() {
        Person jake = new Person("Jake");
        ct.addPerson(jake);
        ct.addChoreToComplete(new Chore("Chore", Chore.Difficulty.HARD));
        ct.assignChoresRandomly();
        assertEquals("Jake\n", ct.getMembersWithUncompletedChores());
        jake.completeChore("Chore");
        assertEquals("", ct.getMembersWithUncompletedChores());

    }

    @Test
    public void testAddChoreWithDifficulty() {
        Person jake = new Person("Jake");
        ct.addPerson(jake);
        ct.addChoreWithDifficulty("testMediumChore", 1);
        ct.assignChoresRandomly();
        ct.addChoreWithDifficulty("testEasyChore", 0);
        ct.addChoreWithDifficulty("testHardChore", 2);
        assertEquals(3, jake.getChoreSum());
    }

    @Test
    public void testAddChoreWithDifficultyDontAddInvalidChore() {
        Person jake = new Person("Jake");
        ct.addPerson(jake);
        ct.addChoreWithDifficulty("eat", 324);
        ct.assignChoresRandomly();
        assertEquals(0, jake.getChoreSum());
        assertEquals(0, ct.getListOfChoresToComplete().size());
    }

    @Test
    public void testCompleteChoreOptionNoChoresToComplete() {
        ct.addChoreToComplete(new Chore("asfd", Chore.Difficulty.MEDIUM));
        ct.completeChoreOption("", "");
        assertEquals(1, ct.getListOfChoresToComplete().size());
    }

    @Test
    public void testCompleteChoreOptionWithChoresToComplete() {
        Person tmpPerson = new Person("Steve");
        ct.addPerson(tmpPerson);
        ct.addChoreToComplete(new Chore("asfd", Chore.Difficulty.MEDIUM));
        ct.assignChoresRandomly();
        ct.completeChoreOption(tmpPerson.getName(), "asfd");
        assertEquals(0, ct.getListOfChoresToComplete().size());
    }

    @Test
    public void testCompleteChoreOptionWithChoresToCompleteButWrongName() {
        Person tmpPerson = new Person("Steve");
        ct.addPerson(tmpPerson);
        ct.addChoreToComplete(new Chore("asfd", Chore.Difficulty.MEDIUM));
        ct.assignChoresRandomly();
        ct.completeChoreOption("IncorrectName", "asfd");
        assertEquals(0, ct.getListOfChoresToComplete().size());
    }

    @Test
    public void testAddPersonOne() {
        ct.addPerson(new Person("Joel"));
        assertEquals(1, ct.getListOfMembers().size());
    }

    @Test
    public void testAddPersonTwo() {
        ct.addPerson(new Person("Joel"));
        assertEquals(1, ct.getListOfMembers().size());
        ct.addPerson(new Person("Brian"));
        assertEquals(2, ct.getListOfMembers().size());
    }

    @Test
    public void testAddChoreToComplete() {
        Chore tmp = new Chore("afs", Chore.Difficulty.HARD);
        ct.addChoreToComplete(tmp);
        assertEquals(1, ct.getListOfChoresToComplete().size());
        assertEquals(tmp, ct.getListOfChoresToComplete().get(0));
    }

    @Test
    public void testAssignTwoChoresRandomlyToTwoPeople() {
        ct.addChoreToComplete(new Chore ("Chore 1", Chore.Difficulty.EASY));
        ct.addChoreToComplete(new Chore ("Chore 1", Chore.Difficulty.EASY));
        Person p1 = new Person("p1");
        Person p2 = new Person("p2");
        ct.addPerson(p1);
        ct.addPerson(p2);
        ct.assignChoresRandomly();
        assertEquals(1, p1.getChoreSum());
        assertEquals(1, p2.getChoreSum());
    }


    @Test
    public void testGetListOfMembers() {
        ct.addPerson(new Person("Joel"));
        ct.addPerson(new Person("asdf"));
        ct.addPerson(new Person("afdagd"));
        ct.addPerson(new Person("asdggas"));
        ct.addPerson(new Person("asd"));
        ct.addPerson(new Person("adgasdg"));
        ct.addPerson(new Person("agddsagd"));
        assertEquals(7, ct.getListOfMembers().size());
        assertEquals("asd", ct.getListOfMembers().get(4).getName());
    }

    @Test
    public void testGetListOfChoresToComplete() {
        Chore tmp = new Chore("fsdfs" ,Chore.Difficulty.HARD);
        ct.addChoreToComplete(new Chore("Do dishes", Chore.Difficulty.HARD));
        ct.addChoreToComplete(new Chore("Do dishes", Chore.Difficulty.HARD));
        ct.addChoreToComplete(new Chore("Do dishes", Chore.Difficulty.MEDIUM));
        ct.addChoreToComplete(new Chore("Do dishes", Chore.Difficulty.EASY));
        ct.addChoreToComplete(new Chore("Do dishes", Chore.Difficulty.HARD));
        ct.addChoreToComplete(tmp);
        ct.addChoreToComplete(new Chore("Do dishes", Chore.Difficulty.HARD));


        assertEquals(7, ct.getListOfChoresToComplete().size());
        assertEquals(tmp, ct.getListOfChoresToComplete().get(5));
    }

}
