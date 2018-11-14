package printers;

import logic.Game;

public abstract class BoardPrinter {
	String[][] board;
	final String space = " ";

	abstract void encodeGame(Game game);

	public String boardToString(Game game) {
		
		encodeGame(game);

		int cellSize = 7;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (Game.DIMY * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
		
		str.append(lineDelimiter);
		
		for(int i=0; i<Game.DIMX; i++) {
				str.append(margin).append(vDelimiter);
				for (int j=0; j<Game.DIMY; j++) {
					str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
				str.append(lineDelimiter);
		}
		return str.toString();
	}
}