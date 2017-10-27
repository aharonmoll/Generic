package ubs.tst;

/**
 * Created by aharon on 6/22/17.
 */

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceExclude;

import java.util.List;


// CHECKSTYLE:OFF
// Switching of checkStyle because this class has 155 public methods (getters/setters), whereas maximum public method
// allowed is 125.
@SpaceClass
public class GcmmRateTrade extends AbstractGcmmTrade {
    private static final long serialVersionUID = 4936023001054453510L;
    private static final String STRINGIZE_DELIMITER = "|";

    private Boolean centerStateSliceProcessed;
    private Long centerStateSliceVersion;

    private Boolean miscSliceProcessed;
    private Long miscSliceVersion;

    private Boolean scenarioSliceProcessed;
    private Long scenarioSliceVersion;

    @Deprecated
    public GcmmRateTrade() {
        centerStateSliceProcessed = false;
        miscSliceProcessed = false;
        scenarioSliceProcessed = false;
        centerStateSliceVersion = 0L;
        miscSliceVersion = 0L;
        scenarioSliceVersion = 0L;

    }

    public GcmmRateTrade(String tradeId, String tradeVersion, String affinityId, String routingId) {
        super(tradeId, affinityId, routingId);
        centerStateSliceProcessed = false;
        miscSliceProcessed = false;
        scenarioSliceProcessed = false;
        setTradeId(tradeId);
        setVersion(tradeVersion);
        centerStateSliceVersion = 0L;
        miscSliceVersion = 0L;
        scenarioSliceVersion = 0L;
    }


    @Override
    public String toStringFpmlQueryCriteria() {
        return getVersion();//as version is the only attributes that may change in order for fpml data to be retrieved again
    }


    @Override
    protected String defaultGroupingId() {
        return getMasterFileData() != null ? getMasterFileData().getRxmId() : "UNKNOWN";
    }

    @Override
    public String toString() {
        return getClass().toString() + " : " + super.toString();
    }

    // LRP attributes
    public String getTicket() {
        return getRateLrpData().getTicket();
    }

    public String getTradeDate() {
        return getRateLrpData().getTradeDate();
    }

    public String getEffectiveDate() {
        return getRateLrpData().getEffectiveDate();
    }

    public String getGroupId() {
        return getRateLrpData().getGroupId();
    }

    public String getTrader() {
        return getRateLrpData().getTrader();
    }

    public String getMaturityDate() {
        return getRateLrpData().getMaturityDate();
    }

    public String getCounterpartyId() {
        return getRateLrpData().getCounterpartyId();
    }

    public String getSettleNextDate() {
        return getRateLrpData().getSettleNextDate();
    }

    public String getTenor() {
        return getRateLrpData().getTenor();
    }

    public String getResetDate() {
        return getRateLrpData().getResetDate();
    }

    public String getDealStatus() {
        return getRateLrpData().getDealStatus();
    }

    @Deprecated
    public void setTicket(String ticket) {
        this.getRateLrpData().setTicket(ticket);
    }

    @Deprecated
    public void setTradeDate(String tradeDate) {
        this.getRateLrpData().setTradeDate(tradeDate);
    }

    @Deprecated
    public void setEffectiveDate(String effectiveDate) {
        this.getRateLrpData().setEffectiveDate(effectiveDate);
    }

    @Deprecated
    public void setGroupId(String groupId) {
        this.getRateLrpData().setGroupId(groupId);
    }

    @Deprecated
    public void setTrader(String trader) {
        this.getRateLrpData().setTrader(trader);
    }

    @Deprecated
    public void setMaturityDate(String maturityDate) {
        this.getRateLrpData().setMaturityDate(maturityDate);
    }

    @Deprecated
    public void setCounterpartyId(String counterpartyId) {
        this.getRateLrpData().setCounterpartyId(counterpartyId);
    }

    @Deprecated
    public void setSettleNextDate(String settleNextDate) {
        this.getRateLrpData().setSettleNextDate(settleNextDate);
    }

    @Deprecated
    public void setTenor(String tenor) {
        this.getRateLrpData().setTenor(tenor);
    }

    @Deprecated
    public void setResetDate(String resetDate) {
        this.getRateLrpData().setResetDate(resetDate);
    }

    @Deprecated
    public void setDealStatus(String dealStatus) {
        this.getRateLrpData().setDealStatus(dealStatus);
    }

