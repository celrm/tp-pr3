package objects;

public abstract class GameObject {
	protected final String name;
	private final int x;
	private final int y;
	
	public GameObject(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	// TODO no sé si va esto o no. no sé si protected o no
	public String getName() {
	    return this.name;
	} 
}
