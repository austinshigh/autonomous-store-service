package com.cscie97.ledger;

/**
 * Represents an individual account within the Ledger Service. An account
 * contains an address that provides a unique identity for the Account. The Account also contains
 * a balance that represents the value of the account. The account can only be updated by the
 * Ledger Service.
 *
 * @author austinhigh
 */
public class Account {

    private String address;
    private int balance;

    /**
     * Class Constructor
     *
     * @param uniqueAddress
     */
    public Account(String uniqueAddress) {
        this.address = uniqueAddress;
        this.balance = 0;
    }


    /**
     * get address
     *
     * @return {@link String}
     * @see String
     */
    public String getAddress() {
        return this.address;
    }


    /**
     * get balance
     *
     * @return {@link int}
     */
    public int getBalance() {
        return this.balance;
    }


    /**
     * set address
     *
     * @param address address
     */
    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * set balance
     *
     * @param balance balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
