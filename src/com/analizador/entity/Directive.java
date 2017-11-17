package com.analizador.entity;

import java.util.ArrayList;

public class Directive {

	public static ArrayList<String> lista = new ArrayList<String>();

	public static void completarDirectivas() {
		String[] l = { "#if", "#ifdef", "#ifndef", "#elif", "#endif", "#include", "#define", "#undef", "#line",
				"#error", "#pragma" };

		for (int i = 0; i < l.length; i++) {
			lista.add(l[i]);
		}
	}

}
