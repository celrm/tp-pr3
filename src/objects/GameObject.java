package objects;

public abstract class GameObject {
	protected final String name;
	private final int x;
	private final int y;
	private final int vida;
	private final int harm;
	
	public GameObject(String name, int x, int y, int vida, int harm) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.vida = vida;
		this.harm = harm;
	}
	
	// TODO no sé si va esto o no. no sé si protected o no
	public String getName() {
	    return this.name;
	}

	public int x() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int y() {
		// TODO Auto-generated method stub
		return 0;
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
}
