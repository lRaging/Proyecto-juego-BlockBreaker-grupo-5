package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


//clase implementada de Nivel con para la implementacion de los metodos abstractos
public class NivelDificil implements Nivel{

	private ShapeRenderer shape;
	private PingBallNormal ball;
	private PingBallMejora ballMejora;
	private Paddle pad;
	private ArrayList<Block> blocks = new ArrayList<>();
	private int vidas;
	private int puntaje;

	int juegoAncho = Gdx.graphics.getWidth(); // Ancho del juego
	int juegoAlto = Gdx.graphics.getHeight(); // Alto del juego
	// Genera coordenadas x e y aleatorias dentro de los límites del juego
	Random random = new Random();
	int xAleatorio = random.nextInt(juegoAncho - 20); // Ajusta el valor máximo para que no se salga de los bordes
	int yAleatorio = random.nextInt(juegoAlto + 1500);
	 
	public NivelDificil() {
		// Método constructor para inicializar el nivel difícil
		// Crear bloques para el nivel
		crearBloques(5);

		shape = new ShapeRenderer();
	    shape.setAutoShapeType(true);
		
		// Inicializar la pelota y el paddle con posiciones y tamaños específicos
		ball = new PingBallNormal(Gdx.graphics.getWidth()/2-10, 41, 5, 14,14, true);
		ballMejora = new PingBallMejora(xAleatorio, yAleatorio, 7, 7, 10, true);
		pad = new Paddle(Gdx.graphics.getWidth()/2-50,40,70,5);

		vidas = 1;
		puntaje = 0;
	}
	 // Método para crear bloques en el nivel con un número específico de filas
	public void crearBloques(int filas) {
		int blockWidth = 48;
		int blockHeight = 20;
		int y = Gdx.graphics.getHeight();
		for (int cont = 0; cont<filas; cont++ ) {
			y -= blockHeight+10;
			for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
				blocks.add(new Block(x, y, blockWidth, blockHeight));
			}
		}
	}

	// Método para comprobar si el jugador ha perdido todas sus vidas
	public boolean perder() {
		if (vidas<=0) {
			return true;
		}
		else 
			return false;
	}

	 // Método para comprobar la posición de la pelota y actualizar el número de vidas si la pelota se sale de la pantalla
	public void comprobarPelota() {
		if (ball.getY()<0) {
			vidas--;
		}
	}


	// Método para actualizar la posición de la pelota y la acción de empezar a moverse al presionar la tecla de espacio
	public void actualizarLugar() {
		if (ball.estaQuieto()) {
			ball.setXY(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11);
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ball.setEstaQuieto(false);
		}else { 
			ball.update();
			ballMejora.update();
		}
	}

	// Método para comprobar colisiones entre las pelotas y el paddle, y realizar acciones según la colisión
	public void colisionesPelotas() {
	    boolean colisionBallMejora = ballMejora.checkCollision(pad);
		ball.checkCollision(pad);
		ballMejora.checkCollision(pad);
		if(colisionBallMejora == true) {
			mejoras();
		} else {
			//si el PingBallMejora se escapa de la pantalla, se le resta un punto siempre y cuando sea mayor estricto a 0
			if ((ballMejora.getY() < 0)) {
				if (puntaje > 0) {
					puntaje--;
				}
			}
			
		}
	}

	// Método para dibujar las pelotas en el juego
	public void dibujarPelotas() {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		ball.draw(shape);
		ballMejora.draw(shape);
		shape.end();
	}

	// Método para verificar si el nivel actual está completo
	public boolean nivelCompleto() {
		if (blocks.isEmpty()) {
			return true;
		}
		return false;
	}

	 // Método para dibujar el paddle en el juego

	public void dibujarPaddle() {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		pad.draw(shape);
		shape.end();
	}

	// Método para actualizar los bloques en el juego y el puntaje del jugador
	public void actualizarBloques() {
		for (int i = 0; i < blocks.size(); i++) {
			Block b = blocks.get(i);
			if (b.isDestroyed()) {
				puntaje =+ 10;
				blocks.remove(b);
				i--; //para no saltarse 1 tras eliminar del arraylist
			}
		}
	}

	// Método para dibujar los bloques en el juego
	public void dibujarBloques() {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		for (Block b : blocks) {
			b.draw(shape);
			ball.checkCollision(b);
		}
		shape.end();
	}
	
	
    public int getPuntaje() {
        return puntaje;
    }

    public int getVidas() {
        return vidas;
    }

// Método para implementar la ventaja del pingBallMejora, aumentando tamaño y reduciendo velocidad
    public void mejoras() {
        ball.setSize(ball.getSize() + 10); 
        float factorReduccion = 0.7f;
        ball.setxSpeed(ball.getxSpeed() * factorReduccion);
        ball.setySpeed(ball.getySpeed() * factorReduccion);
        int nuevoAncho = pad.getWidth() + 20;
        pad.setWidth(nuevoAncho);
    }
}
