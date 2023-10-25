package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class GameScreen implements Screen {
	final BlockBreakerMenu game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private BitmapFont font;
	private ShapeRenderer shape;
	private PingBallNormal ball;
	private Paddle pad;
	private ArrayList<Block> blocks = new ArrayList<>();
	private int vidas;
	private int puntaje;
	private int nivel;
	private Administrar administrar;
    
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
		shape = new ShapeRenderer();

		
		administrar = new Administrar();
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
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);

		administrar.dibujarPaddle();
		//Ver inicio del movimiento y actualizarlo
		administrar.actualizarLugar();
		//verificar si se fue la bola x abajo
		administrar.comprobarPelota();
		// verificar game over
		if (administrar.perder() == true) {
	    	game.setScreen(new GameOverScreen(game));
	    	}
		
		//HACER LO DE LOS BLOQUES PARA DISTINTOS NIVELES
		// verificar si el nivel se terminó
		administrar.nivelCompleto();
		
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
		
		administrar.colisionesPelotas();
		administrar.dibujarPelotas();
		
		
		dibujaTextos();
		shape.end();
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
