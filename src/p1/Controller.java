package p1;

import java.util.Scanner;


public class Controller {
	private Game game;
	private Scanner in;
	
	public Controller(Game j, Scanner sc) {
		this.game = j;
		this.in = sc;

	}
	
	public void run() {
		while(!this.game.playerWins() || !this.game.zombiesWin()){
			this.game.update();
			this.game.draw();
			this.game.command();
			this.game.computer();
		}
		
		if (this.game.playerWins()) {
		    System.out.println("Player wins!");
		}
		else if (this.game.zombiesWin()) {
		    System.out.println("Zombies win :(");
		}
		else {
		    System.out.println("Chao");
		}
	}
	
	public void option(){
		System.out.print("Command > ");
		String[]words = this.in.nextLine().toLowerCase().trim().split("\\");

		switch(words[0]) {
		case "add": 
		case "reset":
		case "list":
		case "exit":
		case "help": this.help();
		}
	}
	
	private void help(){
		System.out.println("Add <plant> <x> <y>: Adds a plant in position x, y.");
		System.out.println("List: Prints the list of available plants.");
		System.out.println("Reset: Starts a new game.");
		System.out.println("Help: Prints this help message.");
		System.out.println("Exit: Terminates the program.");
		System.out.println("[none]: Skips cycle.");
	}

}