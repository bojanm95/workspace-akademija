package helper;

import java.util.ArrayList;

import exception.PraznaListaException;

public class Printer
{
	
	// staticka metoda koju koriste klase koje zahtjevaju ispis liste
	public static void ispisListe(ArrayList<?> lista) throws PraznaListaException {
		
		if(lista.isEmpty()) {
			throw new PraznaListaException(); 
		}
		
		for(int i = 0; i < lista.size(); i++) {
			System.out.println((i+1)+". "+lista.get(i));
		}
		System.out.println("----------------------------------------------------");
	}

}
