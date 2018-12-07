package play;

import java.util.Scanner;

import logic.Game;
import commands.Command;
import commands.CommandParser;
import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.FileContentsException;

public class Controller {
	private Game game;
	private Scanner scanner;
	private final String unknownCommandMsg = "Unknown command. Use ’help’ to see the available commands";
	private final String prompt = "Command > ";
	
	public Controller(Game j, Scanner sc) {
		this.game = j;
		this.scanner = sc;
	}
	
	public void run() {
		printGame();		
		while (!game.isFinished()) {			
			System.out.print(prompt);
			String[] words = scanner.nextLine().trim().split("\\s+");
			try {
				Command command = CommandParser.parseCommand(words);

				if (command != null) {
					if (command.execute(game)) printGame();
				} else
					System.out.println(unknownCommandMsg);
			}
			catch (CommandParseException | CommandExecuteException | FileContentsException ex) {
				System.out.format(ex.getMessage() + " %n %n");
			}
		}
	}
	
	public void printGame() {
		System.out.println();
		System.out.println(game);
	}	
}