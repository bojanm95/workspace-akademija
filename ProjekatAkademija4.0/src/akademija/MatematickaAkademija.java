package akademija;

import exception.PogresanUnosImenaException;

public class MatematickaAkademija extends Akademija
{

	public MatematickaAkademija(String naziv) throws PogresanUnosImenaException
	{
		super(naziv);
	}
	
	
	@Override
	public String toString()
	{
		return "Matematicka akademija: "+ naziv;
	}

}
