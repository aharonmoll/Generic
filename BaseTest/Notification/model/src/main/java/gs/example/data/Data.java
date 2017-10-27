package gs.example.data;

/**
 * Created by aharon on 7/30/15.
 */
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.annotation.pojo.SpaceRouting;

public class Data {
    String id;
    String key;
    boolean processed;

    public Data()
    {

    }

    public Data(boolean processed) {
        this.processed = processed;
    }

    public Data(String key) {
        this.key = key;
    }

    @SpaceId(autoGenerate=false)
    @SpaceRouting
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