package com.mygdx.game;


//interfaz del nivel para especificar métodos para implementar en los distintos tipos de niveles
public interface Nivel {
	
	public void crearBloques(int filas);
	public boolean perder();
	public int obtenerVelocidadXPingBall();
	public int obtenerVelocidadYPingBall();
	public void comprobarPelota();
	public void actualizarLugar();
	public void colisionesPelotas();
	public void dibujarPelotas();
	public boolean nivelCompleto();
	public void dibujarPaddle();
	public void actualizarBloques();
	public void dibujarBloques();
	public void mejoras();
	public int getPuntaje();
	public int getVidas();

}
