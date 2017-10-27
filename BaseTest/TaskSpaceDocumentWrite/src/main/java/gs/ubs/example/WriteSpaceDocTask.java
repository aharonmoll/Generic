package gs.ubs.example;

import com.gigaspaces.document.SpaceDocument;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.executor.Task;
import org.openspaces.core.executor.TaskGigaSpace;

/**
 * Created by aharon on 9/26/16.
 */
public class WriteSpaceDocTask implements Task<Integer> {
    private SpaceDocument spaceDocument;

    @TaskGigaSpace
    private transient GigaSpace gigaSpace;

    public WriteSpaceDocTask(SpaceDocument spaceDocument) {
        this.spaceDocument = spaceDocument;
    }

    public Integer execute() throws Exception {
        System.out.println("SpaceDocument aggId -> "+this.spaceDocument.getProperty("aggId"));
        gigaSpace.getTypeManager().getTypeDescriptor(this.spaceDocument.getTypeName());
        gigaSpace.write(this.spaceDocument);
        return null;
    }
}
