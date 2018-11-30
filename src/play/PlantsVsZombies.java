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
				throw new Exception(constantInfo + Level.all("|")+" [seed]: level must be one of: " + Level.all(", "));
			
			System.out.println("Welcome to plantsVsZombies v3.0");
			System.out.println("Random seed used: " + seed);
			
			Game juego = new Game(rnd, level, seed);
			Scanner sc = new Scanner(System.in);
			Controller controlador = new Controller(juego, sc);

			controlador.run();
		}
		catch (NumberFormatException ex) {
			System.out.format(constantInfo + Level.all("|")+" [seed]: the seed must be a number" + " %n %n");
		}
		catch (Exception ex) {
			System.out.format(ex.getMessage() + " %n %n");
		}

	}
	
//Ya que estamos, se podría también crear una clase PrinterManager
//	para hacer el papel de la clase CommandParser y utilizar este mismo mecanis-
//	mo para gestionar los distintos tipos de printer, lo que implicaría tener un método
//	parse en los printers.
}

