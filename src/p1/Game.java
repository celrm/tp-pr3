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
		
		this.ciclos = -1;
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
	public void nextCycle() {
		this.ciclos++;
	}
	
	public void draw(){
		System.out.println("Number of cycles: " + Integer.toString(this.ciclos));
		System.out.println("Sun coins: " + Integer.toString(this.soles.num()));
		System.out.println("Remaining Zombies: " + Integer.toString(this.zManager.remZombies()));

		GamePrinter print = new GamePrinter(this, Game.DIMX, Game.DIMY);		
		System.out.println(print.toString());
	}
	
	public void update() {

		this.nextCycle();
		int numSoles = this.sunflowerList.generarSoles();
		this.soles.add(numSoles*Sunflower.PRODUCE_SOLES);
		
		//lanzar guisantes -- ¿Creo que esta ya bien?
		for (int i = 0; i < plength(); ++i) {
			//p(i).vida()>0
			if(this.peashooterList.getvida(i) > 0) {
				boolean found = false;
				for (int j = 0; j < zlength() && !found; ++j) {
					if (this.zombieList.getvida(j)> 0 && this.peashooterList.posx(i) == this.zombieList.posx(j)) {
						this.zombieList.danar(zombieList.posx(j), zombieList.posy(j), Peashooter.HARM);
						//this.z(j).serDanado(Peashooter.HARM);
						found = true;
					}
				}
			}
		}
		//zombies atacan
		for (int i = 0; i < zlength(); ++i) {

			sunflowerList.danar(zombieList.posx(i), zombieList.posy(i)-1, Zombie.HARM);
			peashooterList.danar(zombieList.posx(i), zombieList.posy(i)-1, Zombie.HARM);
			
			
					
			/*		if (hayPlantas) this.p(j-1).serDanado(1);
					else {					
						hayPlantas = false;
						j = 0;
						//comprobar si hay zombies delante
						while (j < zlength() && !hayPlantas) {
							hayPlantas = (z(j).vida()> 0) && (z(j).posy() == z(i).posy()-1 && z(j).posx() == z(i).posx());
							++j;
						}
						if (!hayPlantas) this.zombieList.avanza(i);
					}
					
					// Game.computer

				}
			}*/
		}
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
	public void computer() {
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
	
	//compramos una planta
	public boolean addPlant(String planta, int x, int y) {
		boolean sol = false;
		
		//si no hay nada donde queremos colocarla
		if(this.hayCosas(x,y)) System.out.println("There's already something there.");
		//y si la posición está dentro del tablero
		else if(x<0 || y<0 || x>=Game.DIMX || y>=Game.DIMY) System.out.println("Wrong position.");
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
		return sol;
	}

}