package logika;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import splosno.Poteza;

public class Igra {
	public static final int N = 8;
	
	public Plosca plosca;
	
	public Stanje stanje;
	
	private Igralec naPotezi;
	
	
	public Igra() {
		plosca = new Plosca();
		naPotezi = Igralec.CRNI;
		stanje = Stanje.V_TEKU;
	}
	
	public Igralec naPotezi() {
		return naPotezi;
	}

	public Plosca getPlosca() {
		return plosca;
	}
	//klonirana verzija igre
	public Igra(Plosca plosca, Igralec naPotezi, Stanje stanje) {
		Plosca ploscaKopija = new Plosca();
		for (int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				ploscaKopija.plosca[i][j] = plosca.plosca[i][j];
			}
		}
		ploscaKopija.steviloBelih = plosca.steviloBelih;
		ploscaKopija.steviloCrnih = plosca.steviloCrnih;
		
		this.plosca = ploscaKopija;
		this.naPotezi = naPotezi;
		this.stanje = stanje;
	}
	
	/*
	funkcija najde vse možne poteze in jih zapiše v slovar,
	v katerem je pri vsaki potezi seznam žetonov, ki se bodo obrnili, če opravimo to potezo
	*/
	
	public Map<Poteza, LinkedList<Poteza>> poteze() {
		Map<Poteza, LinkedList<Poteza>> ps = new HashMap<Poteza, LinkedList<Poteza>>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (plosca.plosca[i][j] == Polje.PRAZNO) {
					
					// najdemo barvo polja za oba igralca
					Polje polje_nasprotnik = naPotezi().getPoljeNasprotnik();
					Polje polje_igralca = naPotezi().getPolje();
					
					// pogledamo v vse možne smeri:
					
					//--------------------------------------------------- 
					// horizontalne/vertikalne smeri

					int gor = 1;
					LinkedList<Poteza> gor_ps = new LinkedList<Poteza>();
					while ((i - gor >= 0 ) && (plosca.plosca[i - gor][j] == polje_nasprotnik)) {
						gor_ps.add(new Poteza(i - gor, j));
						
						if (i - (gor + 1) >= 0 && plosca.plosca[i - (gor + 1)][j] == polje_igralca) {
							dodajSmerVSlovar(ps, gor_ps, i, j);
							break;	
						}
						else {
							++gor;
						}
					}
					
					int dol = 1;
					LinkedList<Poteza> dol_ps = new LinkedList<Poteza>();
					while ((i + dol < 8) && plosca.plosca[i + dol][j] == polje_nasprotnik) {
						dol_ps.add(new Poteza(i + dol, j));
						
						if (i + (dol + 1) < 8 && plosca.plosca[i + (dol + 1)][j] == polje_igralca) {
							dodajSmerVSlovar(ps, dol_ps, i, j);
							break;
						}
						else {
							++dol;
						}
					}
					
					int desno = 1;
					LinkedList<Poteza> desno_ps = new LinkedList<Poteza>();
					while (j + desno < 8 && plosca.plosca[i][j + desno] == polje_nasprotnik) {
						desno_ps.add(new Poteza(i, j + desno));
						
						if (j + (desno + 1) < 8 && plosca.plosca[i][j + (desno + 1)] == polje_igralca) {
							dodajSmerVSlovar(ps, desno_ps, i, j);
							break;
						}
						else {
							++desno;
						}
					}
					
					int levo = 1;
					LinkedList<Poteza> levo_ps = new LinkedList<Poteza>();
					while (j - levo >= 0 && plosca.plosca[i][j - levo] == polje_nasprotnik) {
						levo_ps.add(new Poteza(i, j - levo));
						
						if (j - (levo + 1) >= 0 && plosca.plosca[i][j - (levo + 1)] == polje_igralca) {
							dodajSmerVSlovar(ps, levo_ps, i, j);
							break;
						}
						else {
							++levo;
						}
					}
					
					//--------------------------------------------------- 
					// diagonalne smeri
					
					int desno_dol= 1; 
					LinkedList<Poteza> desno_dol_ps = new LinkedList<Poteza>();
					while (i + desno_dol < 8 && j + desno_dol < 8 && plosca.plosca[i + desno_dol][j + desno_dol] == polje_nasprotnik) {
						desno_dol_ps.add(new Poteza(i + desno_dol, j + desno_dol));
						
						if (i + (desno_dol + 1) < 8 && j + (desno_dol + 1) < 8 && plosca.plosca[i + (desno_dol + 1)][j + (desno_dol + 1)] == polje_igralca) {
							dodajSmerVSlovar(ps, desno_dol_ps, i, j);
							break;
						}
						else {
							++desno_dol;
						}
					}
					
					
					int desno_gor= 1; 
					LinkedList<Poteza> desno_gor_ps = new LinkedList<Poteza>();
					while (i - desno_gor >= 0 && j + desno_gor < 8 && plosca.plosca[i - desno_gor][j + desno_gor] == polje_nasprotnik) {
						desno_gor_ps.add(new Poteza(i  - desno_gor, j + desno_gor));
				
						if (i - (desno_gor + 1) >= 0 && j + (desno_gor + 1) < 8 && plosca.plosca[i - (desno_gor + 1)][j + (desno_gor + 1)] == polje_igralca) {
							dodajSmerVSlovar(ps, desno_gor_ps, i, j);
							break;
						}
						else {
							++desno_gor;
						}
					}
					
					int levo_gor= 1; 
					LinkedList<Poteza> levo_gor_ps = new LinkedList<Poteza>();
					while (i - levo_gor >= 0 && j - levo_gor >= 0  && plosca.plosca[i - levo_gor][j - levo_gor] == polje_nasprotnik) {
						levo_gor_ps.add(new Poteza(i  - levo_gor, j - levo_gor));
					
						if (i - (levo_gor + 1) >= 0 && j - (levo_gor + 1) >= 0 && plosca.plosca[i - (levo_gor + 1)][j - (levo_gor + 1)] == polje_igralca) {
							dodajSmerVSlovar(ps, levo_gor_ps, i, j);
							break;
						}
						else {
							++levo_gor;
						}
					}

					int levo_dol= 1;
					LinkedList<Poteza> levo_dol_ps = new LinkedList<Poteza>();
					while (i + levo_dol < 8  && j - levo_dol >= 0  && plosca.plosca[i + levo_dol][j - levo_dol] == polje_nasprotnik) {
						levo_dol_ps.add(new Poteza(i  + levo_dol, j - levo_dol));
			
						if (i + (levo_dol + 1) < 8 && j - (levo_dol + 1)>= 0 && plosca.plosca[i + (levo_dol + 1)][j - (levo_dol + 1)] == polje_igralca) {
							dodajSmerVSlovar(ps, levo_dol_ps, i, j);
							break;
						}
						else {
							++levo_dol;
						}
					}

				}
			}
		}
		return ps;
	}
	
	public void dodajSmerVSlovar (Map<Poteza, LinkedList<Poteza>> poteze, LinkedList<Poteza> smer, int i, int j) {
		if (poteze.get(new Poteza(i,j)) == null) {
			poteze.put(new Poteza(i, j), smer);
		}
		else {
			LinkedList<Poteza> obstojeci = poteze.get(new Poteza(i, j));
			for (Poteza poteza : smer) {
				obstojeci.add(poteza);
			}
			poteze.put(new Poteza(i,j), obstojeci);
		}
	}
	
	
	public LinkedList<Poteza> moznePoteze() {
		LinkedList<Poteza> moznePoteze = new LinkedList<Poteza>(poteze().keySet());
		return moznePoteze;
	}
	
	public boolean aliJePloscaPolna() {
		return plosca.steviloCrnih + plosca.steviloBelih == 64;
	}
	
	public Stanje posodobiStanje() {
		if (aliJePloscaPolna()) {
			if (plosca.steviloCrnih > plosca.steviloBelih) return Stanje.ZMAGA_CRNI;
			else if (plosca.steviloBelih > plosca.steviloCrnih) return Stanje.ZMAGA_BELI;
			else return Stanje.NEODLOCENO;
		}
		//plosca ni polna
		else {
			//imamo poteze na voljo za igralca
			if (!poteze().isEmpty()) { 
				return Stanje.V_TEKU;
			}
			//nimamo potez na voljo
			else {
				Igra klonIgra = new Igra(plosca, naPotezi.nasprotnik(), stanje);
				
				//za nasprotnika so se poteze
				if (!klonIgra.poteze().isEmpty()) {
					naPotezi = naPotezi.nasprotnik();
					return Stanje.V_TEKU;
				}
				//za nasprotnika ni vec potez
				else {
					if (plosca.steviloCrnih > plosca.steviloBelih) return Stanje.ZMAGA_CRNI;
					else if (plosca.steviloBelih > plosca.steviloCrnih) return Stanje.ZMAGA_BELI;
					else return Stanje.NEODLOCENO;
				}
			}
		}
	}	
		
	
	public boolean odigraj(Poteza poteza) {
		LinkedList<Poteza> obrnjeniZetoni = poteze().get(poteza);
		if (!poteze().isEmpty()) {
			if (obrnjeniZetoni == null) return false;
			else {
				plosca.odigrajPotezo(poteza, naPotezi);
				for (Poteza obrnjen : obrnjeniZetoni) plosca.obrniZeton(obrnjen, naPotezi);
			}
		}
		naPotezi = naPotezi.nasprotnik();
		return true;
	}
	
}
	
