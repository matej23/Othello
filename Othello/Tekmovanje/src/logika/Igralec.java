package logika;

public enum Igralec {
	CRNI, BELI;

	public Igralec nasprotnik() {
		return (this == CRNI ? BELI : CRNI);
	}

	public Polje getPolje() {
		return (this == CRNI ? Polje.CRNO : Polje.BELO);
	}
	
	public Polje getPoljeNasprotnik() {
		return (this == CRNI ? Polje.BELO : Polje.CRNO);
	}
	
	@Override
	public String toString() {
		return (this == CRNI ? "CRNI" : "BELI");
	}
}

