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
		// lanzar guisantes
		for (int i = 0; i < plength(); ++i) {
			if(p(i).vida()>0) {
				for (int j = 0; j < zlength(); ++j) {
					// hacer cosas
				
				
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
		// hay que usar el rand y el level aquí.
		
		/*
		La frecuencia de aparición de los zombis. La frecuencia determina la probabilidad 
		de que aparezca un zombi en un ciclo dado. Así pues, si la frecuencia es 0.2, saldrá
		aproximadamente un zombie cada 5 ciclos; aunque al tratarse de una probabilidad
		pueden salir más espaciados o menos según la aleatoriedad.
		
		Cuando aparece un zombi el ordenador lo coloca en una de las filas del tablero aleato-
		riamente. Para controlar el comportamiento aleatorio del juego y poder repetir varias veces
		la misma ejecución utilizaremos una semilla, o como se conoce en inglés, seed. Este valor
		opcional proporciona un control del comportamiento del programa lo que nos permitirá
		repetir exactamente una misma ejecución.
		 */
	}

	
}
