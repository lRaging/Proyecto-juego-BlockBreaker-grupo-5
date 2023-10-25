package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public abstract class PingBall {
	private float x;
	private float y;
	float xSpeed ;
	float ySpeed;
	private float size;
	private boolean estaQuieto;

	public PingBall(int x, int y, float size, float xSpeed, float ySpeed, boolean iniciaQuieto) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		estaQuieto = iniciaQuieto;
	}

	public abstract void checkCollision(Paddle paddle);
	public abstract void checkCollision(Block block);
	public abstract void draw(ShapeRenderer shapeRenderer);
	public abstract void update();

	public boolean estaQuieto() {
		return estaQuieto;
	}

	public void setEstaQuieto(boolean bb) {
		estaQuieto = bb;
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}


	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getSize() {
		return size;
	}

}
