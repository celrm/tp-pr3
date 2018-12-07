
package commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import utils.MyStringUtils;
import logic.Game;
import exceptions.CommandExecuteException;
import exceptions.CommandParseException;

public class SaveCommand extends Command {
	private String fileName;
	public SaveCommand() {
		super("save", "save <fileName>", "saves the actual state of the game in fileName");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		String nombre = this.fileName + ".dat";
		boolean valido = MyStringUtils.isValidFilename(nombre);
		if (!valido){
			throw new CommandExecuteException("Invalid filename");
		}
		try (BufferedWriter outStream = new BufferedWriter(new FileWriter(nombre))) {
			outStream.write("Plants Vs Zombies v3.0");
			outStream.newLine();
			outStream.newLine();
			game.store(outStream);
			System.out.println("Game successfully saved in file <" + this.fileName + ">.dat. Use the load command to reload it");

		} catch (IOException e) {
			System.out.println("Error de E/S : " + e);
		}
		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		boolean primeraletra = commandWords[0].equals(this.commandText.substring(0, 1));
		if (!commandWords[0].equals(this.commandText) &&  !primeraletra ){
			return null;
		}
		if (commandWords.length != 2){
			throw new CommandParseException("Incorrect number of arguments for " + this.commandText + " command: " + this.commandTextMsg);
		}
		fileName = commandWords[1];
		return this;
	}

}