package org.cc.kjo;


import org.cc.json.JSONArray;
import org.cc.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class JOTest {

    @DisplayName("我的第一個測試用例")
    @Test
    public void test_jo(){
        JSONObject jo = new JSONObject();
        jo.put("ok","xxxx");
        System.out.println(jo);
    }

    @ParameterizedTest
    @CsvSource({"1,One", "2,Two", "3,Three"})
    public void testDataFromCsv(long id, String name) {
        System.out.println(id+":::"+name);
    }

    @Test
    public void test_json_jo(){
        JSONObject jo = new JSONObject("{x:5,name:abc}");
        System.out.println(jo.toString(4));
    }

    @Test
    public void test_json_ja(){
        JSONArray jo = new JSONArray("[a,b,c,c,de,f]");
        for(Object o : jo){
            System.out.println(o);
        }
    }

}
