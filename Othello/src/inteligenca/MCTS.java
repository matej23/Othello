package inteligenca;

import java.util.HashMap;
import java.util.LinkedList;
//import java.util.LinkedList;
import java.util.Random;

import logika.Igra;
import logika.Igralec;
import logika.Stanje;
import splosno.Poteza;

public class MCTS {

	public static HashMap<TreeIndex, TreeEntry> drevo;
	// slovar vsebuje seznam potez od korena do izbranega vozlisca
	
	private static Random random = new Random ();
	private static int cas = 2400;
	
	public Igralec naPotezi;
	
	public MCTS(Igra igra) {
		
		naPotezi = igra.naPotezi();
		drevo = new HashMap<TreeIndex, TreeEntry>();

	}
	
//	public static Poteza nakljucnaPoteza(LinkedList<Poteza> poteze) {
//		int dolzina = poteze.size();
//		assert (dolzina > 0);
//		return poteze.getFirst();
//	}
	public static Poteza nakljucnaPoteza(LinkedList<Poteza> poteze) {
		assert (poteze.size() > 0);
		int randomIndex = random.nextInt(poteze.size());
		return poteze.get(randomIndex); 
	}
//	public static void otrociPregled(Igra igra) {
//		for (Poteza poteza : igra.moznePoteze()) {
//			simulacija(igra, poteza);
//		}
//	}
	public static void otrociPregled(Igra igra, TreeIndex i) {
		LinkedList<Poteza> klon = (LinkedList<Poteza>) igra.moznePoteze().clone();
		
		while (!klon.isEmpty()) {
			Poteza nakljucnaPoteza = nakljucnaPoteza(klon);
			simulacija(igra, nakljucnaPoteza, i);
			klon.remove(nakljucnaPoteza);
		}
	}
	
	
	public static void simulacija(Igra igra, Poteza poteza, TreeIndex i) {
		Igra kopijaIgre = new Igra(igra.plosca, igra.naPotezi(), igra.stanje);
		Igralec igralec = kopijaIgre.naPotezi();
		TreeIndex indeks = new TreeIndex(i, poteza);
		while (kopijaIgre.stanje == Stanje.V_TEKU) {
			if (kopijaIgre.moznePoteze().size() > 0) {
				Poteza nakljucna = nakljucnaPoteza(kopijaIgre.moznePoteze());
				kopijaIgre.odigraj(nakljucna);
			}
			kopijaIgre.stanje = kopijaIgre.posodobiStanje();
		}
		TreeEntry vnos = new TreeEntry(kopijaIgre);
		vnos.poskusi = 1;
		vnos.zmage = stanjeVRezultat(kopijaIgre.stanje, igralec);
		drevo.put(indeks, vnos);
		
	}
	
	public static double stanjeVRezultat(Stanje stanje, Igralec igralec) {
		if ((stanje == Stanje.ZMAGA_CRNI) && (igralec == Igralec.CRNI)) return 1;
		else if ((stanje == Stanje.ZMAGA_BELI) && (igralec == Igralec.BELI)) return 1;
		else if (stanje == Stanje.NEODLOCENO) return 0.5;
		else return 0;
	}

	
	public static TreeIndex najdiNajboljsegaOtroka(Igra igra) {
		TreeIndex maksimalen = new TreeIndex();
		int j = 0;
		for (TreeIndex i : drevo.keySet()) {
			if (j > 0) {
				if (vrednostUCT(i, drevo.get(i)) > vrednostUCT(maksimalen, drevo.get(maksimalen))) {
					maksimalen = i;
				}
			}
			// samo na zacetku, ko je j = 0, vzamemo za maksimalen prvi kljuc v drevesu
			else {
				maksimalen = i;
				j++;
			}
		}
		return maksimalen;
	}
	
	public static double vrednostUCT (TreeIndex i,TreeEntry e) {
		if (drevo.containsKey(i.parent())) {
			return e.zmage / e.poskusi + Math.sqrt(2 * Math.log(drevo.get(i.parent()).poskusi) / e.poskusi);
		}
		else return e.zmage / e.poskusi;
	}
	
	public Poteza main(Igra igra) {
		long zacetek = System.currentTimeMillis();
		Igra kopijaIgre = new Igra(igra.plosca, igra.naPotezi(), igra.stanje);
		TreeIndex otrok = new TreeIndex();
		while (zacetek + cas > System.currentTimeMillis()) {
			if (!kopijaIgre.poteze().isEmpty()) {
				otrociPregled(kopijaIgre, otrok);
				otrok = najdiNajboljsegaOtroka(kopijaIgre);
				Igra kopija = drevo.get(otrok).igra;
				kopijaIgre = new Igra(kopija.plosca, kopija.naPotezi(), kopija.stanje);
			}
//			else {
//				System.out.print("zakaj smo sploh tukaj ?");
//				break;
//			}
			
		}
		if (otrok == null) {
			System.out.print("ne naredi otroka");
			return null;
		}
		else {
			return otrok.asList().get(0);
		}
	}
	

	
	
		
		
}
	
	
	