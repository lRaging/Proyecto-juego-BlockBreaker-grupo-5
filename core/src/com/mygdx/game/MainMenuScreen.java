package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class MainMenuScreen implements Screen {

	final BlockBreakerMenu game;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;

	// CONSTRUCTOR
	public MainMenuScreen(final BlockBreakerMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		// MENSAJE MENU
		batch.begin();
		font.getData().setScale(2, 2);
		font.draw(batch, "Bienvenido a BLOCK BREAKER!!! ", 200, camera.viewportHeight/2+150);
		font.draw(batch, "1. Nivel Facil!", 200, camera.viewportHeight/2+50);
		font.draw(batch, "2. Nivel Medio!", 200, camera.viewportHeight/2);
		font.draw(batch, "3. Nivel Difcil!", 200, camera.viewportHeight/2-50);
		font.draw(batch, "4. Nivel Libre!", 200, camera.viewportHeight/2-100);
		batch.end();

		// COMIENZA EL JUEGO
		if (Gdx.input.justTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

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
