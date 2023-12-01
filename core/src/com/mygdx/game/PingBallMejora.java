package com.mygdx.game;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Gdx;

//clase hija de la clase abstracta pingball que comparte los mismos métodos con pingBallMejora pero distinto comportamiento
public class PingBallMejora extends PingBall{
	private Color color = Color.RED;

	//constructor para obtener los mismos parametros de la clase padre PingBall e incluir el sonido de colision
	public PingBallMejora(int x, int y, float size, float xSpeed, float ySpeed, boolean iniciaQuieto) {
		super(x, y, size, xSpeed, ySpeed, iniciaQuieto);
		sonidoColision = Gdx.audio.newSound(Gdx.files.internal("choqueBall.mp3")); // Inicializa sonidoColision

	}

	//método para dibujar el círculo con su tamaño y posicion con el color que corresponde
	public void draw(ShapeRenderer shape) {
		shape.setColor(color);
		shape.circle(getX(), getY(), getSize());
	}

	//actualiza la posicion cada vez que se inicialice el juego restando la velocidad
	public void update() {
		setY(getY() - ySpeed);
	}
	
	@Override
	//asegura que si se colisiona con el paddle, haya un cambio de veolcidad de la que venía cayendo
	public boolean checkCollision(Paddle paddle) {
		if (collidesWith(paddle)) {
			ySpeed = -ySpeed;
			return true;
		    
		}
		return false;
	}
	
	public boolean collidesWith(Paddle pp) {
		boolean intersectaX = (pp.getX() + pp.getWidth() >= getX() - getSize()) && (pp.getX() <= getX() + getSize());
		boolean intersectaY = (pp.getY() + pp.getHeight() >= getY() - getSize()) && (pp.getY() <= getY() + getSize());
		return intersectaX && intersectaY;
	}

	//método de la clase padre pero no ocurrirá nada si es que el pingBallMejora choca con el block
	public void checkCollision(Block block) {
	}
	
}
