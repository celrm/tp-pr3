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
			this.game.nextCycle();
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
		String[]words = this.in.nextLine().toLowerCase().trim().split(" ");

		switch(words[0]) {
		case "add":
		case "a": {
			if (words.length == 4) {
				int x = Integer.parseInt(words[2]);
				int y = Integer.parseInt(words[3]);		
				if(x>=0 && y>=0 && x<4 && y<8)
					this.game.addPlant(words[1], x, y);
				else System.out.println("Wrong position.");
			
			}
			else System.out.println("Wrong parameters.");
		} break;
		case "reset":
		case "r": 
			this.game = new Game(this.game.getRand(), this.game.getLevel()); break;
		case "list":
		case "l": this.list(); break;
		case "exit":
		case "e": this.sigoAqui = false; break;
		case "help":
		case "h": this.help(); break;
		case "":; break;
		default: System.out.println("Wrong command."); break;
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