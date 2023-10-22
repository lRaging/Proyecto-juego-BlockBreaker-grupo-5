package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class GameScreen implements Screen {
	final BlockBreakerMenu game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private BitmapFont font;
	private ShapeRenderer shape;
	private PingBall ball;
	private Paddle pad;
	private ArrayList<Block> blocks = new ArrayList<>();
	private int vidas;
	private int puntaje;
	private int nivel;
    private Niveles dificultad;
    
	public GameScreen(BlockBreakerMenu game) {
		this.game = game;
		this.batch = game.getBatch();
		this.font = game.getFont();

		// INICIO DEL JUEGO
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.getData().setScale(3, 2);
		nivel = 1;
		crearBloques(2+nivel);

		shape = new ShapeRenderer();
		ball = new PingBall(Gdx.graphics.getWidth()/2-10, 41, 10, 5, 7, true);
		pad = new Paddle(Gdx.graphics.getWidth()/2-50,40,100,10);
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
	public void dibujaTextos() {
		//actualizar matrices de la cámara
		camera.update();
		//actualizar
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//dibujar textos
		font.draw(batch, "Puntos: " + puntaje, 10, 25);
		font.draw(batch, "Vidas : " + vidas, Gdx.graphics.getWidth()-20, 25);
		batch.end();
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		pad.draw(shape);
		// monitorear inicio del juego
		if (ball.estaQuieto()) {
			ball.setXY(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11);
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ball.setEstaQuieto(false);
		}else ball.update();
		//verificar si se fue la bola x abajo
		if (ball.getY()<0) {
			vidas--;
			//nivel = 1;
			ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
		}
		// verificar game over
		if (vidas<=0) {
	    	game.setScreen(new GameOverScreen(game));
	    	if(Gdx.input.isTouched()) {
	            game.setScreen(new MainMenuScreen(game));
	    	}
		}
		
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
        	dificultad.nivel(1);
        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
        	dificultad.nivel(2);
        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
        	dificultad.nivel(3);
        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {
            dificultad.nivel(4);
        }
   
		// verificar si el nivel se terminó
		if (blocks.size()==0) {
			nivel++;
			crearBloques(2+nivel);
			ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
		}
		//dibujar bloques
		for (Block b : blocks) {
			b.draw(shape);
			ball.checkCollision(b);
		}
		// actualizar estado de los bloques
		for (int i = 0; i < blocks.size(); i++) {
			Block b = blocks.get(i);
			if (b.destroyed) {
				puntaje++;
				blocks.remove(b);
				i--; //para no saltarse 1 tras eliminar del arraylist
			}
		}

		ball.checkCollision(pad);
		ball.draw(shape);

		shape.end();
		dibujaTextos();
	}

	@Override
	public void show() {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose () {

	}

}
