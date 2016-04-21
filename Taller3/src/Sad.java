import processing.core.*;

import processing.video.*;

public class Sad implements Filtrable {
	private Capture cam;
	/** este objeto recibe la camara del computador */
	private PApplet app;

	/** este PApplet nos permite usar metodos de la libreria de processing */

	public Sad(PApplet app, Capture cam) {
		this.app = app;
	
		this.cam = cam; /**
						 * Este metodo es el contructor de la clase que me
						 * permite cargar e inicializar todos los objetos y
						 * variables ademas de recibir parametros del objeto
						 */
	}

	public void filtro() {

		/**
		 * Con este método se hará el filtro de un ambiente triste ,planteado en
		 * el análisis de requerimientos.
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
