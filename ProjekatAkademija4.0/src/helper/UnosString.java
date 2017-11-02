package helper;

import java.util.Scanner;


public class UnosString
{
	static Scanner in = null;

	public static String unos()
	{
		in = new Scanner(System.in);
		in.useDelimiter("\r\n");
		while (true)
		{
			
			String unos = in.next();
			if (unos == null)
			{
				System.err.println("Niste unijeli nista. Unesite ponovo.");
			} else
			{
				
				return unos;
			}
		}
	}

}
