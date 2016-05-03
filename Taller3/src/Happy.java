import processing.core.*;
import processing.video.*;

public class Happy implements Filtrable {

	private PImage im;
	/** este objeto recibe la camara del computador */
	private PApplet app;
	private int camW, camH;

	/** este PApplet nos permite usar metodos de la libreria de processing */

	public Happy(PApplet app) {
		this.app = app;

		/**
		 * Este metodo es el contructor de la clase que me permite cargar e
		 * inicializar todos los objetos y variables ademas de recibir
		 * parametros del objeto
		 */
	}

	public void filtro() {
		PImage imagenNueva = app.createImage(1280, 720, app.RGB);
		imagenNueva.loadPixels();
		for (int i = 0; i < imagenNueva.width; i++) {

			for (int j = 0; j < imagenNueva.height; j++) {
				int pix = i + j * imagenNueva.width;

				int h2 = (int) app.dist(imagenNueva.width / 2, imagenNueva.height / 2, i, j) - 600;

				imagenNueva.pixels[pix] = app.color(255, 255, 15, h2);

			}
		}
		imagenNueva.updatePixels();

		app.image(imagenNueva, 0, 0);
	}

	public PImage filtro(PImage cam) {
		camW = cam.width;
		camH = cam.height;
		cam.loadPixels();

		app.colorMode(app.RGB);

		for (int i = 0; i < cam.width; i++) {

			for (int j = 0; j < cam.height; j++) {
				int pix = i + j * cam.width;

				int r = (int) app.red(cam.pixels[pix]);
				int g = (int) app.green(cam.pixels[pix]);
				int b = (int) app.blue(cam.pixels[pix]);
				float h = app.hue(cam.pixels[pix]);
				float s = app.saturation(cam.pixels[pix]);
				float br = app.brightness(cam.pixels[pix]);
				app.colorMode(app.HSB);
				cam.pixels[pix] = app.color(h, s + 10, br+10);

				app.colorMode(app.RGB);
				cam.pixels[pix] = app.color(r, g, b - 20);

				if (app.saturation(cam.pixels[pix]) > 10) {

					cam.pixels[pix] = app.color(r + 15, g + 15, b - 20);

					/**
					 * Con este método se hará el filtro de un ambiente feliz,
					 * planteado en el análisis de requerimientos.
					 */

				}
				// app.colorMode(app.HSB);

				if (br < 20) {
					cam.pixels[pix] = app.color(r, g, b - 20);
				}
				if (br > 120) {
					cam.pixels[pix] = app.color(255, 255, 60);
				}

			}
			cam.updatePixels();
		}

		return cam;
	}

	public boolean validar() {
		/**
		 * Este booleano se usara para determinar en que momento el filtro esta
		 * activo
		 */
		return false;
	}

}