package derecho;

import processing.core.PApplet;
import processing.core.PImage;

public class Boton {

	private PApplet app;
	private int x, y;
	private PImage[] imgs;
	
	public Boton(PApplet app, int x, int y, String boton) {
		this.app = app;
		this.x = x;
		this.y = y;
		
		imgs = new PImage[3];
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = app.loadImage("boton_"+boton+i+".png");
		}
	}
	
	public void pintar() {
		int mX = app.mouseX;
		int mY = app.mouseY;
		
		if(!app.mousePressed && mX > x && mX < x+imgs[0].width && mY < y+imgs[0].height && mY > y) {
			app.image(imgs[1], x, y);
		} else if(app.mousePressed && mX > x && mX < x+imgs[0].width && mY < y+imgs[0].height && mY > y){
			app.image(imgs[2], x, y);
		} else {
			app.image(imgs[0], x, y);
		}
	}
	
	public boolean validar() {
		int mX = app.mouseX;
		int mY = app.mouseY;
		
		if(mX > x && mX < x+imgs[0].width && mY < y+imgs[0].height && mY > y) {
			return true;
		}
		return false;
	}
}
