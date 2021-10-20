package com.cscie97.ledger;

/**
 * Returned from the Ledger API methods in response to an error condition.
 * Captures the action that was attempted and the reason for the failure.
 *
 * @author austinhigh
 */
public class LedgerException extends Throwable {

    private String action;
    private String reason;

    /**
     * Class Constructor.
     * @param action action performed when error encountered
     * @param reason reason error occurred
     */
    public LedgerException(String action, String reason) {
        super();
        this.action = action;
        this.reason = reason;
    }

    /**
     * get field
     *
     * @return action
     */
    public String getAction() {
        return this.action;
    }

    /**
     * set field
     *
     * @param action action performed when error encountered
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * get field
     *
     * @return reason
     */
    public String getReason() {
        return this.reason;
    }

    /**
     * set field
     *
     * @param reason reason error occurred
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}
