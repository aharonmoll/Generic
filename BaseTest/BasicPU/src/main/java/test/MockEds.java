package test;

import com.gigaspaces.datasource.DataIterator;
import com.gigaspaces.document.SpaceDocument;
import com.gigaspaces.metadata.SpaceTypeDescriptor;
import com.gigaspaces.metadata.SpaceTypeDescriptorBuilder;
import org.openspaces.persistency.ClusterInfoAwareSpaceDataSource;

/**
 * Created by aharon on 6/13/17.
 */
public class MockEds extends ClusterInfoAwareSpaceDataSource {

    @Override
    public DataIterator<SpaceTypeDescriptor> initialMetadataLoad() {
        final SpaceTypeDescriptor typeDescriptor = new SpaceTypeDescriptorBuilder("MockDocument").idProperty("id").create();
        return new DataIterator<SpaceTypeDescriptor>() {
            boolean next = true;
            public boolean hasNext() {
                if (next) {
                    next = false;
                    return true;
                }
                return false;
            }
            public SpaceTypeDescriptor next() {
                return typeDescriptor;
            }
            public void remove() {
            }
            public void close() {
            }
        };
    }

    public DataIterator<Object> initialDataLoad()
    {
        System.out.println("Space details: ");
        System.out.println("Name: "+clusterInfo.getName()+" Instance ID: "+clusterInfo.getInstanceId()+" Number of instances: "+clusterInfo.getNumberOfInstances());
        System.out.println("========= MockEDS called for read!!!!===");
        final SpaceDocument document = new SpaceDocument("MockDocument");
        document.setProperty("id", "111");
        String id = document.getProperty("id");
        boolean routing = Integer.valueOf(id) % clusterInfo.getNumberOfInstances() == (clusterInfo.getInstanceId()-1);
        System.out.println("Boolean: "+routing);
        return new DataIterator<Object>() {
            boolean next = true;
            public boolean hasNext() {
                if (next) {
                    next = false;
                    return true;
                }
                return false;
            }
            public Object next() {
                return document;
            }
            public void remove() {
            }
            public void close() {
            }
        };
    }
}
