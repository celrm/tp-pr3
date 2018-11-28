package logic;

import java.util.Random;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
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
	public long seed;
	
	private ObjectList plantList;
	private ObjectList zombieList;

	private int ciclos;
	private SuncoinManager soles;
	private ZombieManager zManager;

	private boolean gana;
	
	public Game(Random rand, Level n, long seed) {
		this.rand = rand;
		this.level = n;
		this.seed  = seed;
		
		this.plantList = new ObjectList();
		this.zombieList = new ObjectList();
		
		this.zManager = new ZombieManager(this.level, this.rand);
		
		this.ciclos = 0;
		this.soles = new SuncoinManager();
		
		this.gana = false;
		
	}
	
	// Lo llama addCommand.execute()
	public void addPlantToGame(Plant plant, int x, int y) throws CommandExecuteException {
		// uppercase plantname?
		if(this.hayCosas(x,y))
			throw new CommandExecuteException("Failed to add "+plant.getName()+": position (" +x+", "+y+") is already occupied");
		
		if (this.soles.num() < plant.getCost())
			throw new CommandExecuteException("Failed to add "+plant.getName()+": not enough suncoins to buy it");
	
		plant.setPosition(x, y);
		plant.setGame(this);
		this.plantList.add(plant);
		this.soles.add(-plant.getCost());
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
			gana = true;
		//System.out.println("You win!");
  		
		return sol;
  	}

	private boolean zombiesWin() {
  	    //recorre la primera columna hasta que encuentra un zombie
  		boolean sol = false;
  		
  		for (int j = 0; j < Game.DIMX && !sol; ++j) {
  			sol = this.zombieList.hay(j,0);
  		}
  		
  		//if(sol)
		//	System.out.println("Zombies win :(");
  		
		return sol;
  	}

	private void computer() {		
		boolean posible = false;		
		//si hay algún hueco en la columna DIMY - 1
		for (int i = 0; i < Game.DIMX && !posible; ++i)
			posible = !this.hayCosas(i, Game.DIMY - 1);
		
		//y si toca que salga en este ciclo
		if (posible && this.zManager.isZombieAdded()) {
			// Fila aleatoria
			int x;
			do x = Math.abs(this.rand.nextInt() % Game.DIMX);
			while (this.hayCosas(x, Game.DIMY-1));
			
			// Tipo de zombie aleatorio
			int tipo = Math.abs(this.rand.nextInt() % ZombieFactory.numZombies());
			String zombieName = ZombieFactory.zombieName(tipo);
			Zombie zombie = ZombieFactory.getZombie(zombieName);
			
			//y añado al zombie
			if (zombie != null) {
				zombie.setPosition(x, DIMY-1);
				zombie.setGame(this);
				this.zombieList.add(zombie);
			}
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


  	public void generarSoles() {
  		//añade los soles que le tocan
  		this.soles.add(Sunflower.PRODUCE_SOLES);
  	}
  	
  	public void disparar(int x, int y, int harm) {
  		boolean found = false;
  		//recorre la fila en la que está p
		for (int j = y+1; j < Game.DIMY && !found; ++j) {
			//y si hay un zombie lo daña, y deja de buscar
			if (this.zombieList.hay(x, j)) {
	  			this.zombieList.danar(x,j,harm);
	  			found = true;
			}
		}
  	}
  	
  	public void explotar(int x, int y, int harm) {
  		for(int i = x-1; i <= x + 1; ++i)
  			for(int j = y-1; j <= y + 1; ++j) {
  				boolean dentro = !(i<0 || j<0 || i>=Game.DIMX || j>=Game.DIMY);
  				if(dentro && this.zombieList.hay(i,j))
  		  			this.zombieList.danar(i,j,harm);
  			}
  	}

  	public boolean zombieAction(int harm,int x, int y) {
  		boolean sol;
  		//si tiene plantas delante, lo ataca
  		if(this.plantList.hay(x,y-1)) {
  			this.plantList.danar(x,y-1, harm);
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
	
	//para DebugPrinter 
	public String toStringDebugp (int i) {
		return this.plantList.toStringDebug(i);
	}

	public String toStringDebugz (int i) {
		return this.zombieList.toStringDebug(i);
	}
	
	//función auxiliar para saber si un hueco está libre
	private boolean hayCosas(int x, int y) {
		return (this.plantList.hay(x, y) || this.zombieList.hay(x, y));
	}
	
	// Para DebugPrinter
	public int get_tot(){
		return plantList.getCont() + zombieList.getCont();
	}
	
	public int numPlantas(){
		return plantList.getCont();
	}

	public int numZombies(){
		return zombieList.getCont();
	}
	
	public int getSoles(){
		return this.soles.num();
	}
	
	public int remZombies(){
		return this.zManager.numZombies();
	}
	
	public long seed(){
		return seed;
	}
	
	public String getLevelName(){
		return this.level.toString();
	}
	
	// Para imprimir la última vez
	public boolean quienGana(){
		return this.gana;
	}
}
