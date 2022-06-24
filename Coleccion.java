import java.io.*;
import java.util.ArrayList;

public class Coleccion {

	private ArrayList<ArrayList<Character>> mapa;
	private ArrayList<Localidad> localidades;
	
	public Coleccion() {
		
		mapa = new ArrayList<>();
		localidades = new ArrayList<>();
	}
	
	
	public void lectura(String cad) {
		
		FileReader f = null;
		BufferedReader lee = null;
		
		try {
			
			f = new FileReader(cad);
			lee = new BufferedReader(f);
			int cont = 0;
			
			String lin = lee.readLine();
				
			//si o si primero va a haber un mapa
			// y si o si una linea llamda localidad y otra info
			//?? 
			
			//while(lin != null) {
				
				while(lin != null && lin.equals("<LOCALIDAD>") == false) {
					
					mapa.add(new ArrayList<>()); //no se 
					
					char array[] = lin.toCharArray(); 
					
					for(int i=0; i<array.length;i++) {
						
						mapa.get(cont).add(array[i]);
					}
					
					cont++;
					lin = lee.readLine();
				}
				
				int ciudades = 0;
				boolean cont_ciudades = false;
				
				//tengo LOCALIDAD
				while(lin != null) {
					
					lin = lee.readLine();
					
					Localidad l = new Localidad(lin);
					
					lin = lee.readLine();
					
					String a_string[] = lin.split(" ");
					
					int cor1 = Integer.parseInt(a_string[0]); 
					int cor2 = Integer.parseInt(a_string[1]); 
					
					int bool_setcor =l.setCoor(cor1, cor2, mapa); 
					
					if(bool_setcor != -1) {
						localidades.add(l);
						cont_ciudades = true;
					}
					//int cor1 = Integer.parseInt(""+lin.charAt(1));
					//int cor2 = Integer.parseInt(""+lin.charAt(3));
					
					lin = lee.readLine();
					
					//tengo <INFO>
					
					lin = lee.readLine();
					
					int mu=0, mo=0, ho=0, res=0;
					boolean ae = false;
					String top = null;
					
					while(lin != null && lin.equals("<LOCALIDAD>")==false) {	
						
						if(cont_ciudades == true) {
						
						if(lin.charAt(0)=='*') {
							
							top = lin;
						}
						else {
							
							String v[] = lin.split(" ");
							if(v[0].equals("museo")) mu=Integer.parseInt(v[1]);
							if(v[0].equals("monumento")) mo=Integer.parseInt(v[1]);
							if(v[0].equals("hotel")) ho=Integer.parseInt(v[1]);
							if(v[0].equals("restaurante")) res=Integer.parseInt(v[1]);
							if(v[0].equals("aeropuerto")) ae = true;
						}
						
						}
						lin = lee.readLine();
						
					}
					
					if(cont_ciudades == true) {
						InfoTur in = new InfoTur(mu, mo, ho, res, ae);
						if(top != null) in.setTop(top);
						localidades.get(ciudades).setInfo(in);
						ciudades++;
					}
					
					cont_ciudades = false;
				}
				
		} catch (IOException e) {
			System.err.println("Error con _cad");
			System.out.println(e);
			
			
		} finally {
			
			try {
				if (f != null) f.close();
				if (lee != null) lee.close();
				
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
	
	public ArrayList<ArrayList<Character>> getMapa(){
		
		return mapa;
	}
	
	public ArrayList<Localidad> getLocalidades(){
		
		return localidades;
	}
	
	public char getCoorMapa(Coordenadas c) {
		
		char dev;
		
		int fil = c.getFila();
		int col = c.getColumna();
		
		if(fil<0 || col<0 || fil>=mapa.size()||col>=mapa.get(0).size()) {
			
			dev = 'X';
		}
		else {
			
			dev = mapa.get(fil).get(col);
		}
		return dev;
	}
	
	public String toString() {
		
		String s = "";
		
		if(mapa != null) {
			
			for(int i=0; i<mapa.size();i++) {
				for(int j=0; j<mapa.get(0).size(); j++) {
					
					s = s + mapa.get(i).get(j);
				}
				s = s + "\n";
			}
		}		
		if(localidades != null) {
			
			for(int i=0; i<localidades.size(); i++) {
				
				s = s + localidades.get(i).toString();
			}
		}
		
		return s;
	}
	
	
}
