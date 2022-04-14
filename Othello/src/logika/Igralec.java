package logika;

public enum Igralec {
	CRNI, BELI;

	public Igralec nasprotnik() {
		return (this == CRNI ? BELI : CRNI);
	}

	public Polje getPolje() {
		// ne štekamo :( 
		return (this == CRNI ? Polje.CRNO : Polje.BELO);
	}
	
	@Override
	public String toString() {
		return (this == CRNI ? "CRNI" : "BELI");
	}
}

