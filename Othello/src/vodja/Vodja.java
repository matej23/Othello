package vodja;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Random;
import java.util.Map;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

import logika.Igra;
import logika.Igralec;
import splosno.Poteza;

public class Vodja {
	
	private static enum VrstaIgralca { R, C; }
	
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	
	private static Map<Igralec,VrstaIgralca> vrstaIgralca;
	
	public static void igramo () throws IOException {
		while (true) {
			System.out.println("Nova igra. Prosim, da izberete:");
			System.out.println(" 1 - B človek, W računalnik");
			System.out.println(" 2 - B računalnik, W človek");
			System.out.println(" 3 - B človek, W človek");
			System.out.println(" 4 - izhod");
			String s = r.readLine();
			if (s.equals("1")) {
				vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
				vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.C); 
				vrstaIgralca.put(Igralec.BELI, VrstaIgralca.R); 			
			} else if (s.equals("2")) {
				vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
				vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.R); 
				vrstaIgralca.put(Igralec.BELI, VrstaIgralca.C); 			
			} else if (s.equals("3")) {
				vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
				vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.C); 
				vrstaIgralca.put(Igralec.BELI, VrstaIgralca.C); 			
			} else if (s.equals("4")) {
				System.out.println("Nasvidenje!");
				break;
			} else {
				System.out.println("Vnos ni veljaven");
				continue;
			}
			Igra igra = new Igra ();
			igranje : while (true) {
				switch (igra.stanje()) {
				case ZMAGA_CRNI: 
					System.out.println("Zmagal je igralec CRNI");
					break igranje;
				case ZMAGA_BELI: 
					System.out.println("Zmagal je igralec BELI");
					break igranje;
				case NEODLOCENO: 
					System.out.println("Igra je neodločena");
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
	
	private static Random random = new Random ();
	
	public static Poteza racunalnikovaPoteza(Igra igra) {
		List<Poteza> moznePoteze = new LinkedList<Poteza>(igra.poteze().keySet());
		int randomIndex = random.nextInt(moznePoteze.size());
		Poteza poteza = moznePoteze.get(randomIndex);
		igra.odigraj(poteza);
		return poteza;		
	}
	
	public static Poteza clovekovaPoteza(Igra igra) throws IOException {
		while (true) {
			Igralec igralec = igra.naPotezi();
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
			if (igra.odigraj(poteza)) {
				igra.plosca_izpis(igra.getPlosca());
				return poteza;}
			else System.out.println(poteza + " ni možna");
		}
	}

}
