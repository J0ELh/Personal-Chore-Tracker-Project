package persistance;

import org.json.JSONObject;
//NOTE: ALL OF THIS CODE IS INSPIRED BY THE CODE PROVIDED BY JsonSerializationDemo provided on edx from our UBC
//CPSC 210 course team.

//Object that is writable to JSON
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
