package akademija;

import java.util.ArrayList;

import exception.DuplikatException;
import exception.JmbgException;
import exception.PogresanUnosImenaException;
import exception.PraznaListaException;
import helper.Printer;
import helper.UnosInt;
import helper.UnosString;
import interfaces.Meni;
import main.Kurs;
import osoba.Asistent;
import osoba.Mentor;
import osoba.Polaznik;
import osoba.Zaposleni;

public abstract class Akademija implements Meni
{

	protected String naziv;
	protected ArrayList<Kurs> kursevi;
	protected ArrayList<Zaposleni> zaposleni;
	protected ArrayList<Polaznik> polaznici;
	protected ArrayList<Generacija> generacije;

	protected Akademija(String naziv) throws PogresanUnosImenaException
	{
		
		setNaziv(naziv);
		
		
		this.zaposleni = new ArrayList<>();
		this.polaznici = new ArrayList<>();
		this.kursevi = new ArrayList<>();
		this.generacije = new ArrayList<>();
	}

	// meni koji se poziva iz simulacije
	@Override
	public void opcije()
	{
		boolean izlaz = false;

		while (true)
		{

			System.out.println("*********         MENI AKADEMIJE       **********");
			System.out.println("[1] Dodaj kurs");
			System.out.println("[2] Prikazi kurseve");
			System.out.println("[3] Modifikuj kurseve");
			System.out.println("[4] Dodaj zaposlene akademiji");
			System.out.println("[5] Modifikuj zaposlenog");
			System.out.println("[6] Prikazi zaposlene");
			System.out.println("[7] Dodaj polaznike akademiji");
			System.out.println("[8] Modifikuj polaznika");
			System.out.println("[9] Ispisi polaznike");
			System.out.println("[10] Dodaj generaciju");
			System.out.println("[11] Modifikuj generaciju");
			System.out.println("[12] Prikazi generacije");
			System.out.println("[13] Izmjeni naziv akademiji");
			System.out.println("[0] Nazad");
			int odgovor = UnosInt.unos();
			switch (odgovor) {
			case 0:
				izlaz = true;
				break;
			case 1:
				System.out.println("*****************DODAJ KURS******************");
				dodajKurs();
				break;
			case 2:
				System.out.println("*****************ISPIS KURSEVA******************");
				printKursevi();
				break;
			case 3:
				System.out.println("*****************MODIFIKUJ KURS******************");
				modifikujKurs();
				break;
			case 4:
				System.out.println("*****************DODAJ ZAPOSLENOG******************");
				try
				{
					dodajZaposlenog();
				} catch (DuplikatException e)
				{
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				System.out.println("*****************MODIFIKUJ ZAPOSLENOG******************");
				modifikujZaposlenog();
				break;
			case 6:
				System.out.println("*****************ISPIS ZAPOSLENIH******************");
				printZaposleni();
				break;
			case 7:
				System.out.println("*****************DODAJ POLAZNIKA******************");
				try
				{
					dodajPolaznika();
				} catch (DuplikatException e)
				{
					System.out.println(e.getMessage());
				}
				break;
			case 8:
				System.out.println("*****************MODIFIKUJ POLAZNIKA******************");
				modifikujPolaznika();
				break;
			case 9:
				System.out.println("*****************ISPIS POLAZNIKA******************");
				printPolaznik();
				break;
			case 10:
				System.out.println("*****************DODAJ GENERACIJU******************");
				try
				{
					dodajGeneraciju();
				} catch (DuplikatException e)
				{
					System.out.println(e.getMessage());
				}
				break;
			case 11:
				System.out.println("*****************MODIFIKUJ GENERACIJU******************");
				modifikujGeneraciju();
				break;
			case 12:
				System.out.println("*****************ISPIS GENERACIJA******************");
				printGeneracija();
				break;
			case 13:
				System.out.println("*****************IZMJENI NAZIV******************");
				System.out.println(getNaziv());
				System.out.println("Unesite novi naziv: ");
				try
				{
					setNaziv(UnosString.unos());
				} catch (PogresanUnosImenaException e)
				{
					e.getMessage();
				}
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
	// modifikacija kurseva koji su dodani u akademiju
	
	private void modifikujKurs()
	{
		if (kursevi.isEmpty())
		{
			System.out.println("Akademija nema kurseva! Unesite prvo kurseve.");
		} else
		{

			printKursevi();
			System.out.println("Unesite redni broj kursa koju zelite da modifikujete: ");
			while (true)
			{
				int retVal = UnosInt.unos();
				if (retVal > kursevi.size() || retVal < 1)
				{
					System.out.println("Pogresan unos.");
				} else
				{
					kursevi.get(retVal - 1).opcije();
					break;
				}
			}
		}
		
	}
	// modifikacija polaznika koji su dodani u akademiju
	private void modifikujPolaznika()
	{
		if (polaznici.isEmpty())
		{
			System.out.println("Akademija nema polaznika! Unesite prvo polaznike.");
		} else
		{

			printPolaznik();
			System.out.println("Unesite redni broj polaznika koju zelite da modifikujete: ");
			while (true)
			{
				int retVal = UnosInt.unos();
				if (retVal > polaznici.size() || retVal < 1)
				{
					System.out.println("Pogresan unos.");
				} else
				{
					polaznici.get(retVal - 1).opcije();
					break;
				}
			}
		}
	}
	// modifikacija zaposlenih koji su dodani u akademiju
	private void modifikujZaposlenog()
	{

		if (zaposleni.isEmpty())
		{
			System.out.println("Akademija nema zaposlenih! Unesite prvo zaposlene.");
		} else
		{

			ArrayList<Mentor> mentori = new ArrayList<>();
			ArrayList<Asistent> asistenti = new ArrayList<>();
			for (Zaposleni radnik : zaposleni)
			{
				if (radnik instanceof Mentor)
					mentori.add((Mentor) radnik);
				if (radnik instanceof Asistent)
					asistenti.add((Asistent) radnik);
			}

			System.out.println("Unesite da li zelite da modifikujete mentora - [1] ili asistenta - [2]: ");
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
					System.out.println("Izaberite redni broj mentora kojeg zelite da modifikujete: ");
					int odg = UnosInt.unos();
					if (odg > mentori.size() || odg < 1)
					{
						System.out.println("Niste unijeli validan redni broj.");
					} else
					{
						mentori.get(odg - 1).opcije();
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
					System.out.println("Izaberite redni broj asistenta kojeg zelite da modifikujete: ");
					int odg = UnosInt.unos();
					if (odg > asistenti.size() || odg < 1)
					{
						System.out.println("Niste unijeli validan redni broj.");
					} else
					{
						asistenti.get(odg - 1).opcije();
						break;
					}
				}

			} else
			{
				System.out.println("Pogresan unos! Unos moze da bude 1 ili 2.");
			}
		}

	}
	//dodavanje generacije u akademiju
	private void dodajGeneraciju() throws DuplikatException
	{
		try
		{
			
			Generacija gen = new Generacija(zaposleni, polaznici, kursevi);
			for(Generacija gener: generacije) {
				if(gener.getGodinaDo() == gen.getGodinaDo() && gener.getGodinaOd() == gen.getGodinaOd()) {
					throw new DuplikatException();
				}
			}
			generacije.add(gen);
		} catch (PraznaListaException e)
		{
			System.out.println(e.getMessage());
		}

	}
	//pozivanje metode opcija iz generacije
	private void modifikujGeneraciju()
	{

		if (generacije.isEmpty())
		{
			System.out.println("Akademija nema generacija! Unesite prvo generacije.");
		} else
		{

			printGeneracija();
			System.out.println("Unesite redni broj generacije koju zelite da modifikujete: ");
			while (true)
			{
				int retVal = UnosInt.unos();
				if (retVal > generacije.size() || retVal < 1)
				{
					System.out.println("Pogresan unos.");
				} else
				{
					generacije.get(retVal - 1).opcije();
					break;
				}
			}
		}

	}

	
	private void printGeneracija()
	{
		int brojac = 1;
		for (Generacija g : generacije)
		{
			System.out.print(brojac + ". ");
			g.ispis();
			brojac++;
		}
	}
	// dodavanje kurseva u akademiju
	private void dodajKurs()
	{
		System.out.println("Unesite naziv kursa: ");
		String naziv = UnosString.unos();
		System.out.println("Unesite fond casova: ");
		int fondCasova = UnosInt.unos();
		Kurs kurs;
		try
		{
			kurs = new Kurs(naziv, fondCasova);
			kurs.dodajKompetenciju();
			kursevi.add(kurs);
		} catch (PogresanUnosImenaException e)
		{
			System.out.println(e.getMessage());
		}

	}

	private void printKursevi()
	{
		if (kursevi.isEmpty())
		{
			System.out.println("Nema unesenih kurseva!");
		} else
		{
			System.out.println("Lista kurseva: ");
			for (Kurs kurs : kursevi)
			{
				int brojac = 1;
				System.out.print(brojac + ". ");
				kurs.ispis();
				brojac++;
			}
		}

	}
	//dodavanje zaposlenog u akademiju
	private void dodajZaposlenog() throws DuplikatException
	{
		System.out.println("Unesite da li zelite da unesete mentora - [1] ili asistenta - [2]: ");
		int odabir = UnosInt.unos();
		if (odabir == 1 || odabir == 2)
		{
			System.out.println("Unesite ime zaposlenog: ");
			String ime = UnosString.unos();
			System.out.println("Unesite prezime zaposlenog: ");
			String prezime = UnosString.unos();
			System.out.println("Unesite njegov JMBG: ");
			String jmbg = UnosString.unos();
			System.out.println("Unesite zvanje zaposlenog: ");
			String zvanje = UnosString.unos();
			if (odabir == 1)
			{
				Mentor mentor;
				try
				{
					mentor = new Mentor(ime, prezime, jmbg, zvanje);
					zaposleni.add(mentor);
				} catch (JmbgException e)
				{
					System.out.println(e.getMessage());
				} catch (PogresanUnosImenaException e)
				{
					System.out.println(e.getMessage());
				}
			} else if (odabir == 2)
			{
				Asistent asistent;
				try
				{
					asistent = new Asistent(ime, prezime, jmbg, zvanje);
					for(Zaposleni zap: zaposleni) {
						if(zap.getJmbg().equals(asistent.getJmbg())) {
							throw new DuplikatException();
						}
					}
					zaposleni.add(asistent);
				} catch (JmbgException e)
				{
					System.out.println(e.getMessage());
				} catch (PogresanUnosImenaException e)
				{
					System.out.println(e.getMessage());
				}
			}
		} else
		{
			System.out.println("Pogresan unos! Unos moze da bude 1 ili 2.");
		}
	}

	private void printZaposleni()
	{

		if (zaposleni.isEmpty())
		{
			System.out.println("Akademija nema zaposlenih! Unesite prvo zaposlene.");
		} else
		{
			System.out.println("\n Lista mentora: ");
			for (Zaposleni radnik : zaposleni)
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
			for (Zaposleni radnik : zaposleni)
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

	private void dodajPolaznika() throws DuplikatException
	{
		System.out.println("Unesite ime polaznika: ");
		String ime = UnosString.unos();
		System.out.println("Unesite prezime polaznika: ");
		String prezime = UnosString.unos();
		System.out.println("Unesite njegov JMBG: ");
		String jmbg = UnosString.unos();
		System.out.println("Unesite broj indeksa: ");
		String brojIndeksa = UnosString.unos();

		try
		{
			Polaznik polaznik = new Polaznik(ime, prezime, jmbg, brojIndeksa);
			for(Polaznik pol: polaznici) {
				if(pol.getJmbg().equals(pol.getJmbg())) {
					throw new DuplikatException();
				}
			}
			polaznici.add(polaznik);
		} catch (PogresanUnosImenaException e)
		{

			System.out.println(e.getMessage());
		} catch (JmbgException e)
		{
			System.out.println(e.getMessage());
		}
	}

	private void printPolaznik()
	{
		if (polaznici.isEmpty())
		{
			System.out.println("Akademija nema polaznika! Unesite prvo polaznike.");
		} else
		{

			System.out.println("Lista polaznika: ");
			for (Polaznik polaznik : polaznici)
			{
				int brojac = 1;
				System.out.print(brojac + ". ");
				polaznik.ispis();
				brojac++;
			}
		}

	}

	public void ispis()
	{
		System.out.println(this.toString());
		printKursevi();
		printPolaznik();
		printZaposleni();

	}

	public String getNaziv()
	{
		return naziv;
	}
	
	public void setNaziv(String naziv) throws PogresanUnosImenaException
	{
		if(naziv.equals("")) {
			throw new PogresanUnosImenaException("Naziv akademije ne moze biti prazan.");
		}
		this.naziv = naziv;
	}
}
