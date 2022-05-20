package inteligenca;

import java.util.HashMap;
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
	
//	public LinkedList<Poteza> moznePoteze;

	public Igralec naPotezi;
	
	public MCTS(Igra igra) {
		
		naPotezi = igra.naPotezi();
		drevo = new HashMap<TreeIndex, TreeEntry>();
//		moznePoteze = igra.moznePoteze();

	}
	
	public static Poteza nakljucnaPoteza(Igra igra) {
		if (igra.moznePoteze().size() == 0) {
			System.out.print("OJOJJJ");
			return null;
		}
		else {
			int randomIndex = random.nextInt(igra.moznePoteze().size());
			return igra.moznePoteze().get(randomIndex); 
		}
	}


	public static void otrociPregled(Igra igra) {
		while (!igra.moznePoteze().isEmpty()) {
			Poteza nakljucnaPoteza = nakljucnaPoteza(igra);
			simulacija(igra, nakljucnaPoteza);
			igra.moznePoteze().remove(nakljucnaPoteza);
		}
		
	}
	
	// UPAM, DA JE TO PRAV
	
	public static void simulacija(Igra igra, Poteza poteza) {
		Igra kopijaIgre = igra;
		Igralec igralec = kopijaIgre.naPotezi();
		TreeIndex indeks = new TreeIndex(new TreeIndex(), poteza);
		while (kopijaIgre.stanje == Stanje.V_TEKU) {
			Poteza nakljucna = nakljucnaPoteza(kopijaIgre);
			kopijaIgre.odigrajZaMcts(nakljucna);
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
		for (TreeIndex i : drevo.keySet()) {
			if (vrednostUCT(i, drevo.get(i)) > (drevo.get(maksimalen).zmage / drevo.get(maksimalen).poskusi)) {
				maksimalen = i;
			}
		}
		return maksimalen;
	}
	
	public static double vrednostUCT (TreeIndex i,TreeEntry e) {
			return e.zmage / e.poskusi + Math.sqrt(2 * Math.log(drevo.get(i.parent()).poskusi) / e.poskusi);
		}
	
	public static Poteza main(Igra igra) {
		long zacetek = System.currentTimeMillis();
		Igra kopijaIgre = igra;
		TreeIndex otrok = null;
		while (zacetek + cas > System.currentTimeMillis()) {
			if (!kopijaIgre.poteze().isEmpty()) {
				otrociPregled(kopijaIgre);
				otrok = najdiNajboljsegaOtroka(kopijaIgre);
				kopijaIgre = drevo.get(otrok).igra;
			}
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
	
	
	
