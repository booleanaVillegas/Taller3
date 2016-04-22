import processing.core.*;
import processing.video.*;
import processing.video.Capture;

public class Chroma implements Filtrable {

	/** este objeto recibe la camara del computador */
	private PApplet app;
	private Capture cam;
	private PImage imagen;
	int cantidadVerde = 0, posXV = 0, posYV = 0, xV = 0, yV = 0, vI = 0;

	/** este PApplet nos permite usar metodos de la libreria de processing */

	public Chroma(PApplet app) {
		this.app = app;
		this.cam = cam;
		this.imagen = imagen;/**
								 * Este metodo es el contructor de la clase que
								 * me permite cargar e inicializar todos los
								 * objetos y variables ademas de recibir
								 * parametros del objeto
								 */
	}

	public Capture getCam() {
		return cam;
	}

	public void setCam(Capture cam) {
		this.cam = cam;
	}

	public PImage filtro(PImage cam, PImage imagen) {


		float h = 0;
		float s = 0;
		float b = 0;

		int cantidadBlue = 0, posXB = 0, posYB = 0, xB = 0, yB = 0;
		imagen.loadPixels();
		cam.loadPixels();
		app.colorMode(app.HSB, 100);
	
		for (int i = 0; i < cam.width; i++) {
			for (int j = 0; j < cam.height; j++) {
				int pix = i + j * cam.width;
				int pix2 = i + j * imagen.width;

				if ((app.hue(cam.pixels[pix]) > 22 && app.hue(cam.pixels[pix]) < 50)
						&& (app.saturation(cam.pixels[pix]) > 20) && app.brightness(cam.pixels[pix]) > 30) {
					posXV += i;
					posYV += j;
					cantidadVerde++;
					app.colorMode(app.RGB);
					cam.pixels[pix] = imagen.pixels[pix2];
				}
				
				if ((app.hue(cam.pixels[pix]) > 30 && app.hue(cam.pixels[pix]) < 60)
						&& (app.saturation(cam.pixels[pix]) > 40) && app.brightness(cam.pixels[pix]) > 50) {

					posXB += i;
					posYB += j;

					cantidadBlue++;
				//	app.colorMode(app.RGB);
					cam.pixels[pix] =  imagen.pixels[pix2];

				}
			}
		}
		cam.updatePixels();
		imagen.updatePixels();
		
//		int pix2 = app.mouseX + app.mouseY * cam.width;
//		h = app.hue(cam.pixels[pix2]);
//		s = app.saturation(cam.pixels[pix2]);
//		b = app.brightness(cam.pixels[pix2]);
//		
		
		if (app.mousePressed) {
			System.out.println("h" + h);
			System.out.println("s" + s);
			System.out.println("b" + b);
		}

		app.fill(h, s, b);
		

		/**
		 * Con este método se hará el filtro chromakey en el cual se proyectará
		 * el video planteado en el análisis de requerimientos.
		 */
		app.rect(0, 0, 30, 30);

		app.colorMode(app.HSB, 100);
		
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
		if (cantidadVerde > 0) {
			xV = posXV / cantidadVerde;
			yV = posYV / cantidadVerde;
		}
		app.fill(255, 255, 255);
		app.rect(xV, yV, 30, 30);
		
	}

}
