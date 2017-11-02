package exception;

public class DuplikatException extends Exception
{

	private static final long serialVersionUID = 1L;
	
	
	public DuplikatException()
	{
	}
	
	@Override
	public String getMessage()
	{
		
		return "Element nije dodan. Lista vec sadrzi ovaj element.";
	}

}
