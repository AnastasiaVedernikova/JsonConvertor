package json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    HashMap<String, Json> hmap = new HashMap<String, Json>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair j: jsonPairs){
            hmap.put(j.key, j.value);
        }
    }

    @Override
    public String toJson() {

        System.out.println(hmap);
            String a = "{";
            Set<String> keys = hmap.keySet();
            int k = 0;
            int propertyKey = 0;
        for (String key : keys) {
            if (hmap.containsKey(key) && hmap.get(key) != null) {
                propertyKey+=1;
            }
        }

        for (String key : keys) {

                if (hmap.containsKey(key) && hmap.get(key) != null) {
                    String c = key + ": " + hmap.get(key).toJson();
                    k += 1;
                    if (k < propertyKey) {
                        c += ",  ";
                    }

                    a += c;

                }

            }
        a += "}";
            return a;

    }

    public void add(JsonPair jsonPair) {
        hmap.put(jsonPair.key,jsonPair.value);
    }

    public Json find(String name) {
        if (! hmap.containsKey(name)){
            return null;
        }
        Json js = hmap.get(name);

        return js;
    }

    public JsonObject projection(String... names) {

        ArrayList<JsonPair> JP = new ArrayList<>();
        for (String i: names)
            JP.add(new JsonPair(i, hmap.get(i)));
        JsonObject JO = new JsonObject();
        if (JP.isEmpty()){
            return new JsonObject();

        }
        for(JsonPair jp : JP){
            JO.add(jp);
        }

        return JO;
    }
}
