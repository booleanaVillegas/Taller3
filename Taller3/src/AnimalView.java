import processing.core.PApplet;
import processing.core.PImage;
import processing.video.Capture;

public class AnimalView  {
	private Capture cam;
	/** este objeto recibe la camara del computador */
	private PApplet app;

	/** este PApplet nos permite usar metodos de la libreria de processing */

	public AnimalView(PApplet app) {
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

				
				if (app.brightness(cam.pixels[pix]) < 30 ) {
					cam.pixels[pix] = app.color(h, s, b);
					b = 0;
				}
				

				cam.pixels[pix] = app.color(h, s, b);

			}
		}
		cam.loadPixels();

		
		return cam;
	}

}
