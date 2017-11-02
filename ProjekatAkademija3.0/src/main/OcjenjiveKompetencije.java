package main;

import helper.UnosInt;

public class OcjenjiveKompetencije extends Kompetencija
{

	private int ocjena;

	public OcjenjiveKompetencije(String naziv)
	{
		super(naziv);
		this.ocjena = 0;
	}

	public OcjenjiveKompetencije ocjeni(OcjenjiveKompetencije ocjena)
	{
		OcjenjiveKompetencije ocjenjena = new OcjenjiveKompetencije(ocjena.naziv);
		System.out.println("Unesite ocjenu za kompetenciju: (Od 1 do 5)");
		while (true)
		{
			int ocj = UnosInt.unos();
			if (ocj > 5 || ocj < 1)
			{
				System.out.println("Nepravilan unos. Ocjena moze da bude vrijednosti od 1 do 5.");

			} else
			{
				ocjenjena.ocjena = ocj;
				System.out.println("Polaznik ocjenjen!");
				return ocjenjena;
			}
		}

	}

	public void ispis()
	{
		if (ocjena == 0)
		{
			System.out.println("Kompetencija: " + naziv);
		} else
		{
			System.out.println("Kompetencija: " + naziv + " Ocjena: " + ocjena);
		}

	}
	
	
	public int getOcjena()
	{
		return ocjena;
	}
}
