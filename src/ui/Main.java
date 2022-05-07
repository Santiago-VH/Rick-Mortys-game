package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.BoardManager;
import model.Box;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BoardManager manager = new BoardManager();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		boolean validation=true;
		
		do {
		System.out.println("Welcome to Rick & Morty's adventure! Please digit the number of columns\n");
		int columns=Integer.parseInt(br.readLine());
		
			if(columns<0) {
				System.out.println("Don't digit negative numbers, restart the game.\n");
				validation=false;
			}
		
		System.out.println("Now digit the number of rows\n");
		int rows=Integer.parseInt(br.readLine());
		
			if(rows<0) {
				System.out.println("Don't digit negative numbers, restart the game.\n");
				validation=false;
			}
		
			int size=columns*rows;
		System.out.println("Please digit the amount of seeds you want on the game\n");
		int seedsAmount=Integer.parseInt(br.readLine());
		
			if(seedsAmount<0) {
				System.out.println("Don't digit negative numbers, restart the game.\n");
				validation=false;
			} else if(seedsAmount>size) {
				System.out.println("Exceeded limit of seeds relative to the board's size, restart the game.\n");
				validation=false;
					} else if(seedsAmount==0) {
						System.out.println("Please select a number of seeds different than 0, restart the game. \n");
						validation=false;
					  		}
		
		
		System.out.println("Please digit the amount of portals you want in the game (It can't be higher than half of "
				+ "the board's complete size\n)");
		int portalsAmount=Integer.parseInt(br.readLine());
			if(portalsAmount>(0.5*size)) {
				System.out.println("As said before, the portals can't be larger than half of the board's complete size, restart the game\n");
				validation=false;
			}
			
		System.out.println("Please type the username for player 1 (Rick)\n");
		String rick = br.readLine();
			
		System.out.println("Please type the username for player 2 (Morty)\n");
		String morty=br.readLine();
		

		manager.organizeGame(size, seedsAmount, portalsAmount, rick, morty);
		
		System.out.println("pasó por el metodo");
		System.out.println(createBoardView(columns, rows, manager));
		
		
		}while(validation);
		

	}
	
	public static  String createBoardView(int columns, int rows, BoardManager manager) {
		String boardView="";
		boolean isPar=false;
		Box current=manager.getFirstLink();
		for(int i=0; i<rows*columns;i++) {
			
			if(current.getID()%columns==0&&!isPar) {
				boardView+=current.getID()+"\n";
				isPar=true;
				
			} else if(current.getID()%columns!=0&&!isPar) {
				boardView+=current.getID()+" ";
				
			} else if(current.getID()%columns==0&&isPar){
				
				Box temp=current;
				do {
					boardView+=temp.getID()+" ";
					temp=temp.getPrevious();
				} while(temp.getID()%columns!=0);
				boardView+="\n";
				isPar=false;
			}
			current=current.getNext();
			
		}
		return boardView;
	}
	
	

}
