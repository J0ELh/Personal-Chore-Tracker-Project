package persistance;

import model.Chore;
import model.ChoreTracker;
import model.Person;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

//NOTE: ALL OF THIS CODE IS INSPIRED BY THE CODE PROVIDED BY JsonSerializationDemo provided on edx from our UBC
//CPSC 210 course team.

// Represents a reader that reads the ChoreTracker from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ChoreTracker from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ChoreTracker read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseChoreTracker(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ChoreTracker from JSON object and returns it
    private ChoreTracker parseChoreTracker(JSONObject jsonObject) {
        ChoreTracker ct = new ChoreTracker();
        addPeopleAndChores(ct, jsonObject);
        return ct;
    }

    // MODIFIES: ct
    // EFFECTS: parses People and Chores from JSON object and adds them to ChoreTracker
    private void addPeopleAndChores(ChoreTracker ct, JSONObject jsonObject) {
        JSONArray jsonArrayChores = jsonObject.getJSONArray("listOfChoresToComplete");
        for (Object json : jsonArrayChores) {
            JSONObject nextChoreToComplete = (JSONObject) json;
            addChoresToComplete(ct, nextChoreToComplete);
        }

        JSONArray jsonArrayPeople = jsonObject.getJSONArray("listOfMembers");
        for (Object json : jsonArrayPeople) {
            JSONObject nextPerson = (JSONObject) json;
            addPeopleToTracker(ct, nextPerson);
        }
    }

    // MODIFIES: ct
    // EFFECTS: parses Chore from JSON object and adds it to ChoreTracker
    private void addChoresToComplete(ChoreTracker ct, JSONObject jsonObject) {

        Chore.Difficulty difficulty = Chore.Difficulty.valueOf(jsonObject.getString("difficulty"));
        String choreName = jsonObject.getString("taskName");
        Chore c = new Chore(choreName, difficulty);
        ct.addChoreToComplete(c);
    }

    // MODIFIES: ct
    // EFFECTS: parses Person from JSON object and adds it to ChoreTracker
    private void addPeopleToTracker(ChoreTracker ct, JSONObject jsonObject) {
        ArrayList<Chore> choreList = addChoresForIndividual(ct, jsonObject);

        String name = jsonObject.getString("name");
        int choreSum = jsonObject.getInt("choreSum");
        Person p = new Person(name);
        p.setChoreSum(choreSum);
        p.setChoreList(choreList);

        ct.addPerson(p);
    }

    //MODIFIES ChoreTracker, Chore
    //EFFECTS: parses from JSON object and parses it to Chore to add to person's assigned list of chores
    private ArrayList<Chore> addChoresForIndividual(ChoreTracker ct, JSONObject jsonObject) {
        JSONArray jsonArrayChores = jsonObject.getJSONArray("choresAssigned");
        ArrayList<Chore> tmpList = new ArrayList<>();

        for (Object json: jsonArrayChores) {
            JSONObject nextChore = (JSONObject) json;
            addChoresToCompleteForPerson(ct, nextChore, tmpList);
        }

        return tmpList;
    }

    //MODIFIES ChoreTracker, Chore
    //EFFECTS: parses from JSON object and parses it to Chore added to list of chores for each person
    private void addChoresToCompleteForPerson(ChoreTracker ct,JSONObject jsonObject, ArrayList<Chore> tmpList) {

        ArrayList<Chore> choreList = new ArrayList<>();

        Chore.Difficulty difficulty = Chore.Difficulty.valueOf(jsonObject.getString("difficulty"));
        String choreName = jsonObject.getString("taskName");
        Boolean isCompleted = jsonObject.getBoolean("isCompleted?");
        Chore c = new Chore(choreName, difficulty);
        if (isCompleted) {
            c.completeChore();
        }

        tmpList.add(c);
    }


}
