package com.mygdx.game;

public interface Nivel {

	public void crearBloques(int filas);
	public boolean perder();
	public void comprobarPelota();
	public void actualizarLugar();
	public void colisionesPelotas();
	public void dibujarPelotas();
	public void nivelCompleto();
	public void dibujarPaddle();
	public void actualizarBloques();
	public void dibujarBloques();
	public int getPuntaje();
	public int getVidas();

}
