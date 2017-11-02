package helper;

import java.util.Scanner;

public class UnosInt
{


	public static int unos()
	{
		
		Scanner in = null;
		boolean provjera = true;
		int unos = 0;
		do
		{
			try
			{
				in = new Scanner(System.in);
				in.useDelimiter("\r\n");
				unos = Integer.parseInt(in.next());
				provjera = true;
			} 					
			catch (NumberFormatException e)
			{
				System.err.println("Unijeli ste slovo. Unesite ponovo.");
				provjera = false;
			} 
		} while (!provjera);
		
		return unos;
	}

}
