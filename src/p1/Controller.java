package p1;

import java.util.Scanner;


public class Controller {
	private Game game;
	private Scanner in;
	private boolean sigoAqui; // por favor, pon un nombre más decente. he recuperado este bool para el exit
	
	public Controller(Game j, Scanner sc) {
		this.game = j;
		this.in = sc;
		this.sigoAqui = true;
	}
	
	public void run() {
		while(!this.game.playerWins() && !this.game.zombiesWin() && this.sigoAqui){
			this.game.update();
			this.game.draw();
			this.option();
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
	
	private void option(){
		System.out.print("Command > ");
		String[]words = this.in.nextLine().toLowerCase().trim().split("\\");

		switch(words[0]) {
		case "add": 
		case "a": ;
		case "reset":
		case "r": ;
		case "list":
		case "l": this.list();
		case "exit":
		case "e": this.sigoAqui = false;
		case "help":
		case "h": this.help();
		case "":;
		default: System.out.println("Wrong command.");
		}
	}
	
	private void list() {
		System.out.println("[S]unflower: Cost: 20 suncoins Harm: 0");
		System.out.println("[P]eashooter: Cost: 50 suncoins Harm: 1");
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