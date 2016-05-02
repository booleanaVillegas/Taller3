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
	private PImage[] imgs = new PImage[7];
	private Minim minim;
	private int pantallas, n;
	private AudioPlayer player;
	private Movie[] myMovie = new Movie[5];
	private String[] camaras = Capture.list();
	private Chroma chroma;
	private Happy happy;
	private PImage imagenFiltrada;
	private PImage imageFiltradaDos, imagenFiltradaTres, imagenFiltradaCuatro, imgFiltCinco;
	private PFont font;
	private int undido;
	private Boton[] boton = new Boton[3];

	private AnimalView animal;
	private Video video;

	private Sad sad;

	public Logica(PApplet app) {
		this.app = app;
		cam = new Capture(app, camaras[0]);
		cam.start();

		for (int i = 1; i < imgs.length + 1; i++) {
			imgs[i - 1] = app.loadImage("../data/img-0" + i + ".png");
		}
		for (int i = 1; i < 6; i++) {
			myMovie[i - 1] = new Movie(app, "../data/" + i + ".mov");
			myMovie[i - 1].loop();
		}

		boton[0] = new Boton(app, imgs[6].get() );

		chroma = new Chroma(app, cam);

		
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
		chroma.filtro();
		imagenFiltrada = chroma.filtro(cam.get(), myMovie[n]);
		app.fill(255);
		app.textSize(14);

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
			n = 4;
			break;
		case 2:
			imgFiltCinco = sad.filtro(imagenFiltrada.get());
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
			
			if (app.mouseX > 0 && app.mouseY > app.height / 2 && app.mouseX < app.width/2
					&& app.mouseY < app.height) {
				undido = 4;
			}
			
			app.image(imgFiltCinco, 0, 0);
			
			switch (undido) {
			case 1:
				// macaw
				System.out.println("awak");
				n = 0;
				break;
			case 2:
				// serpiente
				System.out.println("serpiente");
				n = 1;
				break;
			case 3:
				// tuqui
				System.out.println("tuqui");
				n = 2;
				break;
			case 4:
				// leopardo
				System.out.println("leopardo");
				n = 3;
				break;

			}
			n = 1;

			break;
		case 3:
			// filtro de animal view
			imagenFiltradaTres = animal.filtro(imagenFiltrada.get());
			app.image(myMovie[n], 0, 0);

			animal.filtro(imagenFiltrada.get());
			animal = new AnimalView(app);
			app.textFont(font, 30);
			app.text("Asi te ven ellos a ti", 200, 300);
			n = 2;
			break;
		case 4:
			app.image(imgs[3], 0, 0);
			break;
		case 5:
			app.image(imgs[4], 0, 0);
			break;
		case 6:
			app.image(imgs[5], 0, 0);
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
		if (app.key == ' ' ) { // espacio.
																	// boton
																	// circular.
			pantallas++;
		}
		if (pantallas >= 6) {
			pantallas = 6;
		}
	}
}
