package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameWinScreen implements Screen {
	// Inicialización de las variables de instancia en el constructor
	private final BlockBreakerMenu game;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private OrthographicCamera camera;
	private Texture backgroundTexture;

	public GameWinScreen(final BlockBreakerMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		 // Carga la textura de fondo de la pantalla de victoria desde un archivo interno
		backgroundTexture = new Texture(Gdx.files.internal("Victoria.png"));
	}

	@Override
	// Método para renderizar la pantalla de victoria del juego
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		font.getData().setScale(2, 2);
		batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth()+160, Gdx.graphics.getHeight());
		batch.end();

		//si se hace click a la pantalla de victoria, se comienza con un nuevo juego mostrando el menu principal 
		if (Gdx.input.isTouched()) {
			game.setScreen(MainMenuScreen.getInstance(game));
			dispose();
		}
	}

	@Override
	 // Métodos de la interfaz Screen que no se implementan en esta clase
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
