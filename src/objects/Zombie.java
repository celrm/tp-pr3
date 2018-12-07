package objects;

import java.io.BufferedWriter;
import java.io.IOException;

import exceptions.CommandParseException;

public abstract class Zombie extends GameObject {
	private String ident;
	public Zombie(String name, String nameMsg, int vida, int harm, int speed, String ident) {
	    super("z", name, nameMsg, vida, harm, speed);
		this.ident = ident;
	}
	
	public void update() {
		boolean haAtacado = this.game.zombieAction(this.harm, this.x, this.y);
		if (!haAtacado && !this.game.hayZombie(x, y-1) && noPrimerCiclo() && toca())
			--this.y;
	}
	
	public String listMsg(StringBuilder sol){
		sol.append(this.getNameMsg()).append(":");
		sol.append(" Speed: ").append(Integer.toString(this.getSpeed()));
		sol.append(" Harm: ").append(Integer.toString(this.getHarm())); 
		sol.append(" Life: ").append(Integer.toString(this.getVida())).append("\n");
		return sol.toString();
	}
	public abstract Zombie parse(String ZombieName);
	
	public void store (BufferedWriter outStream) throws IOException{
		outStream.write(ident);
		outStream.write(":");
		outStream.write(Integer.toString(super.getVida()));
		outStream.write(":");
		outStream.write(Integer.toString(this.x));
		outStream.write(":");
		outStream.write(Integer.toString(this.y));
		outStream.write(":");
		outStream.write(Integer.toString(speed - ((this.game.getCiclos() - this.nacimiento) % speed)));
	}
	public String getIdent() {
		return this.ident;
	}
}
