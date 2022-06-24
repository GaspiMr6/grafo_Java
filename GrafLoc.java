import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.LinkedList;

public class GrafLoc {

	private ArrayList<ArrayList<Integer>> gr;
	private ArrayList<Integer> visitado;
	private boolean primera;
	
	public GrafLoc(int n) {
		
		visitado = new ArrayList<Integer>();
		primera = true;
		
		gr = new ArrayList<ArrayList<Integer>>();
		if(n<0) n=0;
		for(int i=0;i<n;i++) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			for(int j=0;j<n;j++) {
				
				a.add(Integer.MAX_VALUE);
			}
			gr.add(a);
		}
	}
	
	public boolean esVacio() {
		
		for(int i=0; i<gr.size(); i++) {
			for(int j=0; j<gr.size(); j++) {
					if(gr.get(i).get(j)!= Integer.MAX_VALUE) return false;
			}
		}
		return true;
	}
	
	
	public boolean dentro_rango(int o1, int o2) {
		
		if(o1 >= 0 && o1 <gr.size() && o2>=0 && o2<gr.size()) {
			return true;
		}
		return false;
	}
	
	
	public boolean insertaArista(int o1, int o2, int p) {
		
		if(dentro_rango(o1,o2)) {
			
			if(gr.get(o1).get(o2)==p) return false;
			else if(gr.get(o2).get(o1)==p) return false;//esto q es
			else {
			
				gr.get(o1).set(o2, p);
				gr.get(o2).set(o1, p);
				return true;
			}
		}
		return false;
	}
	

	public int recuperaArista(int o1, int o2) {
		
		int dev = -1;
		
		if(dentro_rango(o1,o2)) {
			
			dev = gr.get(o1).get(o2);
		}
		return dev;
	}
	
	public boolean borraArista(int o1, int o2) {
		
		if(dentro_rango(o1, o2)) {
			
			if(gr.get(o1).get(o2) != Integer.MAX_VALUE) {
				
				gr.get(o1).set(o2,Integer.MAX_VALUE);
				gr.get(o2).set(o1,Integer.MAX_VALUE);
				return true;
			}
		}
		return false;
	}
	
	
	public boolean borraVertice(int o1) {
		
		boolean dev = false;
		
		if(o1 < gr.size() && o1>=0) {
			
			for(int i=0; i<gr.size();i++) {
				
				if(gr.get(o1).get(i) != Integer.MAX_VALUE) {
					
					gr.get(o1).set(i, Integer.MAX_VALUE);
					dev = true;
				}
				
				if(gr.get(i).get(o1) != Integer.MAX_VALUE) {
					
					gr.get(i).set(o1, Integer.MAX_VALUE);
					dev = true;
				}
			}
		}
		return dev;
	}
	
	public int getVertices() {
		
		return gr.size();
	}
	
	public TreeSet<Localidad2> insertaLocalidades(Coleccion c){
		
		TreeSet<Localidad2> dev = new TreeSet<Localidad2>();

		if(esVacio() == false) {
			
			int n = c.getLocalidades().size();
			gr = new ArrayList<ArrayList<Integer>>();
			if(n<0) n=0;
			for(int i=0;i<n;i++) {
				ArrayList<Integer> a = new ArrayList<Integer>();
				for(int j=0;j<n;j++) {
					
					a.add(Integer.MAX_VALUE);
				}
				gr.add(a);
			}
			
			//vacio el grafo
			/*
			for(int i=0; i<gr.size(); i++) {
				for(int j=0; j<gr.get(i).size(); j++) {
					if(gr.get(i).get(j)!= Integer.MAX_VALUE) gr.get(i).set(j, Integer.MAX_VALUE);
				}
			}
			*/
		}
		
		for(int i=0; i<c.getLocalidades().size();i++) {
			
			Localidad2 l = new Localidad2(c.getLocalidades().get(i));
			dev.add(l);
		}
		
		ArrayList<Localidad2> ar = new ArrayList<>();
		
		Iterator<Localidad2> it = dev.iterator();
		int cont=0;
		while(it.hasNext()) {
			
			Localidad2 aux = it.next();
			ar.add(aux);
			aux.setVertice(cont++);
		}
		
		
		gr = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<c.getLocalidades().size();i++) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			for(int j=0;j<c.getLocalidades().size();j++) {
				a.add(Integer.MAX_VALUE);
			}
			gr.add(a);
		}
		
		Iterator<Localidad2> it2 = dev.iterator();
		while(it2.hasNext()) {	
			Localidad2 local = it2.next();
			Iterator<Localidad2> it3 = dev.iterator();
			while(it3.hasNext()) {
			
				Localidad2 local2 = it3.next();
				int man = 0;
				man = local.getCoor().calculaMan(local2.getCoor());
				
				//gr.get(local.getVertice()).set(local2.getVertice(), man);
				
				//System.out.println("Local: "+gr.get(local.getVertice()));
				//System.out.println("Local2: "+gr.get(local2.getVertice()));
			
				
				//gr.get(local.getVertice()).set(local2.getVertice(), man);
				//gr.get(local2.getVertice()).set(local.getVertice(), man);
				
				this.insertaArista(local.getVertice(), local2.getVertice(), man);
				
				//System.out.println("INSERTO ARISTA: "+this.insertaArista(local.getVertice(), local2.getVertice(), man));
				//System.out.println("VERTICES: "+local.getVertice() +"  " +local2.getVertice());
				//System.out.println(local.getNombre());
				//System.out.println("Coordenada ("+local.getCoor().getFila()+","+local.getCoor().getColumna());
				//System.out.println("Coordenada ("+local2.getCoor().getFila()+","+local2.getCoor().getColumna());
				//System.out.println("Manjatan: "+man);
				//System.out.println();
				
			}
		}
		
		return dev;
	}
	
	public int num_aristas() {
		
		int dev=0;
		
		for(int i=0; i<gr.size();i++) {
			for(int j=0; j<gr.size();j++) {
				if(gr.get(i).get(j) != Integer.MAX_VALUE) {
					if(i!=j) {   
						dev++;
					}
				}
			}
		}
		return dev/2;
	}
	
	public String matriz() {
		
		String dev ="";
		for(int i=0; i<gr.size();i++) {
			for(int j=0; j<gr.size();j++) {
				if(j!=gr.size()-1) {
					dev=dev+gr.get(i).get(j)+" ";
				}
				else {
					dev=dev+gr.get(i).get(j);
				}
			}
			dev = dev + "\n";
		}
		return dev;
	}
	
	public String toString() {
		
		String dev = "vertices: "+gr.size()+"\n";
		dev = dev + "aristas: "+num_aristas()+"\n";
		dev = dev + matriz();
		
		return dev;
	}
	
	public ArrayList<Integer> vertices_conectados(int original){
		
		ArrayList<Integer> dev = new ArrayList<>();
		dev.add(original);
		
		for(int i=0; i<gr.size();i++) {
			
			if(gr.get(original).get(i) != Integer.MAX_VALUE){
				
				if(dev.contains(i) == false) dev.add(i);
			}
		}
		
		
		return dev;
	}
	
	public ArrayList<ArrayList<Integer>> getGr(){
		
		return gr;
	}
	

	public void r(int i) {
		if(visitado.contains(i)==false) {
			visitado.add(i);
			if(primera) {
				System.out.print(i);
				primera=false;
			}
			else {
				System.out.print(", "+i);
			}
		}

		for(int j=0;j<getVertices();j++) {
			if(gr.get(i).get(j)!=Integer.MAX_VALUE && gr.get(i).get(j)!=0 && !visitado.contains(j)) {
				r(j);
			}
		}
	}
	
	public void escribeDFS(int i) {
		if(i<getVertices() && i>=0) {
			r(i);
			for(int j=0;j<getVertices();j++) {
				if(visitado.contains(j) == false) {
					r(j);
				}
			}
		}
		/*else {
			r(0);
			for(int j=0;j<getVertices();j++) {
				if(!visitado.contains(j) == false) {
					r(j);
				}
			}
		}*/
		visitado.clear();
		primera=true;
		
		if(i<getVertices() && i>=0) 
			System.out.println();
	
			
	}
}
