package ubs.tst;

/**
 * Created by aharon on 6/22/17.
 */

import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import org.apache.commons.collections.MapUtils;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractGcmmTrade  {
    private static final long serialVersionUID = 7520802570186247673L;

    private GcmmTradeStatus status;
    private String spaceId;
    private String routingId;
    private Long creationTime;
    private String tradeId;
    private String version;
    // TV_REPORTING_COB
    private String tvReportingCob;
    // COB_DATE
    private String cobDate;
    // REGION
    private String region;
    // VALUATION_ENGINE
    private String sourceId;
    private String assetClass;
    private String riskRunDate;
    private Long riskRunVersion;
    private String positionId;
    private Boolean fpmlDataProcessed;
    private Long fpmlDataVersion; //This is the slice version that was used to retrieve fpml data
    private Boolean gdsDataProcessed;
    private Long gdsDataVersion; //This is the slice version that was used to retrieve GDS data
    private Long lastEventRecvTime;

    @Deprecated
    public AbstractGcmmTrade() {
    }

    public AbstractGcmmTrade(String key, String affinityId, String routingId) {
        String[] deconstructedAffinityId = affinityId.split(":");
        this.routingId = routingId;
        this.spaceId = key + ":" + affinityId;
        // Set default values
        this.creationTime = System.currentTimeMillis();
        this.lastEventRecvTime = System.currentTimeMillis();
        this.region = deconstructedAffinityId[2];
        this.sourceId = "";
        this.status = GcmmTradeStatus.UNPROCESSED;
        this.fpmlDataProcessed = false;
        this.fpmlDataVersion = 0L;
        this.gdsDataProcessed = false;
        this.gdsDataVersion = 0L;

    }

    public abstract String toStringPositionLevelData();

    public abstract String toStringFpmlQueryCriteria();

    public abstract String toStringGDSQueryKeys();

    public abstract void updatePositionLevelData(AbstractGcmmTrade abstractGcmmTrade);

    public boolean isSecondary() {
        return this.tradeId != null && this.tradeId.contains("_");
    }

    @SpaceId(autoGenerate = false)
    @Override
    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    @SpaceRouting
    @Override
    public String getRoutingId() {
        return routingId;
    }

    public void setRoutingId(String routingId) {
        this.routingId = routingId;
    }

    public GcmmTradeStatus getStatus() {
        return status;
    }

    public void setStatus(GcmmTradeStatus status) {
        this.status = status;
    }

    @Override
    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTvReportingCob() {
        return tvReportingCob;
    }

    public void setTvReportingCob(String tvReportingCob) {
        this.tvReportingCob = tvReportingCob;
    }

    public String getCobDate() {
        return cobDate;
    }

    public void setCobDate(String cobDate) {
        this.cobDate = cobDate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getAssetClass() {
        return assetClass;
    }

    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass;
    }

    public Long getRiskRunVersion() {
        return riskRunVersion;
    }

    public void setRiskRunVersion(Long riskRunVersion) {
        this.riskRunVersion = riskRunVersion;
    }

    public String getRiskRunDate() {
        return riskRunDate;
    }

    public void setRiskRunDate(String riskRunDate) {
        this.riskRunDate = riskRunDate;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public Boolean getFpmlDataProcessed() {
        return fpmlDataProcessed;
    }

    public void setFpmlDataProcessed(Boolean fpmlDataProcessed) {
        this.fpmlDataProcessed = fpmlDataProcessed;
    }

    public Boolean getGdsDataProcessed() {
        return gdsDataProcessed;
    }

    public void setGdsDataProcessed(Boolean gdsDataProcessed) {
        this.gdsDataProcessed = gdsDataProcessed;
    }

    public Long getLastEventRecvTime() {
        return lastEventRecvTime;
    }

    public void setLastEventRecvTime(Long lastEventRecvTime) {
        this.lastEventRecvTime = lastEventRecvTime;
    }

    @Override
    public String toString() {
        return "Trade object with space id: " + this.spaceId;
    }

    public Long getFpmlDataVersion() {
        return fpmlDataVersion;
    }

    public void setFpmlDataVersion(Long fpmlDataVersion) {
        this.fpmlDataVersion = fpmlDataVersion;
    }

    public Long getGdsDataVersion() {
        return gdsDataVersion;
    }

    public void setGdsDataVersion(Long gdsDataVersion) {
        this.gdsDataVersion = gdsDataVersion;
    }

}
