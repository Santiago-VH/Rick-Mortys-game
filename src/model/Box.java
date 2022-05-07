package model;

public class Box {
	Box firstLink;
	private Box next;
	private Box previous;
	private Box portal;
	private int ID;
	private boolean hasSeed;
	private boolean hasPortal;
	private boolean isMorty;
	private boolean isRick;
	
	
	public Box(int ID, boolean seed, boolean isPortal, boolean isMorty, boolean isRick) {
	this.ID=ID;
	seed=false;
	isPortal=false;
	isMorty=false;
	isRick=false;
	}
	
	/////////////////////////////////////GETTERS & SETTERS////////////////////////////////////////
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

	////////////////////////////////////PORTAL METHODS/////////////////////////////////////////////////////
	public void createPortal(int size, Box current1, int portalAmount) {
		
		int id1;
		int id2;
		do {
			id1 = (int)(Math.random() * (size) + 1);
		}while(searchPortal(id1, size, current1));
		
		do {
			id2=(int)(Math.random() * (size) + 1);
		}while(id1==id2||searchPortal(id2, size, current1));
			
		Box aux1=null;
		current1=firstLink;
		boolean stop=false;
		for(int i=0;i<size&&stop==false;i++) {
			if(current1.getID()==id1) {
				current1.setPortal(current1);
				aux1=current1;
				stop=true;
				aux1.setHasPortal(true);
			}
			current1=current1.getNext();
		}
		
		stop=false;
		Box aux2=null;
		Box current2=firstLink;
		for(int i=0;i<size&&stop==false;i++) {
			if(current2.getID()==id2) {
				current2.setPortal(current2);
				aux2=current2;
				stop=true;
			}
			current2=current2.getNext();
		}
		aux1.setPortal(aux2);
		aux2.setPortal(aux1);
	}
	
	
	public boolean searchPortal(int id1, int size, Box current) {
		current=firstLink;
		for(int i=0;i<size;i++) {
			if(current.getID()==id1) {
				if(current.hasPortal() == false)
					return false;
				else {
					return true;
				}
			}
			current=current.getNext();
		}
		return true;
	}
	
	
//////////////////////////SEED METHODS/////////////////////////////////////////////////////////	
	public void sortSeeds(int size, Box current, int seeds) {
		
		int id;
		do {
			id=(int)(Math.random() * (size) + 1);
		}while(searchSeeds(id, size, current));
		
		boolean stop=false;
		current=firstLink;
		for(int i=0;i<size&&stop==false;i++) {
			if(current.getID()==id) {
				current.setSeed(true);
				stop=true;
			}
			current=current.getNext();
		}
		
	}
	
	public boolean searchSeeds(int id, int size, Box current) {
		current=firstLink;
		for(int i=0;i<size;i++) {
			if(current.getID()==id) {
				if(current.isSeed()==false) {
					return false;
				}else {
					return true;
				}
			}
			current=current.getNext();
		}
		return true;
	}	
	
	
	
/////////////////////////////////////PLAYER METHODS/////////////////////////////////////////////
	public void sortPlayers(int size, Box current, int portals) {
		int mortyPosition;
		int rickPosition;
		
		do {
		mortyPosition=(int)(Math.random() * (size) + 1);
		}while(searchPortal(size, portals, current));
		
		do {
		rickPosition=(int)(Math.random() * (size) + 1);	
		}while(mortyPosition==rickPosition||searchPortal(size, portals, current));
		
		boolean stop=false;
		current=firstLink;
		for(int i=0;i<size&&stop==false;i++) {
			
			if(current.getID()==mortyPosition) {
				current.setMorty(true);
			}else if(current.getID()==rickPosition) {
				current.setRick(true);
			}
			
			if(current.isMorty()&&current.isRick()==true) {
				stop=true;
			}
			current=current.getNext();
		}		
	}
	
	public boolean searchPlayer(int mortyPosition, int rickPosition, int size, Box current) {
		//TODO
		return true;
	}
	
}
