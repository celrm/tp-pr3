package objects;

import exceptions.CommandParseException;
import objects.Zombie;

public class ZombieComun extends Zombie {

	public ZombieComun() {
		super("zombie comun","[Z]ombie comun", 5, 1, 2);
	}
	public Zombie parse(String ZombieName) throws CommandParseException {
		boolean primeraletra = ZombieName.equals(this.getSymbol());
		if(!ZombieName.equals(this.getName()) && !primeraletra)
			return null;
		else return new ZombieComun();
	}
}
