package SocGen;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;

/**
 * Created by aharon on 1/1/15.
 */
@SpaceClass
public class Person {

    private Long sequenceNumber;
    private String id;
    private Integer number;

    public Person()
    {

    }

    public Person(String id)
    {
        this.id = id;
    }

    public Person(Long sequenceNumber, String id, Integer number) {
        this.sequenceNumber = sequenceNumber;
        this.id = id;
        this.number = number;
    }

    @SpaceId(autoGenerate = false)
    @SpaceRouting
    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id=id;
    }

    public Long getSequenceNumber()
    {
        return this.sequenceNumber;
    }

    public void setSequenceNumber(Long sequence)
    {
        this.sequenceNumber = sequence;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}