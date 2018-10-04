package p1;

import java.util.Random;

public class Game {
	private SunflowerList a;
	private PeashooterList b;
	private ZombieList c;
	
	private int cycleCount;
	private SuncoinManager soles;
	private Random rand;
	private Level level;
	
	public void update() {
		for (int i = 0; i < a.length(); ++i) {
			if(a.lista(i).vida()>0) a.lista(i).generarSoles();
		}
		//lanzar guisantes
		
		for (int i = 0; i < c.length(); ++i) {
			int posZombie = c.list(i).pos();
			boolean hayPlantas = false;
			int j = 0;
			while (j < a.length() && !hayPlantas) {
				hayPlantas = (a.list(j).pos() == posZombie-1);
				++j;
			}
			if (hayPlantas) a.list(j-1).serDanado(1);
			else {
				hayPlantas = false;
				j = 0;
				while (j < b.length() && !hayPlantas) {
					hayPlantas = (b.list(j).pos() == posZombie-1);
					++j;
				}
				if (hayPlantas) b.list(j-1).serDanado(1);
				else {
				
				hayPlantas = false;
				j = 0;
				while (j < c.length() && !hayPlantas) {
					hayPlantas = (c.list(j).pos() == posZombie-1);
					++j;
				}
				if (!hayPlantas) c.list(i).avanzar();
				
				}
			}
		}
	}
	
	
}
