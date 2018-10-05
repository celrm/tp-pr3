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
		while(this.game.isFinished){
			this.game.update();
			this.game.draw();
			this.game.command();
			this.game.computer();
		}
	}
	

	public void help(){
		
	}
	
	public void option(){
		System.out.print("Command > ");
		String[]words = this.in.nextLine().toLowerCase().trim().split("\\");
	}

}