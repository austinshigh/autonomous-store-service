package com.cscie97.ledger.test;

import com.cscie97.ledger.CommandProcessor;

/**
 *  Calls the Command Processor, tests Ledger Service via CLI.
 *
 */
public class TestDriver {
    public static void main(String[] args) {
        CommandProcessor test = new CommandProcessor();
        test.processCommandFile(args[0]);
    }
}