package inteligenca;

import logika.Igra;
import splosno.Poteza;

public class Inteligenca extends splosno.KdoIgra {

	public Inteligenca() {
		super("NUTRIJE <3");
	}
	
	public Poteza izberiPotezo(Igra igra) {
		MCTS mcts = new MCTS(igra);
		return mcts.main(igra);
	}
}
