package objects;

import exceptions.CommandParseException;
import objects.Zombie;

public class ZombieDeportista extends Zombie {

	public ZombieDeportista() {
		super("zombie deportista", "[Z]ombie deportista", 2, 1, 1, "d");
	}
	public Zombie parse(String ZombieName) throws CommandParseException  {
		if(!ZombieName.equals(this.getIdent()))
			return null;
		else return new ZombieDeportista();
	}
}
