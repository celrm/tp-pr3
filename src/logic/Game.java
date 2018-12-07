package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import commands.LoadCommand;
import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.FileContentsException;
import factories.PlantFactory;
import factories.ZombieFactory;
import objects.Plant;
import objects.Sunflower;
import objects.Zombie;
import printers.BoardPrinter;
import printers.ReleasePrinter;
import lists.ObjectList;

public class Game {
	public static final int DIMX = 4;
	public static final int DIMY = 8;

	private Random rand;
	public long seed;
	private Level level;
	
	private ObjectList plantList;
	private ObjectList zombieList;

	private int ciclos;
	private SuncoinManager soles;
	private ZombieManager zManager;
	
	private boolean win;
	private boolean exit;
	
	private BoardPrinter gamePrinter;
	
	public Game(Random rand, Level n, long seed) {
		this.rand = rand;
		this.level = n;
		this.seed  = seed;
		
		this.plantList = new ObjectList();
		this.zombieList = new ObjectList();
		
		this.zManager = new ZombieManager(this.level, this.rand);
		
		this.ciclos = 0;
		this.soles = new SuncoinManager();
		
		this.win = false;
		this.exit = false;
		this.gamePrinter = new ReleasePrinter();
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
		return exit || playerWins() || zombiesWin();
	}

	private boolean playerWins() {
		//si no hay zombies por salir y están todos muertos
  	    boolean sol = this.zManager.getNumZombies() == 0 && this.zombieList.getCont() == 0;
		
		if(sol)
			win = true;
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
			Zombie zombie = null;
			
			zombie = ZombieFactory.getZombie(zombieName);
			//Como nunca se va a lanzar la excepción en esta parte, no hace falta tratarla
			
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
		return this.plantList.toString(x,y) + this.zombieList.toString(x,y);
	}
	
	//para DebugPrinter 
	public String toStringDebugp (int i) {
		return this.plantList.toStringDebug(i);
	}

	public String toStringDebugz (int i) {
		return this.zombieList.toStringDebug(i);
	}
	
	public void setPrinter(BoardPrinter print) {
		this.gamePrinter = print;
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
		return this.zManager.getNumZombies();
	}
	
	public long seed(){
		return seed;
	}
	
	public String getLevelName(){
		return this.level.toString();
	}
	
	// Para imprimir la última vez
	public boolean quienGana(){
		return this.win;
	}
	
	public String toString(){
		return this.gamePrinter.printGame(this);
	}
	
