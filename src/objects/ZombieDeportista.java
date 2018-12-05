package objects;

import exceptions.CommandParseException;
import objects.Zombie;

public class ZombieDeportista extends Zombie {

	public ZombieDeportista() {
		super("zombie deportista", "[Z]ombie deportista", 2, 1, 1);
	}
	public Zombie parse(String ZombieName) throws CommandParseException {
		boolean primeraletra = ZombieName.equals(this.getSymbol());
		if(!ZombieName.equals(this.getName()) && !primeraletra)
			return null;
		else return new ZombieDeportista();
	}
}
