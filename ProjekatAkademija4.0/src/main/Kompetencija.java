package main;

import java.io.Serializable;

public class Kompetencija implements Serializable
{
	
	
	private static final long serialVersionUID = 1L;
	//deklaracija varijabli
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
	//geteri i seteri
	//===================================
	public String getNaziv()
	{
		return naziv;
	}

	public void setNaziv(String naziv)
	{
		this.naziv = naziv;
	}
	//===================================
	
	

}
