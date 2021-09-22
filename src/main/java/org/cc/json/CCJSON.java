package org.cc.json;



public class CCJSON {

    public static JSONObject laod(String base, String id){

        return null;
        
    }

    public static JSONObject line(Object line) {
        if (line instanceof JSONObject) {
            return (JSONObject) line;
        } else if (line instanceof String) {
            String text = ((String) line).trim();
            text = (text.charAt(0) == '{' ? text : "{" + text + "}");
            return new JSONObject(text);
        }
        return null;
    }

    
   
}
