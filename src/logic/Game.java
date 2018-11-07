package logic;

import java.util.Random;

import factories.ZombieFactory;
import objects.Plant;
import objects.Sunflower;
import objects.Zombie;
import printers.GamePrinter;
import lists.ObjectList;


public class Game {
	public static final int DIMX = 4;
	public static final int DIMY = 8;

	private Random rand;
	private Level level;

	private ObjectList plantList;
	private ObjectList zombieList;

	private int ciclos;
	private SuncoinManager soles;
	private ZombieManager zManager;

	public Game(Random rand, Level n) {
		this.rand = rand;
		this.level = n;
		
		this.plantList = new ObjectList();
		this.zombieList = new ObjectList();
		
		this.zManager = new ZombieManager(this.level, this.rand);
		
		this.ciclos = 0;
		this.soles = new SuncoinManager();
		
	}
	
	// Lo llama addC
	public boolean addPlantToGame(Plant plant, int x, int y) {
		boolean sol = false;
		if(this.hayCosas(x,y))
			System.out.println("There's already something there.");
		
		else if (this.soles.num() >= plant.getCost()) {
			
			this.plantList.add(plant);
			this.soles.add(-plant.getCost());
			sol = true;
		}
		
		else System.out.println("Not enough cash.");
		return sol;
	}
	
	public boolean addZombieToGame(Zombie zombie, int x, int y) {
		boolean sol = false;
		if(this.hayCosas(x,y))
			System.out.println("There's already something there.");
		
			this.zombieList.add(zombie);
			sol = true;

		return sol;
	}
	
	// Lo llama Controller
	public boolean isFinished() {
		return playerWins() || zombiesWin();
	}

	private boolean playerWins() {
		//si no hay zombies por salir y están todos muertos
  	    boolean sol = this.zManager.numZombies() == 0 && this.zombieList.getCont() == 0;
		
		if(sol)
			System.out.println("You win!");
  		
		return sol;
  	}

	private boolean zombiesWin() {
  	    //recorre la primera columna hasta que encuentra un zombie
  		boolean sol = false;
  		
  		for (int j = 0; j < Game.DIMX && !sol; ++j) {
  			sol = this.zombieList.hay(j,0);
  		}
  		
  		if(sol)
			System.out.println("Zombies win :(");
  		
		return sol;
  	}

	private void computer() {		
		// Tipo de zombie aleatorio
		int tipo = Math.abs(this.rand.nextInt() % ZombieFactory.numZombies());

		//Problema? el game accede a la factory
		
		// Si toca que salga en este ciclo
		if (this.zManager.isZombieAdded()) {
			// Fila aleatoria
			int x = Math.abs(this.rand.nextInt() % Game.DIMX);
			String zombieName = ZombieFactory.zombieName(tipo);
			Zombie zombie = ZombieFactory.getZombie(zombieName, x, DIMY-1);
			this.zombieList.add(zombie);
		}
	}
	
	// Lo llama resetC
		public void reset() {
			this.plantList = new ObjectList();
			this.zombieList = new ObjectList();

			this.zManager = new ZombieManager(this.level, this.rand);

			this.ciclos = 0;
			this.soles = new SuncoinManager();
		}

	// Lo llama updateC -- tiene tambien que llamarse cada ciclo no?
	public void update() {
		this.plantList.update();
		this.zombieList.update();
		
		this.computer();
		this.ciclos++;
	}






	
	



  	

  	

	// TODO actions  	

  	public void sunflowerAction() {
  		//añade los soles que le tocan
  		this.soles.add(Sunflower.PRODUCE_SOLES);
  	}

  	public void peashooterAction(int x, int y) {
  		boolean found = false;
  		//recorre la fila en la que está peashooter
		for (int j = y+1; j < Game.DIMY && !found; ++j) {
			//y si hay un zombie lo daña, y deja de buscar
			if (this.zombieList.hay(x, j)) {
	  			this.zombieList.danar(x,j,Peashooter.HARM);
	  			found = true;
			}
		}
  	}

  	public boolean zombieAction(int x, int y) {
  		boolean sol;
  		//si tiene S delante, lo ataca
  		if(this.sunflowerList.hay(x,y-1)) {
  			this.sunflowerList.danar(x,y-1,Zombie.HARM);
  			sol = true;
  		}
  		//si no, si tiene P delante, lo ataca
  		else if(this.peashooterList.hay(x,y-1)) {
  			this.peashooterList.danar(x,y-1,Zombie.HARM);
			sol = true;
		}
  		//si no, no ha atacado a nadie
  		else sol = false;
  		return sol;
  	}
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	

  	//Lo necesitamos public para que no se pisen los zombies
  	public boolean hayZombie(int x, int y) {
  		return this.zombieList.hay(x,y);
  	}

	//usado para saber frecuencias de acciones en plantas y zombies
	public int getCiclos(){
		return this.ciclos;
	}

	//para gameprinter, modifica str si hay algo en alguna lista
	public String toString(int x, int y) {
		String str = "";
		str = this.plantList.toString(x,y) + this.zombieList.toString(x,y);
		return str;
	}

	//función auxiliar para saber si un hueco está libre
	private boolean hayCosas(int x, int y) {
		return (this.plantList.hay(x, y) || this.zombieList.hay(x, y));
	}
}
