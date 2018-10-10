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
    
    //gana el jugador si
  	public boolean playerWins() {
  	    boolean sol = false;
  	    
  	    //no quedan zombies por salir
          if(this.zManager.numZombies() == 0) {
              sol = true;
              
              //y todos están muertos
              for (int i = 0; i < this.zlength() && sol; ++i)
                  if (this.zv(i) > 0) sol = false;
          }
          return sol;
  	}
  	
  	//ganan los zombies si
  	public boolean zombiesWin() {
  	    boolean sol = false;
  	    
  	    //de entre los zombies
  	    for (int i = 0; i < this.zlength() && !sol; ++i) {
  	    	//hay uno en la columna 0
  	        if (this.zy(i) == 0) sol = true;
  	    }
  	    return sol;
  	}
  	
  	public void draw(){
		System.out.println("Number of cycles: " + Integer.toString(this.ciclos));
		System.out.println("Sun coins: " + Integer.toString(this.soles.num()));
		System.out.println("Remaining Zombies: " + Integer.toString(this.zManager.numZombies()));

		GamePrinter print = new GamePrinter(this, Game.DIMX, Game.DIMY);		
		System.out.println(print.toString());
	}
	
	public void update() {		
		//crear soles
		int numSoles = this.sunflowerList.generarSoles();
		this.soles.add(numSoles*Sunflower.PRODUCE_SOLES);
		
		//lanzar guisantes
		for (int i = 0; i < this.plength(); ++i)
			if(this.pv(i) > 0) {
				boolean found = false;
				
				for (int j = 0; j < this.zlength() && !found; ++j)
					if (this.zv(j)> 0 && this.px(i) == this.zx(j)) {
						this.zombieList.danar(this.zx(j), this.zy(j), Peashooter.HARM);
						found = true;
					}
			}
		
		//zombies atacan y avanzan
		for (int i = 0; i < this.zlength(); ++i)
			if(this.zv(i) > 0) {
				boolean alguien = false;
				alguien = sunflowerList.danar(zx(i), zy(i)-1, Zombie.HARM);
				if(!alguien) alguien = peashooterList.danar(zx(i), zy(i)-1, Zombie.HARM);
				if(!alguien) this.zombieList.avanza(i);
			}

		this.anadirZombies();
		
		this.ciclos++;
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
						this.sunflowerList.add(x, y, this);
						this.soles.add(-Sunflower.COSTE);
						sol = true;
					}
					else System.out.println("Not enough cash.");
				}
				
				//o un peashooter
				else if(planta.equals("p") || planta.equals("peashooter")) {
					//si da el dinero
					if (this.soles.num() >= Peashooter.COSTE) {
						this.peashooterList.add(x, y, this);
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
			
	public void reset() {
		this.sunflowerList = new SunflowerList();
		this.peashooterList = new PeashooterList();
		this.zombieList = new ZombieList();
		
		this.zManager = new ZombieManager(this.level);
		
		this.ciclos = 0;
		this.soles = new SuncoinManager();		
	}

	//usado para saber frecuencia de soles y avanzar
	public int getCiclos(){
		return this.ciclos;
	}
	
	//funciones exclusivas para GamePrinter
	public int slength(){
		return this.sunflowerList.length();
	}
	
	public int plength(){
		return this.peashooterList.length();
	}
	
	public int zlength(){
		return this.zombieList.length();
	}
	
	public int sx (int pos){
		return this.sunflowerList.posx(pos);
	}
	
	public int sy (int pos){
		return this.sunflowerList.posy(pos);
	}
	
	public int sv (int pos){
		return this.sunflowerList.getvida(pos);
	}
	
	public int px (int pos){
		return this.peashooterList.posx(pos);
	}
	
	public int py (int pos){
		return this.peashooterList.posy(pos);
	}
	
	public int pv (int pos){
		return this.peashooterList.getvida(pos);
	}
	
	public int zx (int pos){
		return this.zombieList.posx(pos);
	}
	
	public int zy (int pos){
		return this.zombieList.posy(pos);
	}
	
	public int zv (int pos){
		return this.zombieList.getvida(pos);
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
			this.zombieList.add(x, Game.DIMY-1, this);
	    }	
	}
	
	//función auxiliar para saber si un hueco está libre
	private boolean hayCosas(int x, int y) {
		boolean hayCosas = false;
		int j = 0;
		//miramos en la lista de sunflowers
		while (j < slength() && !hayCosas) {
			if (sv(j)>0 && sx(j)==x && sy(j)==y) hayCosas = true;
			j++;
		}
		j = 0;
		//en la lista de peashooters
		while (j < plength() && !hayCosas) {
			if (pv(j)>0 && px(j)==x && py(j)==y) hayCosas = true;
			j++;
		}
		j = 0;
		//y en la lista de zombies
		while (j < zlength() && !hayCosas) {
			if (zv(j)>0 && zx(j)==x && zy(j)==y) hayCosas = true;
			j++;
		}
		return hayCosas;
	}
	
	//sí, lo he buscado; si no, la excepción de parseInt es imposible de evitar.
	private static boolean isParsable(String input){
	    boolean parsable = true;
	    try{
	        Integer.parseInt(input);
	    }catch(NumberFormatException e){
	        parsable = false;
	    }
	    return parsable;
	}

}