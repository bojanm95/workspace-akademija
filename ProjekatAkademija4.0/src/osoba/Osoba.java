package osoba;


import java.io.Serializable;

import exception.JmbgException;
import exception.PogresanUnosImenaException;

public abstract class Osoba implements Serializable
{
	
	
	
	private static final long serialVersionUID = 1L;
	//deklaracije varijabli
	protected String ime, prezime, jmbg;

	protected Osoba(String ime, String prezime, String jmbg) throws PogresanUnosImenaException, JmbgException 
	{
		
		
			setIme(ime);
			setPrezime(prezime);
			setJmbg(jmbg);
		
	}
	
	public Osoba()
	{
	}

	//geteri i seteri
	//===================================================================
	public String getIme()
	{
		
		return ime;
	}

	public void setIme(String ime) throws PogresanUnosImenaException
	{
		if (ime.contains(" "))
		{
			throw new PogresanUnosImenaException();
		}
		this.ime = ime;
	}

	public String getPrezime()
	{
		return prezime;
	}

	public void setPrezime(String prezime) throws PogresanUnosImenaException
	{
		if(prezime.contains(" ")) {
			throw new PogresanUnosImenaException("Unijeli ste pogresan format prezimena");
		}
		this.prezime = prezime;
	}

	public String getJmbg()
	{
		return jmbg;
	}

	public void setJmbg(String jmbg) throws JmbgException
	{
		this.jmbg = jmbg;
		provjeraJmbg();
	}
	//===================================================================

	//provjera unosa jmbga koji se poziva unutar konstruktora osobe
	public void provjeraJmbg() throws JmbgException
	{
		
		//ako je unos prazan
		if (jmbg == null)
		{
			throw new JmbgException("JMBG ne moze biti prazan.");
		}
		//ako je duzina unosa nije 13 karaktera
		if (jmbg.length() != 13)
		{
			throw new JmbgException("JMBG se mora sastojati od tacno 13 karaktera!");
		}
		//ako karakteri nisu brojevi
		for (int i = 0; i < jmbg.length(); i++)
		{
			char karakter = jmbg.charAt(i);
			if (!Character.isDigit(karakter)) {
				throw new JmbgException("JMBG ne moze sadrzati slova.");
			}
		}
		
		if (!Character.isDigit(jmbg.charAt(0))|| !Character.isDigit(jmbg.charAt(1)) || Integer.parseInt(jmbg.substring(0, 2)) > 31)
		{
			throw new JmbgException("Nepravilan format JMBG-a. Prva dva karaktera oznacavaju dan rodjenja.");
		}
		if (!Character.isDigit(jmbg.charAt(2)) || !Character.isDigit(jmbg.charAt(3)) || Integer.parseInt(jmbg.substring(2, 4)) > 12)
		{
			throw new JmbgException();
		}
		if (!Character.isDigit(jmbg.charAt(4)) || Integer.parseInt(jmbg.substring(4, 5)) == 0)
		{
			if (!Character.isDigit(jmbg.charAt(5)) || !Character.isDigit(jmbg.charAt(6)) ||Integer.parseInt(jmbg.substring(5, 7)) > 17)
			{
				throw new JmbgException("Nepravilan format JMBG-a. Godina rodjenja je pogresno unijeta.");
			}
			
				throw new JmbgException("Nepravilan format JMBG-a. Godina rodjenja je pogresno unijeta.");
		
		}
		if (!Character.isDigit(jmbg.charAt(5)) || !Character.isDigit(jmbg.charAt(6)) ||!Character.isDigit(jmbg.charAt(7)) ||Integer.parseInt(jmbg.substring(4, 7)) < 989)
		{
			throw new JmbgException("Nepravilan format JMBG-a. Godina rodjenja je pogresno unijeta.");
		}

	}
	
	
	@Override
	public String toString()
	{
		return "Ime: "+ime+" Prezime: "+prezime+" JMBG: "+jmbg;
	}

}
