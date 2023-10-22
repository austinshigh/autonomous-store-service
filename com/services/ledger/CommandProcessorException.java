package com.cscie97.ledger;

/**
 * Returned from the CommandProcessor methods in response to an error condition.
 * Captures the command that was attempted and the reason for the failure.
 * When commands are read from a file, the line number of the command is included as well.
 *
 * @author austinhigh
 */
public class CommandProcessorException extends Exception{

    private String command;
    private String reason;
    private int lineNumber;


    /**
     * Class Constructor, handles caught CommandProcessorExceptions.
     * @param e
     */
    public CommandProcessorException(CommandProcessorException e) {
        super();
        this.command = e.getCommand();
        this.reason = e.getReason();
    }

    /**
     * Class Constructor specifying reason for exception from Ledger Exception.
     * @param e
     */
    public CommandProcessorException(LedgerException e) {
        super();
        this.reason = e.getReason();
    }

    /**
     * Class Constructor specifying reason for exception.
     * @param reason
     */
    public CommandProcessorException(String reason) {
        super(reason);
        this.reason = reason;
    }

    /**
     * to string
     * @return {@link String}
     * @see String
     */
    @Override
    public String toString() {
        return "CommandProcessorException:" +
                "\ncommand = '" + command + '\'' +
                "\nreason = '" + reason + '\'' +
                "\nlineNumber = " + lineNumber;
    }

    /**
     * get reason
     *
     * @return {@link String}
     * @see String
     */
    public String getReason() {
        return reason;
    }

    /**
     * get line number
     *
     * @return {@link int}
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * get command
     *
     * @return {@link String}
     * @see String
     */
    public String getCommand() {
        return command;
    }

    /**
     * set command
     *
     * @param command command
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * set reason
     *
     * @param reason reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * set line number
     *
     * @param lineNumber lineNumber
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
