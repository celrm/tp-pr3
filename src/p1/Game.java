package p1;

import java.util.Random;

public class Game {

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

	public void draw(){
		GamePrinter print = new GamePrinter(this, 4, 8);
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
						this.zombieList.danar(j);
						found = true;
					}
				}
			}
		}
		
		for (int i = 0; i < zlength(); ++i) {
			int posZombie = z(i).posy();
			boolean hayPlantas = false;
			int j = 0;
			while (j < slength() && !hayPlantas) {
				hayPlantas = (s(j).posy() == posZombie-1);
				++j;
			}
			if (hayPlantas) s(j-1).serDanado(1);
			else {
				hayPlantas = false;
				j = 0;
				while (j < plength() && !hayPlantas) {
					hayPlantas = (p(j).posy() == posZombie-1);
					++j;
				}
				if (hayPlantas) p(j-1).serDanado(1);
				else {
				
				hayPlantas = false;
				j = 0;
				while (j < zlength() && !hayPlantas) {
					hayPlantas = (z(j).posy() == posZombie-1);
					++j;
				}
				if (!hayPlantas) z(i).avanza();
				
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
	    if (this.zManager.isZombieAdded()){
	    	int x = this.rand.nextInt() % 4;
	    	 this.zombieList.addZombie(x, 0, this);
	    }
	}
	
	public void addPlant(String planta, int x, int y) {
		
	}

}