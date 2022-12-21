package persistance;



import model.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;

//NOTE: ALL OF THIS CODE IS INSPIRED BY THE CODE PROVIDED BY JsonSerializationDemo provided on edx from our UBC
//CPSC 210 course team.

public class JsonTest {
    protected void checkChoresPerPerson(Person person, Person jsonPerson) {
        assertEquals(person.getChoreSum(), jsonPerson.getChoreSum());
        assertEquals(person.listChores(), jsonPerson.listChores());
    }
}
