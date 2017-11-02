package akademija;

import java.io.Serializable;
import java.util.ArrayList;

import exception.DuplikatException;
import exception.PraznaListaException;
import helper.Printer;
import helper.UnosInt;
import helper.UnosString;
import interfaces.Meni;
import main.Kurs;
import main.KursUGeneraciji;
import osoba.Asistent;
import osoba.Mentor;
import osoba.Polaznik;
import osoba.Zaposleni;

public class Generacija implements Meni, Serializable
{

	
	private static final long serialVersionUID = 1L;
	//deklaracija varijabli
	private String godinaOd, godinaDo;
	// preuzete liste iz akademije
	private ArrayList<Zaposleni> zaposleniAkademija;
	private ArrayList<Polaznik> polazniciAkademija;
	private ArrayList<Kurs> kurseviAkademija;

	// kreirane liste za datu generaciju
	private ArrayList<Zaposleni> zaposleniGeneracija;
	private ArrayList<Polaznik> polazniciGeneracija;
	private ArrayList<KursUGeneraciji> kurseviGeneracija;

	public Generacija(ArrayList<Zaposleni> zaposleniAkademija, ArrayList<Polaznik> polazniciAkademija,
			ArrayList<Kurs> kurseviAkademija) throws PraznaListaException
	{
		if (zaposleniAkademija.isEmpty() || polazniciAkademija.isEmpty() || kurseviAkademija.isEmpty())
		{
			throw new PraznaListaException();
		}
		// unos pocetka i kraja date generacije
		System.out.println("Unesite datum pocetka generacije: ");
		this.godinaOd = UnosString.unos();
		System.out.println("Unesite datum kraja generacije: ");
		this.godinaDo = UnosString.unos();
		// preuzimanje listi iz akademije
		this.zaposleniAkademija = zaposleniAkademija;
		this.polazniciAkademija = polazniciAkademija;
		this.kurseviAkademija = kurseviAkademija;
		// kreiranje novih listi za generaciju
		zaposleniGeneracija = new ArrayList<>();
		polazniciGeneracija = new ArrayList<>();
		kurseviGeneracija = new ArrayList<>();
	}

