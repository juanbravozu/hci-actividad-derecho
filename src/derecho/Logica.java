package derecho;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Logica {

	private PApplet app;
	private int pantalla;
	private PFont heebo, muli;
	private PImage caso, ganar, perder, gusto;
	private PImage fondo, menu, confirmacion, mouseCulpar;
	private Sospechoso[] sospechosos;
	private Boton[] botones;
	private PImage[] reportes;
	private int contadorBoton;
	private int culpar, sElegido;
	private StringBuffer dialogo;
	private int indexDialogo;
	private Tutorial tutorial;
	private int puntos;
	
	public Logica(PApplet app) {
		this.app = app;
		
		app.background(212);
		heebo = app.createFont("Heebo-Bold.ttf", 28);
		app.textAlign(app.CENTER);
		app.textFont(heebo);
		app.text("Cargando", app.width/2, app.height/2);
		
		muli = app.createFont("Muli-Regular.ttf", 18);
		sospechosos = new Sospechoso[4];
		reportes = new PImage[4];
		for (int i = 0; i < sospechosos.length; i++) {
			sospechosos[i] = new Sospechoso(app, i);
			reportes[i] = app.loadImage("pj"+i+"reporte.png");
		}
		botones = new Boton[4];
		botones[0] = new Boton(app, 418, 637, "culpar");
		botones[1] = new Boton(app, 418, 637, "hablar");
		botones[2] = new Boton(app, 25, 637, "volver");
		botones[3] = new Boton(app, 418, 637, "continuar");
		caso = app.loadImage("pantallaCaso.png");
		fondo = app.loadImage("fondo.png");
		menu = app.loadImage("barra.png");
		confirmacion = app.loadImage("confirmacion.png");
		mouseCulpar = app.loadImage("mouseCulpar.png");
		dialogo = new StringBuffer();
		indexDialogo = 0;
		
		culpar = 2;
		sElegido = 0;
		contadorBoton = 0;
		pantalla = 0;
		puntos = 0;
		
		tutorial = new Tutorial(app);
		ganar = app.loadImage("ganar.png");
		perder = app.loadImage("perder.png");
		gusto = app.loadImage("gusto.png");
	}
	
	//------------------------------------------------------------- PINTAR
	public void pintar() {
		contadorBoton++;
		switch(pantalla) {
			
			case 0:
				tutorial.pintar();
				break;
			case 1:
				app.image(caso, 0, 0);
				break;
				
			case 2:
				app.image(fondo, 0, 0);
				
				pintarSospechosos();
				
				app.image(menu, 0, 606);
				
				if(culpar == 0) {
					app.cursor(mouseCulpar);
				} else {
					app.cursor(app.ARROW);
				}				
				break;
				
			case 3:
				app.image(reportes[0], 0, 0);
				break;
				
			case 4:
				app.image(reportes[1], 0, 0);
				break;
				
			case 5:
				app.image(reportes[2], 0, 0);
				break;
				
			case 6:
				app.image(reportes[3], 0, 0);
				break;
				
			case 7:
				pintarDialogo(0);
				break;
				
			case 8:
				pintarDialogo(1);
				break;
				
			case 9:
				pintarDialogo(2);
				break;
				
			case 10:
				pintarDialogo(3);
				break;
				
			case 11:
				app.image(ganar, 0, 0);
				app.image(menu, 0, 606);
				break;
				
			case 12:
				app.image(perder, 0, 0);
				app.image(menu, 0, 606);
				break;
				
			case 13:
				app.image(gusto, 0, 0);
				break;
		}
		pintarBotones();
		
		if(culpar == 1) {
			app.fill(0, 120);
			app.rect(0, 0, app.width, app.height);
			app.fill(0);
			app.imageMode(app.CENTER);
			app.image(confirmacion, app.width/2, app.height/2);
			app.imageMode(app.CORNER);
		}
		
		int mX = app.mouseX;
		int mY = app.mouseY;
		app.text("X: "+mX+"   Y: "+mY, mX+20, mY);
	}
	
	//----------------------------------------------- MOUSE
	public void soltarMouse() {
		int mX = app.mouseX;
		int mY = app.mouseY;
		
		
		
		switch(pantalla) {
			
			case 0:
				if(tutorial.interaccionMouse()) {
					pantalla++;
				}
				break;
			case 1:
				if(mX > 418 && mX < 862 && mY < 689 && mY > 637 && contadorBoton > 30) {
					contadorBoton = 0;
					pantalla++;
					
				}
				break;
				
			case 2:				
				if(culpar == 2) {
					for (int i = 0; i < sospechosos.length; i++) {
						
						int x = 21+(i*307);
						int y = 83;
						
						switch(i) {
							case 1:
								x = i*307;
								y = 99;
								break;
								
							case 2:
								y = 133;
								break;
								
							case 3:
								y = 128;
						}				
						
						if(sospechosos[i].validarPj(x, y)) {
							pantalla = 3+i;
						}
					}
					
					if(botones[2].validar() && contadorBoton > 30) {
						pantalla = 1;
						contadorBoton = 0;
					}
					
					if(botones[0].validar() && contadorBoton > 30) {
						culpar = 0;
						contadorBoton = 0;
					}
					
				} else if(culpar == 0){
					for (int i = 0; i < sospechosos.length; i++) {
						
						int x = 21+(i*307);
						int y = 83;
						
						switch(i) {
							case 1:
								x = i*307;
								y = 99;
								break;
								
							case 2:
								y = 133;
								break;
								
							case 3:
								y = 128;
						}				
						
						if(sospechosos[i].validarPj(x, y) && contadorBoton > 30) {
							sElegido = i;
							culpar = 1;
							contadorBoton = 0;
						}
					} 
				} else if(culpar == 1) {
					if(mX > 427 && mX < 564 && mY > 375 && mY < 438) {
						if(sElegido == 3) {
							pantalla = 11;
							puntos += 100;
						} else {
							pantalla = 12;
						}
						
						int tiempo = (int)(app.millis()/1000);
						int minutos = (int)(tiempo/60);
						if(minutos < 5)puntos += 50-(minutos*10);
						culpar = 2;
					} else if(mX > 717 && mX < 853 && mY > 375 && mY < 437) {
						culpar = 2;
					}
				}
				
				break;
				
			case 3:
				if(botones[1].validar() && contadorBoton > 30) {
					pantalla = 7;
					contadorBoton = 0;
				}
				break;
				
			case 4:
				if(botones[1].validar() && contadorBoton > 30) {
					pantalla = 8;
					contadorBoton = 0;
				}
				break;
				
			case 5:
				if(botones[1].validar() && contadorBoton > 30) {
					pantalla = 9;
					contadorBoton = 0;
				}
				break;
				
			case 6:
				if(botones[1].validar() && contadorBoton > 30) {
					pantalla = 10;
					contadorBoton = 0;
				}
				break;
				
			case 7:
				if(botones[2].validar() && contadorBoton > 30) {
					pantalla = 3;
					contadorBoton = 0;
					indexDialogo = 0;
					dialogo = new StringBuffer();
				}
				clickearDialogo(0);
				break;
				
			case 8:
				if(botones[2].validar() && contadorBoton > 30) {
					pantalla = 4;
					contadorBoton = 0;
					indexDialogo = 0;
					dialogo = new StringBuffer();
				}
				clickearDialogo(1);
				break;
				
			case 9:
				if(botones[2].validar() && contadorBoton > 30) {
					pantalla = 5;
					contadorBoton = 0;
					indexDialogo = 0;
					dialogo = new StringBuffer();
				}
				clickearDialogo(2);
				break;
				
			case 10:
				if(botones[2].validar() && contadorBoton > 30) {
					pantalla = 6;
					contadorBoton = 0;
					indexDialogo = 0;
					dialogo = new StringBuffer();
				}
				
				clickearDialogo(3);
				break;
				
			case 13:
				if(mX > 214 && mX < 538 && mY > 585 && mY < 635) {
					puntos += 50;
					
				} else if(mX > 742 && mX < 1065 && mY > 585 && mY < 635) {
					
				}
				break;
		}
		
		if(pantalla > 2 && pantalla <= 6 && botones[2].validar() && contadorBoton > 30) {
			pantalla = 2;
			contadorBoton = 0;
			indexDialogo = 0;
			dialogo = new StringBuffer();
		}
		
		if((pantalla == 11 || pantalla == 12) && botones[3].validar()) {
			pantalla = 13;
		}
	}
	
	public void pintarBotones() {
		
		if(pantalla == 2) {
			botones[0].pintar();
		} else if((pantalla > 2 && pantalla <= 6)) {
			botones[1].pintar();
		}		
		if(pantalla == 11 || pantalla == 12) {
			botones[3].pintar();
		}
		
		if(pantalla > 1 && pantalla != 11 && pantalla != 12) botones[2].pintar();
	}
	
	public void pintarSospechosos() {
		
		for (int i = 0; i < sospechosos.length; i++) {
			int x = 21+(i*307);
			int y = 83;
			
			switch(i) {
				case 1:
					x = i*307;
					y = 99;
					break;
					
				case 2:
					y = 133;
					break;
					
				case 3:
					y = 128;
			}
			
			sospechosos[i].pintar(x, y);
		}
	}
	
	public void pintarDialogo(int index) {
		app.image(fondo, 0, 0);
		Sospechoso s = sospechosos[index];
		
		if(index < 2) {
			s.pintarPjDialogo(100, 120);
			app.fill(255);
			app.rect(500, 80, 600, 200, 20);
			
			app.fill(0);
			if(dialogo.length() < s.getRespuestas()[indexDialogo].length()) {
				dialogo.append(s.getRespuestas()[indexDialogo].charAt(dialogo.length()));
			}
			
			app.textFont(muli);
			app.text(dialogo.toString(), 510, 100, 580, 200);
			
			
			for(int i = 0; i < s.getPreguntas().length; i++) {
				app.fill(255);
				app.rect(500, 290+(50*i), 600, 40, 10);
				
				app.fill(0);
				app.text(s.getPreguntas()[i], 800, 320+(50*i));
			}
			
			app.textFont(heebo);
		} else {
			s.pintarPjDialogo(890, 120);
			
			app.fill(255);
			app.rect(100, 80, 600, 200, 20);
			
			app.fill(0);
			if(dialogo.length() < s.getRespuestas()[indexDialogo].length()) {
				dialogo.append(s.getRespuestas()[indexDialogo].charAt(dialogo.length()));
			}
			
			app.textFont(muli);
			app.text(dialogo.toString(), 110, 100, 580, 200);
			
			
			for(int i = 0; i < s.getPreguntas().length; i++) {
				app.fill(255);
				app.rect(100, 290+(50*i), 600, 40, 10);
				
				app.fill(0);
				app.text(s.getPreguntas()[i], 400, 320+(50*i));
			}
		}
		
		
		app.image(menu, 0, 606);
	}

	public void clickearDialogo(int index) {
		Sospechoso s = sospechosos[index];
		
		int mX = app.mouseX;
		int mY = app.mouseY;
		
		if(index < 2) {		
			
			for(int i = 0; i < s.getPreguntas().length; i++) {
				if(mX > 500 && mX < 1100 && mY > 290+(50*i) && mY < 330+(50*i)) {
					dialogo = new StringBuffer();
					indexDialogo = i+1;
				}
			}
			
		} else {
			
			for(int i = 0; i < s.getPreguntas().length; i++) {
				if(mX > 100 && mX < 700 && mY > 290+(50*i) && mY < 330+(50*i)) {
					dialogo = new StringBuffer();
					indexDialogo = i+1;
				}
			}
		}
	}
	
	public int terminarJuego() {
		app.stop();
		return puntos;
	}
}
