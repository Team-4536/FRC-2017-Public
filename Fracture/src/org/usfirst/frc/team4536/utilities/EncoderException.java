package org.usfirst.frc.team4536.utilities;

/**
 * @author Noah
 * Class for the exception for checking if an Encoder is unplugged or not working
 */
public class EncoderException extends Exception {
	
	public EncoderException() {
		super();
		System.out.println("Encoder Exception thrown");
	}
	
}