    @SpaceExclude
    public FpmlData getFpmlData() {
        return fpmlData;
    }

    public void setFpmlData(FpmlData fpmlData) {
        this.fpmlData = fpmlData;
    }

    // /Fpml attributes

    public String getExtTicketNumber() {
        return getFpmlData().getExtTicketNumber();
    }

    public String getUbsCurveName() {
        return getFpmlData().getUbsCurveName();
    }

    public String getPayCurrency() {
        return getFpmlData().getPayCurrency();
    }

    public String getPayFrequency() {
        return getFpmlData().getPayFrequency();
    }

    public String getRecCurrency() {
        return getFpmlData().getRecCurrency();
    }

    public String getUsi() {
        return getFpmlData().getUsi();
    }

    public String getPayFloatIndex() {
        return getFpmlData().getPayFloatIndex();
    }

    public String getRecFloatIndex() {
        return getFpmlData().getRecFloatIndex();
    }

    public String getPayFixedFloat() {
        return getFpmlData().getPayFixedFloat();
    }

    public Double getInitialMarginValue() {
        return getFpmlData().getInitialMarginValue();
    }

    public String getInitialMarginCurrency() {
        return getFpmlData().getInitialMarginCurrency();
    }

    public String getInitialMarginType() {
        return getFpmlData().getInitialMarginType();
    }

    public String getInitialMargin() {
        return getFpmlData().getInitialMargin();
    }

    public Double getFixedRate() {
        return getFpmlData().getFixedRate();
    }

    public Double getRecRate() {
        return getFpmlData().getRecRate();
    }

    public Double getPayRate() {
        return getFpmlData().getPayRate();
    }

    public String getRecFixedFloat() {
        return getFpmlData().getRecFixedFloat();
    }

    public String getFloatDayCount() {
        return getFpmlData().getFloatDayCount();
    }

    public String getResetSource() {
        return getFpmlData().getResetSource();
    }

    public String getFixedDayCount() {
        return getFpmlData().getFixedDayCount();
    }

    public Double getFloatingRate() {
        return getFpmlData().getFloatingRate();
    }

    public String getBuySell() {
        return getFpmlData().getBuySell();
    }

    public Double getPayFace() {
        return getFpmlData().getPayFace();
    }

    public Double getRecFace() {
        return getFpmlData().getRecFace();
    }

    public String getClearingStatus() {
        return getFpmlData().getClearingStatus();
    }

    public String getRecFrequency() {
        return getFpmlData().getRecFrequency();
    }

    public String getObsId() {
        return getFpmlData().getObsId();
    }

    public String getProductId() {
        return getRateLrpData().getProductId();
    }

    public String getBookId() {
        return getRateLrpData().getBookId();
    }

    @Deprecated
    public void setExtTicketNumber(String extTicketNumber) {
        this.getFpmlData().setExtTicketNumber(extTicketNumber);
    }

    @Deprecated
    public void setUbsCurveName(String ubsCurveName) {
        this.getFpmlData().setUbsCurveName(ubsCurveName);
    }

    @Deprecated
    public void setPayCurrency(String payCurrency) {
        this.getFpmlData().setPayCurrency(payCurrency);
    }

    @Deprecated
    public void setPayFrequency(String payFrequency) {
        this.getFpmlData().setPayFrequency(payFrequency);
    }

    @Deprecated
    public void setRecCurrency(String recCurrency) {
        this.getFpmlData().setRecCurrency(recCurrency);
    }

    @Deprecated
    public void setUsi(String usi) {
        this.getFpmlData().setUsi(usi);
    }

    @Deprecated
    public void setPayFloatIndex(String payIndex) {
        this.getFpmlData().setPayFloatIndex(payIndex);
    }

    @Deprecated
    public void setRecFloatIndex(String receiveIndex) {
        this.getFpmlData().setRecFloatIndex(receiveIndex);
    }

    @Deprecated
    public void setPayFixedFloat(String payFixedFloat) {
        this.getFpmlData().setPayFixedFloat(payFixedFloat);
    }

    @Deprecated
    public void setInitialMarginValue(Double initialMarginValue) {
        this.getFpmlData().setInitialMarginValue(initialMarginValue);
    }

    @Deprecated
    public void setInitialMarginCurrency(String initialMarginCurrency) {
        this.getFpmlData().setInitialMarginCurrency(initialMarginCurrency);
    }

