 import processing.core.*;
import processing.video.*;

public class Happy implements Filtrable {

	private PImage im;
	/** este objeto recibe la camara del computador */
	private PApplet app;

	/** este PApplet nos permite usar metodos de la libreria de processing */

	public Happy(PApplet app) {
		this.app = app;

		/**
		 * Este metodo es el contructor de la clase que me permite cargar e
		 * inicializar todos los objetos y variables ademas de recibir
		 * parametros del objeto
		 */
	}

	public PImage filtro(PImage cam) {
	
		cam.loadPixels();

		app.colorMode(app.RGB);

		// app.colorMode(app.HSB);

		for (int i = 0; i < cam.width; i++) {

			for (int j = 0; j < cam.height; j++) {
				int pix = i + j * cam.width;

				int r = (int) app.red(cam.pixels[pix]);
				int g = (int) app.green(cam.pixels[pix]);
				int b = (int) app.blue(cam.pixels[pix]);

				int h2 = (int) app.dist(cam.width / 2, cam.height / 2, i, j) - 400;

				cam.pixels[pix] = app.color(r, g, b - h2);

				if (app.blue(cam.pixels[pix]) > 10) {

					cam.pixels[pix] = app.color(r, g, b-20);
				}

			}
		}
		cam.updatePixels();

		/**
		 * Con este método se hará el filtro de un ambiente feliz, planteado
		 * en el análisis de requerimientos.
		 */
		return cam;
	}

	public boolean validar() {
		/**
		 * Este booleano se usara para determinar en que momento el filtro esta
		 * activo
		 */
		return false;
	}

	@Override
	public void filtro() {

	}

}