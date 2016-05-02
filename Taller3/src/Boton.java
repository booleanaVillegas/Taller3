import processing.core.*;

public class Boton {
	private PApplet app;
	private float opacity = 2, o = 8;
	private PImage imagen;

	public Boton(PApplet app, PImage imagen) {
		this.app = app;
		this.imagen = imagen;
	}

	public void pintar(int posX, int posY) {
		opacity = o + opacity;

		if (opacity <= 0 || opacity >= 255) {

			o *= -1;
		}
		app.colorMode(app.RGB, 255);
		app.tint(255, opacity);
		System.out.println(opacity);
		app.image(imagen, posX, posY);
		app.text("string", posX, posY);
		app.noTint();

	}


}
