package com.cscie97.store.controller;

import com.cscie97.ledger.CommandProcessor;
import com.cscie97.ledger.CommandProcessorException;

public interface Command {

	CommandProcessor commandProcessor = new CommandProcessor();

	public abstract void execute() throws CommandProcessorException, com.cscie97.store.model.CommandProcessorException;

}
