package inteligenca;

import logika.Igra;
import logika.Stanje;
import splosno.Poteza;

public class MonteCarlo {


// https://royhung.com/reversi

	// omejimo s 4 sekundami
	private static int cas = 2400;
	
	private static int N = 0;
	
	public static Poteza MCTS(Igra igra) {
		// generiramo koren, ki nima starsa
		Node koren = new Node(igra, null, igra.zadnjaPoteza);
		long zacetek = System.currentTimeMillis();
		while (zacetek + cas > System.currentTimeMillis()) {
			Node node = koren;
			celotnaSimulacija(node);
		}
		Node n = koren.izbereOtroka();
		return n.poteza;
	}
	
// NI OK!!!
		
//	public static void celotnaSimulacija(Node node) {
//		while ((!node.otroci.isEmpty()) && (node.igra.moznePoteze().isEmpty())) {
//			if (N == 0) node = node.otroci.get(0);
//			else {
//				node = node.izbereOtroka();
//				N++;
//			}
//		}
//		if (node.igra.moznePoteze().isEmpty()) {
//			node = node.izbereOtroka();
//		
//		Stanje stanje = node.simuliraj();
//		
//		node.nazajDoStarsa(node, node.stanjeVRezultat(stanje));
//		}
//	}

// definitivno ni ok 
// -> node.funkcija(node)
// a sta te dve funkciji lahko static?
	


}