package logika;

import splosno.Poteza;

public class Plosca {
	public Polje[][] plosca;
	
	public static final int N = 8;
	
	public int steviloCrnih;
	public int steviloBelih;
	
	public Plosca() {
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
		
		steviloCrnih = 2;
		steviloBelih = 2;
	}
	
	public void odigrajPotezo(Poteza poteza, Igralec igralec) {
		plosca[poteza.getX()][poteza.getY()] = igralec.getPolje();
		
		if (igralec == Igralec.CRNI) steviloCrnih += 1;
		else steviloBelih += 1;
	}
	
	public void obrniZeton(Poteza poteza, Igralec igralec) {
		plosca[poteza.getX()][poteza.getY()] = igralec.getPolje();
		
		if (igralec == Igralec.CRNI) {
			steviloCrnih += 1;
			steviloBelih -= 1;
		}
		else {
			steviloBelih += 1;
			steviloCrnih -= 1;
		}
	}
	
}

