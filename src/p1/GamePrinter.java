package p1;

import p1.Game;
import p1.MyStringUtils;

public class GamePrinter {
	int dimX; 
	int dimY;
	String[][] board;
	final String space = " ";
	
	
	public GamePrinter(Game game, int dimX, int dimY) {
		this.dimX = dimX;
		this.dimY = dimY;
		
		encodeGame(game);
	}
	
	private void encodeGame(Game game) {
 		board = new String[dimX][dimY];
		for(int i = 0; i < dimX; i++) {
			for(int j = 0; j < dimY; j++) {
				board[i][j] =  space;
				//matriz inicializada a vacíos
			}
		}
		for (int i = 0; i < game.plength(); ++i){
			if (game.p(i).vida() > 0){
				board[game.p(i).posx()][game.p(i).posy()] = "P [ " + game.p(i).vida() + " ]";
			}
		}
		for (int i = 0; i < game.slength(); ++i){
			if (game.s(i).vida() > 0){
				board[game.s(i).posx()][game.s(i).posy()] = "S [ " + game.s(i).vida() + " ]";
			}
		}
		for (int i = 0; i < game.zlength(); ++i){
			if (game.z(i).vida() > 0){
				board[game.z(i).posx()][game.z(i).posy()] = "Z [ " + game.z(i).vida() + " ]";
			}
		}
	}
	
	public String toString() {

		int cellSize = 7;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (dimY * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
		
		str.append(lineDelimiter);
		
		for(int i=0; i<dimX; i++) {
				str.append(margin).append(vDelimiter);
				for (int j=0; j<dimY; j++) {
					str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
				str.append(lineDelimiter);
		}
		return str.toString();
	}
}