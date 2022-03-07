package Util;


import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Json {

    public  JSONObject jsontest() {
        Map<String, Object> map = new HashMap<>();
        map.put("name","morpheus");
        map.put("jod","leader");
        JSONObject json = new JSONObject(map);
        System.out.println(json);
        return json;
    }

}
