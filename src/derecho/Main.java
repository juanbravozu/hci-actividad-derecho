package derecho;

import processing.core.PApplet;

public class Main extends PApplet {
	
	private Logica log;
	private Propiedades propiedades;
	private Acciones acciones;

	public static void main(String[] args) {
		PApplet.main("derecho.Main");
	}

	public void settings() {
		size(1280, 720);
	}
	
	public void setup() {
		log = new Logica(this);
		noStroke();
	}
	
	public void draw() {
		log.pintar();
	}
	
	public void mouseReleased() {
		log.soltarMouse();
	}
}
