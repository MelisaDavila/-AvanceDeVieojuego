package clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Item {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private String indiceImagen;
	private boolean capturado;
	/*private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;*/
	
	public Item(int x, int y, int ancho, int alto, String indiceImagen) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.indiceImagen = indiceImagen;
	}
	
	public void pintar(GraphicsContext graficos) {
		if (!capturado)
			graficos.drawImage(Juego.imagenes.get(indiceImagen), this.x, this.y);
		//graficos.fillRect(this.x, this.y, 18, 18);
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, 18, 18);
	}

	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}
	
	
	/*public Item(int tipoItem,int x, int y, String indiceImagen, int velocidad){
		this.x = x;
		this.y = y;
		this.indiceImagen = indiceImagen;
		this.velocidad = velocidad;
		//this.invertir = invertir;
		switch(tipoItem){
		case 1:
			this.altoImagen = 40;
			this.anchoImagen = 40;
			this.xImagen = 0;
			this.yImagen = 0;
		break;
		case 2:
			this.altoImagen = 40;
			this.anchoImagen = 70;
			this.xImagen = 70;
			this.yImagen = 710;
		break;
		case 3:
			this.altoImagen = 70;
			this.anchoImagen = 70;
			this.xImagen = 70;
			this.yImagen = 568;
		break;
    	}
    } */
}
