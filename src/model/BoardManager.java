package model;

public class BoardManager {
	private Box firstLink;
	
	
	public BoardManager() {
		this.firstLink=new Box(1, false, false, false, false);
	}
	
	public void createBoxes(int size, int seeds, int portals) {
		
		Box current1 = firstLink;
		for(int i=1;i<size;i++) {
			int ID=i+1;
			Box newLink = new Box(ID, false, false, false, false); 
			newLink.setPrevious(current1);
			current1.setNext(newLink);
			current1=current1.getNext();
		}
		current1.setNext(firstLink);
		firstLink.setPrevious(current1);
		
		int contPortals=0;
		do {
			createPortal(size, current1, portals);
			contPortals++;
			}while(portals>=contPortals);
		
		int contSeeds=0;
		do {
			sortSeeds(size, current1, seeds);
			contSeeds++;
		}while(seeds>=contSeeds);
	}
	
	public void createPortal(int size, Box current1, int portalAmount) {
		
		int id2;
		int id1;
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
			}
			current1.getNext();
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
			current2.getNext();
		}
		aux1.setPortal(aux2);
		aux2.setPortal(aux1);
		

	}
	
	public boolean searchPortal(int id1, int size, Box current) {
		current=firstLink;
		for(int i=0;i<size;i++) {
			if(current.getID()==id1) {
				if(current.IsPortal() == false)
					return false;
				else {
					return true;
				}
			}
			current.getNext();
		}
		return true;
	}

	public void sortSeeds(int size, Box current, int seeds) {
		
		int id;
		do {
			id=(int)(Math.random() * (size) + 1);
		}while(searchSeeds(id, size, current));
		
		boolean stop=false;
		current=firstLink;
		for(int i=0;i<size;i++) {
			if(current.getID()==id) {
				current.setSeed(true);
			}
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
			current.getNext();
		}
		
		return true;
	}
	
}



