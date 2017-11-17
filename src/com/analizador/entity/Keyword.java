package com.analizador.entity;

import java.util.ArrayList;

public class Keyword {

	public static ArrayList<String> lista = new ArrayList<String>();

	public static void completarPalabrasReservadas() {
		String[] l = { "asm", "auto", "bool", "break", "case", "catch", "char", "char16_t", "char32_t", "wchar_t",
				"class", "const", "const_cast", "continue", "default", "delete", "do", "double", "dynamic_cast", "else",
				"enum", "explicit", "extern", "false", "float", "for", "friend", "goto", "if", "inline", "int", "long",
				"mutable", "namespace", "new", "operator", "private", "protected", "public", "register",
				"reinterpret_cast", "return", "short", "signed", "sizeof", "static", "static_cast", "struct", "switch",
				"template", "this", "throw", "true", "try", "typedef", "typeid", "typename", "union", "unsigned",
				"using", "virtual", "void", "volatile", "while" };

		for (int i = 0; i < l.length; i++) {
			lista.add(l[i]);
		}
	}

}
