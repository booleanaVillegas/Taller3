import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PImage;
import processing.video.Capture;
import processing.video.Movie;

public class Logica {
	PApplet app;
	Capture cam;
	PImage[] imgs = new PImage[10];
	Minim minim;
	AudioPlayer player;
	Movie myMovie;
	String[] camaras = Capture.list();
	Chroma chroma;
	Happy happy;
	PImage imagen;

	public Logica(PApplet app) {
		this.app = app;
		cam = new Capture(app, camaras[0]);
		cam.start();
		imagen = app.loadImage("../data/dbfa1978.jpg");
		chroma = new Chroma(app);

	}

	public void display() {

		/**
		 * Este metodo incluye todas las imagenes y pantallas que se mostraran
		 * en el PApplet
		 */
		if (cam.available() == true) {
			cam.read();
		}

		happy = new Happy(app);
		PImage imagenFiltrada = chroma.filtro(cam.get(), imagen);

		PImage imageFiltradaDos = happy.filtro(imagenFiltrada);

		app.image(imageFiltradaDos, 0, 0);
		chroma.filtro();
		app.fill(255);
		app.textSize(14);

	}

	public void estados() {

		/**
		 * Cambia entre estados del animal o "filtro" que estamos usando en ese
		 * momento.
		 */
	}

	public void makey() {
		/** este metodo tengran todas las interacciones con el makey makey */
	}
}
