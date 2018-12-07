package printers;

import utils.MyStringUtils;
import logic.Game;

public abstract class BoardPrinter implements GamePrinter {
	final String space = " ";
	String[][] board;
	protected int boardX;
	protected int boardY;
	protected int cellSize;
	protected String symbol;
	protected String name;
	
	public BoardPrinter (String symbol, String name,int cellSize){
		this.symbol = symbol;
		this.name = name;
		this.cellSize = cellSize;
		}
	abstract void encodeGame(Game game);
	abstract String cabecera(Game game);
	abstract BoardPrinter parse(String mode);
	public String boardToString(Game game) {
		
		encodeGame(game);

		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (boardY * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
		
		str.append(lineDelimiter);
		
		for(int i=0; i< boardX; i++) {
				str.append(margin).append(vDelimiter);
				for (int j=0; j< boardY; j++) {
					str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
				str.append(lineDelimiter);
		}
		return str.toString();
	}
	
	public String printGame(Game game){
			String cabecera = cabecera(game);
			String tablero = boardToString(game);
			return cabecera+tablero;
	}
}