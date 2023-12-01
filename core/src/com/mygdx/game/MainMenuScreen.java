package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class MainMenuScreen implements Screen {

	// Variables de instancia para el menú principal del juego
	final BlockBreakerMenu game;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
	private int nivel;
	private ShapeRenderer shape;
    private static MainMenuScreen instance;

	// CONSTRUCTOR
	public MainMenuScreen(final BlockBreakerMenu game) {
		// Inicialización de las variables de instancia en el constructor
		this.game = game;
		this.batch = game.getBatch();
		this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		this.shape = new ShapeRenderer();
		
	}

    public static MainMenuScreen getInstance(BlockBreakerMenu game) {
        if (instance == null) {
            instance = new MainMenuScreen(game);
        }
        return instance;
    }
    
	@Override
	 // Método para renderizar el menú principal del juego
	public void render(float delta) {
	       Gdx.gl.glClearColor(0f, 0f, 0f, 1f); // Color de fondo superior (negro)
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		// MENSAJE MENU
		batch.begin();
		font.getData().setScale(2, 2);
		font.draw(batch, "Bienvenido a BLOCK BREAKER!!! ", 200, camera.viewportHeight/2+150);
		font.draw(batch, "Nivel Facil", 200, camera.viewportHeight/2+50);
		font.draw(batch, "Nivel Medio", 200, camera.viewportHeight/2);
		font.draw(batch, "Nivel Dificil", 200, camera.viewportHeight/2-50);
		font.draw(batch, "Nivel Libre", 200, camera.viewportHeight/2-100);
		batch.end();

		 // Dibujar rectángulos alrededor de cada nivel
		shape.begin(ShapeRenderer.ShapeType.Line);
		shape.setColor(Color.SALMON);
		// Rectángulo alrededor de "Nivel Facil"
		shape.rect(140, camera.viewportHeight/2+20, 200, 40);
		// Rectángulo alrededor de "Nivel Medio"
		shape.rect(140, camera.viewportHeight/2-28, 200, 40);
		// Rectángulo alrededor de "Nivel Dificil"
		shape.rect(140, camera.viewportHeight/2-80, 200, 40);
		// Rectángulo alrededor de "Nivel Libre"
		shape.rect(140, camera.viewportHeight/2-130, 200, 40);
		shape.end();
		
		// COMIENZA EL JUEGO
		if (Gdx.input.justTouched()) {
		    float touchX = Gdx.input.getX();
		    float touchY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Invierte la coordenada Y

		    if (touchX > 100 && touchX < 400) {
		        if (touchY > camera.viewportHeight / 2 - 5 && touchY < camera.viewportHeight) {
		            nivel = 1; // Nivel Fácil
		        } else if (touchY > camera.viewportHeight / 2 - 35 && touchY < camera.viewportHeight / 2 - 5) {
		            nivel = 2; // Nivel Medio
		        } else if (touchY > camera.viewportHeight / 2 - 75 && touchY < camera.viewportHeight / 2 - 45) {
		            nivel = 3; // Nivel Difícil
		        } else if (touchY > camera.viewportHeight / 2 - 115 && touchY < camera.viewportHeight / 2 - 85) {
		            nivel = 4; // Nivel Libre
		        }
	            if (nivel != 0) {
	                // El jugador ha seleccionado un nivel, ahora puedes iniciar el juego.
	                game.setScreen(new GameScreen(game, nivel));
	                dispose();
	            }
	        }
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
