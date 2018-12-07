package objects;

import java.io.BufferedWriter;
import java.io.IOException;

import exceptions.CommandParseException;
import logic.Game;

public abstract class GameObject implements Cloneable {
	private final String symbol;
	private final String name;
	private final String nameMsg;
	private int vida;
	protected final int harm;
	protected final int speed;
	protected int x;
	protected int y;
	protected Game game;
	protected int nacimiento;
	
	public GameObject(String symbol, String name, String nameMsg, int vida, int harm, int speed) {
		this.symbol = symbol;
		this.name = name;
		this.nameMsg = nameMsg;
		this.vida = vida;
		this.harm = harm;
		this.speed = speed;
		this.nacimiento = 0;
	}
	
	public abstract String listMsg(StringBuilder sol);
	
	public String getSymbol() {
		return symbol;
	}

	public String getName() {
	    return this.name;
	}

	public String getNameMsg() {
		return nameMsg;
	}

	public int getVida() {
		return vida;
	}

	public int getHarm() {
		return harm;
	}

	public int getSpeed() {
		return speed;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public abstract void update();

	public void danar(int cant) {
		this.vida -=cant;		
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void setGame(Game game) {
		this.game = game;
		this.nacimiento = game.getCiclos();
	}
	
	public String toString() {
		return this.getSymbol().toUpperCase() + " [ " + this.getVida() + " ]";
	}
	
	public String toStringDebug() {
		StringBuilder str = new StringBuilder();
		str.append(this.getSymbol().toUpperCase()).append("[l:").append(this.getVida());
		str.append(",x:").append(this.x);
		str.append(",y:").append(this.y);
		if(speed != 0)
			str.append(",t:").append(speed - ((this.game.getCiclos() - this.nacimiento) % speed));
		else str.append(",t:0");
		str.append("]");
		return str.toString();
	}
	
	protected boolean toca() {
		return this.nacimiento % this.speed == this.game.getCiclos() % this.speed;
	}
	protected boolean noPrimerCiclo() {
		return this.nacimiento != this.game.getCiclos();
	}
	
	public abstract GameObject parse(String ObjectName) throws CommandParseException;
	
	public void store (BufferedWriter outStream) throws IOException{
		outStream.write(this.symbol);
		outStream.write(":");
		outStream.write(Integer.toString(this.vida));
		outStream.write(":");
		outStream.write(Integer.toString(this.x));
		outStream.write(":");
		outStream.write(Integer.toString(this.y));
		outStream.write(":");
		outStream.write(Integer.toString(speed - ((this.game.getCiclos() - this.nacimiento) % speed)));
	}
}
