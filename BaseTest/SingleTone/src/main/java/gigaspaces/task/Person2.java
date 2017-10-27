package gigaspaces.task;

/**
 * Created by aharon on 2/16/15.
 */
public class Person2
{
    //variables
    private int id;
    private String name;
    private String address;
    private int salary;


    Person2(int id)
    {
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public int getSalary(){
        return salary;
    }
    public void setId(){
        this.id = id;
    }
    public void setName(){
        this.name = name;
    }
    public void setAddress(){
        this.address = address;
    }
    public void setSalary(){
        this.salary = salary;
    }

    void printID()
    {
        System.out.println("ID= "+this.id);
    }
}
