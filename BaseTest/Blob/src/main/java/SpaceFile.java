import com.gigaspaces.annotation.pojo.SpaceId;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by aharon on 3/11/15.
 */
public class SpaceFile {

    SpaceFile (){}
    SpaceFile (String name , byte[] content) throws SQLException {
        this.name = name;
        //this.content= content;
        this.blob = new SerialBlob(content);

    }
    String name;
   // byte[] content;
    Blob blob;

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }

    @SpaceId
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public byte[] getContent() throws SQLException {
        return this.blob.getBytes(1, (int) this.blob.length());
    }
    /*
    public void setContent(byte[] content) {
        this.content = content;
    }*/
}
