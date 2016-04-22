import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.video.Capture;
import processing.video.Movie;

public class Logica {
	private PApplet app;
	private Capture cam;
	private PImage[] imgs = new PImage[6];
	private Minim minim;
	private int pantallas;
	private AudioPlayer player;
	private Movie myMovie;
	private String[] camaras = Capture.list();
	private Chroma chroma;
	private Happy happy;
	private PImage imagen;
	private PImage imagenFiltrada;
	private PImage imageFiltradaDos, imagenFiltradaTres;
	private PFont font;
	private AnimalView animal;

	public Logica(PApplet app) {
		this.app = app;
		cam = new Capture(app, camaras[0]);
		cam.start();
		imagen = app.loadImage("../data/dbfa1978.jpg");
		chroma = new Chroma(app);
		for (int i = 0; i < 6; i++) {
			imgs[i] = app.loadImage("../data/img-0" + i + ".png");
		}
		happy = new Happy(app);
		font = app.createFont("../data/Roboto-Light.ttf", 20);
		animal = new AnimalView(app);
	}

	public void display() {
		/**
		 * Este metodo incluye todas las imagenes y pantallas que se mostraran
		 * en el PApplet
		 */
		if (cam.available() == true) {
			cam.read();
		}
		imagenFiltrada = chroma.filtro(cam.get(), imgs[1]);
		imageFiltradaDos = happy.filtro(imagenFiltrada.get());
        imagenFiltradaTres = animal.filtro(imagenFiltrada.get());
		
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
			break;
		case 1:
			// filtro de chroma libres
			//app.image(imgs[1], 0, 0);
			// filtro aqui
			app.image(imageFiltradaDos.get(), 0, 0);
			chroma.filtro();
			break;
		case 2:
			// flitro de chroma encerrados
			app.image(imagenFiltradaTres, 0, 0);
			chroma.filtro();
			// filtro aqui
			break;
		case 3:
			// filtro de animal view
			app.image(imgs[2], 0, 0);

			// filtro aqui
			app.textFont(font, 30);
			app.text("Asi te ven ellos a ti", 200, 300);
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
