package com.services.store.model;

import com.services.ledger.LedgerException;
import com.services.store.authentication.AuthenticationServiceException;
import com.services.store.controller.ControllerException;

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

	public CommandProcessorException(AuthenticationServiceException e) {
		this.reason = e.getReason();
	}

    public String toString() {
		return "services.store.model.CommandProcessorException:\ncommand = '" + this.command + "'\nreason = '" + this.reason + "'\nlineNumber = " + this.lineNumber;
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
