package model;

public class BoardManager {
	Box firstLink;

	public BoardManager() {
		this.firstLink = new Box(1, false, false, false, false);
	}

	public void organizeGame(int size, int seeds, int portals, String morty, String rick) {

		Board board = new Board(0, 0, 0, 0);
		Box box = new Box(0, false, false, false, false);
		Box current1 = firstLink;
		board.createBoxes(size, seeds, portals, current1);

		if (portals != 0) {
			int contPortals = 1;
			do {
				box.createPortal(size, current1, portals, contPortals-1);
				contPortals++;
			} while (portals >= contPortals);
		}

		int contSeeds = 1;
		do {
			box.sortSeeds(size, current1, seeds);
			contSeeds++;
		} while (seeds >= contSeeds);

		box.sortPlayers(size, current1, portals, seeds);
	}

	public Box getFirstLink() {
		return firstLink;
	}

	public void setFirstLink(Box firstLink) {
		this.firstLink = firstLink;
	}

}
