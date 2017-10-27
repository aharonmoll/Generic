package purchase.example;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;

import java.util.UUID;

/**
 * Created by aharon on 8/10/15.
 */
@SpaceClass
public class PurchaseOrder {

    private int retryCounter = 0;

    private UUID id;

    private String number;

    private EPurchaseOrderState state;

    @SpaceId
    public UUID getId() {
        return id;
    }

    public int getRetryCounter() {
        return retryCounter;
    }

    public void setRetryCounter(int retryCounter) {
        this.retryCounter = retryCounter;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public EPurchaseOrderState getState() {
        return state;
    }

    public void setState(EPurchaseOrderState state) {
        this.state = state;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PurchaseOrder [retryCounter=" + retryCounter + ", id=" + id
                + ", number=" + number + ", state=" + state + "]";
    }
}
