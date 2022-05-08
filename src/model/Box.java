package model;

public class Box {
	private Box next;
	private Box previous;
	private Box portal;
	private int ID;
	private String letter;
	private boolean hasSeed;
	private boolean hasPortal;
	private boolean isMorty;
	private boolean isRick;

	public Box(int ID, boolean seed, boolean isPortal, boolean isMorty, boolean isRick) {
		this.ID = ID;
		this.letter="";
		seed = false;
		isPortal = false;
		isMorty = false;
		isRick = false;
	}

	///////////////////////////////////// GETTERS &
	///////////////////////////////////// SETTERS////////////////////////////////////////
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
		return hasSeed;
	}

	public void setSeed(boolean hasSeed) {
		this.hasSeed = hasSeed;
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

	public boolean hasPortal() {
		return hasPortal;
	}

	public void setHasPortal(boolean hasPortal) {
		this.hasPortal = hasPortal;
	}

	public boolean isMorty() {
		return isMorty;
	}

	public void setMorty(boolean isMorty) {
		this.isMorty = isMorty;
	}

	public boolean isRick() {
		return isRick;
	}

	public void setRick(boolean isRick) {
		this.isRick = isRick;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	//////////////////////////////////// PORTAL METHODS/////////////////////////////////////////////////////
	public void createPortal(int size, Box firstLink, int portalAmount, int contPortals) {

		int id1;
		int id2;
		String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Box current1 = firstLink;
		do {
			id1 = (int) (Math.random() * (size) + 1);
		} while (searchPortal(id1, size, current1));

		do {
			id2 = (int) (Math.random() * (size) + 1);
		} while (id1 == id2 || searchPortal(id2, size, current1));

		Box aux1 = null;
		boolean stop = false;
		for (int i = 0; i < size && stop == false; i++) {
			if (current1.getID() == id1) {
				current1.setPortal(current1);
				
				aux1 = current1;
				stop = true;
				aux1.setHasPortal(true);
			}
			current1 = current1.getNext();
		}

		stop = false;
		Box aux2 = null;
		Box current2 = firstLink;
		for (int i = 0; i < size && stop == false; i++) {
			if (current2.getID() == id2) {
				current2.setPortal(current2);
				aux2 = current2;
				stop = true;
			}
			current2 = current2.getNext();
		}
		aux1.setPortal(aux2);
		aux1.setLetter(String.valueOf(symbols.charAt(contPortals)));
		aux2.setPortal(aux1);
		aux2.setLetter(String.valueOf(symbols.charAt(contPortals)));
	}

	public boolean searchPortal(int id1, int size, Box current) {
		for (int i = 0; i < size; i++) {
			if (current.getID() == id1) {
				return current.hasPortal();
			}
			current = current.getNext();
		}
		return false;
	}

//////////////////////////SEED METHODS/////////////////////////////////////////////////////////	
	public void sortSeeds(int size, Box current, int seeds) {

		int id;
		do {
			id = (int) (Math.random() * (size) + 1);
		} while (searchSeeds(id, size, current));

		boolean stop = false;
		for (int i = 0; i < size && stop == false; i++) {
			if (current.getID() == id) {
				current.setSeed(true);
				stop = true;
			}
			current = current.getNext();
		}

	}

	public boolean searchSeeds(int id, int size, Box current) {
		for (int i = 0; i < size; i++) {
			if (current.getID() == id) {
				if (current.isSeed() == false) {
					return false;
				} else {
					return true;
				}
			}
			current = current.getNext();
		}
		return true;
	}

/////////////////////////////////////PLAYER METHODS/////////////////////////////////////////////
	public void sortPlayers(int size, Box current, int portals, int seeds) {
		int mortyPosition;
		int rickPosition;

		do {
			mortyPosition = (int) (Math.random() * (size) + 1);
		} while (searchPortal(size, portals, current)||searchSeeds(current.getID(), size, current));

		do {
			rickPosition = (int) (Math.random() * (size) + 1);
		} while (mortyPosition == rickPosition || searchPortal(size, portals, current)||searchSeeds(current.getID(), size, current));

		boolean stop = false;
		for (int i = 0; i < size && stop == false; i++) {

			if (current.getID() == mortyPosition) {
				current.setMorty(true);
			} else if (current.getID() == rickPosition) {
				current.setRick(true);
			}

			if (current.isMorty() && current.isRick() == true) {
				stop = true;
			}
			current = current.getNext();
		}
	}

	public Box searchMorty(int size) {
		Box current = this;
		for (int i = 0; i < size; i++) {
			if (current.isMorty() == true) {
				return current;
			}
			current = current.getNext();
		}
		return null;
	}

	public Box searchRick(int size) {
		Box current = this;
		for (int i = 0; i < size; i++) {
			if (current.isRick() == true) {
				return current;
			}
			current = current.getNext();
		}
		return null;
	}

}
