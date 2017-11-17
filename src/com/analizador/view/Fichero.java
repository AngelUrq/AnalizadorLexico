package com.analizador.view;

import java.io.BufferedReader;
import java.io.FileReader;

public class Fichero {

	public static String leerArchivo(String ruta) {
		String cadenaFinal = "";

		try {
			String cadena;
			FileReader f = new FileReader(ruta);
			BufferedReader b = new BufferedReader(f);
			while ((cadena = b.readLine()) != null) {
				cadenaFinal += cadena + "\n";
			}
			b.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return cadenaFinal;
	}

}
