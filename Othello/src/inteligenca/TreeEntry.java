package inteligenca;

import logika.Igra;

public final class TreeEntry {
	
	public Igra igra;
	public int poskusi;
	public double zmage;
	
	public TreeEntry (Igra igra) {
		this.igra = igra;
		poskusi = 0;
		zmage = 0;
	}

}
