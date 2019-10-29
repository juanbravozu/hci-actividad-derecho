package derecho;

import processing.core.PApplet;
import processing.core.PImage;

public class Sospechoso {
	
	private PApplet app;
	private String[] preguntas, respuestas;
	private boolean[] pClickeadas; //Variable nueva, indica si se ha clickeado la pregunta
	private PImage[] pj;
	
	public Sospechoso(PApplet app, int numSospechoso) {
		this.app = app;
		preguntas = app.loadStrings("preguntas"+numSospechoso+".txt");
		respuestas = app.loadStrings("respuestas"+numSospechoso+".txt");
		
		preguntas[0] = preguntas[0].substring(1, preguntas[0].length());
		respuestas[0] = respuestas[0].substring(1, respuestas[0].length());
		
		pj = new PImage[2];
		pj[0] = app.loadImage("pj"+numSospechoso+".png");
		pj[1] = app.loadImage("pj"+numSospechoso+"selected.png");
		
		pClickeadas = new boolean[preguntas.length];
		
		for (int i = 0; i < pClickeadas.length; i++) {
			pClickeadas[i] = false;
		}
	}

	public void pintar(int x, int y) {
		int mX = app.mouseX;
		int mY = app.mouseY;
		
		if(mX > x && mX < x+pj[0].width && mY < 606 && mY > y) {
			app.image(pj[1], x, y);
		} else {
			app.image(pj[0], x, y);
		}
	}	
	
	public void pintarPjDialogo(int x, int y) {
		app.image(pj[1], x, y);
	}
	
	public boolean validarPj(int x, int y) {
		int mX = app.mouseX;
		int mY = app.mouseY;
		
		System.out.println(mX > x && mX < x+pj[0].width && mY < 606 && mY > y);
		if(mX > x && mX < x+pj[0].width && mY < 606 && mY > y) {
			System.out.println("Valida");
			return true;
		}
		
		return false;
	}
	
	public String[] getPreguntas() {
		return preguntas;
	}

	public String[] getRespuestas() {
		return respuestas;
	}

	public boolean[] getpClickeadas() {
		return pClickeadas;
	}

	public void setpClickeadas(boolean pClickeadas, int index) {
		this.pClickeadas[index] = pClickeadas;
	}
	
	
}
