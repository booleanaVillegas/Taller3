import processing.core.PApplet;

public class MainApp extends PApplet {
	Logica app;

	public void setup() {
		size(1280, 720);
		app = new Logica(this);
	}

	public void draw() {
		background(255);
		app.estados();

	}

	public void keyPressed() {
		app.wii();

	}
}
