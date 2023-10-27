package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.audio.Sound;


public class PingBallMejora extends PingBall{
	private Color color = Color.RED;
    private boolean destroyed = false;
	
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
	public void checkCollision(Paddle paddle) {
		if (collidesWith(paddle)) {
			color = Color.GREEN;
	        destroyed = true;
		} else {
			color = Color.RED;

		}
	}
	
	private boolean collidesWith(Paddle pp) {
		boolean intersectaX = (pp.getX() + pp.getWidth() >= getX() - getSize()) && (pp.getX() <= getX() + getSize());
		boolean intersectaY = (pp.getY() + pp.getHeight() >= getY() - getSize()) && (pp.getY() <= getY() + getSize());
		return intersectaX && intersectaY;
	}

	private boolean collidesWith(Block bb) {
		boolean intersectaX = (bb.x + bb.width >= getX() - getSize()) && (bb.x <= getX() + getSize());
		boolean intersectaY = (bb.y + bb.height >= getY() - getSize()) && (bb.y <= getY() + getSize());
		return intersectaX && intersectaY;
	}

	public void checkCollision(Block block) {
		if(collidesWith(block)) {
			sonidoColision.play();
			ySpeed = -ySpeed;
			block.destroyed = true;

			
		}
	}
	
	public void destroy() {
		if(destroyed = true) {
			setX(-1000);
	        setY(-1000);
		}
	}
	
}
