package logic;

import java.util.Random;

import factories.ZombieFactory;
import objects.Plant;
import objects.Sunflower;
import objects.Zombie;
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
			plant.setPosition(x, y);
			plant.setGame(this);
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
		
		else {
			zombie.setPosition(x, y);
			zombie.setGame(this);
			this.zombieList.add(zombie);
			sol = true;
		}

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
			Zombie zombie = ZombieFactory.getZombie(zombieName);
			zombie.setPosition(x, DIMY-1);
			zombie.setGame(this);
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


	public String cabezera() {
		StringBuilder str = new StringBuilder();
		str.append("Number of cycles: ").append(Integer.toString(this.ciclos));
		str.append("\nSun coins: ").append(Integer.toString(this.soles.num()));
		str.append("\nRemaining Zombies: ").append(Integer.toString(this.zManager.numZombies()));
		return str.toString();
	}



	
	



  	

  	

	// TODO actions

  	public void generarSoles() {
  		//añade los soles que le tocan
  		this.soles.add(Sunflower.PRODUCE_SOLES);
  	}
  	
  	public void disparar(Plant p,int x, int y) {
  		boolean found = false;
  		//recorre la fila en la que está p
		for (int j = y+1; j < Game.DIMY && !found; ++j) {
			//y si hay un zombie lo daña, y deja de buscar
			if (this.zombieList.hay(x, j)) {
	  			this.zombieList.danar(x,j,p.getHarm());
	  			found = true;
			}
		}
  	}
  	
  	public void explotar(Plant p) {
  		for(int i = p.x()-1; i <= p.x() + 1; ++i)
  			for(int j = p.y()-1; j <= p.y() + 1; ++j) {
  				boolean dentro = !(i<0 || j<0 || i>=Game.DIMX || j>=Game.DIMY);
  				if(dentro && this.zombieList.hay(i,j))
  		  			this.zombieList.danar(i,j,p.getHarm());
  			}
  	}

  	public boolean zombieAction(Zombie z,int x, int y) {
  		boolean sol;
  		//si tiene S delante, lo ataca
  		if(this.plantList.hay(x,y-1)) {
  			this.plantList.danar(x,y-1,z.getHarm());
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
	
	public int get_tot(){
		return plantList.getCont() + zombieList.getCont();
	}
}
