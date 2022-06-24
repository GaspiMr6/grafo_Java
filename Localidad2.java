
public class Localidad2 extends Localidad implements Comparable<Localidad2>{

	//Duda: Localidad tiene constructor por defecto en Java 
	// Localidad2 tiene constructor por defecto?
	
	private int vertice;
	
	public Localidad2(Localidad l){
		super(l.getNombre());
		this.setInfo(l.getInfo());
		this.setId(l.getId());
		
		//if(l.getCoor().getFila()==-1) this.setCoordenadas(null);
		//else 
			this.setCoordenadas(l.getCoor());
		vertice = -1;
	}

	int getVertice() {return vertice;}
	void setVertice(int v) {vertice = v;}
	
	public int compareTo(Localidad2 c) {
		
		if(c != null) {
			int dev=0;
			
			if(this.getNombre().compareTo(c.getNombre())<0) dev = -1;
			else if(this.getNombre().compareTo(c.getNombre())>0) dev = 1;
			else if(this.getNombre().equals(c.getNombre())) {
				
				if(this.getId()<c.getId())dev = -1;
				else if(this.getId()>c.getId()) dev=1;
				else dev = 0;
			}
			return dev; 
		}
		else {
			return -2;
		}
	}
	
	public boolean equals(Object m) {
		
        if (m instanceof Localidad2) {

            Localidad2 l = (Localidad2) m;

            //super.equals(l) 
            if (this.getNombre().equals(l.getNombre()) && this.getId() == l.getId()) 
                return true; 
            
            else return false; 
        }  
        else { 
        	return false; 
        }
        
		/*
        if (m instanceof Localidad2) {

            Localidad2 l = (Localidad2) m;
            if(l.getNombre().equals(this.getNombre()) && l.getId() == getId()) return true;
        }
        return false;
        */
	}
	
	
	
}
