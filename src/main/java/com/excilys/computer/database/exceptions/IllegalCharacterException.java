package main.java.com.excilys.computer.database.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IllegalCharacterException extends Exception{
	private static final long serialVersionUID = 1L;
	final private static Logger logger = LogManager.getLogger(IllegalCharacterException.class);
	
	final private String message = "Speacial characters are not allowed!";
	
	public IllegalCharacterException() {
		logger.info("Speacial characters are not allowed!");
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
