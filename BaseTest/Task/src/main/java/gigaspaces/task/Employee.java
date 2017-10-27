package gigaspaces.task;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceProperty;
import com.gigaspaces.annotation.pojo.SpaceRouting;

/**
 * Created by aharon on 9/3/15.
 */

@SpaceClass
public class Employee {

    public Employee() {

    }

    public Employee(Integer payloadSize) {
        payload = new byte[payloadSize];
    }

    protected Integer id;
    private String name;
    protected Integer companyId;
    protected Integer age;
    private String sexType;
    protected long salary;
    protected Address address;
    private String department;
    private Integer points;

    //@Override
    //public String toString() {
    //    return ("EMPLOYEE [\n\tid=" + id) + ", \n\tname=" + name + ", \n\tcompanyId=" + companyId + ", \n\tsex=" + sexType + ", \n\tsalary=" + salary + ", \n\tpoints=" + points + ", \n\tdepartment=" + department + " \n\tage " + age + ", \n\tpayload=" + payload.length + " bytes" + ", \n\taddress=" + address + "\n]";
    //}

    // Generic payload to simulate some data...
    protected byte[] payload;

    @SpaceId
    @SpaceRouting
    public Integer getId() {
        return id;
    }

    public Employee setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSexType() {
        return sexType;
    }

    public void setSexType(String sexType) {
        this.sexType = sexType;
    }

    @SpaceProperty(nullValue="0")
    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}