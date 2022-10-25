package persistance;

import model.ChoreTracker;

import model.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
//NOTE: ALL OF THIS CODE IS STRONGLY BASED ON THE CODE PROVIDED BY JsonSerializationDemo provided on edx from our UBC
//CPSC 210 course team.
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ChoreTracker ct = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyChoreTracker() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyChoreTracker.json");
        try {
            ChoreTracker ct = reader.read();
            assertEquals(0, ct.getListOfChoresToComplete().size());
            assertEquals(0, ct.getListOfMembers().size());
            assertEquals("", ct.getMembersWithUncompletedChores());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralChoreTracker() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralChoreTracker.json");
        Person a = new Person("Joel");
        Person b = new Person("Mr. M");
        Person c = new Person("Jonny");
        Person d =new Person("Mr. JoinsHouseholdLate");
        try {

            ChoreTracker ct = reader.read();
            assertEquals(4, ct.getListOfMembers().size());
            assertEquals(1, ct.getListOfChoresToComplete().size());
            int totalChoreSum = 0;
            ArrayList<Person> tmpList = ct.getListOfMembers();
            for (int i = 0; i < tmpList.size(); i++) {
                totalChoreSum += tmpList.get(i).getChoreSum();
            }
            assertEquals(6, totalChoreSum);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}