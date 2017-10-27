package gigaspaces.task;

import com.gigaspaces.client.ChangeResult;
import com.gigaspaces.client.ChangeSet;
import com.gigaspaces.query.IdQuery;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.executor.Task;
import org.openspaces.core.executor.TaskGigaSpace;

import java.util.logging.Logger;

/**
 * Created by aharon on 9/3/15.
 */
    public class RemoteTask implements Task<Boolean> {

        private Boolean value;
        private int id;
        @TaskGigaSpace
        private transient GigaSpace gigaSpace;
        Logger log= Logger.getLogger(this.getClass().getName());

        public RemoteTask(int key) {
            this.id = key;
        }

        public Boolean execute() throws Exception {

            ChangeResult<Employee> result=null;
            result = gigaSpace.change(new IdQuery<Employee>(Employee.class, id), new ChangeSet().set("points", 0));
            if (result!=null){
                //log.info("NumberOfChangedEntries: " + result.getNumberOfChangedEntries()+" Employee id "+id);
                return true;
            }
            else return false;
        }
    }

