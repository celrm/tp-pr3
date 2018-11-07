package commands;

//ESTE COMANDO SE AÑADORIA PARA DEPURAR
import logic.Game;
import play.Controller;
import objects.Zombie;
import factories.ZombieFactory;

public class AddZombie extends Command {
	private String zombieName;
	private int x;
	private int y;

	public AddZombie(String zombie, int x, int y) {
		super("zombie", "[Z]ombie <zombie> <x> <y>", "Adds a zombie in position x, y.");
		this.zombieName = zombie;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void execute(Game game, Controller controller) {
		Zombie zombie = ZombieFactory.getZombie(zombieName, x, y);
		boolean executed = false;
		if (zombie != null){
			executed = game.addZombieToGame(zombie, x, y);
		}
		else System.out.println("Zombie doesn't exist");
		if(!executed)
			controller.setNoPrintGameState();
	}
	

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		// TODO preguntar por la primera letra
		if(commandWords[0] != this.commandText || commandWords[0] != this.commandText.split("\\s+")[0])
			return null;
		
		// TODO aquí va a sacar wrong command también
		if (commandWords.length != 4) {
			System.out.println("Wrong parameters.");
			return null;
		}

		int x = Integer.parseInt(commandWords[2]);
		int y = Integer.parseInt(commandWords[3]);

		if(x<0 || y<0 || x>=Game.DIMX || y>=Game.DIMY) {
			System.out.println("Wrong position.");
			return null;
		}

		Command com = new AddZombie(commandWords[1], x, y);
		return com;
	}
}