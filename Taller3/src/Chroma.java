import processing.core.*;
import processing.video.*;
import processing.video.Capture;

public class Chroma implements Filtrable {

	/** este objeto recibe la camara del computador */
	private PApplet app;
	private Capture cam;

	public Chroma(PApplet app) {
		this.app = app;
		this.cam = cam;

	}

	public PImage filtro(PImage cam, Movie video) {

		float h = 0;
		float s = 0;
		float b = 0;

		video.loadPixels();
		cam.loadPixels();
		app.colorMode(app.HSB, 100);
		video.read();
		for (int i = 0; i < cam.width; i++) {
			for (int j = 0; j < cam.height; j++) {
				int pix = i + j * cam.width;
				int pix2 = i + j * video.width;

				if ((app.hue(cam.pixels[pix]) > 22 && app.hue(cam.pixels[pix]) < 50)
						&& (app.saturation(cam.pixels[pix]) > 20) && app.brightness(cam.pixels[pix]) > 30) {

					app.colorMode(app.RGB);
					cam.pixels[pix] = video.pixels[pix2];
				}

				if ((app.hue(cam.pixels[pix]) > 30 && app.hue(cam.pixels[pix]) < 60)
						&& (app.saturation(cam.pixels[pix]) > 40) && app.brightness(cam.pixels[pix]) > 50) {

					cam.pixels[pix] = video.pixels[pix2];

				}
			}
		}
		cam.updatePixels();
		video.updatePixels();

		/*if (app.mousePressed) {
			System.out.println("h" + h);
			System.out.println("s" + s);
			System.out.println("b" + b);
		}*/

		app.fill(h, s, b);
		app.colorMode(app.HSB, 100);

		return cam;
	}

	public boolean validar() {

		return false;
	}

	public Capture getCam() {
		return cam;
	}

	public void setCam(Capture cam) {
		this.cam = cam;
	}

	@Override
	public void filtro() {

	}

}
