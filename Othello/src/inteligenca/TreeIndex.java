package inteligenca;

import java.util.LinkedList;
import java.util.List;

import logika.Igralec;
import splosno.Poteza;


public final class TreeIndex {
	
	private final LinkedList<Poteza> potList;
	private final LinkedList<Igralec> potListIgralec;
	
	public TreeIndex () {
		this.potList = new LinkedList<Poteza> ();
		this.potListIgralec = new LinkedList<Igralec>();
	}

	public TreeIndex (TreeIndex ti, Poteza p, Igralec igralec) {
		// Append p to a deep copy of ti
		this.potList = new LinkedList<Poteza>();
		for (Poteza q : ti.potList) this.potList.addLast(q);
		this.potList.addLast(p);
		this.potListIgralec = ti.potListIgralec;
		potListIgralec.add(igralec);
	}
	
	public TreeIndex (TreeIndex ti) {
		//Deep copy without Poteza
		this.potList = new LinkedList<Poteza>();
		for (Poteza q : ti.potList) this.potList.addLast(q);
		this.potListIgralec = ti.potListIgralec;
	}
	
	public boolean isRoot() {
		return potList.isEmpty();
	}
	
	public List<Poteza> asList() {
		return potList;
	}
	
	public Poteza lastMove() {
		return potList.getLast();
	}
	
	public TreeIndex parent() {
		TreeIndex ti = new TreeIndex();
		for (Poteza p : this.potList) ti.potList.addLast(p);
		ti.potList.removeLast();
		return ti;
	}

	public Igralec zadnjiIgralec(){
		return potListIgralec.getLast();
	}
	
	@Override 
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || this.getClass() != o.getClass()) return false;
		TreeIndex k = (TreeIndex) o;
		return this.potList.equals(k.potList);
	}
	
	@Override
	public int hashCode () {
		return this.potList.hashCode();
	}

	@Override
	public String toString() {
		return potList.toString();
	}

}