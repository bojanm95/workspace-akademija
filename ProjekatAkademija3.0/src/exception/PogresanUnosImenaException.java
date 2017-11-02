package exception;

public class PogresanUnosImenaException extends Exception
{

	
	private static final long serialVersionUID = 1L;

	public PogresanUnosImenaException()
	{
		
	}
	
	public PogresanUnosImenaException(String message)
	{
		super(message);
	}
	
	@Override
	public String getMessage()
	{
		
		return "Unijeli ste pogresan format imena";
	}
	
}
