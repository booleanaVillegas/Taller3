import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.video.Capture;

public class AnimalView {
	private Capture cam;
	/** este objeto recibe la camara del computador */
	private PApplet app;
	float v = (float) (1 / 9.0);
	float[][] kernel = { { v, v, v }, { v, v, v }, { v, v, v } };

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

		cam.loadPixels();
		app.colorMode(app.RGB);
		// Create an opaque image of the same size as the original
		PImage edgeImg = app.createImage(cam.width, cam.height, app.RGB);
		PImage edgeImg2 = app.createImage(cam.width, cam.height, app.RGB);
		// Loop through every pixel in the image
		for (int y = 0; y < cam.height; y++) { // Skip top and bottom edges
			for (int x = 0; x < cam.width; x++) { // Skip left and right
				int pos = x + y * cam.width; // edges

				float g = app.green(cam.pixels[pos]);
				float r = app.red(cam.pixels[pos]);
				float b = app.blue(cam.pixels[pos]);
				float h = app.hue(cam.pixels[pos]);
				float s = app.saturation(cam.pixels[pos]);
				float bri = app.brightness(cam.pixels[pos]);
				edgeImg.pixels[pos] = app.color(r, 0, 0,50);
				cam.pixels[pos] = app.color(0, 0, b, 50);
				if (b < 20) {
					edgeImg2.pixels[pos] = app.color(0, 0, 0);
				} else {

					edgeImg2.pixels[pos] = app.color(r,g,b);
				}

			}
		}
		// State that there are changes to edgeImg.pixels[]

		edgeImg.updatePixels();
		PVector vector;
		vector = new PVector(1, 1);

		float scaleFactor = (float) 1;
		app.image(edgeImg2, 0, 0, edgeImg.width * scaleFactor, edgeImg.height * scaleFactor);
		
		app.image(edgeImg, -10, 0, edgeImg.width * scaleFactor, edgeImg.height * scaleFactor);
		app.image(cam, 10, 0, cam.width * scaleFactor, cam.height * scaleFactor);
		// Draw the new image
		// return edgeImg;

		return edgeImg;
	}

}
