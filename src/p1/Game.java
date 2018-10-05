package p1;

import java.util.Random;

public class Game {
	private SunflowerList sunflowerList;
	private PeashooterList peashooterList;
	private ZombieList zombieList;
	
	private int cycleCount;
	private SuncoinManager soles;
	private Random rand;
	private Level level;
	
	private ZombieManager zManager;
	
    public Game(Random ran, Level n) {
		this.level = n;
		this.rand = ran;
		this.sunflowerList = new SunflowerList();
		this.peashooterList = new PeashooterList();
		this.zombieList = new ZombieList();
		this.cycleCount = 0;
		this.soles = new SuncoinManager();
		this.zManager = new ZombieManager(n);
	}
	
	public int ciclos(){
		return this.cycleCount;
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
	
	public Zombie z(int pos){
		return this.zombieList.lista(pos);
	}
	
	public void cambiarSoles(int n){
		this.soles.add(n);
	}

	public void draw(){
		GamePrinter print = new GamePrinter(this, 4, 8);;
		System.out.println(print);
	}
	public void update() {
		for (int i = 0; i < sunflowerList.length(); ++i) {
			if(sunflowerList.lista(i).vida()>0) sunflowerList.lista(i).generarSoles();
		}
		//lanzar guisantes
		
		for (int i = 0; i < zombieList.length(); ++i) {
			int posZombie = zombieList.list(i).posx();
			boolean hayPlantas = false;
			int j = 0;
			while (j < sunflowerList.length() && !hayPlantas) {
				hayPlantas = (sunflowerList.list(j).posx() == posZombie-1);
				++j;
			}
			if (hayPlantas) sunflowerList.list(j-1).serDanado(1);
			else {
				hayPlantas = false;
				j = 0;
				while (j < peashooterList.length() && !hayPlantas) {
					hayPlantas = (peashooterList.list(j).posx() == posZombie-1);
					++j;
				}
				if (hayPlantas) peashooterList.list(j-1).serDanado(1);
				else {
				
				hayPlantas = false;
				j = 0;
				while (j < zombieList.length() && !hayPlantas) {
					hayPlantas = (zombieList.list(j).posx() == posZombie-1);
					++j;
				}
				if (!hayPlantas) zombieList.list(i).avanzar();
				
				}
			}
		}
	}
	
	public boolean playerWins(){
	    boolean sol = false;
        if(this.zManager.remZombies()){
            sol = true;
            for (int i = 0; i < this.zlength() && sol; ++i){
                if (this.z(i).vida() > 0){
                    sol = false;
                }
            }
        }
        return sol;

	}
	
	public boolean zombieWin(){
	    boolean sol = false;
	    for (int i = 0; i < this.zlength && !sol; ++i){
	        if (this.z(i).posy() == 0){
	            sol = true;
	        }
	    }
	    return true;
	}

	
}
