package com.cscie97.store.model;

import com.cscie97.ledger.Ledger;
import com.cscie97.ledger.LedgerException;
import com.cscie97.ledger.Transaction;
import com.cscie97.store.controller.StoreController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandProcessor {

	private static StoreModelService storeModelService;
	private static StoreController storeController;
	private static String[] idArray;
	private static Ledger ledgerService;

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
		 * @throws CommandProcessorException cscie97.store.command processor exception
		 */
		public static String processCommand(String command) throws CommandProcessorException {
			try {
				// separate input on whitespace, unless whitespace within quotations
				ArrayList< String > commands = new ArrayList < String > ();
				Pattern regex = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'");
				// run regular expression comparison on input
				Matcher regexMatcher = regex.matcher(command);
				// add each new string to arraylist
				while (regexMatcher.find()) {
					commands.add(regexMatcher.group().replace("\"",""));
				}
				if (commands.size() == 0){
					return ("\n");
				}
				String firstArg = commands.get(0);
				if (firstArg.equals("#")){
					// print comments
					return(command);
				}
				System.out.println(">>> " + command);
				switch (firstArg) {
					// compare first word in line to determine method to call
					case "define-store":
						if (commands.size() != 6) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\ndefine-store <identifier> name <name> address <address>" +
									"\n address should follow form 'street city state'");
						}
						// split store location into array for input
						idArray = commands.get(5).split(", ");
						try {
							ledgerService.createAccount(commands.get(1));
							Transaction tx = new Transaction(ThreadLocalRandom.current().nextInt(0, 99999999 + 1),
									20000,
									10,
									"new store",
									"master",
									commands.get(1));
							ledgerService.processTransaction(tx);
							return ("new store id: " + storeModelService.createStore(commands.get(1), commands.get(3), idArray[0], idArray[1], idArray[2]));
						}catch(StoreModelServiceException e){
							throw new CommandProcessorException(e);
						} catch (LedgerException e) {
							e.printStackTrace();
						}
					case "show-store":
						if (commands.size() != 2) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nshow-store <identifier>");
						}
						try {
							return(storeModelService.getStore(commands.get(1)).toString());
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "define-aisle":
						if (commands.size() != 8) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"define-aisle <store_id>:<aisle_number> name <name> description <description> location (floor | store_room)");
						}
						// split ids into array for input
						idArray = commands.get(1).split(":");
						try {
							return("aisle id : " + storeModelService.createAisle(idArray[0], idArray[1], commands.get(3), commands.get(5), commands.get(7)));
						}catch(StoreModelServiceException e){
							throw new CommandProcessorException(e);
					}
					case "show-aisle":
						if (commands.size() != 2) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nshow-aisle <store_id>:<aisle_number>");
						}
						// split ids into array for input
						idArray = commands.get(1).split(":");
						try {
							return(storeModelService.getAisle(idArray[0], idArray[1]).toString());
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "define-shelf":
						if (!(commands.size() != 10) && !(commands.size() != 8)) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"define-aisle <store_id>:<aisle_number> name <name> description <description> location (floor | store_room) temperature (frozen | refrigerated | ambient | warm | hot )");
						}
						// split ids into array for input
						idArray = commands.get(1).split(":");
						try {
							if (commands.size() == 10) {
								return("Shelf defined, new shelf id: " + storeModelService.createShelf(idArray[0], idArray[1], idArray[2], commands.get(3), commands.get(5), commands.get(7), commands.get(9)));
							}else{
								return("Shelf defined, new shelf id: " + storeModelService.createShelf(idArray[0], idArray[1], idArray[2], commands.get(3), commands.get(5), commands.get(7)));
							}
						}catch(StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "show-shelf":
						if (commands.size() != 2) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nshow-shelf <store_id>:<aisle_number>:<shelf_id>");
						}
						// split ids into array for input
						idArray = commands.get(1).split(":");
						try {
							return(storeModelService.showShelfDetails(idArray[0], idArray[1], idArray[2]));
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "define-inventory":
						if (commands.size() != 10) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"define-inventory <inventory_id> location <store_id>:<aisle_number>:<shelf_id> capacity <capacity> count <count> product <product_id>\n");
						}
						// split ids into array for input
						idArray = commands.get(3).split(":");
						try {
							return("Inventory Created, New Inventory Id:" + storeModelService.createInventory(
									idArray[0], idArray[1], idArray[2], commands.get(1), Integer.valueOf(commands.get(5)), Integer.valueOf(commands.get(7)), commands.get(9)
									));
						}catch(StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "update-inventory":
						if (commands.size() != 4) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"update-inventory <inventory_id> update_count <increment or decrement>\n");
						}
						try {
							return(storeModelService.updateInventory(commands.get(1), commands.get(3)));
						}catch(StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "define-product":
						if (!(commands.size() == 12) && !(commands.size() == 14)) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"define-product <product_id> name <name> description <description> size <weight:volume> category <category> unit_price <unit_price> temperature (frozen | refrigerated | ambient | warm | hot )");
						}
						try {
							if(commands.size() == 12) {
								// if temperature is not specified, use 6 parameters
								storeModelService.createProduct(commands.get(1), commands.get(3), commands.get(5), commands.get(7), commands.get(9), Integer.parseInt(commands.get(11)));
							}else{
								// if temperature is specified, use 7 parameters
								storeModelService.createProduct(commands.get(1), commands.get(3), commands.get(5), commands.get(7), commands.get(9), Integer.parseInt(commands.get(11)), commands.get(13));;
							}
							return("new product created, product id:" + commands.get(1));
						}catch(StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "show-product":
						if (commands.size() != 2) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nshow-product <product_id>");
						}
						try {
							return(storeModelService.getProduct(commands.get(1)).toString());
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "show-inventory":
						if (commands.size() != 2) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nshow-inventory <inventory_id>");
						}
						try {
							return(storeModelService.getInventoryItem(commands.get(1)).toString());
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "define-customer":
						if (commands.size() != 12) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"define-customer <customer_id> first_name <first_name> last_name <last_name> type (registered|guest) email_address <email> account <account_address>\n");
						}
						boolean registered = false;
						try {
							if (commands.get(7).equals("registered")) {
								// if customer is registered, transfer 200 credits to their account.
								registered = true;
								ledgerService.createAccount(commands.get(11));
								Transaction tx = new Transaction(ThreadLocalRandom.current().nextInt(0, 99999999 + 1),
										200,
										10,
										"new account",
										"master",
										commands.get(11));
								ledgerService.processTransaction(tx);
							}else{
								ledgerService.createAccount(commands.get(11));
							}
							return("customer id: " + storeModelService.createCustomer(commands.get(1), commands.get(3), commands.get(5), registered, commands.get(9), commands.get(11)));
						}catch(StoreModelServiceException e){
							throw new CommandProcessorException(e);
						} catch (LedgerException e) {
							e.printStackTrace();
						}
					case "show-customer":
						if (commands.size() != 2) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nshow-customer <customer_id>");
						}
						try {
							return(storeModelService.getCustomer(commands.get(1)).toString());
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "update-customer":
						if (commands.size() != 4) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nupdate-customer <customer_id> location <store:aisle>");
						}
						// split ids into array for input
						idArray = commands.get(3).split(":");
						try {
							return(storeModelService.updateCustomerLocation(commands.get(1), idArray[0], idArray[1]));
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "get-customer-basket":
						if (commands.size() != 2) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nget-customer-basket <customer_id>");
						}
						try {
							return("Customer Basket id: " + storeModelService.getCustomerBasketId(commands.get(1)));
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "define-basket":
						if (commands.size() != 2) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\ndefine-basket <basket_id>");
						}
						try {
							return("Basket id: " + storeModelService.defineBasket(commands.get(1)));
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "assign-basket":
						if (commands.size() != 4) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nassign-basket <basket_id> customer <customer_id>");
						}
						try {
							return("Basket id: " + storeModelService.associateBasket(commands.get(1),commands.get(3)));
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "add-basket-item":
						if (commands.size() != 6) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nadd_basket_item <basket_id> product <product_id> item_count <count>");
						}
						try {
							return(storeModelService.addBasketItem(commands.get(1),
									commands.get(3),
									commands.get(5)));
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "remove-basket-item":
						if (commands.size() != 6) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nremove_basket_item <basket_id> product <product_id> item_count <count>");
						}
						try {
							return(storeModelService.removeBasketItem(commands.get(1),
									commands.get(3),
									commands.get(5)));
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "clear-basket":
						if (commands.size() != 2) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nclear-basket <basket_id>");
						}
						try {
							storeModelService.clearBasket(commands.get(1));
							return("Basket has been cleared");
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "show-basket-items":
						if (commands.size() != 2) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nshow-basket-items <basket_id>");
						}
						try {
							return(storeModelService.showBasketItems(commands.get(1)));
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "compute-basket-weight":
						if (commands.size() != 2) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\ncompute-basket-weight <basket_id>");
						}
						try {
							return(storeModelService.computeBasketWeight(commands.get(1)));
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "define-device":
						if (commands.size() != 8) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"define-device <device_id> name <name> type (microphone|camera) location <store>:<aisle>");
						}
						// split ids into array for input
						idArray = commands.get(7).split(":");
						try {
							switch (commands.get(5).toLowerCase()){
								case "microphone":
								case "camera":
									// create sensor if user enters sensor name
									storeModelService.createSensor(commands.get(1), commands.get(3), commands.get(5), idArray[0], idArray[1]);
									return("sensor id:" + commands.get(1));
								case "robot":
								case "turnstile":
								case "speaker":
									// create appliance if user enters appliance name
									storeModelService.createAppliance(commands.get(1), commands.get(3), commands.get(5), idArray[0], idArray[1]);
									return("appliance id:" + commands.get(1));
								default:
									throw new CommandProcessorException("invalid type of device, must be of set (microphone, camera, robot, turnstile, speaker)");
							}
						}catch(StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "show-device":
						if (commands.size() != 2) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nshow-device <device_id>");
						}
						try {
							return(storeModelService.getDevice(commands.get(1)).toString());
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "find-nearest-robot":
						if (commands.size() != 4) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nfind-nearest-robot <store_id> aisle <aisle_id>");
						}
						try {
							return(storeModelService.findNearestRobot(commands.get(1), commands.get(3)));
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "find-nearest-speaker":
						if (commands.size() != 4) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nfind-nearest-robot <store_id> aisle <aisle_id>");
						}
						try {
							return(storeModelService.findNearestSpeaker(commands.get(1), commands.get(3)));
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "create-event":
						if (commands.size() != 4) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\ncreate-event <device_id> event <event>");
						}
						try {
							return(storeModelService.createEvent(commands.get(1), commands.get(3)));
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						} catch (LedgerException e) {
							e.printStackTrace();
						}
					case "create-command":
						if (commands.size() != 4) {
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\ncreate-command <device_id> message <command>");
						}
						try {
							return(storeModelService.createCommand(commands.get(1), commands.get(3)));
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "calculate-basket-total":
						if (commands.size() != 2){
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\ncalculate-basket-total <basket_id>");
						}
						try{
							return(String.valueOf(storeModelService.getBasketTotal(commands.get(1))));
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "open-turnstile":
						if (commands.size() != 2){
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nopen-turnstile <turnstile_id>");
						}
						try{
							return(storeModelService.openTurnstile(commands.get(1)));
						}catch (StoreModelServiceException e){
							throw new CommandProcessorException(e);
						}
					case "open-all-turnstiles":
						if (commands.size() != 2){
							// throw exception if incorrect number of command line arguments
							throw new CommandProcessorException("command should follow form:" +
									"\nopen-all-turnstiles <store_id>");
						}
						try{
							return(storeModelService.openAllTurnstiles(commands.get(1)));
						}catch (StoreModelServiceException e){
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
			ledgerService = new Ledger("test", "testService", "controller");
			ledgerService.fundLedger();
			storeModelService = new StoreModelService("authToken");
			storeController = new StoreController(storeModelService,ledgerService);
			storeModelService.attach(storeController);
			// get script file in test folder specified as parameter
			File myObj = new File("com/cscie97/store/test/" + file);
			Scanner myReader = new Scanner(myObj);
			int i = 0;
			while (myReader.hasNextLine()) {
				// while reader has next line count lines and run process command
				String data = myReader.nextLine();
				i++;
				try {
					System.out.println(processCommand(data));
				} catch (CommandProcessorException e) {
					// catch error, setting line number in script file that error occurred on
					e.setLineNumber(i);
					// print error
					System.out.println(e);
				}
			}
			// close reader
			myReader.close();
		} catch (FileNotFoundException | LedgerException e) {
			// if file not found, print exception
			System.out.println(e);
		}
	}

}
