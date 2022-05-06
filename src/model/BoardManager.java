package model;

public class BoardManager {
	private Box firstLink;
	
	
	public BoardManager() {
		this.firstLink=new Box(1, false);
	}
	
	public void createBoxes(int rows, int columns, int seeds, int portals) {
		
		Box current1 = firstLink;
		for(int i=1;i<(rows*columns);i++) {
			int ID=i+1;
			Box newLink = new Box(ID, false); 
			newLink.setPrevious(current1);
			current1.setNext(newLink);
			current1=current1.getNext();
		}
		current1.setNext(firstLink);
		firstLink.setPrevious(current1);
		
		int cont=0;
		do {
			createPortal(rows, columns, current1, portals);
			cont++;
			}while(portals>=cont);
		
	}
	
	public void createPortal(int rows, int columns, Box current1, int portalAmount) {
		int id2;
		int id1;
		int size = rows*columns;
		
		do {
			id1 = (int)(Math.random() * (rows*columns) + 1);
		}while(searchPortal(id1,size));
		
		do {
			id2=(int)(Math.random() * (rows*columns) + 1);
		}while(id1==id2||searchPortal(id2, portalAmount));
			
		Box aux1=null;
		current1=firstLink;
		boolean stop=false;
		for(int i=0;i<(rows*columns)&&stop==false;i++) {
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
		for(int i=0;i<(rows*columns)&&stop==false;i++) {
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
	
	public boolean searchPortal(int id1, int size) {
		Box current=firstLink;
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

}



