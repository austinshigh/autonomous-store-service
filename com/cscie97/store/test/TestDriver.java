package com.cscie97.store.test;

import com.cscie97.store.model.CommandProcessor;

/**
 *  Calls the Command Processor, tests Store Model Service via CLI.
 *
 */
public class TestDriver {
    public static void main(String[] args) {
        CommandProcessor test = new CommandProcessor();
        test.processCommandFile(args[0]);
    }
}