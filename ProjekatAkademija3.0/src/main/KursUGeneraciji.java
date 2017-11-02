package main;

import java.util.ArrayList;
import exception.PraznaListaException;
import helper.Printer;
import helper.UnosInt;
import interfaces.Meni;
import osoba.Asistent;
import osoba.Mentor;
import osoba.Polaznik;
import osoba.Zaposleni;

public class KursUGeneraciji implements Meni
{
	// preuzeti kurs
	private Kurs kurs;
	// preuzete osobe
	private ArrayList<Zaposleni> zaposleniGeneracija;
	private ArrayList<Polaznik> polazniciGeneracija;
	// nove liste
	private ArrayList<Zaposleni> zaposleniKurs;
	private ArrayList<Polaznik> polazniciKurs;

	public KursUGeneraciji(Kurs kurs, ArrayList<Zaposleni> zaposleni, ArrayList<Polaznik> polaznik)
			throws PraznaListaException
	{
		this.kurs = kurs;
		this.polazniciGeneracija = polaznik;
		this.zaposleniGeneracija = zaposleni;

		this.zaposleniKurs = new ArrayList<>();
		this.polazniciKurs = new ArrayList<>();
		if (zaposleniGeneracija.isEmpty() || polazniciGeneracija.isEmpty())
		{
			throw new PraznaListaException();
		}

	}

	private void dodajPolaznikaKursu()
	{
		
		boolean dodan;
		if (polazniciGeneracija.isEmpty())
		{
			System.out.println("Generacija nema polaznika! Unesite prvo polaznike.");
		} else
		{

			try
			{
				Printer.ispisListe(polazniciGeneracija);
			} catch (PraznaListaException e)
			{
				System.out.println(e.getMessage());
			}
			System.out.println("Unesite redni broj polaznika kojeg zelite da dodate kursu: ");
			while (true)
			{
				dodan = false;
				int retVal = UnosInt.unos();
				if (retVal > polazniciGeneracija.size() || retVal < 1)
				{
					System.out.println("Pogresan unos.");
				} else
				{
					Polaznik polaznik = polazniciGeneracija.get(retVal - 1);
					dodan = polazniciKurs.add(polaznik);
					if(dodan = true)
						System.out.println("Polaznik je dodan!");
					else
						System.out.println("Polaznik nije dodan!");
					break;
				}
			}
		}
	}

	private void dodajZaposlenogKursu()
	{
		if (zaposleniGeneracija.isEmpty())
		{
			System.out.println("Generacija nema zaposlenih! Unesite prvo zaposlene.");
		} else
		{

			ArrayList<Mentor> mentori = new ArrayList<>();
			ArrayList<Asistent> asistenti = new ArrayList<>();
			for (Zaposleni radnik : zaposleniGeneracija)
			{
				if (radnik instanceof Mentor)
					mentori.add((Mentor) radnik);
				if (radnik instanceof Asistent)
					asistenti.add((Asistent) radnik);
			}

			System.out.println("Unesite da li zelite da dodate mentora - [1] ili asistenta - [2]: ");
			int odabir = UnosInt.unos();
			if (odabir == 1)
			{
				System.out.println("Lista dostupnih mentora:");
				try
				{
					Printer.ispisListe(mentori);
				} catch (PraznaListaException e)
				{
					System.out.println(e.getMessage());
				}
				while (true)
				{
					System.out.println("Izaberite redni broj mentora kojeg zelite da dodate kursu: ");
					int odg = UnosInt.unos();
					if (odg > mentori.size() || odg < 1)
					{
						System.out.println("Niste unijeli validan redni broj.");
					} else
					{
						
						if(zaposleniKurs.add(mentori.get(odg - 1)))
							System.out.println("Mentor je dodan!");
						else
							System.out.println("Mentor nije dodan!");
						break;
					}
				}
			} else if (odabir == 2)
			{
				System.out.println("Lista dostupnih asistenata:");
				try
				{
					Printer.ispisListe(asistenti);
				} catch (PraznaListaException e)
				{
					System.out.println(e.getMessage());
				}
				while (true)
				{
					System.out.println("Izaberite redni broj asistenta kojeg zelite da dodate kursud: ");
					int odg = UnosInt.unos();
					if (odg > asistenti.size() || odg < 1)
					{
						System.out.println("Niste unijeli validan redni broj.");
					} else
					{
						
						if(zaposleniKurs.add(asistenti.get(odg - 1)))
							System.out.println("Asistent je dodan!");
						else
							System.out.println("Asistent nije dodan!");
						break;
					}
				}

			} else
			{
				System.out.println("Pogresan unos! Unos moze da bude 1 ili 2.");
			}
		}
	}

