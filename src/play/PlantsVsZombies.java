package play;

import java.util.Random;
import java.util.Scanner;

import logic.Game;
import logic.Level;

public class PlantsVsZombies {

	public static void main(String[] args) {
		long seed = (args.length > 1)?Long.parseLong(args[1]):new Random().nextInt(1000);

		Random rnd = new Random(seed);	
		Level level;

		switch(args[0].toLowerCase()) {
		case "easy" : level  = Level.EASY; break;
		case "hard" : level = Level.HARD; break;
		case "insane" : level = Level.INSANE; break;
		default: level = Level.EASY; break;
		}

		Game juego = new Game(rnd, level, seed);
		Scanner sc = new Scanner(System.in);
		Controller controlador = new Controller(juego, sc);

		
		controlador.run();

	}
	

}

