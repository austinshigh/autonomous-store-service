package com.cscie97.store.controller;

import com.cscie97.ledger.CommandProcessor;

public interface Command {

	CommandProcessor commandProcessor = new CommandProcessor();

	public abstract void execute();

}
