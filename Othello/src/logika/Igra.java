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
	
	public Igralec naPotezi() {
		return naPotezi;
	}

	public Polje[][] getPlosca() {
		return plosca;
	}

	
	// TO DO :(, igralec spremenljivka?
	// za vsako polje pogledamo ali je sosednji zeton nasprotnikov 
	// ce je v isti smeri gledamo toliko casa dokler ne pridemo do nasega ali do roba/praznega polja
	
	//zelo je nadlezno, da se stvari ne da pognati :((
	
	public List<Poteza> poteze() {
		LinkedList<Poteza> ps = new LinkedList<Poteza>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//nasli prazna polja
				if (plosca[i][j] == Polje.PRAZNO) {
					
					//najde barvo polja za oba igralca
					Polje polje_nasprotnik = naPotezi().nasprotnik().getPoljeNasprotnik();
					Polje polje_igralca = naPotezi().nasprotnik().getPolje();
					
					//za prazna polja gledamo v vse mozne smeri
					
					//////////////////////////////////////////////////// enosmerne
					int gor = 1;
					while (i - gor >= 0 && plosca[i - gor][j] == polje_nasprotnik) {
						++gor;
					}
					int gor_final = gor + 1;
					if (i - gor_final >= 0 && plosca[i - gor_final][j] == polje_igralca) {
						ps.add(new Poteza(i, j));
					}
					
					int dol = 1;
					while (i + dol < 8 && plosca[i + dol][j] == polje_nasprotnik) {
						++dol;
					}
					int dol_final = dol + 1;
					if (i + dol_final < 8 && plosca[i + dol_final][j] == polje_igralca) {
						ps.add(new Poteza(i, j));
					}
					
					int desno = 1;
					while (j + desno < 8 && plosca[i][j + desno] == polje_nasprotnik) {
						++desno;
					}
					int desno_final = desno + 1;
					if (j + desno_final < 8 && plosca[i][j + desno_final] == polje_igralca) {
						ps.add(new Poteza(i, j));
					}
					
					int levo = 1;
					while (j - levo >= 0 && plosca[i][j - levo] == polje_nasprotnik) {
						++levo;
					}
					int levo_final = levo + 1;
					if (j - levo_final >= 0 && plosca[i][j - levo_final] == polje_igralca) {
						ps.add(new Poteza(i, j));
					}
					
					/////////////////////////////////////////////////////////////////diagonale
					int desno_dol= 1; 
					while (i + desno_dol < 8 && j + desno_dol < 8 && plosca[i + desno_dol][j + desno_dol] == polje_nasprotnik) {
						++desno_dol;
					}
					int desno_dol_final = desno_dol + 1;
					if (i + desno_dol_final < 8 && j + desno_dol_final < 8 && plosca[i + desno_dol_final][j + desno_dol_final] == polje_igralca) {
						ps.add(new Poteza(i, j));
					}
					
					int desno_gor= 1; 
					while (i - desno_gor >= 0 && j + desno_gor < 8 && plosca[i - desno_gor][j + desno_gor] == polje_nasprotnik) {
						++desno_gor;
					}
					int desno_gor_final = desno_gor + 1;
					if (i - desno_gor_final >= 0 && j + desno_gor_final < 8 && plosca[i - desno_gor_final][j + desno_gor_final] == polje_igralca) {
						ps.add(new Poteza(i, j));
					}
					
					int levo_gor= 1; 
					while (i - levo_gor >= 0 && j - levo_gor >= 0  && plosca[i - levo_gor][j - levo_gor] == polje_nasprotnik) {
						++levo_gor;
					}
					int levo_gor_final = levo_gor + 1;
					if (i - levo_gor_final >= 0 && j - levo_gor_final >= 0 && plosca[i - levo_gor_final][j - levo_gor_final] == polje_igralca) {
						ps.add(new Poteza(i, j));
					}
					
					int levo_dol= 1;
					while (i + levo_dol < 8  && j - levo_dol >= 0  && plosca[i + levo_dol][j - levo_dol] == polje_nasprotnik) {
						++levo_gor;
					}
					int levo_dol_final = levo_dol + 1;
					if (i + levo_dol_final < 8 && j - levo_dol_final >= 0 && plosca[i + levo_dol_final][j - levo_dol_final] == polje_igralca) {
						ps.add(new Poteza(i, j));
					}
				}
			}
		}
		return ps;
	}
	// TO DO, zmaga? 
	// (ali poteze = [] & plosca prazna ali plosca polna, zmaga ...
	// !! dodan je spremenljivka -igra- preko katere zgornja funkcija -poteze- dostopa do podatka o igralcu !!
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
