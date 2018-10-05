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
	

	public void help(){
		
	}
	
	public void option(){
		System.out.print("Command > ");
		String[]words = this.in.nextLine().toLowerCase().trim().split("\\");
	}

}