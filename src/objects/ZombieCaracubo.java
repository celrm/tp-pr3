package objects;

import exceptions.CommandParseException;
import objects.Zombie;

public class ZombieCaracubo extends Zombie {
	
	public ZombieCaracubo() {
		super("zombie caracubo", "[Z]ombie caracubo", 8, 1, 4, "a");
	}
	public Zombie parse(String ZombieName) throws CommandParseException  {
		if(!ZombieName.equals(this.getIdent()))
			return null;
		else return new ZombieCaracubo();
	}
}
