package p1;

import java.util.Random;

public class Game {	
	public static final int DIMX = 4;
	public static final int DIMY = 8;

	private Random rand;
	private Level level;
	
	private SunflowerList sunflowerList;
	private PeashooterList peashooterList;
	private ZombieList zombieList;
	
	private int ciclos;
	private SuncoinManager soles;
	
	private ZombieManager zManager;
	
    public Game(Random rand, Level n) {
		this.rand = rand;
		this.level = n;
		
		this.sunflowerList = new SunflowerList();
		this.peashooterList = new PeashooterList();
		this.zombieList = new ZombieList();
		
		this.zManager = new ZombieManager(this.level, this.rand);
		
		this.ciclos = 0;
		this.soles = new SuncoinManager();
	}
    
  	public boolean playerWins() {
  	    return (this.zManager.numZombies() == 0) && this.zombieList.todosMuertos();
  	}

  	public boolean zombiesWin() {
  	    boolean sol = false;
  	    
  	  for (int j = 0; j < Game.DIMX && !sol; ++j) {
  		  sol = hayZombie(j,0);
  	  }
  	  return sol;
  	}
  	
  	public void update() {		
		this.sunflowerList.update();
		this.peashooterList.update();
		this.zombieList.update();
		
		this.computer();
		
		this.ciclos++;
	}
  	
  	public void draw(){
		System.out.println("Number of cycles: " + Integer.toString(this.ciclos));
		System.out.println("Sun coins: " + Integer.toString(this.soles.num()));
		System.out.println("Remaining Zombies: " + Integer.toString(this.zManager.numZombies()));

		GamePrinter print = new GamePrinter(this, Game.DIMX, Game.DIMY);		
		System.out.println(print.toString());
	}
  	
  	public void sunflowerAction() {
  		this.soles.add(Sunflower.PRODUCE_SOLES);
  	}
  	
  	public void peashooterAction(int x, int y) {
  		boolean found = false;
		for (int j = y+1; j < Game.DIMY && !found; ++j) {
			if (hayZombie(x, j)) {
	  			this.zombieList.danar(x,j,Peashooter.HARM);
	  			found = true;
			}
		}
  	}
  	
  	public boolean zombieAction(int x, int y) {
  		boolean sol;
  		if(this.sunflowerList.hay(x,y-1)) {
  			this.sunflowerList.danar(x,y-1,Zombie.HARM);
  			sol = true;
  		}
  		else if(this.peashooterList.hay(x,y-1)) {
  			this.peashooterList.danar(x,y-1,Zombie.HARM);
			sol = true;
		}
  		else sol = false;
  		return sol;
  	}  
  	
  	//Lo necesitamos para que no se pisen los zombies
  	public boolean hayZombie(int x, int y) {
  		return this.zombieList.hay(x,y);
  	}
	
	//compramos una planta
	public boolean add(String[] words) {
		boolean sol = false;
		
		if (words.length != 4) System.out.println("Wrong parameters.");
		else {
			int x = Integer.parseInt(words[2]);
			int y = Integer.parseInt(words[3]);
			
			if(x<0 || y<0 || x>=Game.DIMX || y>=Game.DIMY) System.out.println("Wrong position.");
				
			else if(this.hayCosas(x,y)) System.out.println("There's already something there.");
			else {
				switch(words[1]) {
				case "sunflower":
				case "s": {
					if (this.soles.num() >= Sunflower.COSTE) {
						this.sunflowerList.add(x, y, this);
						this.soles.add(-Sunflower.COSTE);
						sol = true;
					}
					else System.out.println("Not enough cash.");
				} break;
				case "peashooter":
				case "p": {
					if (this.soles.num() >= Peashooter.COSTE) {
						this.peashooterList.add(x, y, this);
						this.soles.add(-Peashooter.COSTE);
						sol = true;
					}
					else System.out.println("Not enough cash.");
				} break;
				default: System.out.println("Wrong plant."); break;
				}
			}		
		}
		return sol;
	}
			
	public void reset() {
		this.sunflowerList = new SunflowerList();
		this.peashooterList = new PeashooterList();
		this.zombieList = new ZombieList();
		
		this.zManager = new ZombieManager(this.level, this.rand);
		
		this.ciclos = 0;
		this.soles = new SuncoinManager();		
	}

	//usado para saber frecuencia de soles y al avanzar un zombie
	public int getCiclos(){
		return this.ciclos;
	}
	
	public String toString(int x, int y) {
		String sol = "";
		if(this.sunflowerList.hay(x, y))
			sol = this.sunflowerList.toString(x,y);
		else if(this.peashooterList.hay(x, y))
			sol = this.peashooterList.toString(x,y);
		else //if(this.zombieList.hay(x, y))
			sol = this.zombieList.toString(x,y);
		return sol;
	}
	
	//se añaden zombies
	private void computer() {
		boolean posible = false;
		
		//si hay algún hueco en la columna DIMY - 1
		for (int i = 0; i < Game.DIMX && !posible; ++i) {
			posible = !this.hayCosas(i, Game.DIMY - 1);
		}
		
		//y si toca que salga en este ciclo
		if (posible && this.zManager.isZombieAdded()) {
			//aleatoriamente busco una fila libre
			int x;
			do x = Math.abs(this.rand.nextInt() % Game.DIMX);
			while (this.hayCosas(x, Game.DIMY-1));
			
			//y añado al zombie
			this.zombieList.add(x, Game.DIMY-1, this);
	    }
	}
	
	//función auxiliar para saber si un hueco está libre
	public boolean hayCosas(int x, int y) {
		return (this.sunflowerList.hay(x, y) || this.peashooterList.hay(x, y) || this.hayZombie(x, y));
	}
}