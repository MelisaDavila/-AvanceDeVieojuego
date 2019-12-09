package clases;

import java.util.HashMap;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class JugadorAnimado {
	private int x;
	private int y;
	private String indiceImagen;
	private int velocidad;
	private HashMap<String, Animacion> animaciones;
	private String animacionActual;
	private int puntuacion = 0;
	
	//Coordenadas para el fragmento de la imagen a pintar
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	
	public JugadorAnimado(int x, int y, String indiceImagen, int velocidad, String animacionActual) {
		super();
		this.x = x;
		this.y = y;
		this.indiceImagen = indiceImagen;
		this.velocidad = velocidad;
		this.animacionActual = animacionActual;
		inicializarAnimaciones();
	}
	
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getIndiceImagen() {
		return indiceImagen;
	}
	public void setIndiceImagen(String indiceImagen) {
		this.indiceImagen = indiceImagen;
	}
	
	//Obtener las coordenas del fragmento de la imagen a pintar
	public void actualizarAnimacion(double t) {
		Rectangle coordenadasActuales = this.animaciones.get(animacionActual).calcularFrame(t);
		this.xImagen = (int)coordenadasActuales.getX();
		this.yImagen = (int)coordenadasActuales.getY();
		this.anchoImagen = (int)coordenadasActuales.getWidth();
		this.altoImagen = (int)coordenadasActuales.getHeight();
	}
	
	public void mover(){
		if (this.x>=1100)
			this.x = -100;
		if (Juego.derecha)
			this.x+=velocidad;
		
		if (Juego.izquierda)
			this.x-=velocidad;
		
		//if (Juego.arriba)
			this.y-=velocidad;
		
		//if (Juego.abajo)
			this.y+=velocidad;
	}
	
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(
				Juego.imagenes.get(this.indiceImagen), 
				this.xImagen, this.yImagen, 
				this.anchoImagen, this.altoImagen,
				this.x, this.y,
				this.anchoImagen, this.altoImagen
		);
		//graficos.fillRect(this.x, this.y, this.anchoImagen, this.altoImagen);
		graficos.fillText("Puntuacion " + puntuacion, 40, 50);
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, this.anchoImagen, this.altoImagen);
	}
	
	public void inicializarAnimaciones() {
			animaciones = new HashMap<String, Animacion>();
			Rectangle coordenadasCorrer[]= {
					new Rectangle(313,103, 46,81),
					new Rectangle(357,103, 50,80),
					new Rectangle(404,103, 57,57),
					new Rectangle(461,103, 49,76),
					new Rectangle(509,103, 49,78),
					new Rectangle(557,103, 52,77)
			};
			
			
			Animacion animacionCorrer = new Animacion("correr",coordenadasCorrer,0.1);
			animaciones.put("correr",animacionCorrer);
			
            Rectangle coordenadasCaminar[]= {
            		new Rectangle(15, 103, 48,81),
					new Rectangle(65, 103, 46,81),
					new Rectangle(115,103, 46,81),
					new Rectangle(165,103, 46,81),
					new Rectangle(215,103, 46,81),
					new Rectangle(265,103, 46,81),
			};
					
            Animacion animacionCaminar = new Animacion("caminar", coordenadasCorrer,0.1);
            animaciones.put("caminar",animacionCaminar);
			
			Rectangle coordenadasDescanso[] = {
					new Rectangle(26, 16, 63,73),
					new Rectangle(89,16, 63,73),
					new Rectangle(154,16, 63,73),
					new Rectangle(226,16, 63,73)
			};

						
			Animacion animacionDescanso = new Animacion("descanso",coordenadasDescanso,0.2);
			animaciones.put("descanso",animacionDescanso);
	}
	
	public void verificarColisiones(Item item) {
		if (this.obtenerRectangulo().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
				System.out.println("Estan colisionando");
				if (!item.isCapturado())
					this.puntuacion++;
				item.setCapturado(true);				
		}
	}
}


