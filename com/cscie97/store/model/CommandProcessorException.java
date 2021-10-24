package com.cscie97.store.model;

import com.cscie97.ledger.LedgerException;
import com.cscie97.store.controller.ControllerException;

public class CommandProcessorException extends Exception {
	private String command;
	private String reason;
	private int lineNumber;

	public CommandProcessorException(CommandProcessorException var1) {
		this.command = var1.getCommand();
		this.reason = var1.getReason();
	}

	public CommandProcessorException(StoreModelServiceException var1) {
		this.reason = var1.getReason();
	}

	public CommandProcessorException(ControllerException var1) { this.reason = var1.getReason();}

	public CommandProcessorException(String var1) {
		super(var1);
		this.reason = var1;
	}

	public CommandProcessorException(LedgerException var1) { this.reason = var1.getReason();}

	public String toString() {
		return "cscie97.store.model.CommandProcessorException:\ncommand = '" + this.command + "'\nreason = '" + this.reason + "'\nlineNumber = " + this.lineNumber;
	}

	public String getReason() {
		return this.reason;
	}

	public int getLineNumber() {
		return this.lineNumber;
	}

	public String getCommand() {
		return this.command;
	}

	public void setCommand(String var1) {
		this.command = var1;
	}

	public void setReason(String var1) {
		this.reason = var1;
	}

	public void setLineNumber(int var1) {
		this.lineNumber = var1;
	}
}
