
package commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import utils.MyStringUtils;
import logic.Game;
import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.FileContentsException;

public class LoadCommand extends Command {
	private String fileName;
	public LoadCommand() {
		super("load", "[Lo]ad <fileName>", "Load the state of the game from a file.");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException, FileContentsException {
		String nombre = this.fileName + ".dat";
		boolean valido = MyStringUtils.isValidFilename(nombre) && MyStringUtils.fileExists(nombre) && MyStringUtils.isReadable(nombre);
		if (!valido)
			throw new CommandExecuteException("File not found");
		try (BufferedReader inStream = new BufferedReader(new FileReader(nombre))) {
			String line = inStream.readLine().trim();
			if ( !line.equals("Plants Vs Zombies v3.0") )
				throw new FileContentsException("missing Plants Vs Zombies v3.0");
			line = inStream.readLine();
			
			game.load(inStream);
			System.out.println("Game successfully loaded from file " + this.fileName + ".dat");
		} catch (IOException | FileContentsException e) {
			throw new FileContentsException("I/O error: " + e.getMessage());
		}
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(!word(commandWords[0].toLowerCase(),2))
			return null;
		
		numParameters(commandWords.length, 2);
		
		fileName = commandWords[1];
		return this;
	}
}