    @Deprecated
    public void setInitialMarginType(String initialMarginType) {
        this.getFpmlData().setInitialMarginType(initialMarginType);
    }

    @Deprecated
    public void setInitialMargin(String initialMargin) {
        this.getFpmlData().setInitialMargin(initialMargin);
    }

    @Deprecated
    public void setFixedRate(Double fixedRate) {
        this.getFpmlData().setFixedRate(fixedRate);
    }

    @Deprecated
    public void setRecRate(Double recRate) {
        this.getFpmlData().setRecRate(recRate);
    }

    @Deprecated
    public void setPayRate(Double payRate) {
        this.getFpmlData().setPayRate(payRate);
    }

    @Deprecated
    public void setRecFixedFloat(String recFixedFloat) {
        this.getFpmlData().setRecFixedFloat(recFixedFloat);
    }

    @Deprecated
    public void setFloatDayCount(String floatDayCount) {
        this.getFpmlData().setFloatDayCount(floatDayCount);
    }

    @Deprecated
    public void setResetSource(String resetSource) {
        this.getFpmlData().setResetSource(resetSource);
    }

    @Deprecated
    public void setFixedDayCount(String fixedDayCount) {
        this.getFpmlData().setFixedDayCount(fixedDayCount);
    }

    @Deprecated
    public void setFloatingRate(Double floatRate) {
        this.getFpmlData().setFloatingRate(floatRate);
    }

    @Deprecated
    public void setBuySell(String buySell) {
        this.getFpmlData().setBuySell(buySell);
    }

    @Deprecated
    public void setPayFace(Double payFace) {
        this.getFpmlData().setPayFace(payFace);
    }

    @Deprecated
    public void setRecFace(Double recFace) {
        this.getFpmlData().setRecFace(recFace);
    }

    @Deprecated
    public void setClearingStatus(String clearingStatus) {
        this.getFpmlData().setClearingStatus(clearingStatus);
    }

    @Deprecated
    public void setRecFrequency(String recFrequency) {
        this.getFpmlData().setRecFrequency(recFrequency);
    }

    @Deprecated
    public void setObsId(String obsId) {
        this.getFpmlData().setObsId(obsId);
    }

    @Deprecated
    public void setProductId(String productId) {
        getRateLrpData().setProductId(productId);
    }

    @Deprecated
    public void setBookId(String bookId) {
        getRateLrpData().setBookId(bookId);
    }

    public BookstoreData getBookStoreData() {
        return bookStoreData;
    }

    public void setBookStoreData(BookstoreData bookStoreData) {
        this.bookStoreData = bookStoreData;
    }

    public MasterfileData getMasterFileData() {
        return masterFileData;
    }

    public void setMasterFileData(MasterfileData masterFileData) {
        this.masterFileData = masterFileData;
        setGroupingId(defaultGroupingId());
    }

    public ProductTaxonomyData getPtxData() {
        return ptxData;
    }

    public void setPtxData(ProductTaxonomyData ptxData) {
        this.ptxData = ptxData;
    }

    @SpaceExclude
    public RateLrpData getRateLrpData() {
        return rateLrpData;
    }

    public void setRateLrpData(RateLrpData rateLrpData) {
        this.rateLrpData = rateLrpData;
    }

    public Boolean getCenterStateSliceProcessed() {
        return centerStateSliceProcessed;
    }

    public void setCenterStateSliceProcessed(Boolean centerStateSliceProcessed) {
        this.centerStateSliceProcessed = centerStateSliceProcessed;
    }

    public Boolean getMiscSliceProcessed() {
        return miscSliceProcessed;
    }

    public void setMiscSliceProcessed(Boolean miscSliceProcessed) {
        this.miscSliceProcessed = miscSliceProcessed;
    }

    public Boolean getScenarioSliceProcessed() {
        return scenarioSliceProcessed;
    }

    public void setScenarioSliceProcessed(Boolean scenarioSliceProcessed) {
        this.scenarioSliceProcessed = scenarioSliceProcessed;
    }

    public List<CalculatedMeasureData> getTvMeasures() {
        return getRateLrpData().getCalculatedMeasuresData().get(TV.getMeasureName());
    }

    @Deprecated
    public void setTvMeasures(List<CalculatedMeasureData> tvMeasures) {
        this.getRateLrpData().getCalculatedMeasuresData().put(TV.getMeasureName(), tvMeasures);
    }

