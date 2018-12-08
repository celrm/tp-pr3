package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import lists.ObjectList;
import objects.Plant;
import objects.Sunflower;
import objects.Zombie;
import printers.BoardPrinter;
import printers.ReleasePrinter;
import exceptions.CommandExecuteException;
import exceptions.FileContentsException;
import factories.PlantFactory;
import factories.ZombieFactory;

public class Game {
	public static final int DIMX = 4;
	public static final int DIMY = 8;

	private Random rand;
	public long seed;
	private Level level;
	
	private ObjectList plantList;
	private ObjectList zombieList;

	private int cycles;
	private SuncoinManager sManager;
	private ZombieManager zManager;
	
	private boolean exit;	
	private BoardPrinter gamePrinter;
	
	public Game(Random rand, Level n, long seed) {
		this.rand = rand;
		this.level = n;
		this.seed  = seed;
		
		this.plantList = new ObjectList();
		this.zombieList = new ObjectList();
		
		this.zManager = new ZombieManager(this.level, this.rand);
		
		this.cycles = 0;
		this.sManager = new SuncoinManager();
		
		this.exit = false;
		this.gamePrinter = new ReleasePrinter();
	}

	private boolean taken(int x, int y) {
		return (this.plantList.hay(x, y) || this.zombieList.hay(x, y));
	}
	public int numObjects(){
		return numPlants() + numZombies();
	}
	public int numPlants(){
		return plantList.getCont();
	}
	public int numZombies(){
		return zombieList.getCont();
	}
	public int getCycles(){
		return this.cycles;
	}	
	public int getSuncoins(){
		return this.sManager.getSuncoins();
	}	
	public int remZombies(){
		return this.zManager.getRemZombies();
	}	
	public long getSeed(){
		return seed;
	}	
	public String getLevelName(){
		return this.level.name();
	}
	
	// Controller.run()
	public boolean isFinished() {
		boolean victory = playerWins();
		boolean out = exit || victory || zombiesWin();
		if (out) {
			System.out.print("****** Game over!: ");
			if (exit)
				System.out.print("User exit");
			else if (victory)
				System.out.print("You win!");
			else 
				System.out.print("Zombies win :(");
			System.out.println(" ******");
		}
		return out;
	}
	private boolean playerWins() {
		return (this.zManager.getRemZombies() == 0) && (numZombies() == 0);
  	}
	private boolean zombiesWin() {
  		boolean sol = false;
  		for (int j = 0; j < DIMX && !sol; ++j)
  			sol = this.zombieList.hay(j,0);
		return sol;
  	}
	public String toString(){
		return this.gamePrinter.printGame(this);
	}
	
	// AddCommand.execute()
	public void addPlantToGame(Plant plant, int x, int y) throws CommandExecuteException {
		if(x<0 || y<0 || x>=DIMX || y>=DIMY-1) // es este de parse??
			throw new CommandExecuteException("("+x+", "+y+") is an invalid position");
		
		if(this.taken(x,y))
			throw new CommandExecuteException("position ("+x+", "+y+") is already occupied");
		
		if (this.sManager.getSuncoins() < plant.getCost())
			throw new CommandExecuteException("not enough suncoins to buy it");
	
		plant.setPosition(x, y);
		plant.setGame(this);
		plant.setBirth(getCycles());
		this.plantList.add(plant);
		this.sManager.add(-plant.getCost());
	}
	
	// ExitCommand.execute()
	public void exit() {
		exit = true;
	}
	
	// PrintModeCommand.execute()
	public void setPrinter(BoardPrinter print) {
		this.gamePrinter = print;
	}
	// ReleasePrinter.encodeGame()
	public String toStringRelease(int x, int y) {
		return this.plantList.toString(x,y) + this.zombieList.toString(x,y);
	}	
	// DebugPrinter.encodeGame()
	public String toStringDebugPlants(int i) {
		return this.plantList.toStringDebug(i);
	}
	public String toStringDebugZombies(int i) {
		return this.zombieList.toStringDebug(i);
	}
	
	// ResetCommand.execute()
	public void reset() {
		this.plantList = new ObjectList();
		this.zombieList = new ObjectList();

		this.zManager = new ZombieManager(this.level, this.rand);

		this.cycles = 0;
		this.sManager = new SuncoinManager();
	}

	// UpdateCommand.execute() y AddCommand.execute()
	public void update() {
		this.plantList.update();
		this.zombieList.update();
		
		this.computer();
		this.cycles++;
	}
	private void computer() {		
		boolean posible = false;
		for (int i = 0; i < DIMX && !posible; ++i)
			posible = !this.taken(i, DIMY - 1);
		
		if (posible && this.zManager.isZombieAdded()) {
			// Fila aleatoria
			int x;
			do x = Math.abs(this.rand.nextInt() % DIMX);
			while (this.taken(x, DIMY-1));
			
			// Tipo de zombie aleatorio
			int tipo = Math.abs(this.rand.nextInt() % ZombieFactory.numAvZombies());
			String zombieName = ZombieFactory.zombieName(tipo);
			Zombie zombie = ZombieFactory.getZombie(zombieName);
			
			// Añado al zombie
			if (zombie != null) {
				zombie.setPosition(x, DIMY-1);
				zombie.setGame(this);
				zombie.setBirth(getCycles());
				this.zombieList.add(zombie);
			}
		}
	}
	
