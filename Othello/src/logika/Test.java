/*package logika;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import splosno.Poteza;

public class Test {

	public static void main(String[] args) {
		System.out.print("Othello je zabavno delo :) \n\n");

		Igra igra = new Igra();
		
		System.out.print(igra.naPotezi().toString());
		System.out.print("\n" + igra.stanje());
		System.out.print("\n\n");
		polje_izpis(igra.getPlosca());
		System.out.print("\n");
		poteze_izpis(igra.poteze());
		
		System.out.print("\n");
		Poteza poteza = new Poteza(5,4);
		System.out.print(igra.odigraj(poteza));
		System.out.print("\n\n");
		
		///////////////////////////////////////////////////
		
		System.out.print(igra.naPotezi().toString());
		System.out.print("\n" + igra.stanje());
		System.out.print("\n\n");
		polje_izpis(igra.getPlosca());
		System.out.print("\n");
		poteze_izpis(igra.poteze());
		
		System.out.print("\n");
		Poteza poteza_2 = new Poteza(7,7);
		System.out.print(igra.odigraj(poteza_2));
		System.out.print("\n\n");
		polje_izpis(igra.getPlosca());
		System.out.print("\n");
		poteze_izpis(igra.poteze());
	}
	
	public static void polje_izpis(Polje[][] polje) {
		for (Polje[] polja_i : polje) {
			for (Polje polja_j : polja_i) {
				if (polja_j == Polje.CRNO) {
					System.out.print("B");
				}
				else if (polja_j == Polje.BELO) {
					System.out.print("W");
				}
				else {
					System.out.print("O");
				}
			}
			System.out.print("\n");
		}
	}
	
	public static void poteze_izpis(Map<Poteza, LinkedList<Poteza>> moznosti) {
		Set<Poteza> set_poteze = moznosti.keySet();
		LinkedList<Poteza> poteze = new LinkedList<Poteza>();
		poteze.addAll(set_poteze);
		for (Poteza poteza : poteze) {
			System.out.print("\n");
			System.out.print(poteza.toString()+ "\n");
			
			System.out.print("POSLEDICE:  ");
			LinkedList<Poteza> posledice = moznosti.get(poteza);
			for (Poteza posledica_poteze : posledice) {
				System.out.print(posledica_poteze.toString()+",  \n");
			}
			System.out.print("***********************************");
		}
	}
}

*/