import processing.core.*;
import processing.video.*;
public class Video {
	private PApplet app;
	private int posX, posY;
	private Movie movie;
	
	public Video (PApplet app,int posX, int posY, Movie movie){
		this.app= app; 
		this.posX=posX; 
		this.posY=posY; 
		this.movie=movie; 
		
	}
	public void pintar(){
		app.image(movie, posX, posY);
		
	}
	public boolean validar(){
		return app.dist(posX+movie.width/2,posY+movie.height/2, app.mouseX, app.mouseY)<180;
		
		
	}
}