	public void store (BufferedWriter outStream) throws IOException{
		outStream.write("Plants Vs Zombies v3.0");
		outStream.newLine();
		outStream.newLine();
		outStream.write("cycle: ");
		outStream.write(Integer.toString(this.ciclos));
		outStream.newLine();
		outStream.write("sunCoins: ");
		outStream.write(Integer.toString(this.getSoles()));
		outStream.newLine();
		outStream.write("level: ");
		outStream.write(this.getLevelName());
		outStream.newLine();
		outStream.write("remZombies: ");
		outStream.write(Integer.toString(this.remZombies()));
		outStream.newLine();
		outStream.write("plantList: ");
		this.plantList.store(outStream);
		outStream.newLine();
		outStream.write("zombieList: ");
		this.zombieList.store(outStream);
	}
	public void load(BufferedReader inStream) throws FileContentsException {
		Level oldlevel = level;
		ObjectList oldplantList = plantList;
		ObjectList oldzombieList = zombieList;
		int oldciclos = ciclos;
		SuncoinManager oldsoles = soles;
		ZombieManager oldzManager = zManager;
		BoardPrinter oldgamePrinter = gamePrinter;
		
		try {
			String line = inStream.readLine().trim();
			if ( !line.equals("Plants Vs Zombies v3.0") )
				throw new FileContentsException("missing: Plants Vs Zombies v3.0");
			line = inStream.readLine();
			
			String[] words = loadLine(inStream,"cycle",false);
			this.ciclos = Integer.parseInt(words[0]);
			
			words = loadLine(inStream,"sunCoins",false);
			soles.add(Integer.parseInt(words[0])-soles.num());
			
			words = loadLine(inStream,"level",false);
			this.level = Level.parse(words[0]);
			if (this.level == null)
				throw new FileContentsException("Wrong level");
			
			words = loadLine(inStream,"remZombies",false);
			this.zManager.setNumZombies(Integer.parseInt(words[0]));
			
			words = loadLine(inStream,"plantList",true);
			this.plantList = new ObjectList();
			for(int i = 0; i < words.length; ++i) {
				String[] attr = words[i].split("\\:");
				if(attr.length != 5)
					throw new FileContentsException("Wrong number of plant attributes: plant " + i);
				Plant p = PlantFactory.getPlant(attr[0]);
				if (p == null)
					throw new FileContentsException("Unknown plant name: " + attr[0]);
				p.setAttributes(Integer.parseInt(attr[1]),Integer.parseInt(attr[2]),Integer.parseInt(attr[3]),Integer.parseInt(attr[4]));
				p.setGame(this);
				plantList.add(p);
			}
			
			words = loadLine(inStream,"zombieList",true);
			this.zombieList = new ObjectList();
			for(int i = 0; i < words.length; ++i) {
				String[] attr = words[i].split("\\:");
				if(attr.length != 5)
					throw new FileContentsException("wrong number of zombie attributes: zombie " + i);
				Zombie p = ZombieFactory.getZombie(attr[0]);
				if (p == null)
					throw new FileContentsException("Unknown zombie name: " + attr[0]);
				p.setAttributes(Integer.parseInt(attr[1]),Integer.parseInt(attr[2]),Integer.parseInt(attr[3]),Integer.parseInt(attr[4]));
				p.setGame(this);
				zombieList.add(p);
			}
			
			this.gamePrinter = new ReleasePrinter();
		} catch(IOException | FileContentsException ex) {
			level = oldlevel;
			plantList = oldplantList;
			zombieList = oldzombieList;
			ciclos = oldciclos;
			soles = oldsoles;
			zManager = oldzManager;
			gamePrinter = oldgamePrinter;
			throw new FileContentsException(ex.getMessage());
		} catch (NumberFormatException ex) {
			level = oldlevel;
			plantList = oldplantList;
			zombieList = oldzombieList;
			ciclos = oldciclos;
			soles = oldsoles;
			zManager = oldzManager;
			gamePrinter = oldgamePrinter;
			throw new FileContentsException("not a number");			
		}
	}

	public static final String wrongPrefixMsg = "unknown game attribute: ";
	public static final String lineTooLongMsg = "too many words on line commencing: ";
	public static final String lineTooShortMsg = "missing data on line commencing: ";
	
	public String[] loadLine(BufferedReader inStream, String prefix, boolean isList) throws IOException, FileContentsException {
		String line = inStream.readLine().trim();
		// absence of the prefix is invalid
		if ( ! line . startsWith(prefix + ":") )
			throw new FileContentsException(wrongPrefixMsg + prefix);
		// cut the prefix and the following colon off the line
		// then trim it to get the attribute contents
		String contentString = line. substring(prefix . length() +1).trim();
		String[] words;
		// the attribute contents are not empty
		if (! contentString. equals("")) {
			if (! isList ) {
				// split non−list attribute contents into words
				// using 1−or−more−white−spaces as separator
				words = contentString.split ("\\s+");
			// a non−list attribute with contents of more than one word is invalid
			if (words.length != 1)
				throw new FileContentsException(lineTooLongMsg + prefix);
			} else
				// split list attribute contents into words
				// using comma+0−or−more−white−spaces as separator
				words = contentString.split (",\\s*");
		// the attribute contents are empty
		} else {
			// a non−list attribute with empty contents is invalid
			if (! isList )
				throw new FileContentsException(lineTooShortMsg + prefix);
			// a list attibute with empty contents is valid;
			// use a zero−length array to store its words
			words = new String[0];
		}
		return words;
	}

	public void exit() {
		exit = true;
	}
	public boolean getExit() {
		return exit;
	}
}
