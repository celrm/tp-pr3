 package printers;

import logic.Game;

public class DebugPrinter extends BoardPrinter{

	public DebugPrinter() {
		super("d","debug", 25);
	}

	@Override
	public void encodeGame(Game game) {
		boardX = 1;
		boardY = game.numObjects() + 1;;
		if (game.numPlants() == 0 || game.numZombies() == 0){
			boardY = game.numObjects();
		}
		else{
			boardY = game.numObjects() + 1;
		}
		board = new String[boardX][boardY];
		int index = 0;
		for (int i = 0; i < game.numPlants(); ++i){
			board[0][index] = game.toStringDebugPlants(i);
			++index;
		}
		
		if (boardY > game.numObjects()){
			board[0][index] = "--";
			++index;
		}
		for (int i = 0; i < game.numZombies(); ++i){
			board[0][index] = game.toStringDebugZombies(i);
			++index;
		}
	}

	public String cabecera(Game game) {
		StringBuilder str = new StringBuilder();
		str.append("Number of cycles: ").append(Integer.toString(game.getCycles()));
		str.append("\nSun coins: ").append(Integer.toString(game.getSuncoins()));
		str.append("\nRemaining Zombies: ").append(game.remZombies());
		str.append("\nLevel: ").append(game.getLevelName());
		str.append("\nSeed: ").append(game.getSeed());
		str.append("\n");
		return str.toString();
	}
	
	public DebugPrinter parse(String mode) {
		if(!mode.equals(this.symbol) && !mode.equals(this.name))
			return null;

		return this;
	}

}
