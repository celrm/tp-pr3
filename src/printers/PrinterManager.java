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


	

	
	// Lo usa HelpCommand
	public static String commandHelp() {
		StringBuilder strb = new StringBuilder();
		for (Command item : availableCommands) {
			strb.append(item.helpText() + "\n");
		}
		String str = strb.toString();		
		return str;
	}
}
