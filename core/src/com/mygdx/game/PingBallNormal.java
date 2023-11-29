package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

//clase hija de la clase abstracta pingball que comparte los mismos métodos con pingBallMejora pero distinto comportamiento
public class PingBallNormal extends PingBall {
	private Color color = Color.WHITE;


	//constructor para obtener las mismas medidas, velocidad, la posicion inicla de estar quieto de la clase padre PingBall, y se incluye el sonido a implementar
	public PingBallNormal(int x, int y, float size, float xSpeed, float ySpeed, boolean iniciaQuieto) {
		super(x, y, size, xSpeed, ySpeed, iniciaQuieto);
		sonidoColision = Gdx.audio.newSound(Gdx.files.internal("choqueBall.mp3")); // Inicializa sonidoColision
	}

	@Override
	//Se comienza por dibujar el circulo con su tamaño que representa el Pingball y el color de ella
	public void draw(ShapeRenderer shape) {
		shape.setColor(color);
		shape.circle(getX(), getY(), getSize());
	}

	@Override
	//asegurar de que el Pingball inicialize su posicion dentro del juego, y si se colisiona con los bordes, que se produzca un "rebote"
	public void update() {
		if (estaQuieto()) return;
		setX(getX() + xSpeed);
		setY(getY() + ySpeed);
		if (getX() - getSize() < 0 ||getX() + getSize() > Gdx.graphics.getWidth()) {
			xSpeed = -xSpeed;
		}
		if (getY() + getSize() > Gdx.graphics.getHeight()) {
			ySpeed = -ySpeed;
		}
	}

	@Override
	//si se colisiona con el paddle en la parte inferior, debe rebotarse con un cambio de color
	public boolean checkCollision(Paddle paddle) {
		if (collidesWith(paddle)) {
			color = Color.GREEN;
			ySpeed = -ySpeed;
			return true;
		} else {
			color = Color.WHITE;
			return false;
		}
	}

	//retorna si se cumple la coincidencia de coordenadas y colision del paddle con el PingBall
	private boolean collidesWith(Paddle pp) {
		boolean intersectaX = (pp.getX() + pp.getWidth() >= getX() - getSize()) && (pp.getX() <= getX() + getSize());
		boolean intersectaY = (pp.getY() + pp.getHeight() >= getY() - getSize()) && (pp.getY() <= getY() + getSize());
		return intersectaX && intersectaY;
	}

	@Override

	//Si el pingball choca con el block, debe ser destruida y rebotada
	public void checkCollision(Block block) {
		if (collidesWith(block)) {
			sonidoColision.play();
			ySpeed = -ySpeed;
			block.setDestroyed(true);
		}
	}
//retorna si se cumple la coincidencia de coordenadas y colision del block con el PingBall
	private boolean collidesWith(Block bb) {
		boolean intersectaX = (bb.getX() + bb.getWidth() >= getX() - getSize()) && (bb.getX() <= getX() + getSize());
		boolean intersectaY = (bb.getY() + bb.getHeight() >= getY() - getSize()) && (bb.getY() <= getY() + getSize());
		return intersectaX && intersectaY;
	}
}
