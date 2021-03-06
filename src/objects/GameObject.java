package objects;

import java.io.BufferedWriter;
import java.io.IOException;

import logic.Game;
import exceptions.CommandParseException;
import exceptions.FileContentsException;

public abstract class GameObject {
	private final String symbol;
	private final String name;
	private final String nameMsg;
	protected int vida;
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
	}

	public void setBirth(int cycles) {
		this.nacimiento = cycles;	
	}
	
	public String toString() {
		return this.getSymbol().toUpperCase() + " [ " + this.getVida() + " ]";
	}
	
	public String toStringDebug() {
		StringBuilder str = new StringBuilder();
		str.append(this.getSymbol().toUpperCase()).append("[l:").append(this.getVida());
		str.append(",x:").append(this.x);
		str.append(",y:").append(this.y);
		str.append(",t:").append((speed == 0)? 0 :speed - ((this.game.getCycles() - this.nacimiento) % speed));
		str.append("]");
		return str.toString();
	}
	
	protected boolean toca() {
		return this.nacimiento % this.speed == this.game.getCycles() % this.speed;
	}
	protected boolean noPrimerCiclo() {
		return this.nacimiento != this.game.getCycles();
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
		outStream.write(Integer.toString((speed == 0)? 0 :speed - ((this.game.getCycles() - this.nacimiento) % speed)));
	}

	public void setAttributes(int vida, int x, int y, int t) throws FileContentsException {
		if (vida < 0)
			throw new FileContentsException("negative life");
		if (vida > this.vida)
			throw new FileContentsException("too much life");
		this.vida = vida;
		
		if(x<0 || y<0 || x>=Game.DIMX || y>=Game.DIMY)
			throw new FileContentsException("out of range position");
		this.x = x;
		this.y = y;
		
		if (t < 0)
			throw new FileContentsException("negative object cycle");
		this.nacimiento = this.speed - t;
		if (nacimiento < 0)
			throw new FileContentsException("faster speed than cycle");
		
	}
}
