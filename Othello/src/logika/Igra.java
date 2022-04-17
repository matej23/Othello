package logika;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
	
	public Map<Poteza, LinkedList<Poteza>> poteze() {
		Map<Poteza, LinkedList<Poteza>> ps = new HashMap<Poteza, LinkedList<Poteza>>();
		// LinkedList<Poteza> ps = new LinkedList<Poteza>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//nasli prazna polja
				if (plosca[i][j] == Polje.PRAZNO) {
					
					//najde barvo polja za oba igralca
					Polje polje_nasprotnik = naPotezi().getPoljeNasprotnik();
					Polje polje_igralca = naPotezi().getPolje();
					
					//za prazna polja gledamo v vse mozne smeri
					
					//////////////////////////////////////////////////// enosmerne
					int gor = 1;
					LinkedList<Poteza> gor_ps = new LinkedList<Poteza>();
					while (i - gor >= 0 && plosca[i - gor][j] == polje_nasprotnik) {
						gor_ps.add(new Poteza(i - gor, j));
						++gor;
					}
					int gor_final = gor + 1;
					if (i - gor_final >= 0 && plosca[i - gor_final][j] == polje_igralca) {
						ps.put(new Poteza(i, j), gor_ps);
					}
					
					int dol = 1;
					LinkedList<Poteza> dol_ps = new LinkedList<Poteza>();
					while (i + dol < 8 && plosca[i + dol][j] == polje_nasprotnik) {
						dol_ps.add(new Poteza(i + dol, j));
						++dol;
					}
					int dol_final = dol + 1;
					if (i + dol_final < 8 && plosca[i + dol_final][j] == polje_igralca) {
						ps.put(new Poteza(i, j), dol_ps);
					}
					
					int desno = 1;
					LinkedList<Poteza> desno_ps = new LinkedList<Poteza>();
					while (j + desno < 8 && plosca[i][j + desno] == polje_nasprotnik) {
						desno_ps.add(new Poteza(i, j + desno));
						++desno;
					}
					int desno_final = desno + 1;
					if (j + desno_final < 8 && plosca[i][j + desno_final] == polje_igralca) {
						ps.put(new Poteza(i, j), desno_ps);
					}
					
					int levo = 1;
					LinkedList<Poteza> levo_ps = new LinkedList<Poteza>();
					while (j - levo >= 0 && plosca[i][j - levo] == polje_nasprotnik) {
						levo_ps.add(new Poteza(i, j - levo));
						++levo;
					}
					int levo_final = levo + 1;
					if (j - levo_final >= 0 && plosca[i][j - levo_final] == polje_igralca) {
						ps.put(new Poteza(i, j), levo_ps);
					}
					
					/////////////////////////////////////////////////////////////////diagonale
					int desno_dol= 1; 
					LinkedList<Poteza> desno_dol_ps = new LinkedList<Poteza>();
					while (i + desno_dol < 8 && j + desno_dol < 8 && plosca[i + desno_dol][j + desno_dol] == polje_nasprotnik) {
						desno_dol_ps.add(new Poteza(i + desno_dol, j + desno_dol));
						++desno_dol;
					}
					int desno_dol_final = desno_dol + 1;
					if (i + desno_dol_final < 8 && j + desno_dol_final < 8 && plosca[i + desno_dol_final][j + desno_dol_final] == polje_igralca) {
						ps.put(new Poteza(i, j), desno_dol_ps);
					}
					
					int desno_gor= 1; 
					LinkedList<Poteza> desno_gor_ps = new LinkedList<Poteza>();
					while (i - desno_gor >= 0 && j + desno_gor < 8 && plosca[i - desno_gor][j + desno_gor] == polje_nasprotnik) {
						desno_gor_ps.add(new Poteza(i  - desno_gor, j + desno_gor));
						++desno_gor;
					}
					int desno_gor_final = desno_gor + 1;
					if (i - desno_gor_final >= 0 && j + desno_gor_final < 8 && plosca[i - desno_gor_final][j + desno_gor_final] == polje_igralca) {
						ps.put(new Poteza(i, j), desno_gor_ps);
					}
					
					int levo_gor= 1; 
					LinkedList<Poteza> levo_gor_ps = new LinkedList<Poteza>();
					while (i - levo_gor >= 0 && j - levo_gor >= 0  && plosca[i - levo_gor][j - levo_gor] == polje_nasprotnik) {
						levo_gor_ps.add(new Poteza(i  - levo_gor, j - levo_gor));
						++levo_gor;
					}
					int levo_gor_final = levo_gor + 1;
					if (i - levo_gor_final >= 0 && j - levo_gor_final >= 0 && plosca[i - levo_gor_final][j - levo_gor_final] == polje_igralca) {
						ps.put(new Poteza(i, j), levo_gor_ps);
					}
					
					int levo_dol= 1;
					LinkedList<Poteza> levo_dol_ps = new LinkedList<Poteza>();
					while (i + levo_dol < 8  && j - levo_dol >= 0  && plosca[i + levo_dol][j - levo_dol] == polje_nasprotnik) {
						levo_dol_ps.add(new Poteza(i  + levo_dol, j - levo_dol));
						++levo_dol;
					}
					int levo_dol_final = levo_dol + 1;
					if (i + levo_dol_final < 8 && j - levo_dol_final >= 0 && plosca[i + levo_dol_final][j - levo_dol_final] == polje_igralca) {
						ps.put(new Poteza(i, j), levo_dol_ps);
					}
				}
			}
		}
		return ps;
	}
	
	public Stanje stanje() {
		int stevec_crni = 0;
		int stevec_beli = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (plosca[i][j] == Polje.CRNO) stevec_crni++;
				else if (plosca[i][j] == Polje.BELO) stevec_beli++;
				else {
					// V tem primeru niso vsa polja na plosci zapolnjena:
					if (poteze().isEmpty() && naPotezi() == Igralec.CRNI) {
						return Stanje.ZMAGA_BELI;
					}
					else if (poteze().isEmpty() && naPotezi() == Igralec.BELI) {
						return Stanje.ZMAGA_CRNI;
					}
					else return Stanje.V_TEKU;
				}
			}
		}
		if (stevec_crni > stevec_beli) return Stanje.ZMAGA_CRNI;
		else if (stevec_crni < stevec_beli) return Stanje.ZMAGA_BELI;
		else return Stanje.NEODLOCENO;
		
		
	}
	
	public boolean odigraj(Poteza poteza) {
		LinkedList<Poteza> posledica = poteze().get(poteza);
		//ce .get(key) ne najde value vrne null
		if (posledica == null) {
			return false;
		}
		
		else {
			//nasli potezo med moznimi
			plosca[poteza.getX()][poteza.getY()] = naPotezi.getPolje();
			
			for (Poteza poteza_posledica : posledica) {
				plosca[poteza_posledica.getX()][poteza_posledica.getY()] = naPotezi.getPolje();
			}
			
		naPotezi = naPotezi.nasprotnik();
		return true;
		}

	}
	
}
