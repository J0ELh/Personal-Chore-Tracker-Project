package persistance;


import model.Chore;
import model.ChoreTracker;
import model.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
//NOTE: ALL OF THIS CODE IS INSPIRED BY THE CODE PROVIDED BY JsonSerializationDemo provided on edx from our UBC
//CPSC 210 course team.
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ChoreTracker ct = new ChoreTracker();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyChoreTracker() {
        try {
            ChoreTracker ct = new ChoreTracker();
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyChoreTracker.json");
            writer.open();
            writer.write(ct);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyChoreTracker.json");
            ct = reader.read();
            assertEquals(0, ct.getListOfMembers().size());
            assertEquals(0,ct.getListOfChoresToComplete().size());
            assertEquals("",ct.getMembersWithUncompletedChores());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralChoreTracker() {
        try {
            ChoreTracker ct = new ChoreTracker();
            Person a = new Person("Joel");
            Person b = new Person("Mr. M");
            Person c = new Person("Jonny");
            Person d =new Person("Mr. JoinsHouseholdLate");
            ct.addPerson(a);
            ct.addPerson(b);
            ct.addPerson(c);
            ct.addChoreToComplete(new Chore("Chore with difficulty easy", Chore.Difficulty.EASY));
            ct.addChoreToComplete(new Chore("Chore with difficulty medium", Chore.Difficulty.MEDIUM));
            ct.addChoreToComplete(new Chore("Chore with difficulty hard", Chore.Difficulty.HARD));
            ct.assignChoresRandomly();
            ct.addPerson(d);
            ct.addChoreToComplete(new Chore("Chore with difficulty easy unassigned", Chore.Difficulty.EASY));
            a.assignChore(new Chore("CompletedChore", Chore.Difficulty.MEDIUM));
            a.completeChore("CompletedChore");

            JsonWriter writer = new JsonWriter("./data/testReaderGeneralChoreTracker.json");
            writer.open();
            writer.write(ct);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralChoreTracker.json");
            ct = reader.read();
            assertEquals(4, ct.getListOfMembers().size());
            assertEquals(1, ct.getListOfChoresToComplete().size());
            int totalChoreSum = 0;
            ArrayList<Person> tmpList = ct.getListOfMembers();
            //check that the people on the list are all the same
            for (int i = 0; i < tmpList.size(); i++) {
                totalChoreSum += tmpList.get(i).getChoreSum();
                if (tmpList.get(i).getName().equals(a.getName())) {
                    checkChoresPerPerson(a, tmpList.get(i));
                }
                if (tmpList.get(i).getName().equals(b.getName())) {
                    checkChoresPerPerson(b, tmpList.get(i));
                }
            }
            assertEquals(6, totalChoreSum);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}