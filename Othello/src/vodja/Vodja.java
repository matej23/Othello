package vodja;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Set;

import inteligenca.Inteligenca;

import java.util.Map;
import java.util.EnumMap;
import java.util.LinkedList;

import logika.Igra;
import logika.Igralec;
import logika.Plosca;
import logika.Polje;
import splosno.Poteza;

public class Vodja {
	
	private static enum VrstaIgralca { R, C; }
	
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	
	private static Map<Igralec,VrstaIgralca> vrstaIgralca;
	
	public static Inteligenca rac1;
	public static Inteligenca rac2;
	
	
	public static void igramo () throws IOException {
		while (true) {
			System.out.println("Nova igra. Prosim, da izberete:");
			System.out.println(" 1 - B clovek, W racunalnik");
			System.out.println(" 2 - B racunalnik, W clovek");
			System.out.println(" 3 - B clovek, W clovek");
			System.out.println(" 4 - B racunalnik, W racunalnik");
			System.out.println(" 5 - izhod");
			String s = r.readLine();
			if (s.equals("1")) {
				vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
				vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.C); 
				vrstaIgralca.put(Igralec.BELI, VrstaIgralca.R); 
				rac1 = null;
				rac2 = new Inteligenca();
			} else if (s.equals("2")) {
				vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
				vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.R); 
				vrstaIgralca.put(Igralec.BELI, VrstaIgralca.C); 		
				rac1 = new Inteligenca();
				rac2 = null;
			} else if (s.equals("3")) {
				vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
				vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.C); 
				vrstaIgralca.put(Igralec.BELI, VrstaIgralca.C); 
				rac1 = null;
				rac2 = null;
			} else if (s.equals("4")) {
				vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
				vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.R); 
				vrstaIgralca.put(Igralec.BELI, VrstaIgralca.R); 
				rac1 = new Inteligenca();
				rac2 = new Inteligenca();
			} else if (s.equals("5")) {
				System.out.println("Nasvidenje!");
				break;
			} else {
				System.out.println("Vnos ni veljaven");
				continue;
			}
			Igra igra = new Igra ();
			igranje : while (true) {
				switch (igra.posodobiStanje()) {
				case ZMAGA_CRNI: 
					System.out.println("Zmagal je igralec CRNI");
					break igranje;
				case ZMAGA_BELI: 
					System.out.println("Zmagal je igralec BELI");
					break igranje;
				case NEODLOCENO: 
					System.out.println("Igra je neodlocena");
					break igranje;
				case V_TEKU: 
					Igralec igralec = igra.naPotezi();
					VrstaIgralca vrstaNaPotezi = vrstaIgralca.get(igralec);
					Poteza poteza = null;
					switch (vrstaNaPotezi) {
					case C: 
						poteza = clovekovaPoteza(igra);
						break;
					case R:
						poteza = racunalnikovaPoteza(igra);
						break;
					}
					System.out.println("Igralec " + igralec + " je igral " + poteza);
				}
			}
		}
	}

	
	public static Poteza racunalnikovaPoteza(Igra igra) {
		Poteza poteza = null;
		if (igra.naPotezi() == Igralec.CRNI) {
			poteza = rac1.izberiPotezo(igra);
		}
		else {
			poteza = rac2.izberiPotezo(igra);
		}
		igra.odigraj(poteza);
		return poteza;		
	}

	public static Poteza clovekovaPoteza(Igra igra) throws IOException {
		while (true) {
			Igralec igralec = igra.naPotezi();
			
			plosca_izpis(igra.getPlosca());
			poteze_izpis(igra.poteze());
			System.out.print("\n");
			System.out.println("stevilo belih zetonov:" + igra.plosca.steviloBelih);
			System.out.println("stevilo crnih zetonov:" + igra.plosca.steviloCrnih);
			System.out.print("\n");
			
			System.out.println("Igralec " + igralec +
					" vnesite potezo \"x y\"");
			
			String s = r.readLine();
			int i = s.indexOf (' '); 
			if (i == -1 || i  == s.length()) { 
				System.out.println("Napačen format"); continue; 
			}
			String xString = s.substring(0,i);
			String yString = s.substring(i+1);
			int x, y;
			try {
				x = Integer.parseInt(xString);
				y = Integer.parseInt(yString);		
			} catch (NumberFormatException e) {
				System.out.println("Napačen format"); continue; 
			}
			if (x < 0 || x >= Igra.N || y < 0 || y >= Igra.N){
				System.out.println("Napačen format"); continue; 			
			}
			Poteza poteza = new Poteza(x,y);
			if (igra.odigraj(poteza)) return poteza;
			else System.out.println(poteza + " ni možna");
		}
	}
	
	public static void plosca_izpis(Plosca plosca) {
		for (Polje[] polja_i : plosca.plosca) {
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
		Set<Poteza> mnozicaPotez = moznosti.keySet();
		LinkedList<Poteza> poteze = new LinkedList<Poteza>();
		poteze.addAll(mnozicaPotez);
		for (Poteza poteza : poteze) {
			System.out.print("\n");
			System.out.print(poteza.toString()+ "\n");
			
			System.out.print("Obrnjeni zetoni:  ");
			LinkedList<Poteza> obrnjeniZetoni = moznosti.get(poteza);
			for (Poteza obrnjen : obrnjeniZetoni) {
				System.out.print(obrnjen.toString()+",  \n");
			}
			System.out.print("***********************************");
		}
	}

}
