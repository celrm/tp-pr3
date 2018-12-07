package objects;

import exceptions.CommandParseException;
import objects.Zombie;

public class ZombieComun extends Zombie {

	public ZombieComun() {
		super("zombie comun","[Z]ombie comun", 5, 1, 2, "z");
	}
	public Zombie parse(String ZombieName) throws CommandParseException  {
		if(!ZombieName.equals(this.getIdent()))
			return null;
		else return new ZombieComun();
	}
}
