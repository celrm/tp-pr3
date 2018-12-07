
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
		boolean valido = MyStringUtils.isValidFilename(fileName) && MyStringUtils.fileExists(fileName) && MyStringUtils.isReadable(fileName);
		if (!valido)
			throw new CommandExecuteException("File not found");
		try (BufferedReader outStream = new BufferedReader(new FileReader(fileName))) {
			game.load(outStream);
			System.out.println("Game successfully loaded from file " + this.fileName + ">.dat");
		} catch (IOException e) {
			throw new FileContentsException("Error de E/S : " + e);
		}return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		boolean primerasletras = commandWords[0].equals(this.commandText.substring(0, 2));
		if (!commandWords[0].equals(this.commandText) &&  !primerasletras ){
			return null;
		}
		if (commandWords.length != 2){
			throw new CommandParseException("Incorrect number of arguments for " + this.commandText + " command: " + this.commandTextMsg);
		}
		fileName = commandWords[1];
		return this;
	}

}