package exception;

public class PraznaListaException extends Exception
{

	private static final long serialVersionUID = 1L;

	
	public PraznaListaException()
	{
	}
	
	
	@Override
	public String getMessage()
	{
		return "Operacija neuspjela. Proslijedjena lista je prazna.";
	}
	
}