    public List<CalculatedMeasureData> getNotionalMeasures() {
        return getRateLrpData().getCalculatedMeasuresData().get(NOTIONAL.getMeasureName());
    }

    @Deprecated
    public void setNotionalMeasures(List<CalculatedMeasureData> notionalMeasures) {
        this.getRateLrpData().getCalculatedMeasuresData().put(NOTIONAL.getMeasureName(), notionalMeasures);
    }

    public List<CalculatedMeasureData> getPayableAccrInterestMeasures() {
        return getRateLrpData().getCalculatedMeasuresData().get(PAYABLE_ACCRUED_INTEREST.getMeasureName());
    }

    @Deprecated
    public void setPayableAccrInterestMeasures(List<CalculatedMeasureData> payableAccrInterestMeasures) {
        this.getRateLrpData().getCalculatedMeasuresData().put(PAYABLE_ACCRUED_INTEREST.getMeasureName(), payableAccrInterestMeasures);
    }

    public List<CalculatedMeasureData> getReceivableAccrInterestMeasures() {
        return getRateLrpData().getCalculatedMeasuresData().get(RECEIVABLE_ACCRUED_INTEREST.getMeasureName());
    }

    @Deprecated
    public void setReceivableAccrInterestMeasures(List<CalculatedMeasureData> receivableAccrInterestMeasures) {
        this.getRateLrpData().getCalculatedMeasuresData().put(RECEIVABLE_ACCRUED_INTEREST.getMeasureName(), receivableAccrInterestMeasures);
    }

    public List<CalculatedMeasureData> getIrZeroDeltaMeasures() {
        return getRateLrpData().getCalculatedMeasuresData().get(IR_ZERO_DELTA.getMeasureName());
    }

    @Deprecated
    public void setIrZeroDeltaMeasures(List<CalculatedMeasureData> irZeroDeltaMeasures) {
        this.getRateLrpData().getCalculatedMeasuresData().put(IR_ZERO_DELTA.getMeasureName(), irZeroDeltaMeasures);
    }

    public List<CalculatedMeasureData> getFxProductDeltaMeasures() {
        return this.getRateLrpData().getCalculatedMeasuresData().get(FX_PRODUCT_DELTA.getMeasureName());
    }

    @Deprecated
    public void setFxProductDeltaMeasures(List<CalculatedMeasureData> fxProductDeltaMeasures) {
        this.getRateLrpData().getCalculatedMeasuresData().put(FX_PRODUCT_DELTA.getMeasureName(), fxProductDeltaMeasures);
    }

    public List<CalculatedMeasureData> getPayableCurrentAmountMeasures() {
        return this.getRateLrpData().getCalculatedMeasuresData().get(PAYABLE_CURRENT_AMOUNT.getMeasureName());
    }

    @Deprecated
    public void setPayableCurrentAmountMeasures(List<CalculatedMeasureData> payableCurrentAmountMeasures) {
        this.getRateLrpData().getCalculatedMeasuresData().put(PAYABLE_CURRENT_AMOUNT.getMeasureName(), payableCurrentAmountMeasures);
    }

    public List<CalculatedMeasureData> getReceivableCurrentAmountMeasures() {
        return this.getRateLrpData().getCalculatedMeasuresData().get(RECEIVABLE_CURRENT_AMOUNT.getMeasureName());
    }

    @Deprecated
    public void setReceivableCurrentAmountMeasures(List<CalculatedMeasureData> receivableCurrentAmountMeasures) {
        this.getRateLrpData().getCalculatedMeasuresData().put(RECEIVABLE_CURRENT_AMOUNT.getMeasureName(), receivableCurrentAmountMeasures);
    }

    public Long getCenterStateSliceVersion() {
        return centerStateSliceVersion;
    }

    public void setCenterStateSliceVersion(Long centerStateSliceVersion) {
        this.centerStateSliceVersion = centerStateSliceVersion;
    }

    public Long getMiscSliceVersion() {
        return miscSliceVersion;
    }

    public void setMiscSliceVersion(Long miscSliceVersion) {
        this.miscSliceVersion = miscSliceVersion;
    }

    public Long getScenarioSliceVersion() {
        return scenarioSliceVersion;
    }

    public void setScenarioSliceVersion(Long scenarioSliceVersion) {
        this.scenarioSliceVersion = scenarioSliceVersion;
    }

}