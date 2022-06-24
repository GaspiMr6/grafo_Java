import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

public class Prim {

	public static void main(String[] args) {
		
		if(args.length>=2) {
				
			String s = args[0];
			int origen = Integer.parseInt(args[1]);
			
			Coleccion c = new Coleccion();
			c.lectura(s);
			GrafLoc grafo = new GrafLoc(c.getLocalidades().size());
			TreeSet<Localidad2> tree= grafo.insertaLocalidades(c);
				
			ArrayList<Integer> marcados = new ArrayList<>();
			marcados.add(origen);
			
			if(origen>=0 && origen <grafo.getVertices()) {
					
				int total = 0;
				
				ArrayList<ArrayList<Integer>> matriz = new ArrayList<ArrayList<Integer>>(); 
			
				for(int i=0;i<grafo.getVertices();i++) {
					ArrayList<Integer> a = new ArrayList<Integer>();
					matriz.add(a);
				}
				
				while(marcados.size() < grafo.getVertices()) {
					
					int max = Integer.MAX_VALUE;
					int fila = -1;
					int colum = -1;
					
					for(int i=0;i<grafo.getVertices();i++) {
					
						if(marcados.contains(i)) {
						
							for(int j=0; j<grafo.getVertices(); j++) {
								
								if(marcados.contains(j)==false) {
								
									if(i!=j && grafo.getGr().get(i).get(j) < max) {
										
										max = grafo.getGr().get(i).get(j); 
										fila = i;
										colum = j;
									}
								}
							}
						}
					}
					
					matriz.get(fila).add(colum);
					matriz.get(colum).add(fila);
					marcados.add(colum);
					total = total + grafo.getGr().get(fila).get(colum);
				}
				
				//tengo que ordenar el arrayList
				for(int i=0;i<matriz.size();i++) {
					
					Collections.sort(matriz.get(i));
				}
				
				for(int i=0; i<matriz.size();i++) {
					
					Iterator<Localidad2> it = tree.iterator();
					int ya=0;
					while(it.hasNext()) {
						Localidad2 aux = it.next();
						if(aux.getVertice()==i && ya==0) {
							System.out.print(aux.getNombre()+"-");
							ya=1;
						}
					}
					boolean primera = true;
					for(int j=0; j<matriz.get(i).size();j++) {
						
						int vertice = matriz.get(i).get(j);
						
						Iterator<Localidad2> it2 = tree.iterator();
						while(it2.hasNext()) {
							Localidad2 aux = it2.next();
	
							if(aux.getVertice()==vertice) {
								if(primera) {
									
									System.out.print(aux.getNombre()); //**OJO QUITAR UN ESPACIO AL FINAL
									primera=false;
								}
								else {
									
									System.out.print(" "+aux.getNombre()); //**OJO QUITAR UN ESPACIO AL FINAL
								}
									
								
							}
						}
					}
					System.out.println();
				}
			System.out.println("Distancia total="+total);
			}
			else {
				
				System.out.println("Distancia total=0");
			}
			
			/*
			c
				Iterator<Localidad2> it = tree.iterator();
				while(it.hasNext()) {
					Localidad2 aux = it.next();
					System.out.println(aux.getNombre()+"-");
					if(aux.getVertice() == i) {
						Iterator<Localidad2> itj = tree.iterator();
						while(itj.hasNext()) {
							Localidad2 auxj = it.next();
							if(auxj.getVertice()==matriz.get(i).get(auxj.getVertice())){
							}					
						}
					}
				}
				System.out.println();
			}
			*/
		}
		else {
			
			System.out.println("Faltan parametros");
		}

	}
}
