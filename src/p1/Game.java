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
	
    public Game(Random ran, Level n) {
		this.rand = ran;
		this.level = n;
		
		this.sunflowerList = new SunflowerList();
		this.peashooterList = new PeashooterList();
		this.zombieList = new ZombieList();
		
		this.zManager = new ZombieManager(this.level);
		
		this.ciclos = 0;
		this.soles = new SuncoinManager();
	}
    
    public Random getRand() {
    	return this.rand;
    }
    
    public Level getLevel() {
    	return this.level;
    }
	
	public int ciclos(){
		return this.ciclos;
	}
	
	public int slength(){
		return this.sunflowerList.length();
	}
	
	public int plength(){
		return this.peashooterList.length();
	}
	
	public int zlength(){
		return this.zombieList.length();
	}
	
	public Peashooter p (int pos){
		return this.peashooterList.lista(pos);
	}
	
	public Sunflower s (int pos){
		return this.sunflowerList.lista(pos);
	}
	
	public Zombie z (int pos){
		return this.zombieList.lista(pos);
	}
	
	public void reset() {
		this.sunflowerList = new SunflowerList();
		this.peashooterList = new PeashooterList();
		this.zombieList = new ZombieList();
		
		this.zManager = new ZombieManager(this.level);
		
		this.ciclos = 0;
		this.soles = new SuncoinManager();		
	}
	
	public void draw(){
		System.out.println("Number of cycles: " + Integer.toString(this.ciclos));
		System.out.println("Sun coins: " + Integer.toString(this.soles.num()));
		System.out.println("Remaining Zombies: " + Integer.toString(this.zManager.remZombies()));

		GamePrinter print = new GamePrinter(this, Game.DIMX, Game.DIMY);		
		System.out.println(print.toString());
	}
	
	public void update() {

		this.ciclos++;
		
		//crear soles
		int numSoles = this.sunflowerList.generarSoles();
		this.soles.add(numSoles*Sunflower.PRODUCE_SOLES);
		
		//lanzar guisantes
		for (int i = 0; i < this.plength(); ++i) {
			if(this.peashooterList.getvida(i) > 0) {
				
				int filaP = this.peashooterList.posx(i);
				boolean found = false;
				
				for (int j = 0; j < zlength() && !found; ++j) {
					int filaZ = this.zombieList.posx(j);
					if (this.zombieList.getvida(j)> 0 && filaP == filaZ) {
						this.zombieList.danar(filaZ, zombieList.posy(j), Peashooter.HARM);
						found = true;
					}
				}
			}
		}
		
		//zombies atacan y avanzan
		for (int i = 0; i < zlength(); ++i) {
			if(this.zombieList.getvida(i) > 0) {
				boolean alguien = false;
				alguien = sunflowerList.danar(zombieList.posx(i), zombieList.posy(i)-1, Zombie.HARM);
				if(!alguien) alguien = peashooterList.danar(zombieList.posx(i), zombieList.posy(i)-1, Zombie.HARM);
				if(!alguien) this.zombieList.avanza(i);
			}
		}
		this.anadirZombies();
	}
	
	//gana el jugador si
	public boolean playerWins() {
	    boolean sol = false;
	    
	    //no quedan zombies por salir
        if(this.zManager.remZombies() == 0) {
            sol = true;
            
            //y todos están muertos
            for (int i = 0; i < this.zlength() && sol; ++i)
                if (this.zombieList.getvida(i) > 0) sol = false;
        }
        return sol;
	}
	
	//ganan los zombies si
	public boolean zombiesWin() {
	    boolean sol = false;
	    
	    //de entre los zombies
	    for (int i = 0; i < this.zlength() && !sol; ++i) {
	    	//hay uno en la columna 0
	        if (this.z(i).posy() == 0) sol = true;
	    }
	    return sol;
	}
	
	//se añaden zombies
	private void anadirZombies() {
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
			this.zombieList.addZombie(x, Game.DIMY-1, this);
	    }	
	}
	
	//función auxiliar para saber si un hueco está libre
	private boolean hayCosas(int x, int y) {
		boolean hayCosas = false;
		int j = 0;
		//miramos en la lista de sunflowers
		while (j < slength() && !hayCosas) {
			if (s(j).vida()>0 && s(j).posx()==x && s(j).posy()==y) hayCosas = true;
			j++;
		}
		j = 0;
		//en la lista de peashooters
		while (j < plength() && !hayCosas) {
			if (p(j).vida()>0 && p(j).posx()==x && p(j).posy()==y) hayCosas = true;
			j++;
		}
		j = 0;
		//y en la lista de zombies
		while (j < zlength() && !hayCosas) {
			if (z(j).vida()>0 && z(j).posx()==x && z(j).posy()==y) hayCosas = true;
			j++;
		}
		return hayCosas;
	}
	
	//sí, lo he buscado. si no, la excepción de parseInt es imposible de evitar.
	private static boolean isParsable(String input){
	    boolean parsable = true;
	    try{
	        Integer.parseInt(input);
	    }catch(NumberFormatException e){
	        parsable = false;
	    }
	    return parsable;
	}
	
	//compramos una planta
	public boolean add(String[] words) {
		boolean sol = false;
		
		//con los parámetros correctos
		if (words.length != 4 || !isParsable(words[2]) || !isParsable(words[3])) System.out.println("Wrong parameters.");
		else {
			String planta = words[1];
			int x = Integer.parseInt(words[2]);
			int y = Integer.parseInt(words[3]);
			
			//con la posición dentro del tablero
			if(x<0 || y<0 || x>=Game.DIMX || y>=Game.DIMY) System.out.println("Wrong position.");
			
			//si no hay nada donde queremos colocarla
			else if(this.hayCosas(x,y)) System.out.println("There's already something there.");
			
			else {
				// si es una sunflower
				if(planta.equals("s") || planta.equals("sunflower")) {
					//si da el dinero
					if (this.soles.num() >= Sunflower.COSTE) {
						this.sunflowerList.addSunflower(x, y, this);
						this.soles.add(-Sunflower.COSTE);
						sol = true;
					}
					else System.out.println("Not enough cash.");
				}
				
				//o un peashooter
				else if(planta.equals("p") || planta.equals("peashooter")) {
					//si da el dinero
					if (this.soles.num() >= Peashooter.COSTE) {
						this.peashooterList.addPeashooter(x, y, this);
						this.soles.add(-Peashooter.COSTE);
						sol = true;
					}
					else System.out.println("Not enough cash.");
				}
				
				//y si no te equivocas de planta
				else System.out.println("Wrong plant.");
			}
		}
		return sol;
	}

}