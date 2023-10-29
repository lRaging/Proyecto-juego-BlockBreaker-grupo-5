package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class NivelFacil implements Nivel{
	
	private ShapeRenderer shape;
	private PingBallNormal ball;
	private PingBallMejora ballMejora;
	private Paddle pad;
	private ArrayList<Block> blocks = new ArrayList<>();
	private int vidas;
	private int puntaje;
	private int nivel;
	
	int juegoAncho = Gdx.graphics.getWidth(); // Ancho del juego
	int juegoAlto = Gdx.graphics.getHeight(); // Alto del juego
	// Genera coordenadas x e y aleatorias dentro de los límites del juego
	Random random = new Random();
	int xAleatorio = random.nextInt(juegoAncho - 50); // Ajusta el valor máximo para que no se salga de los bordes
	int yAleatorio = random.nextInt(juegoAlto + 2500);
	
	public NivelFacil() {
		// Método constructor para inicializar el nivel fácil
		nivel = 1;
		crearBloques(2+nivel);

		shape = new ShapeRenderer();
	    shape.setAutoShapeType(true);

		// Inicializar la pelota, la pelota de mejora y el paddle con posiciones y tamaños específicos
		ball = new PingBallNormal(Gdx.graphics.getWidth()/2-10, 41, 10, 5, 7, true);
		ballMejora = new PingBallMejora(xAleatorio, yAleatorio, 10, 5, 7, true);
		pad = new Paddle(Gdx.graphics.getWidth()/2-50,40,100,10);

		vidas = 5;
		puntaje = 0;
	}


	// Método para crear bloques en el nivel con un número específico de filas
	public void crearBloques(int filas) {
		int blockWidth = 70;
		int blockHeight = 26;
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
			int xAleatorio = random.nextInt(juegoAncho - 50); // Ajusta el valor máximo para que no se salga de los bordes
			int yAleatorio = random.nextInt(juegoAlto + 2500);
			vidas--;
			ball = new PingBallNormal(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
			ballMejora = new PingBallMejora(xAleatorio, yAleatorio, 10, 5, 7, true);
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
		}else {
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
			if (b.destroyed) {
				puntaje++;
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
	
	//getters de puntaje y vidas
    public int getPuntaje() {
        return puntaje;
    }

    public int getVidas() {
        return vidas;
    }

	//ventaja de aumentar vidas y el tamaño del pingBall
    public void mejoras() {
        vidas++;
        
        ball.setSize(ball.getSize() + 5); 
    }
}
