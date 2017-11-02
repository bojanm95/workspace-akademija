package main;

public class Kompetencija
{
	
	protected String naziv;
	
	public Kompetencija(String naziv)
	{
		this.naziv = naziv;
	}
	
	@Override
	public String toString()
	{
		return "Kompetencija: " + naziv;
	}

	public String getNaziv()
	{
		return naziv;
	}

	public void setNaziv(String naziv)
	{
		this.naziv = naziv;
	}
	
	

}
