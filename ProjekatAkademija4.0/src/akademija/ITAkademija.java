package akademija;

import exception.PogresanUnosImenaException;

public class ITAkademija extends Akademija
{

	
	private static final long serialVersionUID = 1L;

	public ITAkademija(String naziv) throws PogresanUnosImenaException
	{
		super(naziv);
	}
	
	@Override
	public String toString()
	{
		return "IT akademija: " + naziv;
	}


	

}
