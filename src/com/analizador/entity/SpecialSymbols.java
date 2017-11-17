package com.analizador.entity;

import java.util.ArrayList;

public class SpecialSymbols {

	public static ArrayList<String> lista = new ArrayList<String>();

	public static void completarSimbolos() {
		String[] l = { ";", ":", "{", "}", "[", "]", "*", "(", ")", ",", "~", "/", "+", "=", "!" };

		for (int i = 0; i < l.length; i++) {
			lista.add(l[i]);
		}

	}

}
