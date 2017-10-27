/**
 * Created by aharon on 3/24/15.
 */
/*
 * @(#)ITrade.java 14/4/2008
 *
 * Copyright 2007 GigaSpaces Technologies Inc.
 */
package com.gigaspaces.data.trade;


/**
 * ITrade interface to be implemented by specific implementation objects (entry, pojo...)
 *
 * @author hanang
 * @created on 14/4/2008
 * @version 6.5
 **/
public interface Trade
{
    /** indexed field + routing field */
    public Integer getSymbolLabel();
    public void setSymbolLabel(Integer symbolLabel);

    /**
     * secondary key used by metric (not indexed in space).
     */
    public Long getTradeId();
    public void setTradeId(Long tradeId);

    /** indexed field */
    public TradeStatus getTradeStatus();
    public void setTradeStatus(TradeStatus state);

    public Float getPrice();
    public void setPrice(Float price);

    public Long getTimestamp ();
    public void setTimestamp (Long timestamp);

    public Integer getQuantity();
    public void setQuantity(Integer quantity);

    /**
     * payload field
     */
    public byte[] getPayload();
    public void setPayload(byte[] payload);

    /**
     * version field
     */
    public int getVersion();
    public Object setVersion(int version);

    /**
     * Cleans metadata object information.
     */
    public void clearUidInfo();

    /**
     * Represents objects status
     */
    public enum TradeStatus
    {
        NEW,
        VALIDATED,
        PROCESSED;
    }
}

