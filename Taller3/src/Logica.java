import java.util.ArrayList;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.video.Capture;
import processing.video.Movie;
import processing.video.Video;

// 0 jjag, 1 jmacaw, 2 jtuqui, 3 jsnake, 4 jaguar, 5 macaw, 6 tuqui, 7 snake
public class Logica {
	private PApplet app;
	private Capture cam;
	private PImage[] imgs = new PImage[10];
	private Minim minim;
	private int pantallas, n;
	private AudioPlayer player;
	private Movie[] myMovie = new Movie[5];
	private String[] camaras = Capture.list();
	private Chroma chroma;
	private Happy happy;
	private PImage imagenFiltrada;
	private PImage imageFiltradaDos, imgFiltrTres, imgFiltrCuatro;
	private PFont font;
	private int undido;
	private Boton[] boton = new Boton[4];

	private AnimalView animal;
	private Video video;

	private Sad sad;

	public Logica(PApplet app) {
		this.app = app;
		cam = new Capture(app, camaras[0]);
		cam.start();

		for (int i = 1; i <= 10; i++) {
			imgs[i - 1] = app.loadImage("../data/img-0" + i + ".png");
		}
		for (int i = 1; i <= 5; i++) {
			myMovie[i - 1] = new Movie(app, "../data/"+i+".mov");
			myMovie[i - 1].loop();
		}

		boton[0] = new Boton(app, imgs[6].get());
		boton[1] = new Boton(app, imgs[4].get());
		boton[2] = new Boton(app, imgs[5].get());
		boton[3] = new Boton(app, imgs[7].get());
		animal = new AnimalView(app);
		chroma = new Chroma(app);
		sad = new Sad(app);

		font = app.createFont("../data/Roboto-Light.ttf", 20);

	}

	public void display() {
		/**
		 * Este metodo incluye todas las imagenes y pantallas que se mostraran
		 * en el PApplet
		 */
		{
			cam.read();
		}
	
		imagenFiltrada = chroma.filtro(cam.get(), myMovie[n]);
		app.fill(255);
		app.textSize(14);
app.cursor(imgs[9]);

	}

	public void estados() {

		display();
		app.imageMode(app.CORNER);
		switch (pantallas) {
		case 0:
			// inicio
			app.image(imgs[0], 0, 0);
			boton[0].pintar(1150, 600);
			break;
		case 1:
			happy = new Happy(app);
	
			imageFiltradaDos = happy.filtro(imagenFiltrada.get());
			
			app.image(imageFiltradaDos.get(), 0, 0);
			happy.filtro();
			boton[1].pintar(400, 350);
			n = 4;
			break;
		case 2:

			imgFiltrTres = sad.filtro(imagenFiltrada.get());
			// flitro de chroma encerrados

			if (app.mouseX > 0 && app.mouseY > 0 && app.mouseX < app.width / 2 && app.mouseY < app.height / 2) {
				undido = 3;
			}

			if (app.mouseX > app.width / 2 && app.mouseY > app.height / 2 && app.mouseX < app.width
					&& app.mouseY < app.height) {
				undido = 1;
			}

			if (app.mouseX > app.width / 2 && app.mouseY > 0 && app.mouseX < app.width && app.mouseY < app.height / 2) {
				undido = 2;
			}

			if (app.mouseX > 0 && app.mouseY > app.height / 2 && app.mouseX < app.width / 2
					&& app.mouseY < app.height) {
				undido = 4;
			}

			app.image(imgFiltrTres, 0,0);

			/*switch (undido) {
			case 1:
				// macaw
				
				n = 0;
				break;
			case 2:
				// serpiente
			
				n = 1;
				break;
			case 3:
				// tuqui
			
				n = 2;
				break;
			case 4:
				// leopardo
	
				n = 3;
				break;
				
			}*/
			boton[3].pintar(400, 350);
			break;
		case 3:
			// filtro de animal view
			imgFiltrCuatro = animal.filtro(imagenFiltrada.get());
	
			app.image(imgs[8], app.random(190 , 220), app.random(550 , 580));
			if(app.frameCount%8==0){
			n = (int) app.random(0, 4);
			}
			boton[0].pintar(1150, 600);
			break;
		case 4:
			app.image(imgs[1], 0, 0);
			boton[0].pintar(1150, 600);
			break;
		case 5:
			app.image(imgs[2], 0, 0);
			boton[0].pintar(1150, 600);
			break;
		case 6:
			app.image(imgs[3], 0, 0);
			boton[2].pintar(850, 600);
			break;
		}
	}

	public void wii() {
		/** este metodo tendra todas las interacciones con el makey makey */
		if (app.keyCode == 37 && pantallas == 1) { // izquierda, SI
			pantallas = 2;
		}
		if (app.keyCode == 39 && pantallas == 2) { // derecha, No
			pantallas = 3;
		}
		if (app.key == ' ' && pantallas != 1 && pantallas != 2) { // espacio.
																	// boton
																	// circular.
			pantallas++;
		}
		if (pantallas >= 6 && app.keyCode == app.ENTER) {
			pantallas = 1;
		} else if (pantallas >= 6) {
			pantallas = 6;
		}
		if (pantallas==2){
			switch (undido) {
			case 1:
				// macaw
				
				n = 0;
				break;
			case 2:
				// serpiente
			
				n = 1;
				break;
			case 3:
				// tuqui
			
				n = 2;
				break;
			case 4:
				// leopardo
	
				n = 3;
				break;
				
			}
		}
	}
}
