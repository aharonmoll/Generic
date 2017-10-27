package com.gigaspaces.test;

/**
 * Created by yuval on 12/29/16.
 */
import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;

@SpaceClass
public class MySpaceClass {
    int id;
    String data;

    @SpaceId(autoGenerate = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String toString() {
        return "ID:" + id + " Data:"+ data;
    }
}