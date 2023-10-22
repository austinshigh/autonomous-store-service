package com.services.store.test;

import com.services.store.model.CommandProcessor;
import com.services.store.model.CommandProcessorException;

import java.io.FileNotFoundException;

/**
 *  Calls the Command Processor, tests Store Model Service via CLI.
 *
 */
public class TestDriver {
    public static void main(String[] args) throws CommandProcessorException, FileNotFoundException {
        CommandProcessor test = new CommandProcessor();
        test.processCommandFile(args[0]);
    }
}