	private void printZaposleni()
	{

		if (zaposleniKurs.isEmpty())
		{
			System.out.println("Kurs nema zaposlenih! Unesite prvo zaposlene.");
		} else
		{
			System.out.println("\n Lista mentora: ");
			for (Zaposleni radnik : zaposleniKurs)
			{
				if (radnik instanceof Mentor)
				{
					int brojac = 1;
					System.out.print(brojac + ". ");
					radnik.ispis();
					brojac++;
				}
			}
			System.out.println("\n Lista asistenata: ");
			for (Zaposleni radnik : zaposleniKurs)
			{
				if (radnik instanceof Asistent)
				{
					int brojac = 1;
					System.out.print(brojac + ". ");
					radnik.ispis();
					brojac++;
				}
			}
		}
	}

	private void printPolaznik()
	{

		if (polazniciKurs.isEmpty())
		{
			System.out.println("Kurs nema polaznika! Unesite prvo polaznike.");
		} else
		{
			int brojac = 1;

			System.out.println("Lista polaznika: ");
			for (Polaznik polaznik : polazniciKurs)
			{
				System.out.print(brojac + ". ");
				polaznik.ispis();
				brojac++;
			}
		}

	}

	public void ocjeniPolaznika()
	{

		if (polazniciKurs.isEmpty())
		{
			System.out.println("Kurs nema polaznika za ocjenjivanje.");
		} else
		{
			printPolaznik();
			System.out.println("Unesite redni broj polaznika kog zelite da ocjenite: ");
			while (true)
			{
				int odg = UnosInt.unos();
				if (odg > polazniciKurs.size() || odg < 1)
				{
					System.out.println("Niste unijeli validan redni broj.");
				} else
				{
					Polaznik polaznik = polazniciKurs.get(odg - 1);
					System.out.println("Unesite redni broj kompetencije za koju zelite da ga ocjenite: ");
					kurs.printKompetencija();
					odg = UnosInt.unos();
					OcjenjiveKompetencije komp = kurs.getListaKompetencija().get(odg - 1);
					
						OcjenjiveKompetencije kompetencija = new OcjenjiveKompetencije(komp.getNaziv());
						if(polaznik.getStecenaZnanja().contains(kompetencija)) {
							komp.ocjeni(komp);
							break;
						}
						kompetencija.ocjeni(kompetencija);
						polaznik.getStecenaZnanja().add(kompetencija);
						break;
				
				}
			}
		}
	}

	/*
	 * public static void main(String[] args) throws PogresanUnosImenaException,
	 * JmbgException, PraznaListaException { ArrayList<Zaposleni> zap = new
	 * ArrayList<>(); ArrayList<Polaznik> pol = new ArrayList<>(); zap.add(new
	 * Mentor("ime","prezime","0801995160032","doc")); pol.add(new Polaznik("naziv",
	 * "nazim", "0801995160032", "9299"));
	 * 
	 * Kurs kurs = new Kurs("java", 123); kurs.dodajKompetenciju(); KursUGeneraciji
	 * kursa = new KursUGeneraciji(kurs, zap, pol); kursa.opcije(); }
	 */

	@Override
	public void opcije()
	{
		boolean izlaz = false;

		while (true)
		{

			System.out.println("*********         MENI KURS       **********");
			System.out.println("[1] Dodaj polaznika");
			System.out.println("[2] Dodaj zaposlenog");
			System.out.println("[3] Prikazi zaposlene");
			System.out.println("[4] Prikazi polaznike");
			System.out.println("[5] Ocjeni polaznike");
			System.out.println("[0] Nazad");
			int odgovor = UnosInt.unos();
			switch (odgovor) {
			case 0:
				izlaz = true;
				break;
			case 1:
				System.out.println("*****************DODAJ POLAZNIKA******************");
				dodajPolaznikaKursu();
				break;
			case 2:
				System.out.println("*****************DODAJ ZAPOSLENOG******************");
				dodajZaposlenogKursu();
				break;
			case 3:
				System.out.println("*****************ISPISI ZAPOSLENOG******************");
				printZaposleni();
				break;
			case 4:
				System.out.println("*****************ISPISI POLAZNIKA******************");
				printPolaznik();
				break;
			case 5:
				System.out.println("*****************OCJENI POLAZNIKA******************");
				ocjeniPolaznika();
				break;
			default:
				break;
			}

			if (izlaz)
			{
				break;
			}

		}

	}

	public void ispis()
	{

		System.out.println(this.toString());
		kurs.printKompetencija();
		printPolaznik();
		printZaposleni();

	}

	@Override
	public String toString()
	{
		return kurs.toString();
	}

	public Kurs getKurs()
	{
		return kurs;
	}

	public void setKurs(Kurs kurs)
	{
		this.kurs = kurs;
	}

}
