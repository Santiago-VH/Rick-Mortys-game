package model;

public class BoardManager {
	private Box firstLink;
	
	
	public BoardManager() {
		this.firstLink=new Box(1);
	}
	
	public void createBoxes(int rows, int columns, int seeds, int portals) {
	
		
		Box current = firstLink;
		
		for(int i=1;i<(rows*columns);i++) {
			int ID=i+1;
			Box newLink = new Box(ID); 
			newLink.setPrevious(current);
			current.setNext(newLink);
			current=current.getNext();
		}
		current.setNext(firstLink);
		firstLink.setPrevious(current);
		
		do {
			createPortals(rows, columns, current);
			}while(true);
	}
	
	public void createPortals(int rows, int columns, Box current) {
		int id2;
		int id1 = (int)(Math.random() * (rows*columns) + 1);
		
		do {
			id2=(int)(Math.random() * (rows*columns) + 1);
		}while(id1==id2);
		
		current=firstLink;
		for(int i=0;i<(rows*columns);i++) {
			if(current.getID()==id1) {
				current.setPortal(current);
			}
			
			if(current.getID()==id2) {
				current.setPortal(current);
			}
			current.getNext();
		}
	}
	
	

}



