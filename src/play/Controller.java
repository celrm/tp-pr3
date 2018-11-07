package play;

import java.util.Scanner;

import logic.Game;
import commands.Command;
import commands.CommandParser;

public class Controller {
	private Game game;
	private Scanner scanner;
	private boolean exit;
	private GamePrinter gamePrinter;
	
	private boolean noPrint;
	
	public Controller(Game j, Scanner sc) {
		this.game = j;
		this.scanner = sc;
		this.exit = false;
		this.noPrint = true;
	} 
	
	public void run() {
		while (!game.isFinished() && !exit) {
			printGame();
			noPrint = false;
			
//			System.out.print(prompt);
			String[] words = scanner.nextLine().toLowerCase().trim().split("\\s+");
			Command command = CommandParser.parseCommand(words, this);
			
			if (command != null) {
				command.execute(game, this);
			}
			
			else {
//				System.err.println(unknownCommandMsg);
				setNoPrintGameState();
			}
		}
	}
	
	public void setNoPrintGameState() {
		this.noPrint = true;
	}
	
	public void setPrinter(Mode mode) {
		this.gamePrinter = new ;
	}
	
	public void printGame() {
		if(!noPrint) {
		System.out.println(gamePrinter.printGame(game));
		}
	}
	
	public void exit() {
		this.exit = true;
	}
}