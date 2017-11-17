package com.analizador.control;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.analizador.entity.Directive;
import com.analizador.entity.Keyword;
import com.analizador.entity.Operator;
import com.analizador.entity.Palabra;
import com.analizador.entity.SpecialSymbols;

public class AnalizadorLexico {

	private Pattern patron;
	private Matcher matcher;

	private String codigoFuente;

	private ArrayList<Palabra> palabras;

	private int keywords;
	private int identifiers;
	private int operators;
	private int directives;
	private int numbers;
	private int symbols;

	public AnalizadorLexico(String codigoFuente) {
		this.codigoFuente = codigoFuente;
		this.palabras = new ArrayList<Palabra>();
		this.keywords = 0;
		this.identifiers = 0;
		this.operators = 0;
		this.directives = 0;
		this.numbers = 0;
		this.symbols = 0;
		separarPalabras();
	}

	private void separarPalabras() {
		String palabra = "";

		int x = 1;
		int y = 1;

		for (int i = 0; i < codigoFuente.length(); i++) {
			if ((codigoFuente.charAt(i) == ' ' || codigoFuente.charAt(i) == '\n' || codigoFuente.charAt(i) == '\t')) {
				if (palabra != "") {
					palabras.add(new Palabra(x - palabra.length(), y, palabra));
				}

				if (codigoFuente.charAt(i) == ' ') {
					x++;
				}
				if (codigoFuente.charAt(i) == '\n') {
					y++;
					x = 1;
				}
				if (codigoFuente.charAt(i) == '\t') {
					x += 3;
				}
				palabra = "";
			} else {
				if (codigoFuente.charAt(i) == '/') {
					if (codigoFuente.charAt(i + 1) == '*') {
						do {
							i++;
							if (codigoFuente.charAt(i) == '\n') {
								y++;
								x = 1;
							} else {
								x++;
							}
						} while ((codigoFuente.charAt(i) != '*' || codigoFuente.charAt(i + 1) != '/')
								&& i != codigoFuente.length() - 1);
						i++;
					} else if (codigoFuente.charAt(i + 1) == '/') {
						while (codigoFuente.charAt(i + 1) != '\n') {
							i++;
							x++;
						}
						y++;
						x = 1;
					} else {
						palabras.add(new Palabra(x - palabra.length(), y, codigoFuente.charAt(i - 1) + ""));
						palabra = "";
						palabras.add(new Palabra(x, y, '/' + ""));
						x++;
					}
				} else if (Operator.lista.contains(codigoFuente.charAt(i) + "" + codigoFuente.charAt(i + 1))) {
					if (palabra != "") {
						palabras.add(new Palabra(x - palabra.length(), y, palabra));
					}
					palabras.add(new Palabra(x - 2, y, codigoFuente.charAt(i) + "" + codigoFuente.charAt(i + 1)));
					palabra = "";
					x++;
					i++;
				} else if (SpecialSymbols.lista.contains(codigoFuente.charAt(i) + "")) {
					if (palabra != "") {
						palabras.add(new Palabra(x - palabra.length(), y, palabra));
					}
					palabras.add(new Palabra(x, y, codigoFuente.charAt(i) + ""));
					palabra = "";
					x++;
				} else {
					palabra += codigoFuente.charAt(i);
					x++;
				}
			}
		}
	}

	public void analizar() {
		for (int i = 0; i < palabras.size(); i++) {
			if (verificarDirectiva(palabras.get(i).getPalabra())) {
				System.out.println(palabras.get(i).getPalabra() + " en la posicion " + palabras.get(i).getX() + ","
						+ palabras.get(i).getY() + " es una directiva\n");
				directives++;
			} else if (verificarPalabraReservada(palabras.get(i).getPalabra())) {
				System.out.println(palabras.get(i).getPalabra() + " en la posicion " + palabras.get(i).getX() + ","
						+ palabras.get(i).getY() + " es una palabra reservada\n");
				keywords++;
			} else if (verificarIdentificador(palabras.get(i).getPalabra())) {
				System.out.println(palabras.get(i).getPalabra() + " en la posicion " + palabras.get(i).getX() + ","
						+ palabras.get(i).getY() + " es un identificador\n");
				identifiers++;
			} else if (verificarOperadores(palabras.get(i).getPalabra())) {
				System.out.println(palabras.get(i).getPalabra() + " en la posicion " + palabras.get(i).getX() + ","
						+ palabras.get(i).getY() + " es un operador\n");
				operators++;
			} else if (verificarNumeros(palabras.get(i).getPalabra())) {
				System.out.println(palabras.get(i).getPalabra() + " en la posicion " + palabras.get(i).getX() + ","
						+ palabras.get(i).getY() + " es un número\n");
				numbers++;
			} else if (verificarSimbolos(palabras.get(i).getPalabra())) {
				System.out.println(palabras.get(i).getPalabra() + " en la posicion " + palabras.get(i).getX() + ","
						+ palabras.get(i).getY() + " es un símbolo\n");
				symbols++;
			} else {
				System.err.println(palabras.get(i).getPalabra() + " en la posicion " + palabras.get(i).getX() + ","
						+ palabras.get(i).getY() + " error! :(\n");
			}
		}

		System.out.println("Hay " + directives + " directivas");
		System.out.println("Hay " + keywords + " palabras reservadas");
		System.out.println("Hay " + identifiers + " identificadores");
		System.out.println("Hay " + operators + " operadores");
		System.out.println("Hay " + numbers + " números");
		System.out.println("Hay " + symbols + " símbolos");
	}

	private boolean verificarIdentificador(String palabra) {
		String comillas = (char) 34 + "";

		patron = Pattern
				.compile("(((([a-zA-Z]|\\á|\\é|\\í|\\ó|\\ú|\\°|\\#|\\$|\\@|\\_|\\à|\\è|\\ì|\\ò|\\ù)+([0-9])*)+)?|((\\<|"
						+ comillas + ")([a-zA-Z]|\\s)+((\\.([a-zA-Z]+))|\\s)*(\\>|" + comillas + "))?)");
		matcher = patron.matcher(palabra);

		return matcher.matches();
	}

	private boolean verificarNumeros(String palabra) {
		patron = Pattern.compile("[0-9]+(\\.[0-9])?");
		matcher = patron.matcher(palabra);

		return matcher.matches();
	}

	private boolean verificarDirectiva(String palabra) {
		return Directive.lista.contains(palabra);
	}

	private boolean verificarPalabraReservada(String palabra) {
		return Keyword.lista.contains(palabra);
	}

	private boolean verificarOperadores(String palabra) {
		return Operator.lista.contains(palabra);
	}

	private boolean verificarSimbolos(String palabra) {
		return SpecialSymbols.lista.contains(palabra);
	}

	public Pattern getPatron() {
		return patron;
	}

	public Matcher getMatcher() {
		return matcher;
	}

	public String getCodigoFuente() {
		return codigoFuente;
	}

	public int getKeywords() {
		return keywords;
	}

	public int getIdentifiers() {
		return identifiers;
	}

	public int getOperators() {
		return operators;
	}

}
