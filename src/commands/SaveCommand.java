
package commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import utils.MyStringUtils;
import logic.Game;
import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.FileContentsException;

public class SaveCommand extends Command {
	private String fileName;
	public SaveCommand() {
		super("save", "[S]ave <fileName>", "Save the state of the game to a file.");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException,FileContentsException {
		String nombre = this.fileName + ".dat";
		boolean valido = MyStringUtils.isValidFilename(nombre);
		if (!valido)
			throw new CommandExecuteException("Invalid filename: the filename contains invalid characters");
		try (BufferedWriter outStream = new BufferedWriter(new FileWriter(nombre))) {
			game.store(outStream);
			System.out.println("Game successfully saved in file " + this.fileName + ".dat. Use the load command to reload it");
		} catch (IOException e) {
			throw new FileContentsException("Error de E/S : " + e);
		}
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(!word(commandWords[0].toLowerCase(),1))
			return null;
		
		if (commandWords.length != 2){
			throw new CommandParseException("Incorrect number of arguments for " + this.commandText + " command: " + this.commandTextMsg);
		}
		fileName = commandWords[1];
		return this;
	}

}