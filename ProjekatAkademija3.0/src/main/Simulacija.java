package main;

import java.util.ArrayList;
import java.util.Scanner;
import akademija.Akademija;
import akademija.ITAkademija;
import akademija.LikovnaAkademija;
import akademija.MatematickaAkademija;
import exception.PogresanUnosImenaException;
import exception.PraznaListaException;
import helper.Printer;
import helper.UnosInt;
import helper.UnosString;
import interfaces.Meni;

public class Simulacija implements Meni
{
	private static ArrayList<Akademija> akademije;
	
	
	private Simulacija()
	{
		akademije = new ArrayList<>();	
	}
	
	public static ArrayList<Akademija> getInstanceAkademije()
	{
		if(akademije == null)
		{
			akademije = new ArrayList<>();
		}
		
		return akademije;
	}
	
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		new Simulacija().opcije();
		in.close();
	}

	@Override
	public void opcije()
	{
		boolean izlaz = false;
		while(true) {
		
		System.out.println("*****************AKADEMIJA******************");
		System.out.println("*********           MENI          **********");
		System.out.println("[1] Dodaj akademiju");
		System.out.println("[2] Izlistaj akademije");
		System.out.println("[3] Modifikuj akademiju");
		System.out.println("[0] Izlaz");
		int unos = UnosInt.unos();
		switch (unos) {
		case 0:
			System.out.println("Izlazak iz simulacije...");
			izlaz = true;
			break;
		case 1:
			System.out.println("*****************DODAJ AKADEMIJU******************");
			dodajAkademiju();
			break;
		case 2:
			System.out.println("*****************IZLISTAJ AKADEMIJU******************");
			System.out.println("Lista akademija: ");
			for (int i = 0; i < akademije.size(); i++)
			{
				akademije.get(i).ispis();
			}
			break;
		case 3:
			System.out.println("*****************MODIFIKUJ AKADEMIJU******************");
			modifikujAkademiju();
			break;
		default:
			System.out.println("Pogresan unos.");
			break;
		}
		if(izlaz)
			break;
		}
	}
	
	

	public void dodajAkademiju()
	{
		System.out.println("Izaberite tip akademije: \n[1] IT akademija\n[2] Likovna akademija\n[3] Matematicka akademija");
		int odgovor = UnosInt.unos();
		if(odgovor == 1 || odgovor == 2 || odgovor == 3) {
			System.out.println("Unesite naziv akademije: ");
			String naziv = UnosString.unos();
			if(odgovor == 1) {
				ITAkademija itAkademija;
				try
				{
					itAkademija = new ITAkademija(naziv);
					akademije.add(itAkademija);
				} catch (PogresanUnosImenaException e)
				{
					System.out.println(e.getMessage());
				}
			} else if(odgovor == 2) {
				LikovnaAkademija likovnaAkademija;
				try
				{
					likovnaAkademija = new LikovnaAkademija(naziv);
					akademije.add(likovnaAkademija);
				} catch (PogresanUnosImenaException e)
				{
					System.out.println(e.getMessage());
				}
			} else if(odgovor == 3) {
				MatematickaAkademija matematickaAkademija;
				try
				{
					matematickaAkademija = new MatematickaAkademija(naziv);
					akademije.add(matematickaAkademija);
				} catch (PogresanUnosImenaException e)
				{
					System.out.println(e.getMessage());
				}
			}
		} else {
			System.out.println("Pogresan unos. Moguce je unijeti 1, 2 ili 3.");
		}
	}
	
	public void modifikujAkademiju()
	{
		System.out.println("Lista akademija: ");
		try
		{
			Printer.ispisListe(akademije);
		} catch (PraznaListaException e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("Unesite redni broj akademije koju zelite da modifikujete: ");
		while (true)
		{
			int retVal = UnosInt.unos();
			if (retVal > akademije.size() || retVal < 1)
			{
				System.out.println("Pogresan unos.");
			} else
			{
				akademije.get(retVal - 1).opcije();
				break;
			}
		}
	}

}
