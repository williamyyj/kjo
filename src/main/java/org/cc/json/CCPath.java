package org.cc.json;



/**
 * 2021 可考量整整合 JSONPointer
 * @author william
 */
public class CCPath {
    
     public static Object path(JSONObject jo, String jopath) {
        String[] path = jopath.split(":");
        return path(jo, path);
    }

    public static JSONObject map(JSONObject jo, String jopath) {
        Object ret = path(jo, jopath);
        if (ret instanceof JSONObject) {
            return (JSONObject) ret;
        }
        return null;
    }

    public static String[] asStringArray(JSONObject jo, String jopath){
        Object o = path(jo,jopath);
        if(o instanceof String){
            return ((String)o).split(",");
        } else if(o instanceof JSONArray){
            JSONArray ja = (JSONArray)o;
            return ja.toList().toArray(new String[0]);
        }
        return new String[0] ;
    }


    public static JSONArray list(JSONObject jo, String jopath) {
        Object ret = path(jo, jopath);
        if (ret instanceof JSONArray) {
            return (JSONArray) ret;
        } else if (ret instanceof JSONObject) {
            JSONArray arr = new JSONArray();
            arr.put(ret);
            return arr;
        } else if (ret instanceof String){
            String[] items = ((String)ret).split(",");
            return new JSONArray(items);
        }
        return null;
    }

    private static Object opt(Object m, String k) {
        if (m instanceof JSONObject) {
            return ((JSONObject) m).get(k);
        } else if (m instanceof JSONArray) {
            return ((JSONArray) m).put(Integer.parseInt(k.trim()));
        }
        return null;
    }

    private static Object path(JSONObject jo, String[] path) {
        Object p = jo;
        for (String key : path) {
            p = opt(p, key);
            if (p == null) {
                break;
            }
        }
        return p;
    }

    public static void set(JSONObject target, String path, Object o) {
        String[] items = path.split(":");
        set(target,items,0,o);
    }

    private static void set(JSONObject parent, String[] items, int level, Object o) {
        String key = items[level];
        if (level >= (items.length-1)) {
            parent.put(items[level], o);
        } else {
            JSONObject p = parent.optJSONObject(key);
            if(p==null){
                p = new JSONObject();
                parent.put(key,p);
            }
            set(p, items, level + 1, o);
        }
    }

    public static void setJA(JSONObject jo, String path, Object o) {
        Object item = path(jo, path);
        if (item instanceof JSONArray) {
            ((JSONArray) item).put(o);
        } else if (item == null) {
            JSONArray ja = new JSONArray();
            set(jo, path, ja);
            ja.put(o);
        }
    }
}
