package play;

import java.util.Random;
import java.util.Scanner;

import logic.Game;
import logic.Level;

public class PlantsVsZombies {

	public static void main(String[] args) {
		try {
			if(args.length <=0 || args.length > 2)
				throw new Exception("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]");
			
			long seed = (args.length > 1)?Long.parseLong(args[1]):new Random().nextInt(1000);
			Random rnd = new Random(seed);	
			
			Level level;
			switch(args[0].toLowerCase()) {
			case "easy" : level  = Level.EASY; break;
			case "hard" : level = Level.HARD; break;
			case "insane" : level = Level.INSANE; break;
			default: throw new Exception("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: level must be one of: EASY, HARD, INSANE");
			}

			Game juego = new Game(rnd, level, seed);
			Scanner sc = new Scanner(System.in);
			Controller controlador = new Controller(juego, sc);

			controlador.run();
		}
		catch (NumberFormatException ex) {
			System.out.format("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: the seed must be a number" + " %n %n");
		}
		catch (Exception ex) {
			System.out.format(ex.getMessage() + " %n %n");
		}

	}
	

}

