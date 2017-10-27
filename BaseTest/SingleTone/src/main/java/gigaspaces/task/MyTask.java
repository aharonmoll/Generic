package gigaspaces.task;

import org.openspaces.core.executor.Task;

/**
 * Created by aharon on 2/16/15.
 */
public class MyTask implements Task<Integer> {

    private int value;

    public MyTask(int value) {
        this.value = value;
    }



    public Integer execute() throws Exception {
        Integer result=null;

        if (this.value==333)
        {
            Person person = new Person(333);
            result = person.getId();
        }
        else
        {
            Person2 person2 = new Person2(222);
            result = person2.getId();
        }
        return result;
    }
}
