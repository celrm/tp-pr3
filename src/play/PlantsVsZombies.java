package play;

import java.util.Random;
import java.util.Scanner;

import logic.Game;
import logic.Level;

public class PlantsVsZombies {
	
	private static String constantInfo = "Usage: plantsVsZombies ";

	public static void main(String[] args) {
		try {
			if(args.length <=0 || args.length > 2)
				throw new Exception(constantInfo + Level.all("|")+" [seed]");
			
			long seed = (args.length > 1) ? Long.parseLong(args[1]) : new Random().nextInt(1000);
			Random rnd = new Random(seed);
			
			Level level = Level.parse(args[0]);
			if(level == null)
				throw new Exception(": level must be one of: " + Level.all(", "));
			
			
			Game juego = new Game(rnd, level, seed);
			Scanner sc = new Scanner(System.in);
			Controller controlador = new Controller(juego, sc);

			controlador.run();
		}
		catch (RuntimeException ex) {
			System.out.format(constantInfo + Level.all("|")+" [seed]: the seed must be a number" + " %n %n");
		}
		catch (Exception ex) {
			System.out.format(ex.getMessage() + " %n %n");
		}

	}
	

}

