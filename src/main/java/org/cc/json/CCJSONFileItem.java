package org.cc.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CCJSONFileItem implements Callable<CCJSONFileItem>  {

    private JSONObject data;
    private final String id;
    private long lastModified;

    public CCJSONFileItem(String id) {
        this.id = id;
        File f = new File(id);
        if (f.exists()) {
            this.lastModified = f.lastModified();
        }
    }


    public long lastModified() {
        return this.lastModified;
    }

    public JSONObject load() {
        File f = new File(id);
        if (f.exists()) {
            if (data == null) {
                log.debug("Load file : " + f.getAbsolutePath());
                data = loadJSON(f);
            } else if (f.lastModified() > this.lastModified) {
                log.debug("Reload file : " + f.getAbsolutePath());
                data = loadJSON(f);
            } 
        } else {
            log.debug("Can't find file : " + f.getAbsolutePath());
        }
        return data;
    }


    public void unload() {
        data = null;
    }

    private JSONObject loadJSON(File f)   {
        try ( Reader reader = new InputStreamReader(new FileInputStream(f), "UTF-8");) {
            this.lastModified = f.lastModified();
            JSONTokener tk = new JSONTokener(reader);
            return new JSONObject(tk);
        } catch (Exception e){
            e.printStackTrace();
        }
        // return new JSONObject(); 
        return null;
    }


    @Override
    public CCJSONFileItem call() throws Exception {
        return this;
    }


}