package com.cscie97.ledger;

/**
 * Represents a transaction in the Ledger System.
 * Contains a transaction id, an amount, a fee, a note, payer account id and receiver account id.
 * The transaction amount is transferred from the payer’s account balance to the
 * receiver’s account balance. The transaction fee is transferred from the payer’s account to the
 * master account. Transactions are aggregated within blocks.
 *
 * @author austinhigh
 */
public class Transaction {
    private int transactionId;
    private int amount;
    private int fee;
    private String note;
    private String receiver;
    private String payer;

    /**
     * Class Constructor.
     * @param transactionId id (must be unique)
     * @param amount amount being transferred
     * @param fee fee to be paid to master account
     * @param note description of transaction
     * @param payer account id of payer
     * @param receiver account id of receiver
     */
    public Transaction(int transactionId, int amount, int fee, String note, String payer, String receiver) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.fee = fee;
        this.note = note;
        this.payer = payer;
        this.receiver = receiver;
    }

    /**
     * to string
     *
     * @return {@link String}
     * @see String
     */
    @Override
    public String toString() {
        return "Transaction Id: " + transactionId +
                "\nAmount: " + amount +
                "\nFee: " + fee +
                "\nNote: " + note +
                "\nReceiver: " + receiver +
                "\nPayer: " + payer;
    }


    /**
     * Returns concatenation of attributes.
     *
     * @return {@link String}
     * @see String
     */
    public String getConcat(){
        String concat = (this.getFee() +
                this.getAmount() +
                this.getNote() +
                this.getPayer() +
                this.getReceiver() +
                this.getTransactionId());
        return concat;
    }

    /**
     * get transaction id
     *
     * @return {@link int}
     */
    public int getTransactionId() {
        return this.transactionId;
    }

    /**
     * set transaction id
     *
     * @param transactionId transactionId
     */
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * get amount
     *
     * @return {@link int}
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * set amount
     *
     * @param amount amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * get fee
     *
     * @return {@link int}
     */
    public int getFee() {
        return this.fee;
    }

    /**
     * set fee
     *
     * @param fee fee
     */
    public void setFee(int fee) {
        this.fee = fee;
    }

    /**
     * get note
     *
     * @return {@link String}
     * @see String
     */
    public String getNote() {
        return this.note;
    }

    /**
     * set note
     *
     * @param note note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * get receiver
     *
     * @return {@link String}
     * @see String
     */
    public String getReceiver() {
        return this.receiver;
    }

    /**
     * set receiver
     *
     * @param receiver receiver
     */
    public void setRecexiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * get payer
     *
     * @return {@link String}
     * @see String
     */
    public String getPayer() {
        return this.payer;
    }

    /**
     * set payer
     *
     * @param payer payer
     */
    public void setPayer(String payer) {
        this.payer = payer;
    }
}
