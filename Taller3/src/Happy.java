import processing.core.*;
import processing.video.*;

public class Happy implements Filtrable {
	private Capture cam;
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

		cam.updatePixels();

		for (int i = 0; i < cam.width; i++) {

			for (int j = 0; j < cam.height; j++) {
				int pix = i + j * cam.width;
				int h = (int) app.hue(cam.pixels[pix]);
				int s = (int) app.saturation(cam.pixels[pix]);
				int b = (int) app.brightness(cam.pixels[pix]);

				
				if ((app.hue(cam.pixels[pix]) > 22 && app.hue(cam.pixels[pix]) < 50)
						&& (app.saturation(cam.pixels[pix]) > 20) && app.brightness(cam.pixels[pix]) > 30) {
					cam.pixels[pix] = app.color(h, s, b);
				}
				h += app.dist(cam.width / 2, cam.height / 2, i, j) - 300;

				cam.pixels[pix] = app.color(h, s, b);

			}
		}
		cam.loadPixels();

		/**
		 * Con este método se hará el filtro de un ambiente feliz, planteado en
		 * el análisis de requerimientos.
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

	public Capture getCam() {
		return cam;
		// TODO Auto-generated method stub

	}

	@Override
	public void filtro() {
		// TODO Auto-generated method stub

	}

}