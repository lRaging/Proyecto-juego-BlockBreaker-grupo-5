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
	private Nivel administrar;
    
	public GameScreen(BlockBreakerMenu game, int nivel) {
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
        
		definirNivel(nivel);
	}


	public void dibujaTextos() {
		//actualizar matrices de la cámara
		camera.update();
		//actualizar
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//dibujar textos
		font.draw(batch, "Puntos: " + administrar.getPuntaje(), 10, 25);
		font.draw(batch, "Vidas : " + administrar.getVidas(), Gdx.graphics.getWidth()-20, 25);
		batch.end();
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
		administrar.dibujarBloques();
		// actualizar estado de los bloques
		administrar.actualizarBloques();
		
		administrar.colisionesPelotas();
		administrar.dibujarPelotas();
		
		dibujaTextos();
	}

	public void definirNivel(int nivel) {
	    if (nivel == 1) {
	        administrar = new NivelFacil();
	    } else if (nivel == 2) {
	        administrar = new NivelMedio();
    	} else if (nivel == 3) {
    		administrar = new NivelDificil();
    	}else {
    		administrar = new NivelLibre();
    	}
  
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
