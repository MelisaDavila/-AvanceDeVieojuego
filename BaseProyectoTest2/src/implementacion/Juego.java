package implementacion;

import java.util.ArrayList;
import java.util.HashMap;

import clases.Item;
import clases.JugadorAnimado;
import clases.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Juego extends Application{
	private Scene escena;
	private Group root;
	private Canvas canvas;
	private GraphicsContext graficos;
	private GraphicsContext fondo;
	private int puntuacion = 0;
	//private Jugador jugador;
	private JugadorAnimado jugadorAnimado;
	public static boolean derecha=false;
	public static boolean izquierda=false;
	public static boolean arriba=false;
	public static boolean abajo=false;
	public static HashMap<String, Image> imagenes; //Shift+Ctrl+O
	private Item item;
	private Item item2;
	private Item item3;
	private Item item4;
    
	//private ArrayList<Image> imagenes;
	
	private ArrayList<Tile> tiles;
	
	private int[][] mapa = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,2,2,2,2,2,2,2,2,2,2,2,2,2,3,0,0,1,2,2,2,2,2,2,2,3,0,4,5,5,5,5,5,5,5,5,5,6,0,0,0,0,0,0,1,2,3,0,1,2,3,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};
	
	
	/*private ArrayList<Item> items;
	
	private int[][] mapaItems = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			{0,0,0,1,1,1,1,0,0,0,2,,0,0,0,0,0,0,11,1,1,1,1,0,0,0,0,0,3,0,0,0,0,0,0,0,3,0}
	};*/
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage ventana) throws Exception {
		inicializarComponentes();
		graficos = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		ventana.setScene(escena);
		ventana.setTitle("ElSobreviviente");
		gestionarEventos();
		ventana.show();
		cicloJuego();		
	}
	
	public void inicializarComponentes() {
		//jugador = new Jugador(-50,400,"goku",1);
		jugadorAnimado = new JugadorAnimado(50,338,"humano",1, "correr");
		root = new Group();
		escena = new Scene(root,999, 550);
		canvas  = new Canvas(999,550);
		imagenes = new HashMap<String,Image>();
	    item = new Item(300,370,0,0,"Moneda");
		item2 = new Item(400,350,0,0,"Vidas");
		item4 = new Item(500,320,0,0,"Vidas");
		item3 = new Item(600,350,0,0,"CajaDeMonedas");
		cargarImagenes();
		cargarTiles();
	}
	
	public void cargarImagenes() {
		//imagenes.put("goku", new Image("goku.png"));
		//imagenes.put("goku-furioso", new Image("goku-furioso.png"));
		imagenes.put("tilemap", new Image("TilesDelMapa.png"));
		imagenes.put("humano", new Image("Humano.png"));
		imagenes.put("Moneda", new Image("Moneda.png"));
		imagenes.put("Vidas", new Image("Vidas.png"));
		imagenes.put("CajaDeMonedas", new Image("CajaDeMonedas.png"));
		imagenes.put("Fondo1", new Image("Fondo1.jpg"));
		
	}
	
	public void pintar() {
		graficos.setFill(Color.WHITE);
		graficos.fillRect(0, 0, 999, 550);
		graficos.setFill(Color.BLACK);
		graficos.fillText("Puntuacion: " + puntuacion, 10, 10);
		graficos.drawImage(imagenes.get("Fondo1"), 0, -160);
		///Pintar tiles
		for (int i=0;i<tiles.size();i++)
			tiles.get(i).pintar(graficos);
		//jugador.pintar(graficos);
		jugadorAnimado.pintar(graficos);
		item.pintar(graficos);
		item2.pintar(graficos);
		item3.pintar(graficos);
		item4.pintar(graficos);

	}
	
	public void cargarTiles() {
		tiles = new ArrayList<Tile>();
		for(int i=0; i<mapa.length; i++) {
			for(int j=0; j<mapa[i].length; j++) {
				if (mapa[i][j]!=0)
					tiles.add(new Tile(mapa[i][j], i*69, j*69, "tilemap",0));
			}
		}
	}
	
	/*public void cargarItems() {
		items = new ArrayList<Item>();
		for(int i=0; i<mapaItems.length; i++) {
			for(int j=0; j<mapaItems[i].length; j++) {
				if (mapaItems[i][j]!=0)
					items.add(new Item(mapaItems[i][j], i*69, j*69, "moneda",0));
			}
		}
		
	} */
	
	public void gestionarEventos() {
		//Evento cuando se presiona una tecla
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent evento) {
					//Aqui tengo que poner el codigo para identificar cuando se presiono una tecla
					switch (evento.getCode().toString()) {
						case "RIGHT": //derecha
							derecha=true;
							break;
						case "LEFT": //derecha
							izquierda=true;
						break;
						case "UP":
							arriba=true;
							break;
						case "DOWN":
							abajo=true;
							break;
						case "SPACE":
							//jugador.setVelocidad(10);
							//jugador.setIndiceImagen("goku-furioso");
							break;
					}
			}			
		});
		
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent evento) {
				//Aqui tengo que poner el codigo para identificar cuando se soltó una tecla
				switch (evento.getCode().toString()) {
				case "RIGHT": //derecha
					derecha=false;
					break;
				case "LEFT": //derecha
					izquierda=false;
				break;
				case "UP":
					arriba=false;
					break;
				case "DOWN":
					abajo=false;
					break;
				case "SPACE":
					jugadorAnimado.setVelocidad(1000);
					//jugador.setIndiceImagen("goku");
					break;
			}
				
			}
			
		});
		
	}
	
	public void cicloJuego() {
		long tiempoInicial = System.nanoTime();
		AnimationTimer animationTimer = new AnimationTimer() {
			//Esta rutina simula un ciclo de 60FPS
			@Override
			public void handle(long tiempoActualNanoSegundos) {
				double t = (tiempoActualNanoSegundos - tiempoInicial) / 1000000000.0;
				pintar();
				actualizar(t);
				
			}
			
		};
		animationTimer.start(); //Inicia el ciclo
	}
	
	public void actualizar(double t) {
		jugadorAnimado.mover();
		jugadorAnimado.actualizarAnimacion(t);
		jugadorAnimado.verificarColisiones(item);
		jugadorAnimado.verificarColisiones(item2);
		jugadorAnimado.verificarColisiones(item4);
		jugadorAnimado.verificarColisiones(item3);
	}

}
