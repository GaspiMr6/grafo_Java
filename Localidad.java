import java.util.ArrayList;

public class Localidad {

	private String nombre;
	private Coordenadas coor;
	private InfoTur info;
	private int id;
	
	public Localidad(String s) {
		
		nombre = s;
		info = new InfoTur();
		coor = new Coordenadas();
	
		id = -1;
	}
	
	public int setCoor(int n1, int n2, ArrayList<ArrayList<Character>> m) {
		
		int dev=-1;
		
		//if(n1 <= m.size() && n2 <= m.get(0).size()) {

			if(n1>=0 && n2>=0 && coor.getColumna() == -1 && coor.getFila() == -1 && n1 < m.size() && n2 < m.get(0).size() && m.get(n1).get(n2) == 'T') {
				
				coor = new Coordenadas(n1, n2);
				m.get(n1).set(n2, 'L'); 
				
				dev = m.get(0).size() * n1 + n2;
				id = dev;
			}
		//}
		return dev;
	}
	
	public void setInfo(InfoTur i) {
		
		info = i;
	}
	
	public String getNombre() {
		
		return nombre;
	}
	
	public Coordenadas getCoor() {
		
		return coor;
	}
	
	public InfoTur getInfo() {
		
		return info;
	}
	
	public int getId() {
		
		return id;
	}
	
	public void setCoordenadas(Coordenadas c) {
		
		coor = c;
	}
	public void setId(int i) {
		
		id = i;
	}
	
	public String toString() {
		
		String s = "";
		
		if(coor != null && info != null && info.getInfoTur() != null)
		s = id + "-" + nombre + "-" + coor.toString() +"\n" + info.toString();
		
		return s;
	}
}
