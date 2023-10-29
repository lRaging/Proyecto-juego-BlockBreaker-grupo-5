package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

//clase para definir el paddle con sus posicion inicial, ancho, largo y el rectangulo para interactuar con otros elementos
public class Paddle {
    private int x = 20;
    private int y = 20;
    private int width = 100;
    private int height = 10;
    private Rectangle area;



//constructor para inicializar los atributos correspondientes	
    public Paddle(int x, int y, int ancho, int alto) {
    	this.x = x;
    	this.y= y;
    	width = ancho;
    	height = alto;
        area = new Rectangle(x, y, width, height);
    }

	//getters para su composicion y setter para el ancho
    public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public void setWidth(int width) {
		this.width = width;
	}

	//metodo para dibujar la figura y color, ademas las teclas para comenzar el movieminto de esta
	public void draw(ShapeRenderer shape){	
        shape.setColor(Color.BLUE);
        int x2 = x; //= Gdx.input.getX();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x2 =x-15;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x2=x+15; 
       // y = Gdx.graphics.getHeight() - Gdx.input.getY(); 
        if (x2 > 0 && x2+width < Gdx.graphics.getWidth()) {
            x = x2;
        }
        shape.rect(x, y, width, height);
    }

	//getter del area que ocupará el rectángulo
    public Rectangle getArea() {
        return area;
    }

}
