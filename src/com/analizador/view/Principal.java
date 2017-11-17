package com.analizador.view;

import com.analizador.control.AnalizadorLexico;
import com.analizador.entity.Directive;
import com.analizador.entity.Keyword;
import com.analizador.entity.Operator;
import com.analizador.entity.SpecialSymbols;

public class Principal {

	public static void main(String[] args) {
		Principal p = new Principal();
		p.correr();
	}

	private void correr() {
		Keyword.completarPalabrasReservadas();
		Directive.completarDirectivas();
		SpecialSymbols.completarSimbolos();
		Operator.completarOperadores();

		String codigoFuente = Fichero.leerArchivo("C:\\Users\\angel\\workspace\\AnalizadorLexico\\codigoFuente.cpp");

		AnalizadorLexico analizador = new AnalizadorLexico(codigoFuente);
		analizador.analizar();
	}

}