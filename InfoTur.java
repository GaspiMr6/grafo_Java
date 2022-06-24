import java.util.ArrayList;
import java.util.Collections;

public class InfoTur {

	private int museo;
	private int monumento; 
	private int hotel;
	private int restaurante;
	private boolean aeropuerto;
	private String top;
	
		
	public InfoTur() {
		
		museo = 0;
		monumento = 0;
		hotel = 0;
		restaurante = 0;
		aeropuerto = false;
		top = "";
	}
	
	public InfoTur(int i, int j, int k, int l, boolean b) {
		
		if(i<0) museo=0;
		else museo = i;
		
		if (j<0) monumento = 0;
		else monumento = j;
		
		if (k<0) hotel = 0;
		else hotel = k;
		
		if (l<0) restaurante = 0;
		else restaurante = l;
		
		top = "";
		aeropuerto = b;
				
	}
	
	public ArrayList<Integer> getInfoTur(){
		
		ArrayList<Integer> a = new ArrayList<>();
		
		a.add(museo);
		a.add(monumento);
		a.add(hotel);
		a.add(restaurante);
		if (aeropuerto) a.add(1);
		else a.add(0);
		
		return a;
	}
	
	
	public String getMasFrecuente() {
		
		String dev = "";
		int max = 0;
		
		ArrayList<Integer> aux = getInfoTur();
		ArrayList<Integer> v = new ArrayList<>();
		
		v.add(aux.get(4));
		v.add(aux.get(2));
		v.add(aux.get(1));
		v.add(aux.get(0));
		v.add(aux.get(3));
		
		for(int i=0; i<v.size(); i++) {
			
			if(v.get(i) > v.get(max)) {
				
				max = i;
			}
		}
		
		if (max == 0) dev = "aeropuerto";
		else if(max == 1) dev = "hotel";
		else if (max == 2) dev = "monumento";
		else if (max == 3) dev = "museo";
		else dev = "restaurante";

		return dev;
	}
	
	public void setTop(String n) {
		
		top = n;		
	}
	
	public String getTop() {
		
		return top;
	}
	
	public String toString() {
		
		int ae = 0;
		if(aeropuerto == true) ae = 1;
		
		return(museo+" "+monumento+" "+hotel+" "+restaurante+" "+ae+" "+top+"\n");
	}
}
