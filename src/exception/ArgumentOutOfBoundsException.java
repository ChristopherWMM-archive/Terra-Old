 package exception;

public class ArgumentOutOfBoundsException extends Exception {

	private static final long serialVersionUID = 1L;

	public ArgumentOutOfBoundsException() 
	{
		super("Error: Argument is not within the valid range.");
	}

	public ArgumentOutOfBoundsException(String message) 
	{
		super(message);
	}
}
