import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class TNear {

	private TreeMap<Localidad2, TreeSet<Localidad2>> ti;
	private int dn;

	public TNear(int i) {

		if (i < 0)
			i = 0;
		dn = i;
		ti = new TreeMap<Localidad2, TreeSet<Localidad2>>();
	}

	public boolean esVacio() {

		if (ti.isEmpty()) {
			return true;
		}
		return false;
	}

	/*
	 * Recorroro los key for (Integer obj : tree.keySet()) {
	 * System.out.println(obj); // }
	 * 
	 * Recorroro los values for (String obj : tree.values()) {
	 * System.out.println(obj); // }
	 */

	public void insertaLocalidad2(Localidad2 w) {

		// Alicia esto sirve?
		if (!ti.containsKey(w)) { //Key o Value!? OJOO
		//if (!ti.containsValue(w)) { 
				
			Set<Localidad2> keys = ti.keySet();
			for (Iterator<Localidad2> j = keys.iterator(); j.hasNext();) {
				Localidad2 key = (Localidad2) j.next();
				TreeSet<Localidad2> value = (TreeSet<Localidad2>) ti.get(key);

				// != 0 o w.equals(key) == false no se que poner
				if (w.getCoor().calculaMan(key.getCoor()) <= dn && w.equals(key) == false) {

					value.add(w);
				}

			}

			TreeSet<Localidad2> arb = new TreeSet<Localidad2>();
			for (Localidad2 obj : ti.keySet()) {
				if (obj.getCoor().calculaMan(w.getCoor()) <= dn && w.equals(obj) == false) {
					arb.add(obj);
				}
			}

			ti.put(w, arb);
		}
	}

	public void insertaLocalidades(Coleccion c) {

		if(c != null) {
			for (int i = 0; i < c.getLocalidades().size(); i++) {
				insertaLocalidad2(new Localidad2(c.getLocalidades().get(i)));
			}
		}
		
	}

	public boolean borraLocalidad(String p) {

		boolean borrar = false;
		ArrayList<Localidad2> localidades_borrar = new ArrayList<>();

		if(p != null) {
			for (Localidad2 obj : ti.keySet()) {
				if (obj.getNombre().contentEquals(p)) {
					borrar = true;
					localidades_borrar.add(obj);
				}
			}
			if (borrar) {
				for (int i = 0; i < localidades_borrar.size(); i++) {
					ti.remove(localidades_borrar.get(i));
					for (TreeSet<Localidad2> obj : ti.values()) {
						obj.remove(localidades_borrar.get(i));
					}
				}
			}
			return borrar;
		}
		else {
			return false;
		}
	}

	public TreeSet<Localidad2> getLocalidades(String s) {
		/*
		 * for(Map.Entry<Localidad2,TreeSet<Localidad2>> entry : ti.entrySet()) { String
		 * key = entry.getKey(); Integer value = entry.getValue();
		 * 
		 * System.out.println(key + " => " + value); }
		 */

		Set<Localidad2> keys = ti.keySet();
		for (Iterator<Localidad2> i = keys.iterator(); i.hasNext();) {
			Localidad2 key = (Localidad2) i.next();
			TreeSet<Localidad2> value = (TreeSet<Localidad2>) ti.get(key);
			if (key.getNombre().equals(s))
				return value;
		}
		return null;

	}

	public void setDn(int i) {

		if (i > 0) {
			dn = i;
			Set<Localidad2> keys = ti.keySet();
			for (Iterator<Localidad2> j = keys.iterator(); j.hasNext();) {
				Localidad2 key = (Localidad2) j.next();
				TreeSet<Localidad2> value = (TreeSet<Localidad2>) ti.get(key);

				Iterator<Localidad2> it = value.iterator();
				//int cont = 0;

				ArrayList<Localidad2> borrar = new ArrayList<>();

				while (it.hasNext()) {

					Localidad2 aux = it.next();
					if (aux.getCoor().calculaMan(key.getCoor()) > dn) {
						// value.remove(aux);
						borrar.add(aux);
					}
				}

				for (int q = 0; q < borrar.size(); q++) {
					value.remove(borrar.get(q));
				}
			}

			for (Iterator<Localidad2> j = keys.iterator(); j.hasNext();) {
				Localidad2 keyj = (Localidad2) j.next();
				//TreeSet<Localidad2> valuej = (TreeSet<Localidad2>) ti.get(keyj);

				for (Iterator<Localidad2> k = keys.iterator(); k.hasNext();) {
					Localidad2 keyk = (Localidad2) k.next();
					TreeSet<Localidad2> valuek = (TreeSet<Localidad2>) ti.get(keyk);

					if (keyj != keyk) {

						int distancia = keyj.getCoor().calculaMan(keyk.getCoor());
						if (distancia <= dn) {
							if (!valuek.contains(keyj)) {
								valuek.add(keyj);
							}
						}
					}
				}
			}
		}
	}

	public TreeSet<Localidad2> getTop(int i) {

		TreeSet<Localidad2> dev = new TreeSet<Localidad2>();

		if (i >= 0 && i <= 3) {

			Set<Localidad2> keys = ti.keySet();
			for (Iterator<Localidad2> j = keys.iterator(); j.hasNext();) {
				Localidad2 key = (Localidad2) j.next();
				//TreeSet<Localidad2> value = (TreeSet<Localidad2>) ti.get(key);
				//int restador = i;
				if (i == 0) {
					if (key.getInfo().getTop() == null || key.getInfo().getTop().equals("")) {
						dev.add(key);
					}
				} else {

					boolean add = true;
					int estrellita = 0;
					for (int p = 0; p < 3 && add; p++) {

						if (key.getInfo().getTop() != null && key.getInfo().getTop().equals("") == false
								&& key.getInfo().getTop().charAt(p) == '*') {
							estrellita++;
						} else {
							add = false;
						}
					}
					if (estrellita == i) {

						dev.add(key);
					}

				}
			}
			if(dev.isEmpty()) return null;
			else return dev;
		} else {

			return null;
		}
	}

	public String toString() {
		// si no hay localidades en el treeset que hago? pongo
		// localidad3 * y dejo un espacio o no hay espacio?
		String dev = "";

		Set<Localidad2> keys = ti.keySet();
		for (Iterator<Localidad2> j = keys.iterator(); j.hasNext();) {
			Localidad2 key = (Localidad2) j.next();
			TreeSet<Localidad2> value = (TreeSet<Localidad2>) ti.get(key);

			// dev = dev + key.getNombre()+" *";
			dev = dev + key.getNombre() + " * ";

			Iterator<Localidad2> it = value.iterator();
			int cont = 0;
			while (it.hasNext()) {
				// if(cont!=0) {

				// dev=dev+" -";
				// }
				// dev=dev+" ";

				if (cont == 0) {
					Localidad2 aux = it.next();
					dev = dev + aux.getNombre();
					cont++;
				} else {
					Localidad2 aux = it.next();
					dev = dev + " - ";
					dev = dev + aux.getNombre();
					cont++;
				}

			}
			dev = dev + "\n";
		}

		return dev;
	}
}
