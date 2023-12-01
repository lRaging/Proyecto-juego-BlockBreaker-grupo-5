package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BlockBreakerMenu extends Game{
    // Variables de instancia para el menú del juego
    private SpriteBatch batch;
    private BitmapFont font;
    private int higherScore;

    // Método para crear el menú del juego
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        // Configura la pantalla de inicio como la pantalla actual del juego
        this.setScreen(MainMenuScreen.getInstance(this));
    }

    // Método para renderizar el menú del juego
    public void render() {
        super.render(); // important!
    }

    // Método para liberar los recursos utilizados por el menú del juego
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

//setter y getter para los atributos de la clase
    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }

    public int getHigherScore() {
        return higherScore;
    }

    public void setHigherScore(int higherScore) {
        this.higherScore = higherScore;
    }

}
