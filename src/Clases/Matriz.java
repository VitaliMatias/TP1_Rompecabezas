package Clases;

import java.rmi.AccessException;
import java.security.AccessControlException;
import java.util.Arrays;
import java.util.Random;

public class Matriz {
	private int[][] tablero;
	private int[] posVacio;

	public Matriz(int tamañoTablero) {
		tablero = new int[tamañoTablero][tamañoTablero];
		
		Random rnd = new Random();
		int posXVacio = (int) (rnd.nextDouble() * tamañoTablero + 0);
		int posYVacio = (int) (rnd.nextDouble() * tamañoTablero + 0);
		posVacio = new int[] { posXVacio, posYVacio };
		
		int contador = 1;
		for (int x = 0; x < tablero[0].length; x++) {
			for (int y = 0; y < tablero[0].length; y++) {
				if (x != posXVacio || y != posYVacio){
						tablero[x][y] = contador;
						contador++;
				}
			}
		}
	}
	
	public int valorEnPosicion(int x, int y){
		return tablero[x][y];
	}
	
	//posVacio[0] son las X o filas - es izquierda y + es derecha
	//posVacio[1] son las Y o columnas - es arriba y + es abajo
	
	//Los movimientos siempre se hacen tomando como eje la posicion que esta vacia
	// EJ = moverArriba() la pieza vacia sube, y la que esta arriba baja.
	
	public void moverArriba(){
		if (posVacio[1] > 0){
			tablero[posVacio[0]][posVacio[1]] = tablero[posVacio[0]][posVacio[1]-1];
			tablero[posVacio[0]][posVacio[1]-1] = 0;
			posVacio[1]--; 
		}
		else throw new AccessControlException("Movimiento invalido");
	}
	public void moverAbajo(){
		if (posVacio[1] < tablero.length){
			tablero[posVacio[0]][posVacio[1]] = tablero[posVacio[0]][posVacio[1]+1];
			tablero[posVacio[0]][posVacio[1]+1] = 0;
			posVacio[1]++; 
		}
		else throw new AccessControlException("Movimiento invalido");
	}
	public void moverIzquierda(){
		if (posVacio[0] > 0){
			tablero[posVacio[0]][posVacio[1]] = tablero[posVacio[0]-1][posVacio[1]];
			tablero[posVacio[0]-1][posVacio[1]] = 0;
			posVacio[0]--; 
		}
		else throw new AccessControlException("Movimiento invalido");
	}
	public void moverDerecha(){
		if (posVacio[0] < tablero.length){
			tablero[posVacio[0]][posVacio[1]] = tablero[posVacio[0]+1][posVacio[1]];
			tablero[posVacio[0]+1][posVacio[1]] = 0;
			posVacio[0]++; 
		}
		else throw new AccessControlException("Movimiento invalido");
	}

	public void mezclarMatriz(int cantidadMovimientos){
		for (int i = 0; i < cantidadMovimientos; i++) {
			Random rnd = new Random();
			int movimiento = (int) (rnd.nextDouble() * 4 + 0);
			switch (movimiento) {
			case 0:
				try {
					moverArriba();
				} catch (Exception e) {
					if(i!=0)i--;
				}
				break;
			case 1:
				try {
					moverAbajo();
				} catch (Exception e) {
					if(i!=0)i--;
				}
				break;
			case 2:
				try {
					moverDerecha();
				} catch (Exception e) {
					if(i!=0)i--;
				}
				break;
			case 3:			
				try {
					moverIzquierda();
				} catch (Exception e) {
					if(i!=0)i--;
				}
				break;

			default:
				break;
			}
		}
	}
	
	public boolean estaResuelto(){
		boolean ret = true;
		int contador = 1;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				if (valorEnPosicion(i, j) != 0){
					ret = ret && (valorEnPosicion(i, j) == contador);
					contador++;
				}
			}
		}
		return ret;
	}
}


