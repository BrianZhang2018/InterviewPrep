package affirm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * output the key and the valType of value
 *
 * Created by brianzhang on 7/20/20.
 */
public class ParseJson {
    public static void main(String[] args) {
        //{"a": "1", "b":"str", "c":{"a": "1", "b":"str"}}
        HashMap<String, JsonValue> map = new HashMap<>();
        JsonInteger ji = new JsonInteger();
        map.put("a", ji);
        JsonString ja = new JsonString();
        map.put("b", ja);
        JsonRecord jr1 = new JsonRecord(new HashMap(map));
        map.put("c", jr1);

        JsonRecord top = new JsonRecord(new HashMap<>(map));

        List<Output> list = parseJson(top);
        System.out.println(list);

    }

    public static List<Output> parseJson(JsonRecord jr) {
        List<Output> res = new ArrayList<>();
        for (Map.Entry<String, JsonValue> entry : jr.map.entrySet()) {
            Output o;
            if (entry.getValue() instanceof JsonInteger) {
                o = new Output(entry.getKey(), "int");
            } else if (entry.getValue() instanceof JsonRecord) {

                o = new Output(entry.getKey(), "list");
                o.outputs = parseJson((JsonRecord) entry.getValue()); // recursive
            } else if (entry.getValue() instanceof JsonString) {
                o = new Output(entry.getKey(), "string");
            } else {
                o = new Output(entry.getKey(), "unknown");
            }

            res.add(o);
        }

        return res;
    }
}

class Output {
    public String key;
    public String valType;
    public List<Output> outputs;

    public Output(String key, String valType) {
        this.key = key;
        this.valType = valType;
    }

    public Output() {}
}

interface JsonValue<T> {
    public T getValue();
}

class JsonRecord implements JsonValue<List> {
    Map<String, JsonValue> map;

    public JsonRecord(Map<String, JsonValue> map) {
        this.map = map;
    }

    @Override
    public List getValue() {
        return new ArrayList();
    }

    public String getType() {
        return "JsonRecord";
    }
}

class JsonInteger implements JsonValue<Integer> {
    private int value;

    @Override
    public Integer getValue() {
        return 1;
    }

    public String getType() {
        return "string";
    }
}

class JsonString implements JsonValue<String> {
    @Override
    public String getValue() {
        return "string";
    }

    public String getType() {
        return "int";
    }
}

class JsonArray implements JsonValue<List> {
    @Override
    public List getValue() {
        return new ArrayList();
    }

    public String getType() {
        return "List";
    }
}

/*
class JsonBoolean implements JsonValue<Boolean> {
    @Override
    public Boolean getValue() {
        return true;
    }
}
*/



