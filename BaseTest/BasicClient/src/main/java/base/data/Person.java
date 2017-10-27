package base.data;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;

/**
 * Created by aharon on 1/1/15.
 */
@SpaceClass
public class Person {

    private Long SequenceNumber;
    private String id;
    private Integer number;

    public Person ()
    {

    }

    public Person (String id)
    {
        this.id = id;
    }

    @SpaceId(autoGenerate = false)
    //@SpaceRouting
    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id=id;
    }

    //@SpaceSequenceNumber
    public Long getSequenceNumber()
    {
        return this.SequenceNumber;
    }

    public void setSequenceNumber(Long sequence)
    {
        this.SequenceNumber = sequence;
    }

    @SpaceRouting
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}