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

public abstract class Zaposleni extends Osoba implements Meni
{
	
	
	private static final long serialVersionUID = 1L;
	protected String zvanje;
	protected ArrayList<Kompetencija> vjestine;
	protected Zaposleni(String ime, String prezime, String jmbg, String zvanje) throws JmbgException, PogresanUnosImenaException
	{
		super(ime, prezime, jmbg);
		this.zvanje = zvanje;
		this.vjestine = new ArrayList<>();
	}
	
	public Zaposleni() {
		
	}
	
	
	public void dodajVjestinu()
	{
		System.out.println("Unesite naziv vjestine: ");
		String naziv = UnosString.unos();
		Kompetencija komp = new Kompetencija(naziv);
		vjestine.add(komp);

	}
	
	public void printVjestine(){
		System.out.println("Lista vjestina: ");
		try
		{
			Printer.ispisListe(vjestine);
		} catch (PraznaListaException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public String toString()
	{
		
		return ime + " " + prezime + " " + jmbg + " " + zvanje;
	}
	
	public void ispis() {
		
		System.out.println(this.toString());
		printVjestine();
		
	}
	
	@Override
	public void opcije()
	{
		boolean izlaz = true;

		while (true)
		{
			izlaz = true;
			System.out.println("*********         MENI ZAPOSLENI       **********");
			System.out.println("[1] Dodaj vjestine");
			System.out.println("[2] Ispisi vjestine");
			System.out.println("[3] Izmjeni ime");
			System.out.println("[4] Izmjeni prezime");
			System.out.println("[5] Izmjeni jmbg");
			System.out.println("[6] Izmjeni zvanje");
			System.out.println("[0] Nazad");
			int odgovor = UnosInt.unos();
			switch (odgovor) {
			case 0:
				izlaz = false;
				break;
			case 1:
				System.out.println("*****************DODAJ VJESTINE******************");
				dodajVjestinu();
				break;
			case 2:
				System.out.println("*****************ISPIS VJESTINA******************");
				printVjestine();
				break;
			case 3:
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
			case 4:
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
			case 5:
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
			case 6:
				System.out.println("*****************IZMJENI ZVANJE******************");
				System.out.println(getZvanje());
				System.out.println("Unesite novo zvanje: ");
				setZvanje(UnosString.unos());
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


	public String getZvanje()
	{
		return zvanje;
	}


	public void setZvanje(String zvanje)
	{
		this.zvanje = zvanje;
	}
	
	

}
