package model;

public class Board {
	private int size;
	private int seedsAmount;
	private int portalsAmount;
	Box box;
	
	public Board(int size, int seedsAmount, int portalsAmount) {
		this.size=size;
		this.seedsAmount=seedsAmount;
		this.portalsAmount=portalsAmount;
		this.box=new Box(0, false, false, false, false);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSeedsAmount() {
		return seedsAmount;
	}

	public void setSeedsAmount(int seedsAmount) {
		this.seedsAmount = seedsAmount;
	}

	public int getPortalsAmount() {
		return portalsAmount;
	}

	public void setPortalsAmount(int portalsAmount) {
		this.portalsAmount = portalsAmount;
	}

	public void createBoxes(int size, int seeds, int portals, Box firstLink) {
		
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
		
		if(portals!=0) {
		int contPortals=0;
		do {
			box.createPortal(size, current1, portals);
			contPortals++;
			}while(portals>=contPortals);
		}
		
		int contSeeds=0;
		do {
			box.sortSeeds(size, current1, seeds);
			contSeeds++;
		}while(seeds>=contSeeds);
	}
	
	
	
}
