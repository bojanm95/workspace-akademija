package main;

import java.io.Serializable;
import java.util.ArrayList;

import exception.PogresanUnosImenaException;
import helper.UnosInt;
import helper.UnosString;
import interfaces.Meni;


public class Kurs implements Meni, Serializable
{

	
	private static final long serialVersionUID = 1L;
	
	//deklaracija varijabli
	private String naziv;
	private int fondCasova;
	private ArrayList<OcjenjiveKompetencije> listaKompetencija;

	
	public Kurs(String naziv, int fondCasova) throws PogresanUnosImenaException
	{
		super();
		this.naziv = naziv;
		if("".equals(naziv)) {
			throw new PogresanUnosImenaException("Naziv kursa ne moze biti prazan.");
		}
		this.fondCasova = fondCasova;

		this.listaKompetencija = new ArrayList<>();
	}


	// dodavanje novih kompetencija u kurs koje su predstavljene kao ocjenjive kompetencije
	public void dodajKompetenciju()
	{

		System.out.println("Unesite naziv kompetencije: ");
		String naziv = UnosString.unos();
		OcjenjiveKompetencije komp = new OcjenjiveKompetencije(naziv);
		listaKompetencija.add(komp);

	}

	public void printKompetencija()
	{
		int brojac = 1;
		System.out.println("Lista kompetencija: ");
		for (int i = 0; i < listaKompetencija.size(); i++)
		{
			System.out.print(brojac + ". ");
			listaKompetencija.get(i).ispis();
			brojac++;
		}
		System.out.println("----------------------------------------------------");
	}

	@Override
	public String toString()
	{
		return "Kurs: " + naziv + " Fond casova: " + fondCasova;
	}
	// meni kursa koji se poziva unutar akademije
	@Override
	public void opcije()
	{

		boolean izlaz = true;
		while (true)
		{
			System.out.println("*********         MENI KURS       **********");
			System.out.println("[1] Dodaj kompetencije");
			System.out.println("[2] Ukloni kompetencije");
			System.out.println("[3] Ispisi kompetencije");			
			System.out.println("[4] Izmjeni naziv");			
			System.out.println("[5] Izmjeni fond casova");			
			System.out.println("[0] Nazad");
			int odg = UnosInt.unos();
			switch (odg) {
			case 0:
				izlaz = false;
				break;
			case 1:
				System.out.println("*****************DODAJ KOMPETENCIJU******************");
				dodajKompetenciju();
				break;
			case 2:
				System.out.println("*****************UKLONI KOMPETENCIJU******************");
				ukloniKompetenciju();
				break;
			case 3:
				System.out.println("*****************ISPIS KOMPETENCIJA******************");
				printKompetencija();
				break;
			case 4:
				System.out.println("*****************IZMJENA NAZIVA******************");
				System.out.println(getNaziv());
				System.out.println("Unesite novi naziv: ");
				setNaziv(UnosString.unos());
				break;
			case 5:
				System.out.println("*****************IZMJENA FONDA CASOVA******************");
				System.out.println(getFondCasova());
				System.out.println("Unesite novi fond casova: ");
				setFondCasova(UnosInt.unos());
				break;
			default:
				break;
			}

			if (!izlaz)
			{
				break;
			}
		}

	}


	public void ukloniKompetenciju()
	{
		
		printKompetencija();
		System.out.println("Unesite redni broj kompetencije koju zelite da uklonite: ");
		while (true)
		{
			int odg = UnosInt.unos();
			if (odg > listaKompetencija.size() || odg < 1)
			{
				System.out.println("Niste unijeli validan redni broj.");
			} else
			{
				listaKompetencija.remove(listaKompetencija.get(odg - 1));
				System.out.println("Uspjesno uklonjena kompetencija!");
				break;
			}
		}
	}

	
	public void ispis()
	{

		System.out.println(this.toString());
		printKompetencija();

		
	}


	//geteri i seteri
	//=============================================================
	public ArrayList<OcjenjiveKompetencije> getListaKompetencija()
	{
		return listaKompetencija;
	}



	public String getNaziv()
	{
		return naziv;
	}



	public void setNaziv(String naziv)
	{
		this.naziv = naziv;
	}



	public int getFondCasova()
	{
		return fondCasova;
	}



	public void setFondCasova(int fondCasova)
	{
		this.fondCasova = fondCasova;
	}
	
	//=============================================================
	

	
}
