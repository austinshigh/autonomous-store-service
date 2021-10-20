package com.cscie97.ledger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Utility class for feeding the Ledger a set of operations using command syntax.
 *
 * @author austinhigh
 */
public class CommandProcessor {

    private static Ledger currentLedger;
    private static String accessType;

    /**
     * Compares CLI input to available methods, runs appropriate method.
     * This method parses the input from processCommandFile.
     * It splits the input on spaces using a regular expression,
     * while ignoring spaces between quotations.
     * "Test Comment" is preserved as a single String object.
     * <p>
     * This method will run valid CLI arguments, and throw errors providing
     * feedback to the user for incorrect arguments.
     *
     * @param command command
     * @throws CommandProcessorException com.cscie97.ledger. command processor exception
     * @return
     */
    public static String processCommand(String command) throws CommandProcessorException {
        try {
            // separate input on whitespace, unless whitespace within quotations
            ArrayList < String > commands = new ArrayList < String > ();
            Pattern regex = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'");
            // run regular expression comparison on input
            Matcher regexMatcher = regex.matcher(command);
            // add each new string to arraylist
            while (regexMatcher.find()) {
                commands.add(regexMatcher.group());
            }
            String firstArg = commands.get(0);
            switch (firstArg) {
                // compare first word in line to determine method to call
                case "#":
                    // print comments
                    System.out.println(command);
//                case "access-type":
//                    String access = commands.get(1);
//                    if (access.equals("admin") || (access.equals("user"))) {
//                        // if valid input, set class variable to appropriate access type
//                        accessType = access;
//                    } else {
//                        // throw exception displaying appropriate input form
//                        throw new CommandProcessorException("command should follow form:" +
//                                "\naccess-type <admin | user>");
//                    }
                case "create-ledger":
                    if (commands.size() != 6) {
                        // throw exception if incorrect number of command line arguments
                        throw new CommandProcessorException("command should follow form:" +
                                "\ncreate-ledger <name> description <description> seed <seed>");
                    }
                        // if admin access is set, instantiate current ledger class variable
                        currentLedger = new Ledger(commands.get(1), commands.get(3), commands.get(5));
                        try {
                            currentLedger.fundLedger();
                            return("ledger created");
                        } catch (LedgerException e) {
                            throw new CommandProcessorException(e);
                        }
                case "create-account":
                    // create new account in current ledger
                    try {
                        currentLedger.createAccount(commands.get(1));
                        return("account created");
                    } catch (LedgerException e) {
                        throw new CommandProcessorException(e);
                    }
                case "get-account-balance":
                    // print account balance for specified account
                    try {
                        return(Integer.toString(currentLedger.getAccountBalance(commands.get(1))));
                    } catch (LedgerException e) {
                        throw new CommandProcessorException(e);
                    }
                case "process-transaction":
                    // process new transaction
                    if (commands.size() != 12) {
                        // throw exception if incorrect number of arguments
                        throw new CommandProcessorException("command should follow form:" +
                                "\nprocess-transaction <transaction-id> amount <amount> fee <fee> " +
                                "note <note> payer <account-address> receiver <account-address>");
                    };
                    // instantiate new transaction
                    Transaction tx = new Transaction(Integer.parseInt(commands.get(1)),
                            Integer.parseInt(commands.get(3)),
                            Integer.parseInt(commands.get(5)),
                            commands.get(7),
                            commands.get(9),
                            commands.get(11));
                    // process transaction
                    try {
                        currentLedger.processTransaction(tx);
                    } catch (LedgerException e) {
                        throw new CommandProcessorException(e);
                    }
                case "get-account-balances":
                    // get account balances for all accounts in current ledger
                    try {
                        return(currentLedger.getAccountBalances().toString());
                    } catch (LedgerException e) {
                        throw new CommandProcessorException(e);
                    }
                case "get-block":
                    // call toString method for specified block, displaying relevant block information
                    try {
                        return(currentLedger.getBlock(Integer.parseInt(commands.get(1))).toString());
                    } catch (LedgerException e) {
                        throw new CommandProcessorException(e);
                    }
                case "set-block-hash":
                    // set the hash of the specified block to new hash input via CLI
                    // only sets the hash for the deep copy returned by getBlock() does not modify blockchain
                    try {
                        currentLedger.getBlock(Integer.parseInt(commands.get(1))).setHash(commands.get(2));
                    } catch (LedgerException e) {
                        throw new CommandProcessorException(e);
                    }
                case "get-transaction":
                    // call toString method for specified transaction, displaying relevant block information
                    return(currentLedger.getTransaction(commands.get(1)).toString());
                case "set-transaction-amount":
                    // set the amount of the specified transaction to new amount input via CLI
                    // only sets the amount for the deep copy returned by getTransaction() does not modify blockchain
                    currentLedger.getTransaction(commands.get(1)).setAmount(Integer.parseInt(commands.get(2)));
                case "validate":
                    // validate the blockchain
                    try {
                        currentLedger.validate();
                        return("blockchain validated successfully");
                    } catch (LedgerException e) {
                        throw new CommandProcessorException(e);
                    }
                default:
                    // catch all invalid arguments
                    throw new CommandProcessorException("invalid argument");
            }
        } catch (CommandProcessorException e) {
            e.setCommand(command);
            throw new CommandProcessorException(e);
        }
    }

    /**
     * Scans input file line by line,
     * running the processCommmand() method on each line.
     * Also tracks the current line in the input file,
     * and will set the line number when a CommandProcessorException is thrown.
     *
     * @param file file
     */
    public void processCommandFile(String file) {
        try {
            // get script file in test folder specified as parameter
            File myObj = new File("com/cscie97/ledger/test/" + file);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                // while reader has next line count lines and run process command
                String data = myReader.nextLine();
                i++;
                try {
                    processCommand(data);
                } catch (CommandProcessorException e) {
                    // catch error, setting line number in script file that error occurred on
                    e.setLineNumber(i);
                    // print error
                    System.out.println(e);
                }
            }
            // close reader
            myReader.close();
        } catch (FileNotFoundException e) {
            // if file not found, print exception
            System.out.println(e);
        }
    }
}