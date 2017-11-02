package main;

import java.io.Serializable;


public class OcjenjiveKompetencije extends Kompetencija implements Serializable
{

	
	private static final long serialVersionUID = 1L;
	//deklaracija varijabli
	private int ocjena;

	public OcjenjiveKompetencije(String naziv)
	{
		super(naziv);
		this.ocjena = 0;
	}



	public void ispis()
	{
		
			System.out.println("Kompetencija: " + naziv + " Ocjena: " + ocjena);
		

	}
	
	//geteri i seteri
	//==================================
	public int getOcjena()
	{
		return ocjena;
	}
	
	public void setOcjena(int ocjena)
	{
		this.ocjena = ocjena;
	}
	//==================================
}
