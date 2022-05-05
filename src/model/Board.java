package model;

public class Board {
	private int rows;
	private int columns;
	private int seedsAmount;
	private int portalsAmount;
	
	public Board(int rows, int columns, int seeds, int links) {
		
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
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


	
	
}
