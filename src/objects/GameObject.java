package objects;

import logic.Game;

public abstract class GameObject {
	protected final String name;
	protected final String nameMsg;
	protected int x;
	protected int y;
	private int vida;
	protected Game game;
	private final int harm;
	protected final int nacimiento;
	protected final int speed;
	
	public GameObject(String name, String nameMsg, int x, int y, int vida, int harm, Game game, int speed) {
		this.name = name;
		this.nameMsg = nameMsg;
		this.x = x;
		this.y = y;
		this.vida = vida;
		this.harm = harm;
		this.game = game;
		this.nacimiento = game.getCiclos();
		this.speed = speed;
	}
	
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
}
