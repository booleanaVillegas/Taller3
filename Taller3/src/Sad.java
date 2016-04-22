import processing.core.*;

import processing.video.*;

public class Sad implements Filtrable {

	/** este objeto recibe la camara del computador */
	private PApplet app;

	/** este PApplet nos permite usar metodos de la libreria de processing */

	public Sad(PApplet app) {
		this.app = app;

		/**
		 * Este metodo es el contructor de la clase que me permite cargar e
		 * inicializar todos los objetos y variables ademas de recibir
		 * parametros del objeto
		 */
	}

	public PImage filtro(PImage cam) {

		int a = 90;
		cam.loadPixels();
		app.colorMode(app.RGB);

	

		for (int i = 0; i < cam.width; i++) {

			for (int j = 0; j < cam.height; j++) {
				int pix = i + j * cam.width;

				int r = (int) app.red(cam.pixels[pix]);

				int g = (int) app.green(cam.pixels[pix]);
				int b = (int) app.blue(cam.pixels[pix]);
				int h = (int) app.hue(cam.pixels[pix]);

				int s = (int) app.saturation(cam.pixels[pix]);
				int br = (int) app.brightness(cam.pixels[pix]);

				int h2 = (int) app.dist(cam.width / 2, cam.height / 2, i, j) - 400;
				app.colorMode(app.HSB);
				cam.pixels[pix] = app.color(h, s, b - 80);
				app.colorMode(app.RGB);
				cam.pixels[pix] = app.color(r, g, b + h2);

				if (app.blue(cam.pixels[pix]) < 100) {

					cam.pixels[pix] = app.color(r, g, b + 10);
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

	public void filtro() {

		/**
		 * Con este método se hará el filtro de un ambiente triste ,planteado
		 * en el análisis de requerimientos.
		 */
	}

	public boolean validar() {
		/**
		 * Este booleano se usara para determinar en que momento el filtro esta
		 * activo
		 */
		return false;
	}

}
