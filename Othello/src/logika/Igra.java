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
	
	public Poteza zadnjaPoteza;
	
	private boolean obaImataMoznePoteze;
	
	public Igra() {
		plosca = new Plosca();
		naPotezi = Igralec.CRNI;
		stanje = Stanje.V_TEKU;
		obaImataMoznePoteze = true;
		zadnjaPoteza = null;
	}
	
	public Igralec naPotezi() {
		return naPotezi;
	}

	public Plosca getPlosca() {
		return plosca;
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
							if (ps.get(new Poteza(i,j)) == null) {
								ps.put(new Poteza(i, j), gor_ps);
							}
							else {
								LinkedList<Poteza> obstojeci = ps.get(new Poteza(i, j));
								for (Poteza poteza : gor_ps) {
									obstojeci.add(poteza);
								}
								ps.put(new Poteza(i,j), obstojeci);
							}
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
							if (ps.get(new Poteza(i,j)) == null) {
								ps.put(new Poteza(i, j), dol_ps);
							}
							else {
								LinkedList<Poteza> obstojeci = ps.get(new Poteza(i, j));
								for (Poteza poteza : dol_ps) {
									obstojeci.add(poteza);
								}
								ps.put(new Poteza(i,j), obstojeci);
							}
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
							if (ps.get(new Poteza(i,j)) == null) {
								ps.put(new Poteza(i, j), desno_ps);
							}
							else {
								LinkedList<Poteza> obstojeci = ps.get(new Poteza(i, j));
								for (Poteza poteza : desno_ps) {
									obstojeci.add(poteza);
								}
								ps.put(new Poteza(i,j), obstojeci);
							}
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
							if (ps.get(new Poteza(i,j)) == null) {
								ps.put(new Poteza(i, j), levo_ps);
							}
							else {
								LinkedList<Poteza> obstojeci = ps.get(new Poteza(i, j));
								for (Poteza poteza : levo_ps) {
									obstojeci.add(poteza);
								}
								ps.put(new Poteza(i,j), obstojeci);
							}
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
							if (ps.get(new Poteza(i,j)) == null) {
								ps.put(new Poteza(i, j), desno_dol_ps);
							}
							else {
								LinkedList<Poteza> obstojeci = ps.get(new Poteza(i, j));
								for (Poteza poteza : desno_dol_ps) {
									obstojeci.add(poteza);
								}
								ps.put(new Poteza(i,j), obstojeci);
							}
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
							if (ps.get(new Poteza(i,j)) == null) {
								ps.put(new Poteza(i, j), desno_gor_ps);
							}
							else {
								LinkedList<Poteza> obstojeci = ps.get(new Poteza(i, j));
								for (Poteza poteza : desno_gor_ps) {
									obstojeci.add(poteza);
								}
								ps.put(new Poteza(i,j), obstojeci);
							}
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
							if (ps.get(new Poteza(i,j)) == null) {
								ps.put(new Poteza(i, j), levo_gor_ps);
							}
							else {
								LinkedList<Poteza> obstojeci = ps.get(new Poteza(i, j));
								for (Poteza poteza : levo_gor_ps) {
									obstojeci.add(poteza);
								}
								ps.put(new Poteza(i,j), obstojeci);
							}
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
							if (ps.get(new Poteza(i,j)) == null) {
								ps.put(new Poteza(i, j), levo_dol_ps);
							}
							else {
								LinkedList<Poteza> obstojeci = ps.get(new Poteza(i, j));
								for (Poteza poteza : levo_dol_ps) {
									obstojeci.add(poteza);
								}
								ps.put(new Poteza(i,j), obstojeci);
							}
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
	
	// pomozna funkcija za poteze()
	
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
		// ce igralec na potezi ne more nikamor postaviti zetona
		if ((obaImataMoznePoteze) && (poteze().isEmpty())) {
				obaImataMoznePoteze = false;
				return Stanje.V_TEKU;	
			}
		// ce plosca se ni polna
		else if (!aliJePloscaPolna()) {
			obaImataMoznePoteze = true;
			return Stanje.V_TEKU;
		}
		// plosca je polna ali pa igralca zaporedoma nista mogla igrati poteze
		else {
			if (plosca.steviloCrnih > plosca.steviloBelih) return Stanje.ZMAGA_CRNI;
			else if (plosca.steviloBelih > plosca.steviloCrnih) return Stanje.ZMAGA_BELI;
			else return Stanje.NEODLOCENO;
		}
	}
		
	
	public boolean odigraj(Poteza poteza) {
		LinkedList<Poteza> obrnjeniZetoni = poteze().get(poteza);
		if (!poteze().isEmpty()) {
			if (obrnjeniZetoni == null) return false;
			else {
				plosca.odigrajPotezo(poteza, naPotezi);
				zadnjaPoteza = poteza;
				for (Poteza obrnjen : obrnjeniZetoni) plosca.obrniZeton(obrnjen, naPotezi);
			}
		}
		naPotezi = naPotezi.nasprotnik();
		return true;
	}
	
	// za razliko od prejsnje funkcije ne vraca nicesar 
	public void odigrajZaMcts(Poteza poteza) {
		LinkedList<Poteza> obrnjeniZetoni = poteze().get(poteza);
		if (!poteze().isEmpty()) {
			if (obrnjeniZetoni != null) {
				plosca.odigrajPotezo(poteza, naPotezi);
				zadnjaPoteza = poteza;
				for (Poteza obrnjen : obrnjeniZetoni) plosca.obrniZeton(obrnjen, naPotezi);
			}
		}
		naPotezi = naPotezi.nasprotnik();
	}

}
	
