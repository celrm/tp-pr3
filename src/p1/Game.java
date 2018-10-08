package p1;

import java.util.Random;

public class Game {
	
	public static int DIMX = 4;
	public static int DIMY = 8;

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
	
	public void cambiarSoles(int n){
		this.soles.add(n);
	}
	
	public void nextCycle() {
		this.ciclos++;
	}
	
	public void draw(){
		GamePrinter print = new GamePrinter(this, Game.DIMX, Game.DIMY);

		System.out.println("Number of cycles: " + Integer.toString(this.ciclos));
		System.out.println("Sun coins: " + Integer.toString(this.soles.num()));
		System.out.println("Remaining Zombies: " + Integer.toString(this.zManager.remZombies()));
		
		System.out.println(print.toString());
	}
	
	public void update() {
		// crear soles
		for (int i = 0; i < slength(); ++i) {
			if(s(i).vida()>0) s(i).generarSoles();
		}
		//lanzar guisantes
		for (int i = 0; i < plength(); ++i) {
			if(p(i).vida()>0) {
				boolean found = false;
				for (int j = 0; j < zlength() && !found; ++j) {
					if (z(j).vida() > 0 && p(i).posx() == z(j).posx()){
						z(j).serDanado(Peashooter.HARM);
						found = true;
					}
				}
			}
		}
		//zombies atacan
		for (int i = 0; i < zlength(); ++i) {
			if (z(i).vida()>0) {
				boolean hayPlantas = false;
				int j = 0;
				while (j < slength() && !hayPlantas) {
					hayPlantas = (s(j).vida() > 0) && (s(j).posy() == z(i).posy()-1 && s(j).posx() == z(i).posx());
					++j;
				}
				if (hayPlantas) s(j-1).serDanado(1);
				else {
					hayPlantas = false;
					j = 0;
					while (j < plength() && !hayPlantas) {
						hayPlantas = (p(j).vida() > 0) && (p(j).posy() == z(i).posy()-1 && p(j).posx() == z(i).posx());
						++j;
					}
					if (hayPlantas) p(j-1).serDanado(1);
					else {
					
					hayPlantas = false;
					j = 0;
					while (j < zlength() && !hayPlantas) {
						hayPlantas = (z(j).vida()> 0) && (z(j).posy() == z(i).posy()-1 && z(j).posx() == z(i).posx());
						++j;
					}
					if (!hayPlantas) z(i).avanza();
					
					}
				}
			}
		}
	}
	
	public boolean playerWins(){
	    boolean sol = false;
        if(this.zManager.remZombies() == 0){
            sol = true;
            for (int i = 0; i < this.zlength() && sol; ++i){
                if (this.z(i).vida() > 0){
                    sol = false;
                }
            }
        }
        return sol;

	}
	
	public boolean zombiesWin(){
	    boolean sol = false;
	    for (int i = 0; i < this.zlength() && !sol; ++i){
	        if (this.z(i).posy() == 0){
	            sol = true;
	        }
	    }
	    return sol;
	}
	
	public void computer() {
    //int x = Math.abs(this.rand.nextInt() % Game.DIMX);
		boolean posible = false;
		for (int i = 0; i < Game.DIMX && !posible; ++i){
			posible = !this.hayCosas(i, Game.DIMY - 1);
		}
	
		if (posible && this.zManager.isZombieAdded()){
			int x = Math.abs(this.rand.nextInt() % Game.DIMX);
			boolean done = false;
			while (!done){
				if (!this.hayCosas(x, Game.DIMY-1)){
					this.zombieList.addZombie(x, Game.DIMY-1, this);
					done = true;
				}
				else{
					x = Math.abs(this.rand.nextInt() % Game.DIMX);
				}
			}
	    }	
	}
	
	private boolean hayCosas(int x, int y) {
		boolean hayCosas = false;
		int j = 0;
		while (j < slength() && !hayCosas) {
			if (s(j).vida()>0 && s(j).posx()==x && s(j).posy()==y) hayCosas = true;
			j++;
		}
		j = 0;
		while (j < plength() && !hayCosas) {
			if (p(j).vida()>0 && p(j).posx()==x && p(j).posy()==y) hayCosas = true;
			j++;
		}
		j = 0;
		while (j < zlength() && !hayCosas) {
			if (z(j).vida()>0 && z(j).posx()==x && z(j).posy()==y) hayCosas = true;
			j++;
		}
		return hayCosas;
	}
	
	public boolean addPlant(String planta, int x, int y) {
		boolean sol = false;
		if(this.hayCosas(x,y)) System.out.println("There's already something there.");
		else if(x<0 || y<0 || x>=Game.DIMX || y>=Game.DIMY) System.out.println("Wrong position.");
		else {
			if(planta.equals("s") || planta.equals("sunflower")) {
				if (this.soles.num() >= Sunflower.COSTE) {
					this.sunflowerList.addSunflower(x, y, this);
					this.cambiarSoles(-Sunflower.COSTE);
					sol = true;
				}
				else System.out.println("Not enough cash.");
			}
			else if(planta.equals("p") || planta.equals("peashooter")) {
				if (this.soles.num() >= Peashooter.COSTE) {
					this.peashooterList.addPeashooter(x, y, this);
					this.cambiarSoles(-Peashooter.COSTE);
					sol = true;
				}
				else System.out.println("Not enough cash.");
			}
			else System.out.println("Wrong plant.");
		}
		return sol;
	}

}