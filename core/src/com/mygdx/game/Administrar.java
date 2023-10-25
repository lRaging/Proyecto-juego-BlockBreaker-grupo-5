package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Administrar {
	
	private ShapeRenderer shape;
	private PingBallNormal ball;
	private PingBallMejora ballMejora;
	private Paddle pad;
	private ArrayList<Block> blocks = new ArrayList<>();
	private int vidas;
	private int puntaje;
	private int nivel;
	
	public Administrar() {
		nivel = 1;
		crearBloques(2+nivel);

		shape = new ShapeRenderer();
		ball = new PingBallNormal(Gdx.graphics.getWidth()/2-10, 41, 10, 5, 7, true);
		ballMejora = new PingBallMejora(Gdx.graphics.getWidth()/2-10, 350, 10, 5, 7, true);
		pad = new Paddle(Gdx.graphics.getWidth()/2-50,40,100,10);
        shape.setAutoShapeType(true);
        
		vidas = 3;
		puntaje = 0;
	}
	
	public void crearBloques(int filas) {
		blocks.clear();
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
	
	public boolean perder() {
		if (vidas<=0) {
			return true;
		}
		else 
			return false;
	}
	
	public void comprobarPelota() {
		if (ball.getY()<0) {
			vidas--;
			ball = new PingBallNormal(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
		}
	}
	
	public void actualizarLugar() {
		if (ball.estaQuieto()) {
			ball.setXY(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11);
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ball.setEstaQuieto(false);
		}else { 
			ball.update();
			ballMejora.update();
		}
	}
	
	public void colisionesPelotas() {
		ball.checkCollision(pad);
		ballMejora.checkCollision(pad);
	}
	
	public void dibujarPelotas() {
		ball.draw(shape);
		ballMejora.draw(shape);
		
	}
	
	public void nivelCompleto() {
		if (blocks.isEmpty()) {
			nivel++;
			crearBloques(2+nivel);
			ball = new PingBallNormal(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
		}
	}
	
	public void dibujarPaddle() {
		pad.draw(shape);

	}
}