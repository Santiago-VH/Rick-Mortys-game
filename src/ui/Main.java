package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.BoardManager;
import model.Box;
import model.Board;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BoardManager manager = new BoardManager();
		Board board = new Board(0, 0, 0, 0);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		
		System.out.println("Welcome to Rick & Morty's adventure! Please digit the number of columns\n");
		int columns=Integer.parseInt(br.readLine());
		
		while(columns<0) {
				System.out.println("Don't digit negative numbers, try again!\n");
				columns=Integer.parseInt(br.readLine());
			}
		
		System.out.println("Now digit the number of rows\n");
		int rows=Integer.parseInt(br.readLine());
		
		while(rows<0) {
				System.out.println("Don't digit negative numbers, try again!\n");
				rows=Integer.parseInt(br.readLine());
			}
		
		
			int size=columns*rows;	
			
		System.out.println("Please digit the amount of seeds you want on the game\n");
		
		int seedsAmount=Integer.parseInt(br.readLine());
		
		while(seedsAmount<0||seedsAmount>size||seedsAmount==0) {
				System.out.println("An error ocurred while registering the number, try again!\n");
				seedsAmount=Integer.parseInt(br.readLine());
			}
		
		
		System.out.println("Please digit the amount of portals you want in the game (It can't be higher than half of "
				+ "the board's complete size\n)");
		
		int portalsAmount=Integer.parseInt(br.readLine());
		while(portalsAmount>0.5*size||portalsAmount<0) {
				System.out.println("An error ocurred while registering the number, try again!\n");
				portalsAmount=Integer.parseInt(br.readLine());
			}
			
		System.out.println("Please type the username for player 1 (Rick)\n");
		String rick = br.readLine();
			
		System.out.println("Please type the username for player 2 (Morty)\n");
		String morty=br.readLine();
		

		manager.organizeGame(size, seedsAmount, portalsAmount, rick, morty);
		System.out.println("Game starts!\n");
		
		boolean gameFinished=false;
		int option;
		do {
		System.out.println("It's Rick's turn! (player "+rick+"), what do you wanna do?\n"
						+ "1. Throw dice\n"
						+ "2. View board\n"
						+ "3. Show portals\n"
						+ "4. Scoreboard");
		
		option=Integer.parseInt(br.readLine());
		while(option!=1||option!=2||option!=3||option!=4) {	
			System.out.println("Please select one of the 4 options, try again!");
			option=Integer.parseInt(br.readLine());
		}
		
		do {
		switchMenu(option);
		}while(option!=1);
		
		System.out.println("You've rolled a "+board.getDiceValue()+"! Do you wanna advance or go back?\n"
						+ "1. Advance"
						+ "2. Go back");
		
		
		}while(!gameFinished);
		
		//System.out.println(createStandardBoardView(columns, rows, manager));
		

	}
	
	
	public static void switchMenu(int option) {
		
		switch(option) {
		
			case 1: throwDice();
			break;
		
			case 2: //seeStandardBoard();
			break;
		
			case 3: //seePortals();
			break;
		
			case 4: //seeLeaderboard();
			break;
		
			}
		
	}
	
	public static String createStandardBoardView(int columns, int rows, BoardManager manager) {
		String boardView="";
		boolean isPar=false;
		Box current=manager.getFirstLink();
		for(int i=0; i<rows*columns;i++) {
			

			if(current.getID()%columns==0&&!isPar) {
				boardView+="["+current.getID()+"]"+"\n";
				isPar=true;
				
			} else if(current.getID()%columns!=0&&!isPar) {
				boardView+="["+current.getID()+"] ";
				
			} else if(current.getID()%columns==0&&isPar){
				
				Box temp=current;
				do {
					boardView+="["+temp.getID()+"] ";
					temp=temp.getPrevious();
				} while(temp.getID()%columns!=0);
				boardView+="\n";
				isPar=false;
			}
			current=current.getNext();
			
		}
		return boardView;
	}
	
	public static int throwDice() {
		int diceValue=(int)(Math.random() * (6) + 1);
		return diceValue;
		
	}
	
	

}
