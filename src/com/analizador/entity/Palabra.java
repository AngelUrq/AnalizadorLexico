package com.analizador.entity;

public class Palabra {

	private int x;
	private int y;
	private String palabra;

	public Palabra(int x, int y, String palabra) {
		this.x = x;
		this.y = y;
		this.palabra = palabra;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getPalabra() {
		return palabra;
	}

}
