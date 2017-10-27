package com.gs.latest;

import com.gigaspaces.annotation.pojo.SpaceId;

/**
 * Created by aharon on 9/5/16.
 */
public class LatestPointer {

    public Integer aggregateId;
    public Integer latestUniqueId;

    public LatestPointer() {
    }

    public LatestPointer(Integer aggregateId, Integer latestUniqueId) {
        this.aggregateId = aggregateId;
        this.latestUniqueId = latestUniqueId;
    }

    @SpaceId
    public Integer getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(Integer aggregateId) {
        this.aggregateId = aggregateId;
    }

    public Integer getLatestUniqueId() {
        return latestUniqueId;
    }

    public void setLatestUniqueId(Integer latestUniqueId) {
        this.latestUniqueId = latestUniqueId;
    }
}
