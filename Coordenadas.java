
public class Coordenadas {

	private int fila;
	private int columna;
	
	public Coordenadas() {
		
		fila = -1;
		columna = -1;
	}
	
	public Coordenadas(int f, int c) {
		
		if(f >= 0 && c >= 0) {
			
			fila = f;
			columna = c;
		}
		else {
			
			fila = -1;
			columna = -1;
		}
	}
	
	public int getFila() {
		
		return fila;
	}
	
	public int getColumna() {
		
		return columna;
	}
	
	public boolean equals(Object p) {
		
		if(fila==columna) return true;
		return false;
	}
	
	public int calculaMan(Coordenadas c) {
		
		int dev = Math.abs(fila-c.getFila())+Math.abs(columna-c.getColumna());
		return dev;
	}
	
	public String toString() {
		
		return ("("+fila+","+columna+")");
	}
}
