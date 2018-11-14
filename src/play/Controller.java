package play;

import java.util.Scanner;

import printers.BoardPrinter;
import printers.ReleasePrinter;
import logic.Game;
import commands.Command;
import commands.CommandParser;

public class Controller {
	private Game game;
	private Scanner scanner;
	private boolean exit;
	private BoardPrinter gamePrinter;
	private final String unknownCommandMsg = "Unknown command";
	private final String prompt = "Command > ";
	private boolean dontPrint;
	
	public Controller(Game j, Scanner sc) {
		this.game = j;
		this.scanner = sc;
		this.exit = false;
		this.dontPrint = false;
		this.gamePrinter = new ReleasePrinter(j);
	} 
	
	public void run() {
		while (!game.isFinished() && !exit) {
			printGame();
			dontPrint = false;
			
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
	
	public void setNoPrintGameState() {
		this.dontPrint = true;
	}
	
	public void setPrinter(BoardPrinter print) {
		this.gamePrinter = print;
	}
	
	public void printGame() {
		if(!dontPrint) {
		System.out.println();
		System.out.println(gamePrinter.printGame(game));
		}
	}
	
	public void exit() {
		this.exit = true;
	}
	
}