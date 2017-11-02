package osoba;

import exception.JmbgException;
import exception.PogresanUnosImenaException;

public class Mentor extends Zaposleni
{
	
	

	public Mentor(String ime, String prezime, String jmbg, String zvanje) throws JmbgException, PogresanUnosImenaException
	{
		super(ime, prezime, jmbg, zvanje);
	}
	
	

}