	// meni generacije koji se poziva unutar akademije
	@Override
	public void opcije()
	{
		boolean izlaz = false;

		while (true)
		{

			System.out.println("*********         MENI GENERACIJE       **********");
			System.out.println("[1] Dodaj kurs");
			System.out.println("[2] Modifikuj kurs");
			System.out.println("[3] Dodaj zaposlene");
			System.out.println("[4] Dodaj polaznike");
			System.out.println("[7] Prikazi kurseve");
			System.out.println("[8] Prikazi zaposlene");
			System.out.println("[9] Prikazi polaznike");
			System.out.println("[0] Nazad");
			int odgovor = UnosInt.unos();
			switch (odgovor) {
			case 0:
				izlaz = true;
				break;
			case 1:
				System.out.println("*****************DODAJ KURS******************");
				try
				{
					dodajKurs();
				} catch (DuplikatException e)
				{
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.println("*****************MODIFIKUJ KURS******************");
				modifikujKurs();
				break;
			case 3:
				System.out.println("*****************DODAJ ZAPOSLENOG******************");
				dodajZaposlenog();
				break;
			case 4:
				System.out.println("*****************DODAJ POLAZNIKA******************");
				try
				{
					dodajPolaznika();
				} catch (DuplikatException e)
				{
					System.out.println(e.getMessage());
				}
				break;
			case 7:
				System.out.println("*****************ISPIS KURSEVA******************");
				printKursevi();
				break;
			case 8:
				System.out.println("*****************ISPIS ZAPOSLENIH******************");
				printZaposleni();
				break;
			case 9:
				System.out.println("*****************ISPIS POLAZNIKA******************");
				printPolaznik();
				break;
			default:
				System.out.println("Pogresan unos.");
				break;
			}

			if (izlaz)
			{
				break;
			}

		}

	}

	private void printZaposleni()
	{
		if (zaposleniGeneracija.isEmpty())
		{
			System.out.println("Generacija nema zaposlenih! Unesite prvo zaposlene.");
		} else
		{
			System.out.println("\n Lista mentora: ");
			for (Zaposleni radnik : zaposleniGeneracija)
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
			for (Zaposleni radnik : zaposleniGeneracija)
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
	//dodavanje novih polaznika u generaciju iz akademije
	private void dodajPolaznika() throws DuplikatException
	{
		if (polazniciAkademija.isEmpty())
		{
			System.out.println("Akademija nema polaznika! Unesite prvo polaznike.");
		} else
		{

			printPolaznikAkademija();
			System.out.println("Unesite redni broj polaznika koju zelite da dodate generaciji: ");
			while (true)
			{
				int retVal = UnosInt.unos();
				if (retVal > polazniciAkademija.size() || retVal < 1)
				{
					System.out.println("Pogresan unos.");
				} else
				{
					if (polazniciGeneracija.contains(polazniciAkademija.get(retVal - 1)))
					{
						throw new DuplikatException();
					}
					polazniciGeneracija.add(polazniciAkademija.get(retVal - 1));
					break;
				}
			}
		}

	}

	private void printPolaznik()
	{
		int brojac = 1;
		if (polazniciGeneracija.isEmpty())
		{
			System.out.println("Generacija nema polaznika! Unesite prvo polaznike.");
		} else
		{

			System.out.println("Lista polaznika: ");
			for (Polaznik polaznik : polazniciGeneracija)
			{
				System.out.print(brojac + ". ");
				polaznik.ispis();
				brojac++;
			}
		}

	}

	private void printPolaznikAkademija()
	{
		int brojac = 1;
		if (polazniciAkademija.isEmpty())
		{
			System.out.println("Akademija nema polaznika! Unesite prvo polaznike.");
		} else
		{

			System.out.println("Lista polaznika: ");
			for (Polaznik polaznik : polazniciAkademija)
			{
				System.out.print(brojac + ". ");
				polaznik.ispis();
				brojac++;
			}
		}

	}
	// dodavanje zaposlenih u generaciju iz akademije
	private void dodajZaposlenog()
	{
		if (zaposleniAkademija.isEmpty())
		{
			System.out.println("Akademija nema zaposlenih! Unesite prvo zaposlene.");
		} else
		{

			ArrayList<Mentor> mentori = new ArrayList<>();
			ArrayList<Asistent> asistenti = new ArrayList<>();
			for (Zaposleni radnik : zaposleniAkademija)
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
					System.out.println("Izaberite redni broj mentora kojeg zelite da dodate generaciji: ");
					int odg = UnosInt.unos();
					if (odg > mentori.size() || odg < 1)
					{
						System.out.println("Niste unijeli validan redni broj.");
					} else
					{
						zaposleniGeneracija.add(mentori.get(odg - 1));
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
					System.out.println("Izaberite redni broj asistenta kojeg zelite da dodate generaciji: ");
					int odg = UnosInt.unos();
					if (odg > asistenti.size() || odg < 1)
					{
						System.out.println("Niste unijeli validan redni broj.");
					} else
					{
						zaposleniGeneracija.add(asistenti.get(odg - 1));
						break;
					}
				}

			} else
			{
				System.out.println("Pogresan unos! Unos moze da bude 1 ili 2.");
			}
		}

	}
	// dodavanje kurseva u generaciju kreiranjem novog objekta KursUGeneraciji
	private void dodajKurs() throws DuplikatException
	{
		if (zaposleniGeneracija.isEmpty() || polazniciGeneracija.isEmpty())
		{
			System.out.println("Nema unesenih polaznika/zaposlenih u generaciji.");

		} else if (kurseviAkademija.isEmpty())
		{
			System.out.println("Nema unesenih kurseva!");
		} else
		{
			printKurseviAkademija();
			System.out.println("Unesite redni broj kursa koji zelite da dodate generaciji: ");
			while (true)
			{
				int odg = UnosInt.unos();
				if (odg > kurseviAkademija.size() || odg < 1)
				{
					System.out.println("Niste unijeli validan redni broj.");
				} else
				{
					try
					{
						KursUGeneraciji kurs = new KursUGeneraciji(kurseviAkademija.get(odg - 1), zaposleniGeneracija,
								polazniciGeneracija);
						if (kurseviGeneracija.isEmpty())
						{
							kurseviGeneracija.add(kurs);
							break;
						} else
						{
							for (KursUGeneraciji k : kurseviGeneracija)
							{

								if (k.getKurs() == kurs.getKurs())
								{

									throw new DuplikatException();

								}
							}

							kurseviGeneracija.add(kurs);
							break;
						}

					} catch (PraznaListaException e)
					{
						System.out.println(e.getMessage());
					}

				}

			}
		}

	}

	private void modifikujKurs()
	{
		if (kurseviGeneracija.isEmpty())
		{
			System.out.println("Generacija nema kurseva! Unesite prvo kurseve.");
		} else
		{

			printKursevi();
			System.out.println("Unesite redni broj kursa koju zelite da modifikujete: ");
			while (true)
			{
				int retVal = UnosInt.unos();
				if (retVal > kurseviGeneracija.size() || retVal < 1)
				{
					System.out.println("Pogresan unos.");
				} else
				{
					kurseviGeneracija.get(retVal - 1).opcije();
					break;
				}
			}
		}
	}

	private void printKursevi()
	{
		if (kurseviGeneracija.isEmpty())
		{
			System.out.println("Nema unesenih kurseva!");
		} else
		{
			System.out.println("Lista kurseva: ");
			for (KursUGeneraciji kurs : kurseviGeneracija)
			{
				int brojac = 1;
				System.out.print(brojac + ". ");
				kurs.ispis();
				brojac++;
			}
		}

	}

	private void printKurseviAkademija()
	{
		if (kurseviAkademija.isEmpty())
		{
			System.out.println("Nema unesenih kurseva!");
		} else
		{
			System.out.println("Lista kurseva: ");
			for (Kurs kurs : kurseviAkademija)
			{
				int brojac = 1;
				System.out.print(brojac + ". ");
				kurs.ispis();
				brojac++;
			}
		}

	}

	public void ispis()
	{
		System.out.println("Generacija: " + godinaOd + "/" + godinaDo);
		printKursevi();
		printPolaznik();
		printZaposleni();

	}

	public String getGodinaOd()
	{
		return godinaOd;
	}

	public void setGodinaOd(String godinaOd)
	{
		this.godinaOd = godinaOd;
	}

	public String getGodinaDo()
	{
		return godinaDo;
	}

	public void setGodinaDo(String godinaDo)
	{
		this.godinaDo = godinaDo;
	}

}
