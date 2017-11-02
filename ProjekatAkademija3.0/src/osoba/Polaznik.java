package osoba;

import java.util.ArrayList;

import exception.JmbgException;
import exception.PogresanUnosImenaException;
import exception.PraznaListaException;
import helper.Printer;
import helper.UnosInt;
import helper.UnosString;
import interfaces.Meni;
import main.Kompetencija;
import main.OcjenjiveKompetencije;

public class Polaznik extends Osoba implements Meni
{

	private String brojIndeksa;
	private ArrayList<Kompetencija> interesovanja;
	private ArrayList<Kompetencija> prethodnoObrazovanje;
	private ArrayList<OcjenjiveKompetencije> stecenaZnanja;

	public Polaznik(String ime, String prezime, String jmbg, String brojIndeksa) throws PogresanUnosImenaException, JmbgException
	{
		super(ime, prezime, jmbg);
		this.brojIndeksa = brojIndeksa;
		this.interesovanja = new ArrayList<>();
		this.prethodnoObrazovanje = new ArrayList<>();
		this.setStecenaZnanja(new ArrayList<>());

	}

	public void dodajPrethodnoObrazovanje()
	{
		System.out.println("Unesite naziv prethodnog obrazovanja:");
		String naziv = UnosString.unos();
		Kompetencija obrazovanje = new Kompetencija(naziv);
		prethodnoObrazovanje.add(obrazovanje);
	}

	public void dodajInteresovanja()
	{
		System.out.println("Unesite naziv interesovanja: ");
		String naziv = UnosString.unos();
		Kompetencija komp = new Kompetencija(naziv);
		interesovanja.add(komp);

	}

	public void printInteresovanja()
	{
		System.out.println("\nLista interesovanja: ");
		try
		{
			Printer.ispisListe(interesovanja);
		} catch (PraznaListaException e)
		{
			System.out.println(e.getMessage());
		}

	}

	public void printObrazovanja()
	{
		System.out.println("Lista prethodnih obrazovanja: ");
		try
		{
			Printer.ispisListe(prethodnoObrazovanje);
		} catch (PraznaListaException e)
		{
			System.out.println(e.getMessage());
		}
	}

	@Override
	public String toString()
	{
		return ime + " " + prezime + " " + jmbg + " " + brojIndeksa;
	}

	public void ispis()
	{

		System.out.println(this.toString());
		printInteresovanja();
		printObrazovanja();
		printStecenaZnanja();
		

	}
	
	public void printStecenaZnanja()
	{

		System.out.println("Stecena znanja: ");
		try
		{
			Printer.ispisListe(stecenaZnanja);
		} catch (PraznaListaException e)
		{
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void opcije()
	{
		boolean izlaz = true;

		while (true)
		{
			izlaz = true;
			System.out.println("*********         MENI POLAZNIK       **********");
			System.out.println("[1] Dodaj interesovanja");
			System.out.println("[2] Ispisi interesovanja");
			System.out.println("[3] Dodaj prethodna obrazovanja");
			System.out.println("[4] Ispisi prethodna obrazovanja");
			System.out.println("[5] Ispisi stecena znanja");
			System.out.println("[6] Izmjeni ime");
			System.out.println("[7] Izmjeni prezime");
			System.out.println("[8] Izmjeni jmbg");
			System.out.println("[0] Nazad");
			int odgovor = UnosInt.unos();
			switch (odgovor) {
			case 0:
				izlaz = false;
				break;
			case 1:
				System.out.println("*****************DODAJ INTERESOVANJA******************");
				dodajInteresovanja();
				break;
			case 2:
				System.out.println("*****************ISPIS INTERESOVANJA******************");
				printInteresovanja();
				break;
			case 3:
				System.out.println("*****************DODAJ PRETHODNA OBRAZOVANJA******************");
				dodajPrethodnoObrazovanje();
				break;
			case 4:
				System.out.println("*****************ISPIS PRETHODNIH OBRAZOVANJA******************");
				printObrazovanja();
				break;
			case 5:
				System.out.println("*****************ISPIS STECENIH ZNANJA******************");
				printStecenaZnanja();
				break;
			case 6:
				System.out.println("*****************IZMJENI IME******************");
				System.out.println(getIme());
				System.out.println("Unesite novo ime: ");
				try
				{
					setIme(UnosString.unos());
				} catch (PogresanUnosImenaException e1)
				{
					e1.getMessage();
				}
				break;
			case 7:
				System.out.println("*****************IZMJENI PREZIME******************");
				System.out.println(getPrezime());
				System.out.println("Unesite novo prezime: ");
				try
				{
					setPrezime(UnosString.unos());
				} catch (PogresanUnosImenaException e1)
				{
					e1.getMessage();
				}
				break;
			case 8:
				System.out.println("*****************IZMJENI JMBG******************");
				System.out.println(getJmbg());
				System.out.println("Unesite novi JMBG: ");
				try
				{
					setJmbg(UnosString.unos());
				} catch (JmbgException e)
				{
					System.out.println(e.getMessage());
				}
				break;
			default:
				System.out.println("Pogresan unos.");
				break;
			}
			if(!izlaz) {
				break;
			}
		}
	}

	public ArrayList<OcjenjiveKompetencije> getStecenaZnanja()
	{
		return stecenaZnanja;
	}

	public void setStecenaZnanja(ArrayList<OcjenjiveKompetencije> stecenaZnanja)
	{
		this.stecenaZnanja = stecenaZnanja;
	}

}
