package printers;
import exceptions.CommandExecuteException;

public class PrinterManager {
	
	private static BoardPrinter[] availableModes = {
		new DebugPrinter(),
		new ReleasePrinter(),
	};
	
	public static BoardPrinter parsePrinter(String mode) throws CommandExecuteException{
		BoardPrinter p = null;
		for (BoardPrinter item : availableModes) {
			if(p==null)
				p = item.parse(mode);
		}
		if (p == null){
			//Es un parseo pero contenido en un execute, es entonces execute??
			throw new CommandExecuteException("Unknown print mode: " + mode);
		}
		return p;
	}
}