	// Sunflower.update()
  	public void generarSoles() {
  		this.sManager.add(Sunflower.PRODUCE_SOLES);
  	}	
	// Peashooter.update()  	
  	public void disparar(int x, int y, int harm) {
  		boolean found = false;
		for (int j = y+1; j < DIMY && !found; ++j) {
			if (this.plantList.hay(x, j))
	  			found = true;
			if (this.zombieList.hay(x, j)) {
	  			this.zombieList.danar(x,j,harm);
	  			found = true;
			}
		}
  	}	
	// Petacereza.update()
  	public void explotar(int x, int y, int harm) {
  		for(int i = x-1; i <= x + 1; ++i)
  			for(int j = y-1; j <= y + 1; ++j) {
  				boolean dentro = !(i<0 || j<0 || i>=DIMX || j>=DIMY);
  				if(dentro && this.zombieList.hay(i,j))
  		  			this.zombieList.danar(i,j,harm);
  			}
  	}  		
	// Zombie.update()
  	public boolean zombieAction(int harm,int x, int y) {
  		if(this.plantList.hay(x,y-1)) {
  			this.plantList.danar(x,y-1, harm);
  			return true;
  		}
  		return false;
  	}  	
  	// Zombie.update() - Para que no se pisen los zombies
  	public boolean hayZombie(int x, int y) {
  		return this.zombieList.hay(x,y);
  	}
	
  	// SaveCommand.execute()
	public void store (BufferedWriter outStream) throws IOException{
		outStream.write("cycle: ");
		outStream.write(Integer.toString(getCycles()));
		outStream.newLine();
		outStream.write("sunCoins: ");
		outStream.write(Integer.toString(getSuncoins()));
		outStream.newLine();
		outStream.write("level: ");
		outStream.write(this.getLevelName());
		outStream.newLine();
		outStream.write("remZombies: ");
		outStream.write(Integer.toString(remZombies()));
		outStream.newLine();
		outStream.write("plantList: ");
		this.plantList.store(outStream);
		outStream.newLine();
		outStream.write("zombieList: ");
		this.zombieList.store(outStream);
	}
	
  	// LoadCommand.execute()
	public void load(BufferedReader inStream) throws FileContentsException {
		Level oldlevel = level;
		ObjectList oldplantList = plantList;
		ObjectList oldzombieList = zombieList;
		int oldciclos = cycles;
		SuncoinManager oldsManager = sManager;
		ZombieManager oldzManager = zManager;
		BoardPrinter oldgamePrinter = gamePrinter;
		// Cutrísima forma de hacer el backup
		
		try {
			String[] words = loadLine(inStream,"cycle",false);
			this.cycles = Integer.parseInt(words[0]);
			if (this.cycles < 0)
				throw new FileContentsException("negative cycle");		
			
			words = loadLine(inStream,"sunCoins",false);
			int newsuncoins = Integer.parseInt(words[0]);
			if (newsuncoins < 0)
				throw new FileContentsException("negative suncoins");
			sManager.add(newsuncoins - sManager.getSuncoins());
			
			words = loadLine(inStream,"level",false);
			this.level = Level.parse(words[0]);
			if (this.level == null)
				throw new FileContentsException("unknown level name");
			
			words = loadLine(inStream,"remZombies",false);
			int newremz = Integer.parseInt(words[0]);
			if (newremz < 0)
				throw new FileContentsException("negative remaining zombies");
			this.zManager.setRemZombies(newremz);
			
			words = loadLine(inStream,"plantList",true);
			this.plantList = new ObjectList();
			for(int i = 1; i <= words.length; ++i) {
				String[] attr = words[i-1].split("\\:");
		
				if(attr.length != 5)
					throw new FileContentsException("wrong number of attributes in plant #" + i);
				
				Plant p = PlantFactory.getPlant(attr[0]);
				if (p == null)
					throw new FileContentsException("unknown plant #"+i+" name " + attr[0]);
				try{
					int x = Integer.parseInt(attr[2]);
					int y = Integer.parseInt(attr[3]);
					if(taken(x,y))
						throw new FileContentsException("its position occupied");						
					p.setAttributes(Integer.parseInt(attr[1]),x,y,Integer.parseInt(attr[4]));
				} catch (FileContentsException e) {
					throw new FileContentsException("plant #"+i+" has "+e.getMessage());
				}
				p.setGame(this);
				plantList.add(p);
			}
			
			words = loadLine(inStream,"zombieList",true);
			this.zombieList = new ObjectList();
			for(int i = 1; i <= words.length; ++i) {
				String[] attr = words[i-1].split("\\:");
				
				if(attr.length != 5)
					throw new FileContentsException("wrong number of attributes in zombie #" + i);
				
				Zombie z = ZombieFactory.getZombie(attr[0]);
				if (z == null)
					throw new FileContentsException("unknown zombie #"+i+" name " + attr[0]);
				
				try{
					int x = Integer.parseInt(attr[2]);
					int y = Integer.parseInt(attr[3]);
					if(taken(x,y))
						throw new FileContentsException("its position occupied");						
					z.setAttributes(Integer.parseInt(attr[1]),x,y,Integer.parseInt(attr[4]));
				} catch (FileContentsException e) {
					throw new FileContentsException("zombie #"+i+" has "+e.getMessage());
				}
				z.setGame(this);
				zombieList.add(z);
			}
			
			this.gamePrinter = new ReleasePrinter();
		} catch(IOException | FileContentsException ex) {
			level = oldlevel;
			plantList = oldplantList;
			zombieList = oldzombieList;
			cycles = oldciclos;
			sManager = oldsManager;
			zManager = oldzManager;
			gamePrinter = oldgamePrinter;
			throw new FileContentsException(ex.getMessage());
		} catch (NumberFormatException ex) {
			level = oldlevel;
			plantList = oldplantList;
			zombieList = oldzombieList;
			cycles = oldciclos;
			sManager = oldsManager;
			zManager = oldzManager;
			gamePrinter = oldgamePrinter;
			throw new FileContentsException("expected integer");			
		}
	}
	
	// Por qué public? y va aquí?
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
}
