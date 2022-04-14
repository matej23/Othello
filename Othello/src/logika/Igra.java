package logika;

import java.util.LinkedList;
import java.util.List;

import splosno.Poteza;

public class Igra {
	public static final int N = 8;
	
	public Polje[][] plosca;
	
	private Igralec naPotezi;
	
	public Igra() {
		plosca = new Polje[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				plosca[i][j] = Polje.PRAZNO;
			}
		}
		plosca[3][3] = Polje.BELO;
		plosca[3][4] = Polje.CRNO;
		plosca[4][3] = Polje.CRNO;
		plosca[4][4] = Polje.BELO;
		naPotezi = Igralec.CRNI;
	}
	
	public Igralec naPotezi () {
		return naPotezi;
	}

	public Polje[][] getPlosca () {
		return plosca;
	}

	
	// TO DO :(, igralec spremenljivka?
	// za vsako polje pogledamo ali je sosednji zeton nasprotnikov 
	// ce je v isti smeri gledamo toliko casa dokler ne pridemo do nasega ali do roba/praznega polja
	
	public List<Poteza> poteze() {
		LinkedList<Poteza> ps = new LinkedList<Poteza>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (plosca[i][j] == Polje.PRAZNO) {
					ps.add(new Poteza(i, j));
				}
			}
		}
		return ps;
	}
	// TO DO, zmaga? 
	// (ali poteze = [] & plošča prazna ali plosca polna, zmaga ...
	public Stanje stanje() {
		if (poteze() == null) return Stanje.ZMAGA_CRNI;
		else {
			return Stanje.V_TEKU;
		}
	}
	
//	public boolean odigraj(Poteza poteza) {
//		
//	}
	
}
