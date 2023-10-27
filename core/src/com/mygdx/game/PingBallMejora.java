package com.mygdx.game;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Gdx;

public class PingBallMejora extends PingBall{
	private Color color = Color.RED;

	
	public PingBallMejora(int x, int y, float size, float xSpeed, float ySpeed, boolean iniciaQuieto) {
		super(x, y, size, xSpeed, ySpeed, iniciaQuieto);
		sonidoColision = Gdx.audio.newSound(Gdx.files.internal("choqueBall.mp3")); // Inicializa sonidoColision

	}

	public void draw(ShapeRenderer shape) {
		shape.setColor(color);
		shape.circle(getX(), getY(), getSize());
	}
	
	public void update() {
		setY(getY() - ySpeed);
	}
	
	@Override
	public boolean checkCollision(Paddle paddle) {
		if (collidesWith(paddle)) {
			sonidoColision.play();
		    setY(-1000);
		    return true;
		    
		}
		return false;
	}
	
	private boolean collidesWith(Paddle pp) {
		boolean intersectaX = (pp.getX() + pp.getWidth() >= getX() - getSize()) && (pp.getX() <= getX() + getSize());
		boolean intersectaY = (pp.getY() + pp.getHeight() >= getY() - getSize()) && (pp.getY() <= getY() + getSize());
		return intersectaX && intersectaY;
	}

	public void checkCollision(Block block) {
	}
	
}
