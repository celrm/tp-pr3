package objects;

import exceptions.CommandParseException;
import objects.Zombie;

public class ZombieCaracubo extends Zombie {

	public ZombieCaracubo() {
		super("zombie caracubo", "[Z]ombie caracubo", 8, 1, 4);
	}
	public Zombie parse(String ZombieName) throws CommandParseException {
		boolean primeraletra = ZombieName.equals(this.getSymbol());
		if(!ZombieName.equals(this.getName()) && !primeraletra)
			return null;
		else return new ZombieCaracubo();
	}
}
