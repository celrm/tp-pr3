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
	private boolean exit;
	private final String unknownCommandMsg = "Unknown command. Use ’help’ to see the available commands";
	private final String prompt = "Command > ";
	
	public Controller(Game j, Scanner sc) {
		this.game = j;
		this.scanner = sc;
		this.exit = false;
	}
	
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
				else System.out.println(unknownCommandMsg);
			}
			catch (CommandParseException | CommandExecuteException | FileContentsException ex) {
				System.out.format(ex.getMessage() + " %n %n");
			}
		}
		
		// Para pintar el tablero al final también
		if (!exit){
			if (game.quienGana())
				System.out.println("You win!");
			else System.out.println("Zombies win :(");
		}
		else System.out.println("****** Game over!: User exit ******");
	}
	

	public void printGame() {
		System.out.println();
		System.out.println(game);
	}
	
	public void exit() {
		this.exit = true;
	}
	
}