package p2;

import java.util.Scanner;

public class Controller {
	private Game game;
	private Scanner scanner;
	private boolean exit;	
//	private boolean firstCycle;
	
	private boolean noPrint;
	
	public Controller(Game j, Scanner sc) {
		this.game = j;
		this.in = sc;
		this.exit = false;
		this.firstCycle = true;
	}
	
	public void run() {
		while (!game.isFinished() && !exit) {
			printGame();
			noPrint = false;
			
			System.out.print(prompt);
			String[] words = scanner.nextLine().toLowerCase().trim().split("\\s+");
			Command command = CommandParser.parseCommand(words, this);
			
			if (command != null) {
				command.execute(game, this);
			}
			else {
				System.err.println(unknownCommandMsg);
				setNoPrintGameState();
			}
		}
	}
	setNoPrintGameState();
	
	private boolean add(String[] words) {
		
	}
	
	private void list() {
		System.out.println("[S]unflower: Cost: " + Integer.toString(Sunflower.COSTE) + " suncoins Harm: " + Integer.toString(Sunflower.HARM));
		System.out.println("[P]eashooter: Cost: " + Integer.toString(Peashooter.COSTE) + " suncoins Harm: " + Integer.toString(Peashooter.HARM));
	}
	 
	private void help() {
		System.out.println("Add <plant> <x> <y>: Adds a plant in position x, y.");
		System.out.println("List: Prints the list of available plants.");
		System.out.println("Reset: Starts a new game.");
		System.out.println("Help: Prints this help message.");
		System.out.println("Exit: Terminates the program.");
		System.out.println("[none]: Skips cycle.");
	}

}