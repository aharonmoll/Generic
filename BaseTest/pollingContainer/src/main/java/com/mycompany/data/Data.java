package com.mycompany.data;

/**
 * Created by aharon on 7/30/15.
 */
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;

public class Data {
    String id;
    String key;
    boolean processed;

    public Data ()
    {

    }

    public Data(String id, String key,boolean processed) {
        this.id = id;
        this.key = key;
        this.processed = processed;
    }

    @SpaceId(autoGenerate=false)
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @SpaceIndex
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public boolean getProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    @Override
    public String toString() {
        return "MyData [id=" + id + ", key=" + key + "]";
    }
}