package persistance;

import org.json.JSONObject;
//NOTE: ALL OF THIS CODE IS STRONGLY BASED ON THE CODE PROVIDED BY JsonSerializationDemo provided on edx from our UBC
//CPSC 210 course team.

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}