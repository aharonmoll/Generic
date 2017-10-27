package gs.example;

import com.gigaspaces.document.SpaceDocument;
import com.gigaspaces.sync.DataSyncOperation;
import com.gigaspaces.sync.OperationsBatchData;
import com.gigaspaces.sync.SpaceSynchronizationEndpoint;

/**
 * Created by aharon on 6/13/17.
 */
public class MySpaceSynchronizationEndpoint extends SpaceSynchronizationEndpoint {
    @Override
    public void onOperationsBatchSynchronization(OperationsBatchData batchData) {
        // Get operations in batch
        DataSyncOperation[] operations = batchData.getBatchDataItems();
        for (DataSyncOperation operation : operations) {
            switch (operation.getDataSyncOperationType()) {
                case WRITE:
                    System.out.println("WRITE operation");
                    break;
                case UPDATE:
                    System.out.println("UPDATE operation");
                    break;
                default:
                    System.out.println(operation.getDataSyncOperationType() + " operation");
            }

            // Print type name for either POJO or SpaceDocument
            if (operation.supportsDataAsObject()) {
                Object pojo = operation.getDataAsObject();
                System.out.println("POJO: " + operation.getTypeDescriptor().getTypeName());
            } else if (operation.supportsDataAsDocument()) {
                SpaceDocument document = operation.getDataAsDocument();
                System.out.println("SpaceDocument: " + document.getTypeName());
            }
        }
    }
}