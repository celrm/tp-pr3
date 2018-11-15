package objects;

import logic.Game;

public abstract class GameObject implements Cloneable {
	protected final String name;
	private final String nameMsg;
	protected int x;
	protected int y;
	private int vida;
	protected Game game;
	protected final int harm;
	protected int nacimiento;
	protected final int speed;
	
	public GameObject(String name, String nameMsg, int vida, int harm, int speed) {
		this.name = name;
		this.nameMsg = nameMsg;
		this.vida = vida;
		this.harm = harm;
		this.nacimiento = 0;
		this.speed = speed;
	}
	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public abstract String toString();
	public abstract String toStringDebug();
	
	// TODO no sé si va esto o no. no sé si protected o no
	public String getName() {
	    return this.name;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public int getHarm() {
		return harm;
	}

	public int getVida() {
		return vida;
	}

	public void update() {
		
	}

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

	public String getNameMsg() {
		return nameMsg;
	}
}
