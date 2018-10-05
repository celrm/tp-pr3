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
	
	
}
