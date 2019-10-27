package derecho;

import processing.core.PApplet;
import processing.core.PImage;

public class Tutorial {

	private PApplet app;
	private int pantalla;
	private int contadorPantalla;
	private PImage[] imgs;
	
	public Tutorial(PApplet app) {
		this.app = app;
		pantalla = 0;
		imgs = new PImage[4];
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = app.loadImage("tutorial"+i+".png");
		}
	}
	
	public void pintar() {
		app.image(imgs[pantalla], 0, 0);
		contadorPantalla++;
	}
	
	public boolean interaccionMouse() {
		if(contadorPantalla > 30) {
			if(pantalla >= 3) {
				return true;
			}
			pantalla++;
			contadorPantalla = 0;
		}
		return false;
	}
}
