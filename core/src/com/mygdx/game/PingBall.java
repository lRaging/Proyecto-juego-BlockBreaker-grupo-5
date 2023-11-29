package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


//clase de tipo abstracta para implementar dos variantes de ella que tendrán distintos comportamientos
public abstract class PingBall {
	private float x;
	private float y;
	protected float xSpeed ;
	protected float ySpeed;
	private float size;
	private boolean estaQuieto;
	protected Sound sonidoColision; 


	//constructor para la posicion (x,y), tamaño, velocidad en ambas coordenadas  y su posicion inicial
	public PingBall(int x, int y, float size, float xSpeed, float ySpeed, boolean iniciaQuieto) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		estaQuieto = iniciaQuieto;
	}

	//métodos abstractos a implementar en las clases PingBallMejora y PingBallNormal
	public abstract boolean checkCollision(Paddle paddle);
	public abstract void checkCollision(Block block);
	public abstract void draw(ShapeRenderer shapeRenderer);
	public abstract void update();

	public boolean estaQuieto() {
		return estaQuieto;
	}

	//setter y getter para los atributos que poseen esta clase
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
	
	public void setSize(float size) {
	    this.size = size;
	}
	
	public void setySpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}
	public float getySpeed() {
		return ySpeed;
	}
	public void setxSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}
	public float getxSpeed() {
		return ySpeed;
	}
}
