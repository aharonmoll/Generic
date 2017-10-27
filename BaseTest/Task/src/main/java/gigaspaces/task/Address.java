package gigaspaces.task;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by aharon on 9/3/15.
 */
public class Address implements Externalizable {
    private String city;
    private String street;
    private Integer streetNumber;
    private Integer flatNumber;

    @Override
    public String toString() {
        return ("{\n\t\tcity=" + city) + ", \n" +
                "\t\tstreet=" + street + ", \n" +
                "\t\tstreetNumber=" + streetNumber + ", \n" +
                "\t\tflatNumber=" + flatNumber + "\n" +
                "\t\t}";
    }

    public Address() { }

    public Address(String city, String street, Integer streetNum, Integer flatNum)
    {
        this.city = city;
        this.street = street;
        this.streetNumber = streetNum;
        this.flatNumber = flatNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }


    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.city);
        out.writeObject(this.street);
        out.writeInt(this.streetNumber);
        out.writeInt(this.flatNumber);
    }


    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setCity((String) in.readObject());
        this.setStreet((String) in.readObject());
        this.setStreetNumber(in.readInt());
        this.setFlatNumber(in.readInt());
    }
}
