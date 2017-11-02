package osoba;


import exception.JmbgException;
import exception.PogresanUnosImenaException;

public class Mentor extends Zaposleni
{
	
	
	private static final long serialVersionUID = 1L;

	public Mentor()
	{
		super();
	}

	public Mentor(String ime, String prezime, String jmbg, String zvanje) throws JmbgException, PogresanUnosImenaException
	{
		super(ime, prezime, jmbg, zvanje);
	}
	
	

}
