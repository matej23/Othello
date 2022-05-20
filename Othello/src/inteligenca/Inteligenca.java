package inteligenca;

import logika.Igra;
import splosno.Poteza;

public class Inteligenca extends splosno.KdoIgra {

	public Inteligenca() {
		// ime skupine, haha:
		super(":(");
	}
	
	public Poteza izberiPotezo(Igra igra) {
		MCTS mcts = new MCTS(igra);
		return mcts.main(igra);
	}
}
