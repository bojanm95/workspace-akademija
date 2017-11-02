package akademija;

import exception.PogresanUnosImenaException;

public class LikovnaAkademija extends Akademija
{

	public LikovnaAkademija(String naziv) throws PogresanUnosImenaException
	{
		super(naziv);
	}
	
	
	@Override
	public String toString()
	{
		return "Likovna akademija: " + naziv;
	}
	

}
