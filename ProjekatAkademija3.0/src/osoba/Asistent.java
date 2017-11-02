package osoba;

import exception.JmbgException;
import exception.PogresanUnosImenaException;

public class Asistent extends Zaposleni
{

	public Asistent(String ime, String prezime, String jmbg, String zvanje) throws JmbgException, PogresanUnosImenaException
	{
		super(ime, prezime, jmbg, zvanje);
	}
	
	

}
