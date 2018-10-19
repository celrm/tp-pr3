package p1;

import java.util.Scanner;

public class Controller {
	private Game game;
	private Scanner in;
	private boolean exit;	
	private boolean firstCycle;
	
	public Controller(Game j, Scanner sc) {
		this.game = j;
		this.in = sc;
		this.exit = false;
		this.firstCycle = true;
	}
	
	public void run() {
		boolean hasAnythingChanged = true;
		while(!this.game.playerWins() && !this.game.zombiesWin() && !this.exit){
			if(this.firstCycle) {
				this.game.draw();
				this.firstCycle = false;
			}
			
			hasAnythingChanged = this.user();
			
			if (hasAnythingChanged && !this.firstCycle) {
				this.game.update();
				this.game.draw();
			}
		}

	    System.out.println("Game over");
	    
		if (this.game.playerWins())
		    System.out.println("Player wins!");
		
		else if (this.game.zombiesWin())
		    System.out.println("Zombies win :(");
	
		else
		    System.out.println("Chao");
	}
	
	private boolean user(){
		boolean sol = false;
		System.out.print("Command > ");
		String[] words = this.in.nextLine().toLowerCase().trim().split(" ");

		switch(words[0]) {
		case "add":
		case "a": sol = this.add(words); break;
		case "reset":
		case "r": {
			this.game.reset();
			this.firstCycle = true;
			sol = true;
		} break;
		case "list":
		case "l": this.list(); break;
		case "exit":
		case "e": this.exit = true; break;
		case "help":
		case "h": this.help(); break;
		case "": sol = true; break;
		default: System.out.println("Wrong command."); break;
		}
		return sol;
	}
	
	private boolean add(String[] words) {
		boolean sol = false;
		
		if (words.length != 4) System.out.println("Wrong parameters.");
		else {
			int x = Integer.parseInt(words[2]);
			int y = Integer.parseInt(words[3]);
			
			if(x<0 || y<0 || x>=Game.DIMX || y>=Game.DIMY) System.out.println("Wrong position.");

			else sol = this.game.addPlant(words[1], x, y);
		}
		return sol;
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