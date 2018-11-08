package objects;

import logic.Game;

public abstract class GameObject {
	protected final String name;
	protected final String nameMsg;
	private int x;
	private int y;
	private int vida;
	private final int harm;
	private Game game;
	
	public GameObject(String name, String nameMsg, int x, int y, int vida, int harm, Game game) {
		this.name = name;
		this.nameMsg = nameMsg;
		this.x = x;
		this.y = y;
		this.vida = vida;
		this.harm = harm;
		this.game = game;
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
		// TODO Auto-generated method stub
		
	}

	public void danar(int cant) {
		// TODO Auto-generated method stub
		
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
}
