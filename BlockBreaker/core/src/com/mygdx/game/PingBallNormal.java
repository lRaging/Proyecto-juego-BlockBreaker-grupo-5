package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PingBallNormal extends PingBall {
	private Color color = Color.WHITE;

	public PingBallNormal(int x, int y, float size, float xSpeed, float ySpeed, boolean iniciaQuieto) {
		super(x, y, size, xSpeed, ySpeed, iniciaQuieto);
	}

	@Override
	public void draw(ShapeRenderer shape) {
		shape.setColor(color);
		shape.circle(getX(), getY(), getSize());
	}

	@Override
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
	public void checkCollision(Paddle paddle) {
		if (collidesWith(paddle)) {
			color = Color.GREEN;
			ySpeed = -ySpeed;
		} else {
			color = Color.WHITE;
		}
	}

	private boolean collidesWith(Paddle pp) {
		boolean intersectaX = (pp.getX() + pp.getWidth() >= getX() - getSize()) && (pp.getX() <= getX() + getSize());
		boolean intersectaY = (pp.getY() + pp.getHeight() >= getY() - getSize()) && (pp.getY() <= getY() + getSize());
		return intersectaX && intersectaY;
	}

	@Override
	public void checkCollision(Block block) {
		if (collidesWith(block)) {
			ySpeed = -ySpeed;
			block.destroyed = true;
		}
	}

	private boolean collidesWith(Block bb) {
		boolean intersectaX = (bb.x + bb.width >= getX() - getSize()) && (bb.x <= getX() + getSize());
		boolean intersectaY = (bb.y + bb.height >= getY() - getSize()) && (bb.y <= getY() + getSize());
		return intersectaX && intersectaY;
	}
}
