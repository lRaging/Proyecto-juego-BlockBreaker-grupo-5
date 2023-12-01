package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen {
	// Variables de instancia para la pantalla de juego
	private final BlockBreakerMenu game;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private OrthographicCamera camera;
	private Texture backgroundTexture;

	// Inicialización de la cámara, el batch, el font, el shape y la textura de fondo del juego
	public GameOverScreen(final BlockBreakerMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		backgroundTexture = new Texture(Gdx.files.internal("GameOver.png"));
	}

	 // Método para renderizar la pantalla de juego
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		font.getData().setScale(2, 2);
		batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth()+160, Gdx.graphics.getHeight());
		batch.end();

		//si se realiza un click, se inicializa el juego desde el menuPrincipal
		if (Gdx.input.isTouched()) {
			game.setScreen(MainMenuScreen.getInstance(game));
			dispose();
		}
	}

	// Métodos de la interfaz Screen que no se implementan en esta clase
	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
