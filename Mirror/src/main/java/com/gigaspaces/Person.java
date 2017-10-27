package com.gigaspaces;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.metadata.index.SpaceIndexType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by Yuvald on 17-Dec-14.
 */
@Entity
@Table(name="PERSONS")
@SpaceClass
public class Person {

    @Id
    private String personId;
    private String lastName;
    private String firstName;
    private String address;
    private String city;

    public Person(){}

    @SpaceId(autoGenerate = false)
    @SpaceRouting
    public String getPersonId() { return personId; }
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @SpaceIndex(type= SpaceIndexType.BASIC)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @SpaceIndex(type= SpaceIndexType.BASIC)
    public String getLastName() { return lastName; }
    public void setLastName(String lastName){ this.lastName = lastName; }

    public String getAddress() { return address; }
    public void setAddress(String address){ this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
}
