import java.util.ArrayList;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.video.Capture;
import processing.video.Movie;

// 0 jjag, 1 jmacaw, 2 jtuqui, 3 jsnake, 4 jaguar, 5 macaw, 6 tuqui, 7 snake
public class Logica {
	private PApplet app;
	private Capture cam;
	private PImage[] imgs = new PImage[6];
	private Minim minim;
	private int pantallas, n;
	private AudioPlayer player;
	private Movie[] myMovie = new Movie[9];
	private String[] camaras = Capture.list();
	private Chroma chroma;
	private Happy happy;
	private PImage imagenFiltrada;
	private PImage imageFiltradaDos, imagenFiltradaTres, imagenFiltradaCuatro, imgFiltCinco;
	private PFont font;

	private AnimalView animal;
	private Video video;

	private Sad sad;

	public Logica(PApplet app) {
		this.app = app;
		cam = new Capture(app, camaras[0]);
		cam.start();

		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = app.loadImage("../data/img-0" + i + ".png");
		}
		for (int i = 0; i < 9; i++) {
			myMovie[i] = new Movie(app, "../data/" + i + ".mpeg");
			myMovie[i].loop();
		}
		chroma = new Chroma(app, cam);

		happy = new Happy(app);
		font = app.createFont("../data/Roboto-Light.ttf", 20);
		animal = new AnimalView(app);

		sad = new Sad(app);

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
		imagenFiltrada = chroma.filtro(cam.get(), myMovie[1]);
		app.fill(255);
		app.textSize(14);

	}

	public void estados() {

		System.out.println(app.frameCount);
		display();
		app.imageMode(app.CORNER);
		switch (pantallas) {
		case 0:
			// inicio
			app.image(imgs[1], 0, 0);
			app.image(imgs[0], 0, 0);
			app.image(myMovie[1], 0, 0);
			break;
		case 1:
			// filtro aqui
			imageFiltradaDos = happy.filtro(imagenFiltrada.get());
			app.image(imageFiltradaDos.get(), 0, 0);
			n = 1;
			break;
		case 2:
			imgFiltCinco = sad.filtro(imagenFiltrada.get());
			// flitro de chroma encerrados
			app.image(imgFiltCinco, 0, 0);

			n = 2;

			break;
		case 3:
			// filtro de animal view
			imagenFiltradaTres = animal.filtro(imagenFiltrada.get());
			app.image(imgs[2], 0, 0);

			animal.filtro(imagenFiltrada.get());

			// filtro aqui
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

	public void makey() {
		/** este metodo tendra todas las interacciones con el makey makey */
		if (app.keyCode == 37 && pantallas == 1) { // izquierda, SI
			pantallas = 2;
		}
		if (app.keyCode == 39 && pantallas == 2) { // izquierda, No
			pantallas = 3;
		}
		if (app.key == ' ' && pantallas != 1 && pantallas != 2) { // espacio.
																	// boton
																	// circular.
			pantallas++;
		}
		if (pantallas >= 6) {
			pantallas = 6;
		}
	}
}
