package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.BoardManager;

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
		
		System.out.println("Please digit the amount of seeds you want on the game\n");
		int seedsAmount=Integer.parseInt(br.readLine());
		
			if(seedsAmount<0) {
				System.out.println("Don't digit negative numbers, restart the game.\n");
				validation=false;
			} else if(seedsAmount>(columns*rows)) {
				System.out.println("Exceeded limit of seeds relative to the board's size, restart the game.\n");
				validation=false;
					} else if(seedsAmount==0) {
						System.out.println("Please select a number of seeds different than 0, restart the game. \n");
						validation=false;
					  		}
		
		
		System.out.println("Please digit the amount of portals you want in the game (It can't be higher than half of "
				+ "the board's complete size\n)");
		int portalsAmount=Integer.parseInt(br.readLine());
		
			if(portalsAmount>(0.5*(columns * rows))) {
				System.out.println("As said before, the portals can't be larger than half of the board's complete size, restart the game\n");
				validation=false;
			}
		
		manager.createBoxes(columns, rows, seedsAmount, portalsAmount);
		}while(validation==true);
			
			
			
		
	}

}
