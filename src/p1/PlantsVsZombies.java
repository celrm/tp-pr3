package p1;

import java.util.Random;

public class PlantsVsZombies {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long seed = (args.length > 1?Long.parseLong(args[1]):new Random().nextInt(1000));

		Random rnd = new Random(seed);

		Level level;

		switch(args[0].toLowerCase()) {
		case "easy" : level = Level.EASY;
		case "hard" : level = Level.HARD;
		case "insane" : level = Level.INSANE;
		default: level = Level.EASY;
		}




	}

}
