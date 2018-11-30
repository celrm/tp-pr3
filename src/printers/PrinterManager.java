package printers;
import exceptions.CommandParseException;

public class PrinterManager {
	
	private static BoardPrinter[] availableModes = {
		new DebugPrinter(),
		new ReleasePrinter(),
	};
	
	public static BoardPrinter parsePrinter(String mode) {
		BoardPrinter p = null;
		for (BoardPrinter item : availableModes) {
			if(p==null)
				p = item.parse(mode);
		}
			
		return p;
	}
}
