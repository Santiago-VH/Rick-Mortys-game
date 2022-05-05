package model;

public class Box {
	private Box next;
	private Box previous;
	private Box portal;
	private int ID;
	private boolean seed;
	
	
	public Box(int ID) {
	this.ID=ID;
	}
	
	public Box getNext() {
		return next;
	}
	
	public void setNext(Box next) {
		this.next = next;
	}
	
	public Box getPrevious() {
		return previous;
	}
	
	public void setPrevious(Box previous) {
		this.previous = previous;
	}

	public boolean isSeed() {
		return seed;
	}

	public void setSeed(boolean seed) {
		this.seed = seed;
	}

	public Box getPortal() {
		return portal;
	}

	public void setPortal(Box portal) {
		this.portal = portal;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	
}
