package inteligenca;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import logika.Igra;
import logika.Igralec;
import logika.Stanje;
import splosno.Poteza;

public class Node {
	
	public Igra igra;
//	public List<Poteza> potekIgre; tega verjetno ne rabim
	
	public Poteza poteza;
	public Node stars;
	public List<Node> otroci;
	
	public List<Poteza> moznePoteze; 
	public Igralec NaPotezi;
	
	public int poskusi;
	public double zmage;
	
	public Node (Igra igra, Node stars, Poteza poteza) {
		this.igra = igra;
		this.stars = stars;
		this.poteza = poteza;
		otroci = new LinkedList<Node>();
		
		moznePoteze = igra.moznePoteze();
		NaPotezi = igra.naPotezi();
		
		poskusi = 0;
		zmage = 0;
		
	}
	
	// doda otroke izbranemu vozliscu
	public void dodajOtroke(Node node) {
		for (Poteza poteza : moznePoteze) {
			Igra kopija = new Igra();
			kopija.odigrajZaMcts(poteza);
			Node otrok = new Node(kopija, node, poteza);
			otroci.add(otrok);
		}
	}
	
	// izbere otroka glede na vrednost UCT
	public Node izbereOtroka() {
		Node najboljsiOtrok = null;
		double maksimalnaUCT = 0;
		for (Node otrok : otroci) {
			if ((najboljsiOtrok == null) || (vrednostUCT() > maksimalnaUCT)) {
				najboljsiOtrok = otrok;
				maksimalnaUCT = vrednostUCT();
			}
		}
		return najboljsiOtrok;
	}
	
	private static Random random = new Random ();
	
	// nakljucno odigra igro do konca
	public Stanje simuliraj() {
		Stanje stanje = igra.stanje;
		while (stanje == Stanje.V_TEKU) {
			int randomIndex = random.nextInt(moznePoteze.size());
			igra.odigrajZaMcts(moznePoteze.get(randomIndex)); 
		}
		return stanje;
	}
	
	// spremeni koncno stanje (nedoloceno, zmaga crni ali beli) v stevilo za izracun zmag
	public double stanjeVRezultat(Stanje stanje) {
		if (stanje == Stanje.ZMAGA_CRNI) return 1;
		else if (stanje == Stanje.ZMAGA_BELI) return -1;
		// v tem primeru stanje == Stanje.NEODLOCENO
		else return 0.5;
		}
	
	// po drevesu navzgor do starsa, sproti spreminja stevilo zmag in poskusov
	public void nazajDoStarsa(Node node, double steviloZmag) {
		while (node != null) {
			node.poskusi +=1;
			if (node.NaPotezi == Igralec.CRNI) node.zmage += steviloZmag;
			node = node.stars;
		}
	}
	
	public double vrednostUCT () {
		return zmage / poskusi + Math.sqrt(2 * Math.log(stars.poskusi) / poskusi);
	}
}
