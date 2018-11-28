package play;

import java.util.Scanner;

import printers.BoardPrinter;
import printers.ReleasePrinter;
import logic.Game;
import commands.Command;
import commands.CommandParser;
import exceptions.CommandExecuteException;
import exceptions.CommandParseException;

public class Controller {
	private Game game;
	private Scanner scanner;
	private boolean exit;
	private BoardPrinter gamePrinter;
	private final String unknownCommandMsg = "Unknown command";
	private final String prompt = "Command > ";
	
	public Controller(Game j, Scanner sc) {
		this.game = j;
		this.scanner = sc;
		this.exit = false;
		this.gamePrinter = new ReleasePrinter(j);
	} 
	
//	while (!game.isFinished()){
//		System.out.print(prompt);
//		String[] words = in.nextLine().trim(). split ("\\s+");
//		try {
//		Command command = CommandGenerator.parse(words);
//		if (command != null) {
//		if (command.execute(game)) printGame();
//		} else
//		System.out.println(unknownCommandMsg);
//		} catch (CommandParseException | CommandExecuteException ex) {
//		System.out.format(ex.getMessage() + " %n %n");
//		}
//		}
	
	public void run() {
		printGame();
		
		while (!game.isFinished() && !exit) {
			
			System.out.print(prompt);
			String[] words = scanner.nextLine().toLowerCase().trim().split("\\s+");
			try {
				Command command = CommandParser.parseCommand(words);

				if (command != null) {
					if (command.execute(game)) printGame();
				}
				else System.err.println(unknownCommandMsg);
			}
			catch (CommandParseException | CommandExecuteException ex) {
				System.out.format(ex.getMessage() + " %n %n");
			}
		}
		
		// Para pintar el tablero al final también
		if (!exit){
			if (game.quienGana())
				System.out.println("You win!");
			else System.out.println("Zombies win :(");
		}
	}
	
	public void setPrinter(BoardPrinter print) {
		this.gamePrinter = print;
	}
	
	public void printGame() {
		System.out.println();
		System.out.println(gamePrinter.printGame(game));
	}
	
	public void exit() {
		this.exit = true;
	}
	
}