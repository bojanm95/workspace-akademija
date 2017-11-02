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
		//preuzimanje osoba iz generacije
		this.polazniciGeneracija = polaznik;
		this.zaposleniGeneracija = zaposleni;
		//kreiranje novih lista za kurs unutar generacije
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
					if(dodan == true)
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

	
	// ocjenjivanje polaznika za njegove stecene vjestine
	public void ocjeniKompetenciju()
	{
		if (!kurs.getListaKompetencija().isEmpty())
		{
			if (!polazniciKurs.isEmpty())
			{
				System.out.println();
				
				int ocjena;

				// Ispis polaznika indeksirano od 1 - n
				try
				{
					Printer.ispisListe(polazniciKurs);
				} catch (PraznaListaException e)
				{
					e.getMessage();
				}
				int indexPolaznika;

				// Odabir polaznika gdje se uneseni index od strane korisnika umanjuje za 1
				System.out.println();
				System.out.print("Odaberite polaznika kojeg ocjenjujete: ");
				indexPolaznika = UnosInt.unos() - 1;
				System.out.println();

				for (int i = 0; i < kurs.getListaKompetencija().size(); i++)
				{
					// Prolaz kroz listu korisnikovih kompetencija gdje se provjerava da li je
					// kompetencija iz kursa vec ocjenjena
					boolean graded = false;
					for (int j = 0; j < polazniciKurs.get(indexPolaznika).getStecenaZnanja().size(); j++)
						if (polazniciKurs.get(indexPolaznika).getStecenaZnanja().get(j).naziv
								.equals(kurs.getListaKompetencija().get(i).naziv))
						{
							graded = true;
							break;
						}

					// Ako je kompetencija prethodno ocjenjena, prelazi se na sledecu kompetenciju
					if (graded)
						continue;

					// Ocjenjivanje kompetencije koja nije prethodno bila ocjenjena
					System.out.print("Ocjena za " + kurs.getListaKompetencija().get(i) + " kompetenciju: ");
					while (true)
					{
						ocjena = UnosInt.unos();

						if (ocjena < 1 || ocjena > 10)
							System.out.print("Ocjena mora biti u opsegu [1 - 5].\nUnesite ponovo: ");
						else
							break;
					}

						OcjenjiveKompetencije ocj = new OcjenjiveKompetencije(kurs.getListaKompetencija().get(i).getNaziv());
						ocj.setOcjena(ocjena);
						polazniciKurs.get(indexPolaznika).getStecenaZnanja().add(ocj);
						
					
				}
			}
			else
				System.out.println("Kurs nema polaznika koje mozete ocjeniti!");
		}
		else
			System.out.println("Kurs nema kompetencija koje mozete ocjeniti!");

	}

	 
	//meni za kurs u generaciji koji se poziva unutar same generacije
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
				ocjeniKompetenciju();
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

	//geteri i seteri
	//============================
	public Kurs getKurs()
	{
		return kurs;
	}

	public void setKurs(Kurs kurs)
	{
		this.kurs = kurs;
	}
	//============================

}
