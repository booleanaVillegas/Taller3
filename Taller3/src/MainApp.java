import processing.core.PApplet;

public class MainApp extends PApplet {
	Logica app;

	public void setup() {
		size(1280, 720);
		app = new Logica(this);
	}

	public void draw() {
		app.display();

	}

	public void keyPressed() {
		app.makey();

	}
}
