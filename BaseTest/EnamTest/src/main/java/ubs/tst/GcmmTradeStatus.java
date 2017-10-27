package ubs.tst;
/**
 * Created by aharon on 6/22/17.
 */
public enum GcmmTradeStatus {
    UNPROCESSED("UNPROCESSED"),
    ERROR("ERROR"),
    LRP_PROCESSED("LRP_PROCESSED"),
    FPML_ASSEMBLED("FPML_ASSEMBLED"),
    GDS_DATA_FETCHED("GDS_DATA_FETCHED"),
    CONSOLIDATED("CONSOLIDATED"),
    LIVE("LIVE");

    private final String text;

    /**
     * @param text
     */
    private GcmmTradeStatus(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}