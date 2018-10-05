package p1;

import java.util.Scanner;


public class Controller {
	private Game game;
	private Scanner in;
	private boolean si;
	
	public Controller(Game j, Scanner sc) {
		this.game = j;
		this.in = sc;
		this.si = true;

	}
	
	public void run() {
		while(this.si){
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