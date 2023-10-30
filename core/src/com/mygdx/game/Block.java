package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class Block {
    //inicializar los atributos de la clase 
    int x,y,width,height;
    Color cc;
    boolean destroyed;

    //constructor para inicializar y asignar las variables de instancia
    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        destroyed = false;
        // Se crea un objeto Random para generar un color aleatorio para el block
        Random r = new Random(x+y);
        //llamada a un color para el block
       cc = new Color(0.1f+r.nextFloat(1), r.nextFloat(1), r.nextFloat(1), 10);
  
    }

    //método para dibujar el block con su respectivas medidas y asignar el color
    public void draw(ShapeRenderer shape){
    	shape.setColor(cc);
        shape.rect(x, y, width, height);
    }
}
